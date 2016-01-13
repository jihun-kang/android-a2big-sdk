package com.a2big.android.library.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.a2big.android.library.init.A2BigApp;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageContainer;
import com.android.volley.toolbox.ImageLoader.ImageListener;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

////import com.bluepegg.creamstyle.R;
////import com.bluepegg.creamstyle.objects.Look;
////import com.bluepegg.creamstyle.objects.manager.LookManager;


public class Utils {
	private Context mContext;
	private Typeface mTypeface;
	private static final String EMAIL_ADDRESS_REGEX = "^([a-zA-Z0-9\\+_.-])+@([a-zA-Z0-9_.-])+\\.([a-zA-Z])+([a-zA-Z])+";
	private static final String PASSWORD_REGX = "^[a-zA-Z0-9]*$";
	private static final String NUM_REGX = "^[0-9]*$";
	
	public static final int NONE_UPDATE = 0;
	public static final int MINOR_UPDATE = 1;
	public static final int MAJOR_UPDATE = 2;

	public Utils(Context pContext) {
		mContext = pContext;
	}
	
	public void hideKeyBoard(View pView) {
		InputMethodManager imm = (InputMethodManager)mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(pView.getWindowToken(), 0);
	}
	
	public void hideKeyBoard() {
		InputMethodManager imm = (InputMethodManager)mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.toggleSoftInput(InputMethodManager.HIDE_NOT_ALWAYS, 0);
	}
	
