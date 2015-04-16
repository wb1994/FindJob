package com.example.xyzp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class CollectionFragment extends Fragment{   
	private ListView listview;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)   
	{  
	    View mView=inflater.inflate(R.layout.collection_layout, container, false); 
		      
	    listview = (ListView)mView.findViewById(R.id.listView1);
		
	    return mView;  
	}  
  
}
