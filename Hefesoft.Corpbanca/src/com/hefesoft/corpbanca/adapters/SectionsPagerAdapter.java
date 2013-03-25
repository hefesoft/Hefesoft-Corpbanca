package com.hefesoft.corpbanca.adapters;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class SectionsPagerAdapter extends FragmentStatePagerAdapter  {

	private List<Fragment> fragments;
	private final ArrayList<String> mTitleList = new ArrayList<String>();
	
    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
        
        if(fragments == null)
        {
        	fragments = new ArrayList<Fragment>();
        }       
    }
    
	public void addFragment(Fragment fragment, String Title){
		fragments.add(fragment);
		mTitleList.add(Title);
	}
	
	public void clearFragment(){
		fragments.clear();
		mTitleList.clear();
	}

    @Override
    public Fragment getItem(int position) {
    	 return this.fragments.get(position);
    }

    @Override
    public int getCount() {
    	 return this.fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
    	 return mTitleList.get(position);
    }
}