package com.example.loadinglistvewonscrolling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

public class MainActivity extends Activity {
	int size = 0;
	List<String> keyword = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		new MyAsyncTask().execute("http://54.173.51.136/tweetmap/size.php");
	}

	class MyAsyncTask extends AsyncTask<String, Integer, Integer> {

		@Override
		protected Integer doInBackground(String... params) {
			return MyConnection.urlConnection(params[0]);
		}

		@Override
		protected void onPostExecute(Integer result) {
			size = result;
			Log.d("result in adapter", result + "");
			new MyKeyWordAsyncTask()
					.execute("http://54.173.51.136/tweetmap/keywords.php");
		}
	}

	class MyKeyWordAsyncTask extends AsyncTask<String, Integer, List<String>> {

		@Override
		protected List<String> doInBackground(String... params) {
			return MyConnection.urlListConnection(params[0]);
		}

		@Override
		protected void onPostExecute(List<String> result) {
			for(int i=0;i<1000;i++){
				result.add("hello");
			}
			MyAdapter adapter = new MyAdapter(MainActivity.this, 1001,result);
			ListView list = (ListView) findViewById(R.id.list_view);
			list.setAdapter(adapter);
			Log.d("keywordList", result.toString());
			list.setOnScrollListener(new OnScrollListener() {
				
				@Override
				public void onScrollStateChanged(AbsListView view, int scrollState) {
					Log.d("onScrollStateChanged",scrollState+"");
					
				}
				
				@Override
				public void onScroll(AbsListView view, int firstVisibleItem,
						int visibleItemCount, int totalItemCount) {
					Log.d("onScroll","firstVisibleItem->"+firstVisibleItem+" visibleItemCount->"
							+visibleItemCount+" totalItemCount->"+totalItemCount);
					
				}
			});
		}

	}
}
