package com.a2big.android.library.banner;

import com.a2big.android.library.objecttype.AbstractObject;
import com.google.gson.Gson;

import java.util.ArrayList;


public class BannerObject extends AbstractObject {
	private BannerData mRegInfo;

	public BannerObject() {
	}

	public BannerData getBannerData(){
		return mRegInfo;
	}
	@Override
	public boolean onResponseListener(String pResponse) {

		Gson gson = new Gson();
		mRegInfo = gson.fromJson(pResponse, BannerData.class);
		return true;
	}


	public class BannerData{
		private ArrayList<BannerItem> results;
		private String error;
		private String desc;

		public ArrayList<BannerItem> getResults() {
			return results;
		}
		public void setResults(ArrayList<BannerItem> results) {
			this.results = results;
		}

		public String getError(){ return this.error; }
		public void setError(String e){ this.error = e;}

		public String getDesc(){ return this.desc; }
		public void setDesc(String d){ this.desc = d;}

	}

	public class BannerItem{
		private String photoUrl;
		private String webURL;

		public String getPhotoUrl() {
			return photoUrl;
		}
		public void setPhotoUrl(String p) {
			photoUrl = p;
		}

		public String getWebURL() {
			return webURL;
		}
		public void setWebURL(String p) {
			webURL = p;
		}

	}


}
