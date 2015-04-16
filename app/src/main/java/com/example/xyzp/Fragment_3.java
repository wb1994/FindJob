package com.example.xyzp;


import java.util.GregorianCalendar;



import android.content.Intent;
import android.os.Bundle;
//import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class Fragment_3 extends Fragment{

	private LinearLayout layout;
	@Override
	public View onCreateView(LayoutInflater inflater,
			 ViewGroup container,  Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		 View mView=inflater.inflate(R.layout.calendar_layout, container, false); 
		 layout = (LinearLayout) mView.findViewById(R.id.calendar);
		 CalendarView calView = new CalendarView(getActivity(),this);
		 layout.addView(calView);
		 calView.requestFocus();
		 return mView;
	}
	
	public void showDiaryList(int year, int month, int day) {
		Intent k = new Intent(getActivity(), DiaryList.class);
		k.putExtra("cal", new GregorianCalendar(year,month,day));
		startActivity(k);
	}
	

}
