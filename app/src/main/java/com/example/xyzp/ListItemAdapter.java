package com.example.xyzp;

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

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.sax.StartElementListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ListItemAdapter extends BaseAdapter {
	private Context context;
	private List<Map<String, String>> data;
	private StringBuilder builder;
	private Handler handler;
	
	private String position;
	private String pos_num;
	private String salary_lowest;
	private String salary_highest;
	private String workplace;
	private String welfare;
	private String requirement;
	private String com_uri;
	private String interview_place;
	private String interview_date;
	private String career_talk_place;
	private String career_talk_date;
	private String other_management;
	private String company;
	
	
	
	
	public ListItemAdapter(Context context,List<Map<String, String>> data){
		this.context = context;
		this.data = data;
	}
	@Override
	public int getCount() {
		return this.data.size();
	}
	@Override
	public Object getItem(int position) {
		return this.data.get(position);
		
	}
	@Override
	public long getItemId(int position) {
		return 0;
	}
	@Override
	public View getView(final int pos, View convertView, ViewGroup parent) {
		if(convertView == null){//加载list_item
			convertView =LayoutInflater.from(context).inflate(R.layout.list_item, null);
		}
		//将数据绑定到指定的控件
		TextView tvName = (TextView)convertView.findViewById(R.id.tv_com_name);
		TextView tvMsg = (TextView)convertView.findViewById(R.id.tv_short_msg);
		TextView tvDate = (TextView)convertView.findViewById(R.id.tv_date_meet);
		Map<String, String> m = data.get(pos);
		final String name = m.get("company");
		final String msg = m.get("position");
		tvName.setText(name);
		tvMsg.setText(msg);
		tvDate.setText(m.get("date"));
		

		handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			
				
		}
	};
		convertView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(context, "item is clicked !", Toast.LENGTH_SHORT).show();
				
				Map<String, String> m= (Map<String, String>)getItem(pos);
				final String id = m.get("_id");
				System.out.println("id:"+id);
				Thread thread = new Thread(){
					public void run() {

						String url = "http://10.25.35.43:8080/NCHU_Ping/fkk/myservlet?cmd=get_msg";
						HttpPost httpPost = new HttpPost(url);						

						List<BasicNameValuePair> param = new ArrayList<BasicNameValuePair>();
						BasicNameValuePair bnvp1 = new BasicNameValuePair("id", id);	
						
						param.add(bnvp1);							
						UrlEncodedFormEntity formEntity;
						try {
							formEntity = new UrlEncodedFormEntity(param,"UTF-8");
							httpPost.setEntity(formEntity);
						} catch (UnsupportedEncodingException e1) {
							e1.printStackTrace();
						}
			
						

						DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
						HttpResponse httpResponse;
						try {
							httpResponse = defaultHttpClient.execute(httpPost);
							HttpEntity httpEntity = httpResponse.getEntity();
							InputStream in = httpEntity.getContent();

							builder = new StringBuilder();
							byte buf[] = new byte[128];
							int length = in.read(buf);
							while(length != -1){
								builder.append(new String(buf,0,length));
					 			length = in.read(buf);
							}
							System.out.println(builder);
							
							
							String json = builder.toString();
							try {
								JSONObject jObj = new JSONObject(json);
								JSONArray array = jObj.getJSONArray("get_msg");
								
							    JSONObject object = array.getJSONObject(0);
							    company = object.getString("company");
								position = object.getString("position");
								pos_num = object.getString("pos_num");
								salary_lowest = object.getString("salary_lowest");
								salary_highest = object.getString("salary_highest");
								workplace = object.getString("workplace");
								welfare = object.getString("welfare");
								requirement = object.getString("requirement");
								com_uri = object.getString("com_uri");
								interview_place = object.getString("interview_place");
								interview_date = object.getString("interview_date");
								career_talk_place = object.getString("career_talk_place");
								career_talk_date = object.getString("career_talk_date");
								other_management = object.getString("other_management");
								
								handler.sendMessage(handler.obtainMessage());
								
								
								Intent intent = new Intent();
								intent.putExtra("_id", id);
								intent.putExtra("company", company);
								intent.putExtra("position", position);
								intent.putExtra("pos_num", pos_num);
								intent.putExtra("salary_lowest", salary_lowest);
								intent.putExtra("salary_highest", salary_highest);
								intent.putExtra("workplace", workplace);
								intent.putExtra("welfare", welfare);
								intent.putExtra("requirement", requirement);
								intent.putExtra("com_uri", com_uri);
								intent.putExtra("interview_place", interview_place);
								intent.putExtra("interview_date", interview_date);
								intent.putExtra("career_talk_place", career_talk_place);
								intent.putExtra("career_talk_date", career_talk_date);
								intent.putExtra("other_management", other_management);

								intent.setAction("com.neuedu.recinfo");
								context.startActivity(intent);
								
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								System.out.println("数据解析异常");
								e.printStackTrace();
							}							
						    } catch (ClientProtocolException e) {
								System.out.println("网络连接异常");
								e.printStackTrace();
						    } catch (IOException e) {
								
								System.out.println("获取数据异常");
								e.printStackTrace();
						} 
						
					}
				};
				thread.start();
				
				
				
				
			}
		});
		
		return convertView;
	}
	

	
	
}
