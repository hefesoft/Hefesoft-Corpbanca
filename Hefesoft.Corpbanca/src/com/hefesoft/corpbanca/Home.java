package com.hefesoft.corpbanca;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;

import com.hefesoft.corpbanca.adapters.SectionsPagerAdapter;
import com.hefesoft.corpbanca.slider.MenuActivity;
import com.korovyansk.android.slideout.SlideoutActivity;

public class Home extends FragmentActivity implements ActionBar.TabListener {

	SectionsPagerAdapter mSectionsPagerAdapter;
	ActionBar actionBar;
    ViewPager mViewPager;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());  
		
        actionBar = getActionBar();	
	    actionBar.setHomeButtonEnabled(true);
	    
	    // Set up the action bar.
        actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });
        
        getActionBar().setDisplayHomeAsUpEnabled(true);
		
        mSectionsPagerAdapter.addFragment(new com.hefesoft.corpbanca.tutorial.Pantalla_1(), "1");
        mSectionsPagerAdapter.addFragment(new com.hefesoft.corpbanca.tutorial.Pantalla_2(), "2");
        mSectionsPagerAdapter.addFragment(new com.hefesoft.corpbanca.tutorial.Pantalla_3(), "3");
        mSectionsPagerAdapter.addFragment(new com.hefesoft.corpbanca.tutorial.Pantalla_4(), "4");
        mSectionsPagerAdapter.addFragment(new com.hefesoft.corpbanca.tutorial.Pantalla_5(), "5");
		mSectionsPagerAdapter.notifyDataSetChanged();
		generarMenu();
        
	}
	
	private void generarMenu() {
		
		getActionBar().removeAllTabs();
		
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {        
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_home, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == android.R.id.home){
			
			int Orientation = getResources().getConfiguration().orientation;
			int width = 0;
			
			if(android.content.res.Configuration.ORIENTATION_LANDSCAPE == Orientation)
			{
				width = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 400, getResources().getDisplayMetrics());
			}
			
			else if(android.content.res.Configuration.ORIENTATION_PORTRAIT == Orientation)
			{
				width = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics());
			}
			
			else 
			{
				width = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, getResources().getDisplayMetrics());
			}
			
			SlideoutActivity.prepare(Home.this, R.id.Home_Contenedor, width);
			startActivity(new Intent(Home.this, MenuActivity.class));
			overridePendingTransition(0, 0);
		}
		
		if(item.getItemId() == R.id.Home_Log_Out){
			
			int pid = android.os.Process.myPid(); 
			android.os.Process.killProcess(pid); 
		}
		
		return true;
	}

	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

}
