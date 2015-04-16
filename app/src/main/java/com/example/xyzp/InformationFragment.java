package com.example.xyzp;

import java.lang.reflect.Field;

import com.astuetz.PagerSlidingTabStrip;
import com.example.xyzp.MainFragment.MyPagerAdapter;

import android.app.ActionBar.Tab;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;

public class InformationFragment extends Fragment{

	
	private PagerSlidingTabStrip tabs;
	private DisplayMetrics dm;
	ViewPager pager;
	
	   public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)   
	    {  
		   View mView=inflater.inflate(R.layout.information_layout, container, false);  
	        setOverflowShowingAlways();
			dm = getResources().getDisplayMetrics();
			pager = (ViewPager) mView.findViewById(R.id.pager2);
			tabs = (PagerSlidingTabStrip) mView.findViewById(R.id.tabs2);
			
	        return mView; 
	    }
	   @Override
	public void onResume() {
		// TODO Auto-generated method stub
		   System.out.println("info f onresume");
		super.onResume();
	}
	public void refreshTabs(){
		   System.out.println("info f refresh tab");
		pager.setAdapter(new MyPagerAdapter( getActivity().getSupportFragmentManager()));
		tabs.setViewPager(pager);
		
		setTabsValue();
 
	}
	   @Override
	public void onStop() {
		   System.out.println("info f onstop");
		super.onStop();
	}
	   /**
		 * 对PagerSlidingTabStrip的各项属性进行赋值
		 */
		private void setTabsValue() {
			//设置Tab是自动填满屏幕的
			tabs.setShouldExpand(true);
			//设置Tab的分割线是透明的
			tabs.setDividerColor(Color.TRANSPARENT);
			// 设置Tab底部线的高度
			tabs.setUnderlineHeight((int) TypedValue.applyDimension(
					TypedValue.COMPLEX_UNIT_DIP, 1, dm));
			// 设置Tab Indicator的高度
			tabs.setIndicatorHeight((int) TypedValue.applyDimension(
					TypedValue.COMPLEX_UNIT_DIP, 4, dm));
			// 设置Tab标题文字的大小
			tabs.setTextSize((int) TypedValue.applyDimension(
					TypedValue.COMPLEX_UNIT_SP, 16, dm));
			// 设置Tab Inidcator的颜色
			tabs.setIndicatorColor(Color.parseColor("#45c01a"));
			// 设置选中Tab文字的颜色
			tabs.setSelectedTextColor(Color.parseColor("#45c01a"));
			//设置取消点击Tab时的颜色
			tabs.setTabBackground(0);
		//	tabs.setBackgroundColor(Color.parseColor("#00FFFF"));
		}
		
		public class MyPagerAdapter extends FragmentPagerAdapter {

			public MyPagerAdapter(FragmentManager fm) {
				super(fm);
			}

			private final String[] titles = {"面试日历", "我的日程"};

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
			

					return  new Fragment_3();
				case 1:
//					if (fragment_4 == null) {
//						fragment_4 = new Fragment_4();
//					}
					return new Fragment_4();
				
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