	public boolean validEmail(CharSequence pEmail) {
		Pattern pattern = Pattern.compile(EMAIL_ADDRESS_REGEX, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(pEmail);
		if(matcher.matches())
			return true;
		
		return false;
	}
	
	public boolean validPassword(CharSequence pPassword) {
		Pattern pattern = Pattern.compile(PASSWORD_REGX, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(pPassword);
		if(matcher.matches())
			return true;
		
		return false;
	}
	
	public boolean isNumber(CharSequence pNumber) {
		Pattern pattern = Pattern.compile(NUM_REGX, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(pNumber);
		if(matcher.matches())
			return true;
		
		return false;
	}
	
	public String getAppVersion() {
		String version = "";
		try {
			PackageInfo packageInfo = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), PackageManager.GET_META_DATA);
			version = packageInfo.versionName;
		} catch(NameNotFoundException e) {
			e.printStackTrace();
		}
		
		return version;
	}
	
	public int deviceWidth() {
		DisplayMetrics metrics = new DisplayMetrics();
		WindowManager windowManager = (WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE);
		windowManager.getDefaultDisplay().getMetrics(metrics);
		return metrics.widthPixels;
	}
	
	public int deviceHeight() {
		DisplayMetrics metrics = new DisplayMetrics();
		WindowManager windowManager = (WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE);
		windowManager.getDefaultDisplay().getMetrics(metrics);
		return metrics.heightPixels;
	}
	
	public boolean modifySize() {
		if(deviceWidth() == 1080)
			return true;
		
		return false;
	}
	
	public int deviceSize(Context pContext) {
		DisplayMetrics metrics = new DisplayMetrics();
		WindowManager windowManager = (WindowManager)pContext.getSystemService(Context.WINDOW_SERVICE);
		windowManager.getDefaultDisplay().getMetrics(metrics);
		int deviceWidth = metrics.widthPixels;
		int deviceHeight = metrics.heightPixels;
		
		if(deviceWidth == 1080)
			return 1;
		else if(deviceWidth == 480)
			return 2;
		else if(deviceWidth == 540) 
			return 3;
		else if(deviceWidth == 1440)
			return 4;
		
		
		return 0;
	}
	
	
	public Toast showToastMessage(Context pContext, int pMessage, boolean pTitleVisible){
		/*
		LayoutInflater inflater = (LayoutInflater)pContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.toast_layout, null);
		LinearLayout titleContainer = (LinearLayout)view.findViewById(R.id.titleContainer);
		TextView toastTitle = (TextView)view.findViewById(R.id.toast_title);
		ImageView topSeparator = (ImageView)view.findViewById(R.id.topSeparator);
		TextView toastMessage = (TextView)view.findViewById(R.id.toast_message);
		
		if(pTitleVisible == false) {
			titleContainer.setVisibility(View.GONE);
			topSeparator.setVisibility(View.GONE);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			params.gravity = Gravity.CENTER;
			toastMessage.setLayoutParams(params);
			toastMessage.setGravity(Gravity.CENTER);
		}
		
		toastMessage.setText(pMessage);
		
		Toast toast = new Toast(pContext);
		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setView(view);
		toast.show();

		return toast;
		*/
		return null;
	}

	public Toast showToastMessage(Context pContext, String pMessage, boolean pTitleVisible){
		/*
		LayoutInflater inflater = (LayoutInflater)pContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.toast_layout, null);
		LinearLayout titleContainer = (LinearLayout)view.findViewById(R.id.titleContainer);
		TextView toastTitle = (TextView)view.findViewById(R.id.toast_title);
		ImageView topSeparator = (ImageView)view.findViewById(R.id.topSeparator);
		TextView toastMessage = (TextView)view.findViewById(R.id.toast_message);
		
		if(pTitleVisible == false) {
			titleContainer.setVisibility(View.GONE);
			topSeparator.setVisibility(View.GONE);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			params.gravity = Gravity.CENTER;
			toastMessage.setLayoutParams(params);
			toastMessage.setGravity(Gravity.CENTER);
		} 
		
		toastMessage.setText(pMessage);
		
		Toast toast = new Toast(pContext);
		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setView(view);
		toast.show();
		
		return toast;
		*/
		return null;
	}

	/*
	public String getDefaultGoogleEmail(Context pContext){
		AccountManager accountManager = AccountManager.get(pContext);
		Account[] accounts = accountManager.getAccountsByType("com.google");
		if(accounts != null && accounts.length > 0){
			return accounts[0].name;
		}
		
		return getDefaultEmail(pContext);
	}
	*/
	/*
	public String getDefaultEmail(Context pContext){
		final Account[] accounts = AccountManager.get(pContext).getAccounts();
		for(Account account : accounts){
			if(Pattern.compile(EMAIL_ADDRESS_REGEX).matcher(account.name).matches()){
				return account.name;
			}
		}
		
		return null;
	}
	*/
	public String getModel(){
		StringBuilder sb = new StringBuilder();
		sb.append(android.os.Build.MANUFACTURER + " "); 
		sb.append(android.os.Build.MODEL); 
		return sb.toString();
	}
	/*
	public String getDeviceID(Context pContext){
		TelephonyManager telephonyManager = (TelephonyManager)pContext.getSystemService(Context.TELEPHONY_SERVICE);
		String id = null;
		if(telephonyManager != null){
			id = telephonyManager.getDeviceId();
		}
		
		return (TextUtils.isEmpty(id) ? "N/A" : id);
	}
	
	public CharSequence setTextHtmlStyle(String pText){
		return Html.fromHtml(pText);
	}
	*/
	public int darkenColor(int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[2] *= 0.8f;
        return Color.HSVToColor(hsv);
    }
	
	public int dpToPx(Resources res, int dp) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, res.getDisplayMetrics());
	}
		
	
	public boolean isRunningTask(Context pContext) {
		boolean running = false;
		
		ActivityManager am = (ActivityManager)pContext.getSystemService(Activity.ACTIVITY_SERVICE);
		List<ActivityManager.RecentTaskInfo> list = am.getRecentTasks(Integer.MAX_VALUE, Intent.FLAG_ACTIVITY_NEW_TASK);
		
		for(ActivityManager.RecentTaskInfo taskInfo : list) {
			if(taskInfo.baseIntent.getComponent().getPackageName().equals(pContext.getPackageName())) {
				running = true;
				break;
			}
		}
		
		return running;
	}
	
	public void setTypeface(Typeface pTypeface) {
		mTypeface = pTypeface;
	}
	
	public Typeface getTypeface() {
		return mTypeface;
	}
	/*
	public Dialog getFullScreenDialog(Context pContext) {
		Dialog dialog = new Dialog(pContext, android.R.style.Theme_Translucent_NoTitleBar);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.loading_layout);
		Window window = dialog.getWindow();
	    WindowManager.LayoutParams wlp = window.getAttributes();

	    wlp.gravity = Gravity.CENTER;
	    wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
	    window.setAttributes(wlp);
	    dialog.getWindow().setLayout(LayoutParams.FILL_PARENT, LayoutParams.MATCH_PARENT);
	    return dialog;
	}
	*/
	public int deviceSize() {
		DisplayMetrics metrics = new DisplayMetrics();
		WindowManager windowManager = (WindowManager)A2BigApp.getApplication().getSystemService(Context.WINDOW_SERVICE);
		windowManager.getDefaultDisplay().getMetrics(metrics);
		int deviceWidth = metrics.widthPixels;
		
		if(deviceWidth == 1080)
			return 1;
		else if(deviceWidth == 480)
			return 2;
		else if(deviceWidth == 540) 
			return 3;
		else if(deviceWidth == 1440) {
			return 4;
		}
		
		return 0;
	}
	
	public double screenSize() {
		DisplayMetrics metrics = new DisplayMetrics();
		WindowManager windowManager = (WindowManager)A2BigApp.getApplication().getSystemService(Context.WINDOW_SERVICE);
		windowManager.getDefaultDisplay().getMetrics(metrics);
		int deviceWidth = metrics.widthPixels;
		int deviceHeight = metrics.heightPixels;
		int density = metrics.densityDpi;
		
		double width = (double)deviceWidth / (double)density;
		double height = (double)deviceHeight / (double)density;
		double x = Math.pow(width, 2);
		double y = Math.pow(height, 2);
		double screenInches = Math.sqrt(x + y);
		
		return screenInches;
	}
	
	public String getCurrentLanguage() {
		return Locale.getDefault().getLanguage();
	}
	

	public String getAppVersionName() {
		Context context = A2BigApp.getApplication();
		String version = "";
		try{
			PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
			version = pi.versionName;
		}catch (NameNotFoundException e){
			e.printStackTrace();
		}
		
		return version;
	}
	
	public int pxToDp(int px)
	{
	    return (int) (px / Resources.getSystem().getDisplayMetrics().density);
	}
	
	public void drawCircleImage(String pRequestUrl, final RelativeLayout pImageBounder, final int pWidth, final int pHeight, final int pDefault) {
		ImageLoader imageLoader = A2BigApp.getApplication().getImageLoader();
		imageLoader.get(pRequestUrl, new ImageListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onResponse(ImageContainer response, boolean arg1) {
				Bitmap bm = response.getBitmap();
				
				if(bm != null) {
					Bitmap output = createBitmap(bm);
					ImageView imageView = new ImageView(mContext);
					RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(pWidth, pHeight);
					params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
					imageView.setLayoutParams(params);
					imageView.setImageBitmap(output);
					pImageBounder.addView(imageView);

				} else {
					bm = BitmapFactory.decodeResource(mContext.getResources(), pDefault);
					
					Bitmap output = createBitmap(bm);
					ImageView imageView = new ImageView(mContext);
					RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(pWidth, pHeight);
					params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
					imageView.setLayoutParams(params);
					pImageBounder.addView(imageView);
					imageView.setImageBitmap(output);
					
				}
			}
			
		});
	}
	
	private Bitmap createBitmap(Bitmap bitmap) {
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		
		final Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
		final Canvas canvas = new Canvas(output);
		 
		final int color = Color.RED;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
	
		paint.setAntiAlias(true);
		paint.setFilterBitmap(true);
		paint.setDither(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawCircle((((float)width -1) / 2), ((float)height -1) / 2, (Math.min((float) width, (float) height / 2)), paint);
		 
		paint.setXfermode(new PorterDuffXfermode(android.graphics.PorterDuff.Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		
		return output;
	}
	
	public boolean updateStatus(String pVersion) {
		pVersion = pVersion.replace(".", " ");
		DevLog.defaultLogging(pVersion);
		String[] market = pVersion.split(" ");
		String m1 = market[0];
		String m2 = market[1];
		if(m2.length() == 1) {
			m2 = "0" + m2;
		}
		
		String m3 = market[2];
		if(m3.length() == 1) {
			m3 = "0" + m3;
		}
		
		StringBuilder mSb = new StringBuilder();
		String marketVersion = mSb.append(m1).append(m2).append(m3).toString();
		
		String lVersion = getAppVersion().replace(".", " ");
		String[] local = lVersion.split(" ");
		String l1 = local[0];
		String l2 = local[1];
		if(l2.length() == 1) {
			l2 = "0" + l2;
		}
			
		String l3 = local[2];
		if(l3.length() == 1) {
			l3 = "0" + l3;
		}
		
		StringBuilder lSb = new StringBuilder();
		String localVersion = lSb.append(l1).append(l2).append(l3).toString();
		
		int localVersionValue = Integer.valueOf(localVersion);
		int marketVersionValue = Integer.valueOf(marketVersion);
		
		if(marketVersionValue - localVersionValue == 0 || marketVersionValue < localVersionValue) {
			return false;
		} 
		
		return true;
	}
	
	public String createFormat(String pFullName, String pAddr1, String pAddr2, String pCity, String pState, String pCountry, String pPostal, String pTel) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("email", A2BigApp.getApplication().getSettingManager().getEmail()));
		params.add(new BasicNameValuePair("fullname", pFullName));
		params.add(new BasicNameValuePair("addr1", pAddr1));
		params.add(new BasicNameValuePair("addr2", pAddr2));
		params.add(new BasicNameValuePair("city", pCity));
		params.add(new BasicNameValuePair("state", pState));
		params.add(new BasicNameValuePair("country", pCountry));
		params.add(new BasicNameValuePair("postal", pPostal));
		params.add(new BasicNameValuePair("tel", pTel));
		
		JSONArray array = new JSONArray();
		JSONObject obj = new JSONObject();
		
		for(NameValuePair p : params) {
			try {
				obj.put(p.getName(), p.getValue());
			} catch(JSONException e) {
				DevLog.defaultLogging("JSONException: " + e.toString());
			} 
		}
		
		array.put(obj);
		
		return array.toString();
	}
	
	public int getActionBarHeight() {
		A2BigApp app = A2BigApp.getApplication();
		TypedValue value = new TypedValue();
		if(app.getTheme().resolveAttribute(android.R.attr.actionBarSize, value, true)) {
			return TypedValue.complexToDimensionPixelSize(value.data, app.getResources().getDisplayMetrics());
		}
		
		return 0;
	}
	
	public boolean isEmptyManager() {
		/*
		LookManager lookManager = CreamNYC.getApplication().getLookManager();
		Map<Integer, Look> looks = lookManager.getLooks();
		
		if(looks.size() > 0)
			return false;
		*/
		return true;
	}
	
	public String commaNumber(String pNumber) {
		StringTokenizer strToken = new StringTokenizer(pNumber, ".");
		
		String num = "";
		String decimal = "";
		
		int count = 0;
		while(strToken.hasMoreTokens()) {
			if(count == 0) {
				num = strToken.nextToken();
			} else {
				decimal = "." + strToken.nextToken();
			}
			count++;
		}
		
		int headNumber = num.length() % 3;
		String result = "";
		
		for(int i = 0; i < num.length(); ++i) {
			
			if(headNumber > 0 && i == headNumber) {
				result = result + ",";
			} else if(i - headNumber != 0 && (i - headNumber) % 3 == 0) {
				result = result + ",";
			}
			
			result = result + num.charAt(i);
		}
		
		return result + decimal;
		
	}
	
	public String getSearchKeyword(String pKeyword) {
		String keyword = pKeyword.replaceAll(" ", "");
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < keyword.length(); ++i) {
			sb.append(keyword.charAt(i));
		}
		
		return sb.toString().toLowerCase();
	}
	
	public String getLanguage() {
		Locale locale = A2BigApp.getApplication().getResources().getConfiguration().locale;
		return locale.getLanguage();
	}
}
