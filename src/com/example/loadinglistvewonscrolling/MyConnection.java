package com.example.loadinglistvewonscrolling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.JsonReader;
import android.util.Log;

public class MyConnection {
	public static int urlConnection(String uri) {
		URL url = null;
		try {
			url = new URL(uri);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		HttpURLConnection con = null;

		try {
			con = (HttpURLConnection) url.openConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
		con.setDoInput(true);
		InputStream in = null;
		try {
			in = con.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		StringBuilder builder = new StringBuilder();
		InputStreamReader is = new InputStreamReader(in);
		BufferedReader reader = new BufferedReader(is);
		String line = null;
		try {
			line = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		while (line != null) {
			Log.d("not null", "not null");
			builder.append(line);
			try {
				line = reader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Log.d("result", builder.toString());
		try {
			Log.d("message", con.getResponseMessage());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Log.d("code", con.getResponseCode() + "");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JSONObject object = null;
		try {
			object = new JSONObject(builder.toString());
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			return object.getInt("size");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}

	}

	public static List<String> urlListConnection(String uri) {
		URL url = null;
		try {
			url = new URL("http://54.173.51.136/tweetmap/keywords.php");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		HttpURLConnection con = null;
		try {
			con = (HttpURLConnection) url.openConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
		InputStream in = null;
		try {
			in = con.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		JsonReader reader = null;
		List<MyModel> myModelList = null;
		try {
			reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
			myModelList = readMyModelArray(reader);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return getKeywordsList(myModelList);
	}

	public static List<MyModel> readMyModelArray(JsonReader reader) {
		List<MyModel> myModel = new ArrayList<MyModel>();
		try {
			reader.beginArray();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (reader.hasNext()) {
				myModel.add(readMyModel(reader));

			}
			reader.endArray();
			return myModel;
		} catch (IOException e) {

			e.printStackTrace();
			return null;
		}

	}

	public static MyModel readMyModel(JsonReader reader) {
		String keyword = null;

		try {
			reader.beginObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (reader.hasNext()) {
				String name = reader.nextName();
				if (name.equals("keyword")) {
					keyword = reader.nextString();
				}else{
					reader.skipValue();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			reader.endObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new MyModel(keyword);

	}

	public static List<String> getKeywordsList(List<MyModel> myModel) {
		List<String> keywordList = new ArrayList<String>();
		for (int i = 0; i < myModel.size(); i++) {
			keywordList.add(myModel.get(i).getKeyword());
		}
		return keywordList;

	}

}