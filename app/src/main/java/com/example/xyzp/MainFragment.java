package com.example.xyzp;

import java.lang.reflect.Field;

import java.lang.reflect.Method;

import com.astuetz.PagerSlidingTabStrip;




import android.graphics.Color;
import android.os.Bundle;
//import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.Window;

public class MainFragment extends Fragment{

	private Fragment_0 fragment_0;
	private Fragment_1 fragment_1;
	private Fragment_2 fragment_2;
	
	private PagerSlidingTabStrip tabs;
	private DisplayMetrics dm;

	
	
	
	   public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)   
	    {  
	        View mView=inflater.inflate(R.layout.activity_main, container, false);  
	        
			setOverflowShowingAlways();
			dm = getResources().getDisplayMetrics();
			ViewPager pager = (ViewPager) mView.findViewById(R.id.pager);
			tabs = (PagerSlidingTabStrip) mView.findViewById(R.id.tabs);
			pager.setAdapter(new MyPagerAdapter( getActivity().getSupportFragmentManager()));
			tabs.setViewPager(pager);
			setTabsValue();
			
		
			
	        return mView;  
	    }

	 

		private void setTabsValue() {
			tabs.setShouldExpand(true);
			tabs.setDividerColor(Color.TRANSPARENT);
			tabs.setUnderlineHeight((int) TypedValue.applyDimension(
					TypedValue.COMPLEX_UNIT_DIP, 1, dm));
			tabs.setIndicatorHeight((int) TypedValue.applyDimension(
					TypedValue.COMPLEX_UNIT_DIP, 4, dm));
			tabs.setTextSize((int) TypedValue.applyDimension(
					TypedValue.COMPLEX_UNIT_SP, 16, dm));
			tabs.setIndicatorColor(Color.parseColor("#45c01a"));
			tabs.setSelectedTextColor(Color.parseColor("#45c01a"));
			tabs.setTabBackground(0);
		//	tabs.setBackgroundColor(Color.parseColor("#00FFFF"));
		}
		
		public class MyPagerAdapter extends FragmentPagerAdapter {

			public MyPagerAdapter(FragmentManager fm) {
				super(fm);
			}
		
		
			private final String[] titles = { "计算机", "管理", "金融" };

			@Override
			public CharSequence getPageTitle(int position) {
				return titles[position];
			}

			@Override
			public int getCount() {
				return titles.length;
			}

			@Override
			public Fragment getItem(int position) {
				switch (position) {
				case 0:
					if (fragment_0 == null) {
						fragment_0 = new Fragment_0();
					}
					return fragment_0;
				case 1:
					if (fragment_1 == null) {
						fragment_1 = new Fragment_1();
					}
					return fragment_1;
				case 2:
					if (fragment_2 == null) {
						fragment_2 = new Fragment_2();
					}
					return fragment_2;
				default:
					return null;
				}
			}

		}

		private void setOverflowShowingAlways() {
			try {
				
				ViewConfiguration config = ViewConfiguration.get(getActivity());
				Field menuKeyField = ViewConfiguration.class
						.getDeclaredField("sHasPermanentMenuKey");
				menuKeyField.setAccessible(true);
				menuKeyField.setBoolean(config, false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}
