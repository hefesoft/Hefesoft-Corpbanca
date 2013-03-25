package com.hefesoft.corpbanca;

import java.io.IOException;
import java.io.InputStream;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.hefesoft.corpbanca.sindicalizados.Ingresos;
import com.hefesoft.corpbanca.slider.MenuActivity;
import com.hefesoft.corpbanca.util.SystemUiHider;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class Splash extends Activity {
	
	private static final boolean AUTO_HIDE = true;

	
	private static final int AUTO_HIDE_DELAY_MILLIS = 3000;


	private static final boolean TOGGLE_ON_CLICK = true;

	
	private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;

	private SystemUiHider mSystemUiHider;
	
	ImageView mImage;
	
	Boolean click = false;
		
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		handler.postDelayed(runnable, 1000);
		
		setContentView(R.layout.activity_splash);

		mImage = (ImageView)findViewById(R.id.image_splash);		
		
		final View controlsView = findViewById(R.id.fullscreen_content_controls);
		final View contentView = findViewById(R.id.fullscreen_content);

		// Set up an instance of SystemUiHider to control the system UI for
		// this activity.
		mSystemUiHider = SystemUiHider.getInstance(this, contentView,
				HIDER_FLAGS);
		mSystemUiHider.setup();
		mSystemUiHider
				.setOnVisibilityChangeListener(new SystemUiHider.OnVisibilityChangeListener() {
					// Cached values.
					int mControlsHeight;
					int mShortAnimTime;

					@Override
					@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
					public void onVisibilityChange(boolean visible) {
						if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
							// If the ViewPropertyAnimator API is available
							// (Honeycomb MR2 and later), use it to animate the
							// in-layout UI controls at the bottom of the
							// screen.
							if (mControlsHeight == 0) {
								mControlsHeight = controlsView.getHeight();
							}
							if (mShortAnimTime == 0) {
								mShortAnimTime = getResources().getInteger(
										android.R.integer.config_shortAnimTime);
							}
							controlsView
									.animate()
									.translationY(visible ? 0 : mControlsHeight)
									.setDuration(mShortAnimTime);
						} else {
							// If the ViewPropertyAnimator APIs aren't
							// available, simply show or hide the in-layout UI
							// controls.
							controlsView.setVisibility(visible ? View.VISIBLE
									: View.GONE);
						}

						if (visible && AUTO_HIDE) {
							// Schedule a hide().
							delayedHide(AUTO_HIDE_DELAY_MILLIS);
						}
					}
				});

		// Set up the user interaction to manually show or hide the system UI.
		contentView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (TOGGLE_ON_CLICK) {
					mSystemUiHider.toggle();
				} else {
					mSystemUiHider.show();
				}
				
				
				handlerIntent.postDelayed(runnableIntent, 1000);
			}
		});

		// Upon interacting with UI controls, delay any scheduled hide()
		// operations to prevent the jarring behavior of controls going away
		// while interacting with the UI.
		

		
		
	
	}

	private void cargarImagen() {
		// load image
    	try {
	    	// get input stream
	    	InputStream ims = getAssets().open("img/logo_gestionline_Positivo.png");
	    	// load image as Drawable
	    	Drawable d = Drawable.createFromStream(ims, null);
	    	// set image to ImageView
	    	mImage.setImageDrawable(d);
    	}
    	catch(IOException ex) {
    		return;
    	}
	}
	
	Handler handler = new Handler();
	Handler handlerIntent = new Handler();
	
	private Runnable runnable = new Runnable() {
		   @Override
		   public void run() {
		      
			   cargarImagen();
			   mSystemUiHider.show();
			   
			   
			   handlerIntent.postDelayed(runnableIntent, 4000);
			   
		   }
		};
		
		private Runnable runnableIntent = new Runnable() {
			   @Override
			   public void run() {
			      
				   if(click == false)
				   {
					Intent in = new Intent(Splash.this, Login.class);
					startActivity(in);
					click = true;
				   }
			   }
			};

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

		// Trigger the initial hide() shortly after the activity has been
		// created, to briefly hint to the user that UI controls
		// are available.
		delayedHide(100);
	}

	/**
	 * Touch listener to use for in-layout UI controls to delay hiding the
	 * system UI. This is to prevent the jarring behavior of controls going away
	 * while interacting with activity UI.
	 */
	View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
		@Override
		public boolean onTouch(View view, MotionEvent motionEvent) {
			if (AUTO_HIDE) {
				delayedHide(AUTO_HIDE_DELAY_MILLIS);
			}
			return false;
		}
	};

	Handler mHideHandler = new Handler();
	Runnable mHideRunnable = new Runnable() {
		@Override
		public void run() {
			mSystemUiHider.hide();
		}
	};

	/**
	 * Schedules a call to hide() in [delay] milliseconds, canceling any
	 * previously scheduled calls.
	 */
	private void delayedHide(int delayMillis) {
		mHideHandler.removeCallbacks(mHideRunnable);
		mHideHandler.postDelayed(mHideRunnable, delayMillis);
	}
}
