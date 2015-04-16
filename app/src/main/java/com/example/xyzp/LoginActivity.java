package com.example.xyzp;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;


import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	 
	private Button btn_login,btn_cancel,btn_register;
	private EditText edit_id,edit_psw;
	private String id,psw;
	private StringBuilder builder;
	private Handler handler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
				
		btn_login = (Button)findViewById(R.id.btn_login);
		btn_cancel = (Button)findViewById(R.id.btn_cancel);
		btn_register = (Button)findViewById(R.id.btn_register);
		edit_id = (EditText)findViewById(R.id.edit_id);
		edit_psw = (EditText)findViewById(R.id.edit_psw);
	
		
			handler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				if(1 == msg.what){
					Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(LoginActivity.this,MainTabActivity.class);
					startActivity(intent);
				}else if(0 == msg.what){
					Toast.makeText(LoginActivity.this, "用户名或密码不正确", Toast.LENGTH_SHORT).show();
				}else if(2 == msg.what){
					Toast.makeText(LoginActivity.this, "网络连接异常", Toast.LENGTH_SHORT).show();
				}
					
			}
		};
	

		btn_register.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction("com.neuedu.register");
				startActivity(intent);
			}
		});
		

		btn_login.setOnClickListener(new OnClickListener() {	
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction("com.neuedu.maintab");
				startActivity(intent);
			}
//			public void onClick(View v) {
//				Thread thread = new Thread(){
//					@Override
//					public void run() {
//
//						String url = "http://10.25.35.43:8080/NCHU_Ping/fkk/myservlet?cmd=login";
//						HttpPost httpPost = new HttpPost(url);
//						String name = edit_id.getText().toString();
//						String psw = edit_psw.getText().toString();
//
//						List<BasicNameValuePair> param = new ArrayList<BasicNameValuePair>();
//						BasicNameValuePair bnvp1 = new BasicNameValuePair("name", name);
//						BasicNameValuePair bnvp2 = new BasicNameValuePair("psw", psw);
//						param.add(bnvp1);
//						param.add(bnvp2);
//
//						UrlEncodedFormEntity formEntity;
//						try {
//							formEntity = new UrlEncodedFormEntity(param,"UTF-8");
//							httpPost.setEntity(formEntity);
//						} catch (UnsupportedEncodingException e1) {
//							e1.printStackTrace();
//						}
//
//
//						DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
//						HttpResponse httpResponse;
//						try {
//							httpResponse = defaultHttpClient.execute(httpPost);
//							HttpEntity httpEntity = httpResponse.getEntity();
//							InputStream in = httpEntity.getContent();
//
//							builder = new StringBuilder();
//							byte buf[] = new byte[128];
//							int length = in.read(buf);
//							while(length != -1){
//								builder.append(new String(buf,0,length));
//					 			length = in.read(buf);
//							}
//							System.out.println(builder);
//
//
//							String json = builder.toString();
//							JSONObject jObject = new JSONObject(json);
//							JSONObject key = jObject.getJSONObject("login");
//							int code = key.getInt("code");
//							handler.sendMessage(handler.obtainMessage(code));
//						} catch (ClientProtocolException e) {
//							System.out.println("网络连接异常");
//							e.printStackTrace();
//						} catch (IOException e) {
//							handler.sendMessage(handler.obtainMessage(2));
//							System.out.println("获取数据异常");
//							e.printStackTrace();
//						} catch (JSONException e) {
//							System.out.println("数据解析异常");
//							e.printStackTrace();
//						}
//
//					}
//				};
//				thread.start();
//
//			}
		});
		
		

		
		
	}
	



}
