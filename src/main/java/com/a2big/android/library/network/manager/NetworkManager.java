package com.a2big.android.library.network.manager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

import com.a2big.android.library.init.A2BigApp;
import com.a2big.android.library.utils.DevLog;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

////import org.apache.commons.io.IOUtils;

public class NetworkManager {
	private static final String TAG = "NetworkManager";
	private static final int HARD_TIMEOUT = 1000 * 10;
	private static final int SOCKET_TIMEOUT = 1000 * 10;
	private static final String SERVER_URL = "http://release.a2big.com/";
	private static final String DEV_SERVER_URL = "http://outer.a2big.com";

	private boolean isDev = true;
	private String mServerUrl;
	private HttpClient mClient = null;
	private CookieStore mCookieStore = new BasicCookieStore();
	private HttpContext mLocalContext = new BasicHttpContext();
	
	private final static int TIMEOUT = 0;
	private final static int CANCEL_TIMEOUT = 1;
	private boolean mTimeoutFlag = false;
	
	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			
			switch(msg.what) {
				case TIMEOUT:
				{
					mTimeoutFlag = true;
					break;
				}
				
				case CANCEL_TIMEOUT:
				{
					if(hasMessages(TIMEOUT))
						removeMessages(TIMEOUT);
					
					break;
				}
			}
		}
		
	};
	
	
	public NetworkManager() {
		
	}
	
	public void setServerMode(boolean pDev) {
		isDev = pDev;
	}
	
	public String getServerMode() {
		return isDev ? DEV_SERVER_URL : SERVER_URL;
	}
	
	public boolean isNetworkAvailable(Context pContext) {
		/*
		try{
			ConnectivityManager connManager = (ConnectivityManager)pContext.getSystemService(Context.CONNECTIVITY_SERVICE);
			State wifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();	// Wifi
			if(wifi == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTING) {
				return true;
			}
			
			State mobile = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();	// mobile
			if(mobile == NetworkInfo.State.CONNECTED || mobile == NetworkInfo.State.CONNECTING) {
				return true;
			}
		} catch(NullPointerException e) {
			return false;
		}
		
		return false;
		*/
		
		try{
			ConnectivityManager connManager = (ConnectivityManager)pContext.getSystemService(Context.CONNECTIVITY_SERVICE);
			State wifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();	// Wifi
			if(wifi == State.CONNECTED) {
				return true;
			}
			
			State mobile = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();	// mobile
			if(mobile == State.CONNECTED) {
				return true;
			}
		} catch(NullPointerException e) {
			return false;
		}
		
		return false;
	}
	
	
	public int getWifiSignalStrength(Context pContext) {
		WifiManager wifiManager = (WifiManager)pContext.getSystemService(Context.WIFI_SERVICE);
		int signalStrength = 0;
		int state = wifiManager.getWifiState();
		if(state == WifiManager.WIFI_STATE_ENABLED) {
			List<ScanResult> results = wifiManager.getScanResults();
			
			for(ScanResult result : results) {
				if(result.BSSID.equals(wifiManager.getConnectionInfo().getBSSID())) {
					int level = WifiManager.calculateSignalLevel(wifiManager.getConnectionInfo().getRssi(), result.level);
					
					int difference = level * 100 / result.level;
						
					if(difference >= 100) {
						signalStrength = 4;
					} else if(difference >= 75) {
						signalStrength = 3;
					} else if(difference >= 50) {
						signalStrength = 2;
					} else if(difference >= 25) {
						signalStrength = 1;
					}
					
				}
			}
		}
		
		return signalStrength;
	}
	
	public boolean isWifiNetworkEnabled() {
        ConnectivityManager manager = (ConnectivityManager) A2BigApp.getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        
        if (wifi.isConnected())
            return true;
        
        return false;        
    }
    
    public boolean isMobileNetworkEnabled() {
    	ConnectivityManager manager = (ConnectivityManager) A2BigApp.getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
    	State mobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
    	
    	if(mobile == State.CONNECTED)
    		return true;
    	
    	return false;
    }
	
	public boolean connect(String pUrl) {
		mServerUrl = pUrl;
		
		try {
			
			TrustManager easyTrustManager = new X509TrustManager() {

				@Override
				public void checkClientTrusted(X509Certificate[] chain,
						String authType) throws CertificateException {
					
				}

				@Override
				public void checkServerTrusted(X509Certificate[] chain,
						String authType) throws CertificateException {
					
				}

				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
				
			};
			
			SSLContext sslContext;
			sslContext = SSLContext.getInstance("TLS");
			sslContext.init(null, new TrustManager[]{easyTrustManager}, null);
			
			final SSLSocketFactory socketFactory = SSLSocketFactory.getSocketFactory();
			socketFactory.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			
			SchemeRegistry schemeRegistry = new SchemeRegistry();
			schemeRegistry.register(new Scheme("http", new PlainSocketFactory(), 80));
			schemeRegistry.register(new Scheme("https", socketFactory, 443));
			
			HttpParams params = new BasicHttpParams();
			params.setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
			params.setParameter(CoreProtocolPNames.USER_AGENT, System.getProperty("http.agent"));
			HttpProtocolParams.setUserAgent(params, "http.agent");
			mClient = new DefaultHttpClient(new ThreadSafeClientConnManager(params, schemeRegistry), params);
			mClient.getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, true);
			setTimeout(SOCKET_TIMEOUT);
			
//			mLocalContext.setAttribute(ClientContext.COOKIE_STORE, mCookieStore);
		} catch(Exception e) {
			e.printStackTrace();
			
			return false;
		}
		
		return true;
	}
	
	public String getServerUrl() {
		return mServerUrl;
	}
	
	public void setTimeout(int pMilli) {
		if(mClient != null) {
			HttpParams params = mClient.getParams();
			HttpConnectionParams.setConnectionTimeout(params, pMilli);
			HttpConnectionParams.setSoTimeout(params, pMilli);
		}
	}
	
	public void disconnect() {
		new LogoutAsync().execute();
	}
	
	class LogoutAsync extends AsyncTask<Void, Void, Boolean> {

		@Override
		protected Boolean doInBackground(Void... params) {
			if(mClient.getConnectionManager() != null) {
				mClient.getConnectionManager().shutdown();
				return true;
			}
			
			return false;
		}
		
	}


	//	public int uploadFile(String sourceFileUri) {
	public String uploadImageFile(String pSubUrl, String pFilePath, List<NameValuePair> pArgs){

		List<NameValuePair> pArg;

		String uri = mServerUrl + "/" + pSubUrl;
		BitmapFactory.Options options = new BitmapFactory.Options();
		String outPut = null;
		DevLog.defaultLogging("[Network Manager] uploadImageFile  111111 "+uri );

		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = true;
		// image path `String` where your image is located
		BitmapFactory.decodeFile(pFilePath, options);

		int bitmapWidth = 400;
		int bitmapHeight = 250;
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;
		if (height > bitmapHeight || width > bitmapWidth) {
			if (width > height) {
				inSampleSize = Math.round((float) height
						/ (float) bitmapHeight);
			} else {
				inSampleSize = Math.round((float) width
						/ (float) bitmapWidth);
			}
		}
		DevLog.defaultLogging("[Network Manager] uploadImageFile  22222" );

		options.inJustDecodeBounds = false;
		options.inSampleSize = inSampleSize;
		DevLog.defaultLogging("[Network Manager] uploadImageFile  3333 "+pFilePath );

		Bitmap bmpScale = BitmapFactory.decodeFile(pFilePath,
				options);

		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		// CompressFormat set up to JPG, you can change to PNG or
		// whatever you
		// want;

		bmpScale.compress(Bitmap.CompressFormat.JPEG, 100, bos);
		bmpScale.compress(Bitmap.CompressFormat.JPEG, 100, bos);

/*
		File file = new File(pFilePath);
		MultipartEntity multipartEntity = new MultipartEntity();
		ContentBody contentBody = new FileBody(file);
		multipartEntity.addPart("image", new FileBody(file));
	/////	httpPost.setEntity(multipartEntity);

*/
		byte[] data = bos.toByteArray();
		MultipartEntity entity = new MultipartEntity(
				HttpMultipartMode.BROWSER_COMPATIBLE);
		// entity.addPart("avatar", new ByteArrayBody(data,mSignMessg +
		// "-" + new Random().nextInt(1000) + ".jpg"));

		entity.addPart("userfile", new ByteArrayBody(data, pFilePath));
		// add your other name value pairs in entity.
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

		//nameValuePairs
		//		.add(new BasicNameValuePair("user_id", "jhis21c"));


		for(int i=0; i< pArgs.size(); i++){
			nameValuePairs
					.add(new BasicNameValuePair(pArgs.get(i).getName(), pArgs.get(i).getValue()));
		}

		// nameValuePairs.add(new BasicNameValuePair("image", ba1));
		//DevLog.defaultLogging("userfile " + ba1);

		// nameValuePairs.add(new BasicNameValuePair("image”, ba1));
		DevLog.defaultLogging("[Network Manager] uploadImageFile  33333" );

		for (int i = 0; i < nameValuePairs.size(); i++) {

			try {
				entity.addPart(
						nameValuePairs.get(i).getName(),
						new StringBody(nameValuePairs.get(i).getValue()));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				DevLog.defaultLogging("image respons " + e);

				e.printStackTrace();
			}

		}


		DevLog.defaultLogging("[Network Manager] uploadImageFile  44444" );

		// httppost.setEntity(entity);
		//
		// HttpResponse response = httpClient.execute(httppost);

		try {
			HttpClient httpclient = new DefaultHttpClient();
		//	HttpPost httppost = new HttpPost("http://everybody.a2big.com/images/upload.php");
			HttpPost httppost = new HttpPost(uri);

			httppost.setEntity(entity);

			DevLog.defaultLogging( "nameValuePairs " + nameValuePairs);

			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity1 = response.getEntity();

			// print responce
			outPut = EntityUtils.toString(response.getEntity());
			DevLog.defaultLogging("GET RESPONSE—-" + outPut);

			// is = entity.getContent();
			DevLog.defaultLogging("good connection—-");


			bmpScale.recycle();

		} catch (Exception e) {
			DevLog.defaultLogging("Error in http connection " + e.toString());
		}
		DevLog.defaultLogging("[Network Manager] uploadImageFile  5555");

		return outPut;
	}
