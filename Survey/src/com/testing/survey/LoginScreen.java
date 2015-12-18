package com.testing.survey;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.testing.survey.app.AppController;
import com.testing.survey.app.Config;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.TextureView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class LoginScreen extends Activity {

	EditText etUserName, etPassword;
	String userName, password;
	private ProgressDialog pDialog;
	ConnectionDetector con;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		con = new ConnectionDetector(LoginScreen.this);
		pDialog = new ProgressDialog(this);
		pDialog.setCancelable(false);
		etUserName = (EditText) findViewById(R.id.etUserName);
		etPassword = (EditText) findViewById(R.id.etPassword);
		

	}

	public void login(View v) {

		ValidateEmailPassword();

	}

	private void ValidateEmailPassword() {

		userName = etUserName.getText().toString().trim();

		final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

		password = etPassword.getText().toString().trim();

		// final String passwordPattern =
		// "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";

		if (userName.matches(emailPattern)) {
			if (password.length() >= 8) {
				// json object request
				if (con.isConnectingToInternet()) {

					Log.v("collected Data", userName + " " + password);
					checkLogin(userName, password);

				} else {
					con.failureAlert();
				}

			} else if (password.length() <= 0) {
				editText_default_round(etUserName);
				editText_red_round(etPassword);
				ToastMessage("Please enter password");
			} else {
				editText_default_round(etUserName);
				editText_red_round(etPassword);
				ToastMessage("Invalid Password");
			}

		} else {
			if (userName.length() <= 0) {
				editText_red_round(etUserName);
				ToastMessage("Please enter email id");
			} else {
				editText_red_round(etUserName);
				ToastMessage("Email Id is not valid");
			}
		}
	}

	private void checkLogin(final String uName, final String pass) {
		// TODO Auto-generated method stub
		String tag_string_req = "req_login";
		pDialog.setMessage("Logging in ...");
		showDialog();
		StringRequest strReq = new StringRequest(Method.POST, Config.URL_LOGIN,
				new Response.Listener<String>() {

					@Override
					public void onResponse(String response) {
						hideDialog();

						try {
							JSONObject jObj = new JSONObject(response);
							
							//Log.v("responce", jObj.toString());
							Intent i=new Intent(getApplicationContext(),IdentificationScreen.class);
							i.putExtra("JSON_VALUE", jObj.toString());
							startActivity(i);
							/*boolean error = jObj.getBoolean("error");
							// Check for error node in json
							if (!error) {
								
								Intent i=new Intent(getApplicationContext(),IdentificationScreen.class);
								i.putExtra("JSON_VALUE", response.toString());
								startActivity(i);
								

							} else {
								// Error in login. Get the error message
								String errorMsg = jObj.getString("error_msg");
								Toast.makeText(getApplicationContext(),
										errorMsg, Toast.LENGTH_LONG).show();
							}*/
						} catch (JSONException e) {
							// JSON error
							e.printStackTrace();
							Toast.makeText(getApplicationContext(),
									"Json error: " + e.getMessage(),
									Toast.LENGTH_LONG).show();
						}

					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						Log.v("error", error.toString());
						Toast.makeText(getApplicationContext(),
								error.getMessage(), Toast.LENGTH_LONG).show();
						hideDialog();
					}
				}) {

			@Override
			protected Map<String, String> getParams() {
				// Posting parameters to login url
				Map<String, String> params = new HashMap<String, String>();
				params.put("user_name", uName);
				params.put("pass", pass);
				Log.v("added Params", uName + " " + pass);
				return params;
			}

		};

		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(strReq, tag_string_req);

	}

	private void showDialog() {
		if (!pDialog.isShowing())
			pDialog.show();
	}

	// Hiding the dialog box
	private void hideDialog() {
		if (pDialog.isShowing())
			pDialog.dismiss();
	}

	public void editText_red_round(EditText value) {
		value.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.edittext_red_round_corners));
	}

	public void editText_default_round(EditText value) {
		value.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.login_using_border));
	}

	public void ToastMessage(String message) {
		Toast toast = Toast.makeText(getApplicationContext(), message,
				Toast.LENGTH_SHORT);
		View view = toast.getView();
		view.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.toast_message_background));
		toast.setGravity(Gravity.CENTER, 0, 0);
		// TextView text = (TextView) view.findViewById(android.R.id.message);
		// text.setShadowLayer(0, 0, 0, Color.TRANSPARENT);
		toast.show();
	}
}
