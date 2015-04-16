package com.example.xyzp;






import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.List;
import com.navdrawer.SimpleSideDrawer;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;
import android.widget.TextView;
import android.app.*;

public class MainTabActivity extends FragmentActivity {

	private SimpleSideDrawer mNav;
	private TabHost mTabHost;
	private String service;
	
	
	private ListView listview;
	private InformationFragment infoFragment;
	public String getService(){
		return this.service;
	}

	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_tab);
        mNav = new SimpleSideDrawer(this);
        mNav.setLeftBehindContentView(R.layout.left_menu);
        
	    listview = (ListView)findViewById(R.id.listView1);
		List<String> data= new ArrayList<String>();
		
//左划菜单
		
		String m1 = "管理";
		String m2 = "金融";
		String m3 = "传媒";
		
		
		data.add(m1);
		data.add(m2);
		data.add(m3);
		
        ArrayAdapter<String> adapter = 
        		new ArrayAdapter<String>(MainTabActivity.this, android.R.layout.simple_list_item_1, data); 
		listview.setAdapter(adapter);
		
		

        setupIntent();
        //设置ActionBar背景
//        getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.ofm_collect_icon));
    }

    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.moreselections, menu);
		return true;
	}

	@Override
	public boolean onMenuOpened(int featureId, Menu menu) {
		if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
			if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
				try {
					Method m = menu.getClass().getDeclaredMethod(
							"setOptionalIconsVisible", Boolean.TYPE);
					m.setAccessible(true);
					m.invoke(menu, true);
				} catch (Exception e) {
				}
			}
		}
		return super.onMenuOpened(featureId, menu);
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		
		 switch (item.getItemId()) { 
		 case R.id.action_more:
			    mTabHost.setCurrentTabByTag("A_TAB");
			    mNav.toggleLeftDrawer();
			 break;
		 case R.id.action_collection:
			 mTabHost.setCurrentTabByTag("B_TAB");
			 break;
			 
		 }
		return super.onMenuItemSelected(featureId, item);
	}
	

	
	

	
	private void setupIntent() {
		this.mTabHost = (TabHost)findViewById(android.R.id.tabhost);
		this.mTabHost.setup();
		
		TabSpec spec1 = this.mTabHost.newTabSpec("A_TAB");
		spec1.setIndicator("首页");
		spec1.setContent(R.id.frag_main);
		mTabHost.addTab(spec1);
		

		
		TabSpec spec2 = this.mTabHost.newTabSpec("B_TAB");
		spec2.setIndicator("面试技巧");
		spec2.setIndicator("面试技巧",getResources().getDrawable(R.drawable.actionbar_item_pressed));
		spec2.setContent(R.id.frag_collection);
		mTabHost.addTab(spec2);
		
		TabSpec spec3 = this.mTabHost.newTabSpec("C_TAB");
		spec3.setIndicator("我的面试");
		spec3.setContent(R.id.frag_information);
		mTabHost.addTab(spec3);

		 
//		DisplayMetrics dm = getResources().getDisplayMetrics();
//		TabWidget tabWidget = mTabHost.getTabWidget();
//		tabWidget.setBackgroundColor(Color.parseColor("#F0F8FF"));
//        for (int i =0; i < tabWidget.getChildCount(); i++) {           //修改TabHost高度和宽度
//            tabWidget.getChildAt(i).getLayoutParams().height = 60;  
//            tabWidget.getChildAt(i).getLayoutParams().width = 65;       
//            //修改字体大小
//            TextView tv = (TextView) tabWidget.getChildAt(i).findViewById(android.R.id.title);
//            tv.setPadding(0, -5, 0, 0);
//            tv.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, dm));
//       
//            tv.setTextColor(this.getResources().getColorStateList(android.R.color.black));
//            tv.setTextColor(Color.parseColor("#515151"));
//            ImageView iv = (ImageView) tabWidget.getChildAt(i).findViewById(android.R.id.icon);
//            iv.setPadding(0, 10, 0, 0);           
//            iv.setImageDrawable(getResources().getDrawable(R.drawable.actionbar_item_pressed));
//           }
		mTabHost.setOnTabChangedListener(new OnTabChangeListener() {
			
			@Override
			public void onTabChanged(String tabId) {
				if(tabId.equals("C_TAB")){
					InformationFragment infoF = (InformationFragment)getSupportFragmentManager().findFragmentById(R.id.frag_information);
					infoF.refreshTabs();
				}
			}
		});

	}
}