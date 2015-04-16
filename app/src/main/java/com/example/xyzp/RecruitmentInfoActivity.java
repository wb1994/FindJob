package com.example.xyzp;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.neuedu.database.UserDao;
import com.example.xyzp.Fragment_4.SlideAdapter;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.TextView;
import android.widget.Toast;

public class RecruitmentInfoActivity extends Activity{

	private TextView tv_company,tv_position,tv_pos_num,tv_salary_lowest,tv_salary_highest;
	private TextView tv_workplace,tv_welfare,tv_requirement,tv_com_uri,tv_interciew_palce;
	private TextView tv_interciew_date,tv_career_talk_place,tv_career_talk_date,tv_other_management;
	int hour,minute;
	int yr,month,day;
	private String mytime;
	private boolean isShow = true;
	private boolean isinsert = true;
	private AlarmManager aManager;
	private PendingIntent pendingIntent;
	private UserDao userDao;
	
	

	
	

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.recruitment_info);

		 
		 tv_company = (TextView)findViewById(R.id.tv_company);
		 tv_position = (TextView)findViewById(R.id.tv_position);
		 tv_pos_num = (TextView)findViewById(R.id.tv_pos_num);
		 tv_salary_lowest = (TextView)findViewById(R.id.tv_salary_lowest);
		 tv_salary_highest = (TextView)findViewById(R.id.tv_salary_highest);
		 tv_workplace = (TextView)findViewById(R.id.tv_workplace);
		 tv_welfare = (TextView)findViewById(R.id.tv_welfare);
		 tv_requirement = (TextView)findViewById(R.id.tv_requirement);		 
		 tv_com_uri = (TextView)findViewById(R.id.tv_com_uri);
		 tv_interciew_palce = (TextView)findViewById(R.id.tv_interview_place);
		 tv_interciew_date = (TextView)findViewById(R.id.tv_interview_date);
		 tv_career_talk_place = (TextView)findViewById(R.id.tv_career_talk_place);
		 tv_career_talk_date = (TextView)findViewById(R.id.tv_career_talk_date);
		 tv_other_management = (TextView)findViewById(R.id.tv_other_management);		 
			
		 tv_company.setText(getIntent().getStringExtra("company"));
		 tv_position.setText(getIntent().getStringExtra("position"));
		 tv_pos_num.setText(getIntent().getStringExtra("pos_num"));
		 tv_salary_lowest.setText(getIntent().getStringExtra("salary_lowest"));
		 tv_salary_highest.setText(getIntent().getStringExtra("salary_highest"));
		 tv_workplace.setText(getIntent().getStringExtra("workplace"));
		 tv_welfare.setText(getIntent().getStringExtra("welfare"));
		 tv_requirement.setText(getIntent().getStringExtra("requirement"));
		 tv_com_uri.setText(getIntent().getStringExtra("com_uri"));
		 tv_interciew_palce.setText(getIntent().getStringExtra("interciew_palce"));
		 tv_interciew_date.setText(getIntent().getStringExtra("interciew_date"));
		 tv_career_talk_place.setText(getIntent().getStringExtra("career_talk_place"));
		 tv_career_talk_date.setText(getIntent().getStringExtra("career_talk_date"));
		 tv_other_management.setText(getIntent().getStringExtra("other_management"));
		
		 

	
