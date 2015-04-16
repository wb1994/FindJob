package com.example.xyzp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.neuedu.database.UserDao;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Fragment_4 extends Fragment implements OnItemClickListener{

	
	private SlideListView mListView;
	private SlideAdapter adapter;
	private List<TestModel> listModels = new ArrayList<TestModel>();
	private UserDao userDao;
	private List<Map<String,String>> data = new ArrayList<Map<String,String>>();
	@Override
	 public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)   
    {  
		View mView=inflater.inflate(R.layout.layout_4, container, false);  
        
 
        adapter = new SlideAdapter();
		
		mListView = (SlideListView) mView.findViewById(R.id.listview_list);
		mListView.initSlideMode(SlideListView.MOD_BOTH);
		mListView.setAdapter(adapter);
		mListView.setOnItemClickListener(this);
        return mView; 
    } 
	@Override
	public void onResume() {
		super.onResume();
		listModels.clear();

		
  		userDao = new UserDao(getActivity(), "mydatabase", null, 1);
  		data = userDao.query_recru_someinfo();
  		System.out.println("f4:"+data);		
		for (int i = 0; i < data.size(); i++) {
			TestModel item = new TestModel();
				item.setTitle(i + "."+data.get(i).get("company"));
				item.setContent("  "+ data.get(i).get("position"));
				item.setTime("  "+ data.get(i).get("interview_date"));
			listModels.add(item);
		}
		adapter.notifyDataSetChanged();
		
	}
	
	
	public class SlideAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return listModels.size();
        }

        @Override
        public Object getItem(int position) {
            return listModels.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder = new ViewHolder();
            if (convertView == null) {
            	convertView = getActivity().getLayoutInflater().inflate(R.layout.layout_list_item, null);

                holder.title = (TextView) convertView.findViewById(R.id.company);
                holder.time = (TextView) convertView.findViewById(R.id.time);
                holder.content = (TextView) convertView.findViewById(R.id.content);
               
                holder.delete2 = (RelativeLayout)convertView.findViewById(R.id.delete2);
                holder.other2 = (RelativeLayout)convertView.findViewById(R.id.other2);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            TestModel item = listModels.get(position);

            holder.title.setText(item.getTitle());
            holder.content.setText(item.getContent());
            holder.time.setText(item.getTime());
            
            holder.delete2.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

					Toast.makeText(getActivity(), "删除" + position, Toast.LENGTH_SHORT).show();
					mListView.slideBack();
				}
			});
            holder.other2.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Toast.makeText(getActivity(), "其他" + position, Toast.LENGTH_SHORT).show();
					mListView.slideBack();
				}
			});
            
            return convertView;
        }

    }

	 private static class ViewHolder {
	        public TextView title;
	        public TextView time;
	        public TextView content;	       
	        public RelativeLayout delete2;
	        public RelativeLayout other2;

	    }

	    @Override
	    public void onItemClick(AdapterView<?> parent, View view, int position,
	            long id) {
	    	Toast.makeText(getActivity(), "点击" + position, Toast.LENGTH_SHORT).show();
	    }
}
