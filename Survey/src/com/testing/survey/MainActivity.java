package com.testing.survey;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	private Toolbar toolbar;

	private TextView tvEmptyView;
	private RecyclerView mRecyclerView;
	private DataAdapter mAdapter;
	private LinearLayoutManager mLayoutManager;
	String District, Constituency, Zone, jsonValue;
	private int numberOfQuestions = 20;

	public static String[] answers;

	private List<Student> studentList;

	protected Handler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		savedInstanceState = getIntent().getExtras();
		District = savedInstanceState.getString("District");
		Constituency = savedInstanceState.getString("Constituency");
		Zone = savedInstanceState.getString("Zone");
		jsonValue = savedInstanceState.getString("JSON_VALUE");
		// numberOfQuestions=savedInstanceState.getInt("numberOfQuestions");
		answers = new String[numberOfQuestions];

		toolbar = (Toolbar) findViewById(R.id.toolbar);
		tvEmptyView = (TextView) findViewById(R.id.empty_view);
		mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
		studentList = new ArrayList<Student>();
		handler = new Handler();
		/*
		 * if (toolbar != null) { setSupportActionBar(toolbar);
		 * getSupportActionBar().setTitle("Android Students");
		 * 
		 * }
		 */
		initiolize();

		loadData();

		// use this setting to improve performance if you know that changes
		// in content do not change the layout size of the RecyclerView
		mRecyclerView.setHasFixedSize(true);

		mLayoutManager = new LinearLayoutManager(this);

		// use a linear layout manager
		mRecyclerView.setLayoutManager(mLayoutManager);

		// create an Object for Adapter
		mAdapter = new DataAdapter(studentList);

		// set the adapter object to the Recyclerview
		mRecyclerView.setAdapter(mAdapter);
		// mAdapter.notifyDataSetChanged();

		if (studentList.isEmpty()) {
			mRecyclerView.setVisibility(View.GONE);
			tvEmptyView.setVisibility(View.VISIBLE);

		} else {
			mRecyclerView.setVisibility(View.VISIBLE);
			tvEmptyView.setVisibility(View.GONE);
		}

		/*
		 * mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
		 * 
		 * @Override public void onLoadMore() { //add null , so the adapter will
		 * check view_type and show progress bar at bottom
		 * studentList.add(null); mAdapter.notifyItemInserted(studentList.size()
		 * - 1);
		 * 
		 * handler.postDelayed(new Runnable() {
		 * 
		 * @Override public void run() { // remove progress item
		 * studentList.remove(studentList.size() - 1);
		 * mAdapter.notifyItemRemoved(studentList.size()); //add items one by
		 * one int start = studentList.size(); int end = start + 20;
		 * 
		 * for (int i = start + 1; i <= end; i++) { studentList.add(new
		 * Student("Student " + i, "AndroidStudent" + i + "@gmail.com"));
		 * mAdapter.notifyItemInserted(studentList.size()); }
		 * mAdapter.setLoaded(); //or you can add all at once but do not forget
		 * to call mAdapter.notifyDataSetChanged(); } }, 2000);
		 * 
		 * } });
		 */
	}

	private void initiolize() {
		// TODO Auto-generated method stub
		for (int i = 0; i < numberOfQuestions; i++) {
			answers[i] = null;
		}
	}

	// load initial data
	private void loadData() {

		/*try {
			
			
			JSONObject array = new JSONObject(jsonValue);
			Log.v("MainActivity", array.toString());

			
			if (array.has("survey_questions")) {
				try {
					String prevoiusQuestionId=null;
					JSONArray questions = array.getJSONArray("survey_questions");
					Log.v("saikrupa", questions.toString());

					for (int i = 0; i < questions.length(); i++) {
						String question_id,question_value,option_id,option_value;
						question_id=questions.getJSONObject(i).getString("question_id");
						question_value=questions.getJSONObject(i).getString("question_value");
						option_id=questions.getJSONObject(i).getString("option_id");
						option_value=questions.getJSONObject(i).getString("option_value");
						Log.v("responce"+i, question_id+"/"+question_value+"/"+option_id+"/"+option_value);
						if(prevoiusQuestionId==null || !prevoiusQuestionId.equals(question_id))
						{
							prevoiusQuestionId=question_id;
						}else if(prevoiusQuestionId.equals(question_id))
						{
							
						}
					}

				} catch (Exception e) {
					
					e.printStackTrace();

				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}*/

		
		 for (int i = 1; i <= 20; i++) { studentList.add(new Student(new
		 String[]{i+".","question1","ans1","ans2","ans3","ans4","ans5"}));
		  }
		 
		// Toast.makeText(getApplicationContext(), studentList[0].toString(),
		// duration)
	}

	public void submit(View v) {
		submitAlert();
	}
	
public void submitAlert() {
		
    	AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
    	 
        // Setting Dialog Title
        alertDialog.setTitle("Alert");
 
        // Setting Dialog Message
        alertDialog.setMessage("Would you like to submit the questions");
 
        // Setting OK Button
        alertDialog.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
				finish();
				
			}
		});
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
				//((Activity)_context).finish();
			}
		});
        // Showing Alert Message
        AlertDialog alert = alertDialog.create();
        alert.show();
	}
	

}
