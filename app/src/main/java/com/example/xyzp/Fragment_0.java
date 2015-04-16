package com.example.xyzp;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.example.xyzp.MainFragment.MyPagerAdapter;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.xyzp.MainFragment.MyPagerAdapter;

import com.example.xyzp.MyListView.OnRefreshListener;
import com.example.xyzp.R;

import com.neuedu.database.UserDao;
import android.os.AsyncTask;
import android.os.Bundle;  
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;  
import android.view.View;  
import android.view.ViewGroup;  
import android.widget.Toast;
  
public class Fragment_0 extends Fragment   
{   private MyListView listview;
	private StringBuilder builder;
	private Handler handler;
	private MyPagerAdapter myPagerAdapter;
	private UserDao userDao;
	private List<Map<String,String>> data = new ArrayList<Map<String,String>>();
	private String type;



    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)   
    {  
        View mView=inflater.inflate(R.layout.layout_0, container, false); 
    	      
        listview = (MyListView)mView.findViewById(R.id.lv);
        
        MainFragment m = new MainFragment();
		myPagerAdapter = m.new MyPagerAdapter(getActivity().getSupportFragmentManager());

        type = myPagerAdapter.getPageTitle(0).toString();	
		System.out.print("------------type:"+type);
//初始化适配器
  		userDao = new UserDao(getActivity(), "mydatabase", null, 1);
  		data = userDao.query_list_info(type);

		//使用自定义适配器
		final ListItemAdapter myAdapter = new ListItemAdapter(getActivity(),data);
		listview.setAdapter(myAdapter);

		
		handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			
				myAdapter.notifyDataSetChanged();
				
		}
	};
	

		
		handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			
				myAdapter.notifyDataSetChanged();
				
		}
	};
	
		


		listview.setonRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
				new AsyncTask<Void, Void, Void>() {
					protected Void doInBackground(Void... params) {
						try {
							Thread.sleep(1000);

														
							Thread thread = new Thread(){
								public void run() {
									//
									String url = "http://10.25.35.43:8080/NCHU_Ping/fkk/myservlet?cmd=refresh";
									HttpPost httpPost = new HttpPost(url);

									System.out.print("type:"+type);
									//
									
									List<BasicNameValuePair> param = new ArrayList<BasicNameValuePair>();
									BasicNameValuePair bnvp1 = new BasicNameValuePair("type", type);	
									
									param.add(bnvp1);							
									UrlEncodedFormEntity formEntity;
									try {
										formEntity = new UrlEncodedFormEntity(param,"UTF-8");
										httpPost.setEntity(formEntity);
									} catch (UnsupportedEncodingException e1) {
										e1.printStackTrace();
									}
						
									
									//3
									DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
									HttpResponse httpResponse;
									try {
										httpResponse = defaultHttpClient.execute(httpPost);
										HttpEntity httpEntity = httpResponse.getEntity();
										InputStream in = httpEntity.getContent();
										InputStreamReader reader = new InputStreamReader(in);

										builder = new StringBuilder();
										char buf[] = new char[128];
										int length = reader.read(buf);
										while(length != -1){
											builder.append(new String(buf,0,length));
								 			length = reader.read(buf);
										}
										System.out.println(builder);
										
										
										String json = builder.toString();
										
										JSONObject jObject = new JSONObject(json);
										JSONArray array = jObject.getJSONArray("refresh");
										
										for(int i=0;i<array.length();i++){
											JSONObject jObj = array.getJSONObject(i);	
											String name = jObj.getString("company");
											String msg = jObj.getString("position");
											String _id = jObj.getString("_id");
											System.out.println(name+msg+_id);
											Map<String,String> m = new HashMap<String, String>();
											m.put("company", name);
											m.put("position", msg);	
											m.put("_id", _id);
											Date date = new Date();
											SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");										
											m.put("date", dateFormat.format(date));
											data.add(m);
										}
										
										handler.sendMessage(handler.obtainMessage());
									} catch (ClientProtocolException e) {
										System.out.println("网络连接异常");
										e.printStackTrace();
									} catch (IOException e) {
										handler.sendMessage(handler.obtainMessage(2));
										System.out.println("获取数据异常");
										e.printStackTrace();
									} catch (JSONException e) {
										System.out.println("数据解析异常");
										e.printStackTrace();
									} 
									
								}
							};
							thread.start();
							
							
							
						}
							
						 catch (Exception e) {

														
							Thread thread = new Thread(){
								public void run() {
									//1. ָ指定连接路径的字符串
									String url = "http://10.25.35.43:8080/NCHU_Ping/fkk/myservlet?cmd=refresh";
									HttpPost httpPost = new HttpPost(url);
									String type = myPagerAdapter.getPageTitle(0).toString();	
									System.out.println("type:"+type);
									//封装参数
									
									List<BasicNameValuePair> param = new ArrayList<BasicNameValuePair>();
									BasicNameValuePair bnvp1 = new BasicNameValuePair("type", type);	
									
									param.add(bnvp1);							
									UrlEncodedFormEntity formEntity;
									try {
										formEntity = new UrlEncodedFormEntity(param,"UTF-8");
										httpPost.setEntity(formEntity);
									} catch (UnsupportedEncodingException e1) {
										e1.printStackTrace();
									}
						
									
									//3. 创建DefaultHttpClient对象，以调用方法执行HttpPost对象
									DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
									HttpResponse httpResponse;
									try {
										httpResponse = defaultHttpClient.execute(httpPost);
										HttpEntity httpEntity = httpResponse.getEntity();
										InputStream in = httpEntity.getContent();
										System.out.println("接收数据");
										builder = new StringBuilder();
										byte buf[] = new byte[128];
										int length = in.read(buf);
										while(length != -1){
											builder.append(new String(buf,0,length));
								 			length = in.read(buf);
										}
										System.out.println(builder);
										
										
										String json = builder.toString();
										
										JSONObject jObject = new JSONObject(json);
										JSONArray array = jObject.getJSONArray("refresh");
										
										for(int i=0;i<array.length();i++){
											JSONObject jObj = array.getJSONObject(i);	
											String name = jObj.getString("name");
											String msg = jObj.getString("position");
											String id = jObj.getString("id");
											System.out.println(name+msg+id);
											Map<String,String> m = new HashMap<String, String>();
											m.put("name", name);
											m.put("msg", msg);	
											m.put("id", id);
											Date date = new Date();
											SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");										
											m.put("date", dateFormat.format(date));
											data.add(m);
										}
										
										handler.sendMessage(handler.obtainMessage());
									} catch (ClientProtocolException e) {
										System.out.println("网络连接异常");
										e.printStackTrace();
									} catch (IOException e) {
										handler.sendMessage(handler.obtainMessage(2));
										System.out.println("获取数据异常");
										e.printStackTrace();
									} catch (JSONException e) {
										System.out.println("数据解析异常");
										e.printStackTrace();
									} 
									
								}
							};
							thread.start();
							
							
							
						}
												
						return null;
					}

					@Override
					protected void onPostExecute(Void result) {
						myAdapter.notifyDataSetChanged();
						listview.onRefreshComplete();
					}
				}.execute(null, null, null);
			}
		});
		
        return mView;  
    }  
    
    
    @Override
    public void onDestroyView() {
        // TODO Auto-generated method stub
        super.onDestroyView();
        System.out.println("destroy");
        for(int i=0;i<data.size();i++){
            if(!userDao.queryById(data.get(i).get("_id")))
                System.out.println("id:   "+data.get(i).get("_id"));
            userDao.insert_list_info(data.get(i).get("_id"),data.get(i).get("company"),data.get(i).get("position"),data.get(i).get("date"),type);
            System.out.println("insert!");
        }
    }      
}  