/* test
	//	public int uploadFile(String sourceFileUri) {
	public String uploadImageFile(String pSubUrl, String pFilePath, List<NameValuePair> pArgs){

		List<NameValuePair> pArg;

		String uri = mServerUrl + "/" + pSubUrl;
		BitmapFactory.Options options = new BitmapFactory.Options();
		String outPut;
		DevLog.defaultLogging("[Network Manager] uploadImageFile  111111 "+uri );

		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = true;
		// image path `String` where your image is located
		BitmapFactory.decodeFile(pFilePath, options);

		int bitmapWidth = 400;
		int bitmapHeight = 250;
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;
		if (height > bitmapHeight || width > bitmapWidth) {
			if (width > height) {
				inSampleSize = Math.round((float) height
						/ (float) bitmapHeight);
			} else {
				inSampleSize = Math.round((float) width
						/ (float) bitmapWidth);
			}
		}
		DevLog.defaultLogging("[Network Manager] uploadImageFile  22222" );

		options.inJustDecodeBounds = false;
		options.inSampleSize = inSampleSize;
		DevLog.defaultLogging("[Network Manager] uploadImageFile  3333 "+pFilePath );

		Bitmap bmpScale = BitmapFactory.decodeFile(pFilePath,
				options);

		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		// CompressFormat set up to JPG, you can change to PNG or
		// whatever you
		// want;

		bmpScale.compress(Bitmap.CompressFormat.JPEG, 100, bos);
		bmpScale.compress(Bitmap.CompressFormat.JPEG, 100, bos);

		byte[] data = bos.toByteArray();
		MultipartEntity entity = new MultipartEntity(
				HttpMultipartMode.BROWSER_COMPATIBLE);
		// entity.addPart("avatar", new ByteArrayBody(data,mSignMessg +
		// "-" + new Random().nextInt(1000) + ".jpg"));

		entity.addPart("userfile", new ByteArrayBody(data, pFilePath));
		// add your other name value pairs in entity.
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

		nameValuePairs
				.add(new BasicNameValuePair("user_id", "jhis21c"));

		// nameValuePairs.add(new BasicNameValuePair("image", ba1));
		//DevLog.defaultLogging("userfile " + ba1);

		// nameValuePairs.add(new BasicNameValuePair("image”, ba1));
		DevLog.defaultLogging("[Network Manager] uploadImageFile  33333" );

		for (int i = 0; i < nameValuePairs.size(); i++) {

			try {
				entity.addPart(
						nameValuePairs.get(i).getName(),
						new StringBody(nameValuePairs.get(i).getValue()));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				DevLog.defaultLogging("image respons " + e);

				e.printStackTrace();
			}

		}
		DevLog.defaultLogging("[Network Manager] uploadImageFile  44444" );

		// httppost.setEntity(entity);
		//
		// HttpResponse response = httpClient.execute(httppost);

		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost("http://everybody.a2big.com/images/upload.php");

			httppost.setEntity(entity);

			DevLog.defaultLogging( "nameValuePairs " + nameValuePairs);

			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity1 = response.getEntity();

			// print responce
			outPut = EntityUtils.toString(response.getEntity());
			DevLog.defaultLogging("GET RESPONSE—-" + outPut);

			// is = entity.getContent();
			DevLog.defaultLogging("good connection—-");


			bmpScale.recycle();

		} catch (Exception e) {
			DevLog.defaultLogging("Error in http connection " + e.toString());
		}
		DevLog.defaultLogging("[Network Manager] uploadImageFile  5555");

		return "";
	}
*/
	public String uploadFile(String pSubUrl, String pFilePath, List<NameValuePair> pArgs) {
		HttpURLConnection connection  = null;
		DataOutputStream outputStream = null;
		DataInputStream inputStream = null;
		String str = null;
		String uri = mServerUrl + "/" + pSubUrl;
		String lineEnd = "\r\n";
		String twoHyphens = "--";
		String boundary = "*****";
		
		int bytesRead, bytesAvailable, bufferSize;
		byte[] buffer;
		int maxBufferSize = 1024 * 1024;

		DevLog.defaultLogging("uploadFile> 111111");

		try {
			
			FileInputStream fileInputStream = new FileInputStream(new File(pFilePath));
			
			URL url = new URL(uri);
			connection = (HttpURLConnection)url.openConnection();
			
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(true);
			
			connection.setRequestMethod("POST");
			
			/* If you want to send data, put data into header
			connection.setRequestProperty("Connection", "Keep-Alive");
		    connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
		    connection.setRequestProperty("Cookie", mSettingManager.getCookie());
		    connection.setRequestProperty("Uid-Value", String.valueOf(mSettingManager.getUid()));
		    */
			
		    outputStream = new DataOutputStream(connection.getOutputStream());
		    outputStream.writeBytes(twoHyphens + boundary + lineEnd);
		    outputStream.writeBytes("Content-Disposition: form-data; name=\"uploadedfile\";filename=\"" + pFilePath +"\"" + lineEnd);
		    outputStream.writeBytes(lineEnd);
		    
		    
		    bytesAvailable = fileInputStream.available();
		    bufferSize = Math.min(bytesAvailable, maxBufferSize);
		    buffer = new byte[bufferSize];
		    
		    // Read file
		    bytesRead = fileInputStream.read(buffer, 0, bufferSize);
		    
		    while(bytesRead > 0) {
		    	outputStream.write(buffer, 0, bufferSize);
		    	bytesAvailable = fileInputStream.available();
		    	bufferSize = Math.min(bytesAvailable, maxBufferSize);
		    	bytesRead = fileInputStream.read(buffer, 0, bufferSize);
		    }
		    
		    outputStream.writeBytes(lineEnd);
		    outputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
		    
		    // Response from the server
		    BufferedReader br = null;
		    
		    int responseCode = connection.getResponseCode();
		    if (responseCode == 200){
				br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String input;
				StringBuffer sbInput = new StringBuffer();
				
				while ((input = br.readLine()) != null){
					sbInput.append(input);
				}
				
				br.close();
				br = null;
	    		str = sbInput.toString();

				DevLog.defaultLogging("uploadFile>22222222 " + str);

			}
			DevLog.defaultLogging("uploadFile>333333333 " + str);

			fileInputStream.close();
		    outputStream.flush();
		    outputStream.close();
			
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return str;
	}
	
	/*
	public String postAndGetString(String pSubUrl, List<NameValuePair> pArgs) {
		String str = null;
		InputStream is = null;
		String url = mServerUrl + "/" + pSubUrl;
		DevLog.Logging(TAG, ">>>>>>>>>>>>>>>"+url);
		DevLog.defaultLogging(">>>>>>>>>>>>>>>"+url);

		DevLog.Logging(TAG, pArgs.toString());
		DevLog.defaultLogging(pArgs.toString());

		final HttpPost post = new HttpPost(url);
		post.setHeader("Content-Type", "application/x-www-form-urlencoded");

		UrlEncodedFormEntity entity;
		
		mTimeoutFlag = false;
		mHandler.sendEmptyMessage(CANCEL_TIMEOUT);
		mHandler.sendEmptyMessageDelayed(TIMEOUT, HARD_TIMEOUT);
		
		try {
			entity = new UrlEncodedFormEntity(pArgs, "UTF-8");
			post.setEntity(entity);
			
			HttpResponse response = mClient.execute(post);
			HttpEntity resEntity = response.getEntity();
			
			if(mTimeoutFlag) {
				if(post != null) {
					post.abort();
				}
				
				return null;
			}
			
			
			if(resEntity != null) {
				try {
				    StringWriter writer = new StringWriter();
				   //////////// IOUtils.copy(resEntity.getContent(),writer);
				    str = writer.toString();
					DevLog.defaultLogging(">>>>>>>>>>>>>>>{POST DEBUG}"+str);

					if(mTimeoutFlag) {
						str = null;
						return null;
					}
					
				} catch (ParseException e) {
					e.printStackTrace();
					return null;
				} finally {
					if(is != null) {
						is.close();
					}
				}
				
				return str;
			}
			
			
		} catch (final UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
			
		} catch (final ClientProtocolException e) {
			e.printStackTrace();
			return null;
		
		} catch (final IOException e) {
			e.printStackTrace();
			return null;
			
		} catch(final IllegalStateException e) {
			e.printStackTrace();
			return null;
			
		} catch(final Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return str;
	}
	*/

	// TODO Auto-generated method stub
	public String postAndGetString(String pSubUrl, List<NameValuePair> pArgs) {
		String str = null;
		InputStream is = null;
		String url = mServerUrl + "/" + pSubUrl;
		DevLog.Logging(TAG, ">>>>>>>>>>>>>>>" + url);
		DevLog.defaultLogging(">>>>>>>>>>>>>>>" + url);

		HttpClient http = new DefaultHttpClient();
		try {
			HttpParams params = http.getParams();
			HttpConnectionParams.setConnectionTimeout(params, 5000);
			HttpConnectionParams.setSoTimeout(params, 5000);

			HttpPost httpPost = new HttpPost(url);
			UrlEncodedFormEntity entityRequest =
					new UrlEncodedFormEntity(pArgs, "UTF-8");

			httpPost.setEntity(entityRequest);

			HttpResponse responsePost = http.execute(httpPost);
			HttpEntity resEntity = responsePost.getEntity();
			str = EntityUtils.toString(resEntity);
			//DevLog.defaultLogging(">>>>>>>>>>>>>>>{POST DEBUG}" + str);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}



	private HttpEntity get(String pSubUrl) {
		HttpEntity resEntity = null;
		String url = mServerUrl + "/" + pSubUrl;
		DevLog.Logging(TAG, url);
		DevLog.defaultLogging(">>>>>>>>>>>>>>>" + url);

		HttpGet get = new HttpGet(url);
		
		try {
			HttpResponse response = mClient.execute(get);
			int status = response.getStatusLine().getStatusCode();
			resEntity = response.getEntity();
			 
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
		
		return resEntity;
	}
	
	public String getAndGetString(String pSubUrl) {
		String str = null;
		String url = mServerUrl + "/" + pSubUrl;
		DevLog.Logging(TAG, url);
		DevLog.defaultLogging(">>>>>>>>>>>>>>>" + url);

		final HttpGet get = new HttpGet(url);
		
		mTimeoutFlag = false;
		mHandler.sendEmptyMessage(CANCEL_TIMEOUT);
		mHandler.sendEmptyMessageDelayed(TIMEOUT, HARD_TIMEOUT);
		
		try {
			HttpResponse response = mClient.execute(get);
			HttpEntity resEntity = response.getEntity();
			
			if(mTimeoutFlag) {
				if(get != null) {
					get.abort();
				}
				
				return null;
			}
			
			if(resEntity != null) {
				try {
					str = EntityUtils.toString(resEntity);
					resEntity.consumeContent();
					
					if(mTimeoutFlag) {
						str = null;
						return null;
					}
					
				} catch (ParseException e) {
					e.printStackTrace();
					return null;
					
				} catch (IOException e) {
					e.printStackTrace();
					return null;
				}
			}
			
			
		} catch (final UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
			
			
		} catch (final ClientProtocolException e) {
			e.printStackTrace();
			return null;
			
		} catch (final IOException e) {
			e.printStackTrace();
			return null;
			
		} catch (final IllegalStateException e) {
			e.printStackTrace();
			return null;
			
		} catch (final Exception e) {
			e.printStackTrace();
			return null;
			
		}
		
		return str;
	}
	
	
	public String getCookie(String pStr) {
		List<Cookie> list = mCookieStore.getCookies();
		
		for(int i = 0;  i < list.size(); ++i) {
			Cookie cookie = list.get(i);
			if(cookie.getName().equals(pStr)) 
				return cookie.getValue();
		}
		
		return null;
	}
	
	public InputStream getInputStream(String pUrl) {
		DevLog.Logging(TAG, pUrl);
		HttpGet get = new HttpGet(pUrl);
		
		try {
			HttpResponse response = mClient.execute(get);
			int status = response.getStatusLine().getStatusCode();
			HttpEntity resEntity = response.getEntity();
			
			if(resEntity != null) {
				InputStream in = resEntity.getContent();
				return in;
			}
			 
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public Bitmap getBitmap(String pSubUrl, byte pBuffer[]) {
		HttpEntity entity = get(pSubUrl);
		
		if(entity != null) {
			try {
				InputStream in = entity.getContent();
				int len;
				int writeSize = 0;
				if(pBuffer.length < entity.getContentLength())
					pBuffer = new byte[(int)entity.getContentLength()];
				
				while((len = in.read(pBuffer, writeSize, pBuffer.length - writeSize)) > 0) {
					writeSize += len;
				}
				
				in.close();
				
				if(writeSize > 0) {
					try {
						Bitmap bitmap = BitmapFactory.decodeByteArray(pBuffer, 0, (int) entity.getContentLength());
						return bitmap;
					} catch(OutOfMemoryError e) {
						e.printStackTrace();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					entity.consumeContent();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return null;
	}
	//////////////////////////////////////////////////////////////////////////
	//Sending multi mage and some data
	//////////////////////////////////////////////////////////////////////////
	//public void uploadUserPhoto(String pSubUrl,File image1, File image2, File image3) {
	public String uploadUserPhoto(String pSubUrl,List<NameValuePair> pArgs) {
		String outPut = null;

		File image1 = new File("/sdcard/tmp_contact_1422116333510.jpg");
		File image2 = new File("/sdcard/tmp_contact_1422116525553.jpg");
		File image3 = new File("/sdcard/tmp_contact_1422116568248.jpg");

		DevLog.defaultLogging("image1 file size  " + image1.length());
		DevLog.defaultLogging("image2 file size  " + image2.length());
		DevLog.defaultLogging("image2 file size  " + image3.length());

		if( image1 == null) {
			DevLog.defaultLogging("Error image1 is null " );

		}


		try {
			String uri = mServerUrl + "/" + pSubUrl;

			HttpPost httppost = new HttpPost(uri);

			MultipartEntity multipartEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
			multipartEntity.addPart("Title", new StringBody("Title"));
			multipartEntity.addPart("Nick", new StringBody("Nick"));
			multipartEntity.addPart("Email", new StringBody("Email"));
			//multipartEntity.addPart("Description", new StringBody(Settings.SHARE.TEXT));
			multipartEntity.addPart("file1", new FileBody(image1));
			multipartEntity.addPart("file2", new FileBody(image2));
			multipartEntity.addPart("file3", new FileBody(image3));
			httppost.setEntity(multipartEntity);

			HttpClient httpclient = new DefaultHttpClient();
			//httpclient.execute(httppost, new PhotoUploadResponseHandler());
			HttpResponse responsePost = httpclient.execute(httppost);
			HttpEntity resEntity = responsePost.getEntity();
			outPut = EntityUtils.toString(resEntity);
			DevLog.defaultLogging(">>>>>>>>>>>>>>>{POST DEBUG}" + outPut);



		} catch (Exception e) {
			//Log.e(ServerCommunication.class.getName(), e.getLocalizedMessage(), e);
			DevLog.defaultLogging("Error >>>>>>>>>>>>>>>uploadUserPhoto" );

		}

		return outPut;
	}

}
