package com.hefesoft.corpbanca.slider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;

import com.hefesoft.corpbanca.Login;
import com.hefesoft.corpbanca.R;
import com.korovyansk.android.slideout.SlideoutActivity;

public class SampleActivity extends Activity {

	@TargetApi(11)
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sample);
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
	    	getActionBar().hide();
	    }
		findViewById(R.id.sample_button).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						int width = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
						SlideoutActivity.prepare(SampleActivity.this, R.id.inner_content, width);
						
						Intent in = new Intent(SampleActivity.this, com.hefesoft.corpbanca.slider.MenuActivity.class);
						 startActivity(in);
						
						//startActivity(new Intent(SampleActivity.this,
								//MenuActivity.class));
						overridePendingTransition(0, 0);
					}
				});
	}


}
