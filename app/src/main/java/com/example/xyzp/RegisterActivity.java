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

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity implements OnClickListener{

	private Button btn_ok,btn_cancel;
	private EditText edit_id,edit_psw,edit_psw2,edit_email;
	private StringBuilder builder;
	private Handler handler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		edit_id = (EditText) findViewById(R.id.edit_id);
		edit_psw = (EditText) findViewById(R.id.edit_psw);
		edit_psw2 = (EditText) findViewById(R.id.edit_psw2);
		edit_email = (EditText) findViewById(R.id.edit_email);
		
		
		btn_ok = (Button)findViewById(R.id.btn_ok);
		btn_ok.setOnClickListener(this);
		
		
		
		handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			if(1 == msg.what){
			
				Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(RegisterActivity.this,MainTabActivity.class);
				startActivity(intent);
			}else if(0 == msg.what){
				Toast.makeText(RegisterActivity.this, "用户名已存在", Toast.LENGTH_SHORT).show();
			}else if(2 == msg.what){
				Toast.makeText(RegisterActivity.this, "网络连接异常", Toast.LENGTH_SHORT).show();
			}
			else if(3 == msg.what){
				Toast.makeText(RegisterActivity.this, "邮箱已被注册", Toast.LENGTH_SHORT).show();
			}
				
		}
	};
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		

		switch (v.getId()) {
		case R.id.btn_ok:
	
			//判断两次输入的密码是否相等
			if(!edit_psw.getText().toString().equals(edit_psw2.getText().toString())){
				new AlertDialog.Builder(this)
				.setTitle("您两次输入的密码不一致，请重新输入！")
				.setPositiveButton("确定", null)
				.show();
				
			}
			
			else{
				Thread thread = new Thread(){
				@Override
				public void run() {

					String url = "http://10.25.35.43:8080/NCHU_Ping/fkk/myservlet?cmd=register";
					HttpPost httpPost = new HttpPost(url);

					String name = edit_id.getText().toString();
					String psw = edit_psw.getText().toString();
					String email = edit_email.getText().toString();

					
					List<BasicNameValuePair> param = new ArrayList<BasicNameValuePair>();
					BasicNameValuePair bnvp1 = new BasicNameValuePair("name", name);
					BasicNameValuePair bnvp2 = new BasicNameValuePair("psw", psw);
					BasicNameValuePair bnvp3 = new BasicNameValuePair("email", email);
					param.add(bnvp1);
					param.add(bnvp2);
					param.add(bnvp3);
					
					UrlEncodedFormEntity formEntity;
					try {
						formEntity = new UrlEncodedFormEntity(param,"UTF-8");
						httpPost.setEntity(formEntity);
					} catch (UnsupportedEncodingException e1) {
						e1.printStackTrace();
					}
		
					
					//3. ����DefaultHttpClient�����Ե��÷���ִ��HttpPost����
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
						JSONObject jObject = new JSONObject(json);
						JSONObject key = jObject.getJSONObject("register");
						int code = key.getInt("code");
						handler.sendMessage(handler.obtainMessage(code));
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
			
			

			Intent intent = new Intent();
			intent.setAction("com.neuedu.login");
			startActivity(intent);
		}
		
			break;

		default:
			break;
		}
				
	}
}
