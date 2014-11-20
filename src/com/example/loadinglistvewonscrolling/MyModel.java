package com.example.loadinglistvewonscrolling;

import android.os.Parcel;
import android.os.Parcelable;

public class MyModel implements Parcelable{
    String keyword;
    
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
    public static final Parcelable.Creator<MyModel> creator=new Creator<MyModel>() {
		
		@Override
		public MyModel[] newArray(int size) {
			
			return new MyModel[size];
		}
		
		@Override
		public MyModel createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new MyModel(source);
		}
	};
	public MyModel(Parcel p){
		keyword=p.readString();
	}
	public MyModel() {
		// TODO Auto-generated constructor stub
	}
	public MyModel(String keyword) {
		this.keyword=keyword;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(keyword);
		
	}

}