//		mListView.initSlideMode(SlideListView.MOD_BOTH);
		
		 //get the current date
		 Calendar today = Calendar.getInstance();
		 yr = today.get(Calendar.YEAR);
		 month = today.get(Calendar.MONTH);
	 	 day = today.get(Calendar.DAY_OF_MONTH)+1;
				 
		 userDao = new UserDao(RecruitmentInfoActivity.this, "mydatabase", null, 1);
		 
		 prepareBroadcast();
		
		 aManager.cancel(pendingIntent);

		 
		 tv_company = (TextView)findViewById(R.id.tv_company);
		 tv_position = (TextView)findViewById(R.id.tv_position);
		 tv_pos_num = (TextView)findViewById(R.id.tv_pos_num);
		 tv_salary_lowest = (TextView)findViewById(R.id.tv_salary_lowest);
		 tv_salary_highest = (TextView)findViewById(R.id.tv_salary_highest);
		 tv_workplace = (TextView)findViewById(R.id.tv_workplace);
		 tv_welfare = (TextView)findViewById(R.id.tv_welfare);
		 tv_requirement = (TextView)findViewById(R.id.tv_requirement);
		 
		 tv_com_uri = (TextView)findViewById(R.id.tv_com_uri);
		 tv_interciew_palce = (TextView)findViewById(R.id.tv_interview_place);
		 tv_interciew_date = (TextView)findViewById(R.id.tv_interview_date);
		 tv_career_talk_place = (TextView)findViewById(R.id.tv_career_talk_place);
		 tv_career_talk_date = (TextView)findViewById(R.id.tv_career_talk_date);
		 tv_other_management = (TextView)findViewById(R.id.tv_other_management);
		 
		 
		 System.out.println(getIntent().getStringExtra("company")+"..............");
		 
		 tv_position.setText(getIntent().getStringExtra("position"));
		 tv_company.setText(getIntent().getStringExtra("company"));
		 tv_pos_num.setText(getIntent().getStringExtra("pos_num"));
		 tv_salary_lowest.setText(getIntent().getStringExtra("salary_lowest"));
		 tv_salary_highest.setText(getIntent().getStringExtra("salary_highest"));
		 tv_workplace.setText(getIntent().getStringExtra("workplace"));
		 tv_welfare.setText(getIntent().getStringExtra("welfare"));
		 tv_requirement.setText(getIntent().getStringExtra("requirement"));
		 tv_com_uri.setText(getIntent().getStringExtra("com_uri"));
		 tv_interciew_palce.setText(getIntent().getStringExtra("interciew_palce"));
		 tv_interciew_date.setText(getIntent().getStringExtra("interciew_date"));
		 tv_career_talk_place.setText(getIntent().getStringExtra("career_talk_place"));
		 tv_career_talk_date.setText(getIntent().getStringExtra("career_talk_date"));
		 tv_other_management.setText(getIntent().getStringExtra("other_management"));
		
		 

	}
	
	
    
	private void prepareBroadcast() {
		aManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
       // Intent intent=new Intent(RecruitmentInfoActivity.this,MyReceiver.class);
        Intent intent = new Intent("com.findjob.receiver");
        pendingIntent=PendingIntent.getBroadcast(RecruitmentInfoActivity.this, 0, intent, 0);
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.info_choose, menu);
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
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		 switch (item.getItemId()) { 
		   case R.id.item_collect:
			   isShow = true;

			   	new AlertDialog.Builder(this)
			   .setTitle("要参加此次面试？")
			   .setPositiveButton("确定", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					new DatePickerDialog(RecruitmentInfoActivity.this, mDateSetListener, yr, month, day).show();	
					
					
				}
			})
			.setNegativeButton("取消", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
			}).create().show(); 
		 }
		return super.onOptionsItemSelected(item);
	}
	
	private TimePickerDialog.OnTimeSetListener mTimeSetListener = new OnTimeSetListener() {
		
		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minuteOfHour) {
			// TODO Auto-generated method stub
			hour = hourOfDay;
			minute = minuteOfHour;
			
			SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
			Date date = new Date(0,0,0,hour,minute);
			String strTime = timeFormat.format(date);
			mytime = (month+1)+"/"+day+"/"+yr+" "+strTime;
		//	Toast.makeText(getBaseContext(),"--------"+mytime, 0).show();
			if(isinsert){
			//将时间按id添加到数据库
				userDao.insert_recru_info(getIntent().getStringExtra("_id")
						, getIntent().getStringExtra("company")
						, getIntent().getStringExtra("position")
						, getIntent().getStringExtra("pos_num")
						, getIntent().getStringExtra("salary_lowest")
						, getIntent().getStringExtra("salary_highest")
						, getIntent().getStringExtra("workplace")
						, getIntent().getStringExtra("welfare")
						, getIntent().getStringExtra("requirement")
						, getIntent().getStringExtra("com_uri")
						, getIntent().getStringExtra("interciew_palce")
						, getIntent().getStringExtra("interciew_date")
						, getIntent().getStringExtra("career_talk_place")
						, getIntent().getStringExtra("career_talk_date")
						, getIntent().getStringExtra("other_management")
						, mytime);
				isinsert=false;
				Toast.makeText(RecruitmentInfoActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
		}
		}

		
	};
	
	private DatePickerDialog.OnDateSetListener mDateSetListener = new OnDateSetListener() {
		
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			// TODO Auto-generated method stub
			yr = year;
			month = monthOfYear;
			day = dayOfMonth;
		//	Toast.makeText(getBaseContext(), (month+1)+"/"+day+"/"+year, 1).show();
			if(isShow){			
				new TimePickerDialog(RecruitmentInfoActivity.this, mTimeSetListener, hour, minute, true).show();						
				isShow = false;
			}
		}
	};
	
	
	
	protected void onDestroy() {
		System.out.println("on Des...");
		System.out.println("���͹㲥");
		aManager.setRepeating(AlarmManager.RTC, 0, 10000, pendingIntent);
		super.onDestroy();
	};
}
