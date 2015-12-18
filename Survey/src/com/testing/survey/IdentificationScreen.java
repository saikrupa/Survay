package com.testing.survey;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class IdentificationScreen extends Activity{

	Spinner spDistrict,spConstituency,spZone;
	String []arraySpinner={"1","2","3","4","5"};
	String jsonValue;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_identification);
		savedInstanceState=getIntent().getExtras();
		jsonValue=savedInstanceState.getString("JSON_VALUE");
		
		
		spDistrict=(Spinner)findViewById(R.id.spDistrict);
		spConstituency=(Spinner)findViewById(R.id.spConstituency);
		spZone=(Spinner)findViewById(R.id.spZone);
		
		dataToSpinner();
		
	}
	
	public void dataToSpinner() {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.spinner_item, arraySpinner);
        spDistrict.setAdapter(adapter);
		spConstituency.setAdapter(adapter);
		spZone.setAdapter(adapter);
	}
	public void questions(View v) {
		
		Intent i=new Intent(getApplicationContext(),MainActivity.class);
		i.putExtra("District", spDistrict.getSelectedItem().toString());
		i.putExtra("Constituency", spConstituency.getSelectedItem().toString());
		i.putExtra("Zone", spZone.getSelectedItem().toString());
		i.putExtra("JSON_VALUE", jsonValue);
		startActivity(i);
	}
	
}
