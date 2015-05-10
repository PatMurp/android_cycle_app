package wit.cc.activities;

import wit.cc.R;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity implements OnClickListener {
	
	private boolean mIsBackButtonPressed;
	private SharedPreferences settings;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		settings = getSharedPreferences("loginPrefs", 0);
		if (settings.getBoolean("loggedin", false)) {
			
			startHomeScreen();
		}
		
		setContentView(R.layout.activity_login);
		Button login  = (Button) findViewById(R.id.loginBtn);
		login.setOnClickListener(this);
	}
	
	@Override
	public void onBackPressed() {
		mIsBackButtonPressed = true;
		super.onBackPressed();
	}

	@Override
	public void onClick(View v) {
		
		CharSequence email = ((TextView) findViewById(R.id.loginEmailField)).getText();
		CharSequence password = ((TextView) findViewById(R.id.loginPasswordField)).getText();
		
		if (email.length() <= 0 || password.length() <= 0) {
			Toast.makeText(this, "You must enter an email & password", Toast.LENGTH_SHORT).show();
		} else if (!email.toString().matches("p@p.com") 
				|| !password.toString().matches("p")) {
			Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show();
		} else if (!mIsBackButtonPressed) {
			
			// update logged in preferences
			SharedPreferences.Editor editor = settings.edit();
			editor.putBoolean("loggedin", true);
			editor.commit();
			// start home screen if back button not already pressed
			startHomeScreen();
			this.finish(); // destroy login activity
		}
	}
	
	// start home screen
	private void startHomeScreen() {
		Intent intent = new Intent(Login.this, Home.class);
		Login.this.startActivity(intent);
	}

}
