package com.neuedu.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class UserDao extends SQLiteOpenHelper{

	public UserDao(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sql = "create table list_info(_id text primary key ,company text,position text,date text,type text)";
		String sql2 = "create table recru_info(_id text primary key ,company text,position text,pos_num text," +
				"salary_lowest text,salary_highest text,workplace text,welfare text,requirement text,com_uri text," +
				"interview_place text,interview_date text,career_talk_place text," +
				"career_talk_date text,other_management text,mytime datetime)";
		db.execSQL(sql);
		db.execSQL(sql2);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		String sql = "create table list_info(_id text primary key ,company text,position text,date text,type text)";
		db.execSQL(sql);
	}

	public void insert_list_info(String _id,String company,String position,String date,String type){
		
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("_id", _id);
		values.put("company", company);
		values.put("position", position);
		values.put("date", date);
		values.put("type", type);		
		db.insert("list_info", null, values );
		System.out.println("insert success !");
		db.close();
	}
	

	
	public List<Map<String, String>> query_list_info(String type){
		System.out.print("------------DB type:"+type);
		String sql = "select _id,company,position,date from list_info where type=?";
		List<Map<String, String>> data = new ArrayList<Map<String,String>>();
		SQLiteDatabase db = getReadableDatabase();
		Cursor c =db.rawQuery(sql, new String[]{type});
		Map<String,String> u = null; 
		while(c.moveToNext()){
			u = new HashMap<String, String>();
			u.put("_id",c.getString(0));
			u.put("company", c.getString(1));
			u.put("position", c.getString(2));
			u.put("date", c.getString(3));
			
			data.add(u);
		}
		c.close();
		db.close();
		return data;
	}
	
	
	public boolean queryById(String id){
		String sql = "select company from list_info where _id=?";
		String company = null;
		SQLiteDatabase db = getReadableDatabase();
		Cursor c =db.rawQuery(sql, new String[]{id});
		while(c.moveToNext()){
			 company = c.getString(0);
		}
		db.close();
		if(company == null){
			return false;
		}
		return true;
	}
	

public void insert_recru_info(String _id,String company,String position,String pos_num,String salary_lowest,
								String salary_highest,String workplace,String welfare,String requirement,
								String com_uri,String interview_place,String interview_date,String career_talk_place,
								String career_talk_date,String other_management,String mytime){
		
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		
		values.put("_id", _id);
		values.put("company", company);
		values.put("position", position);
		values.put("pos_num", pos_num);
		values.put("salary_lowest", salary_lowest);
		values.put("salary_highest", salary_highest);
		values.put("workplace", workplace);
		values.put("welfare", welfare);
		values.put("com_uri", com_uri);
		values.put("requirement", requirement);
		values.put("interview_place", interview_place);
		values.put("interview_date", interview_date);
		values.put("career_talk_place", career_talk_place);
		values.put("career_talk_date", career_talk_date);
		values.put("other_management", other_management);
		values.put("mytime", mytime);
		db.insert("recru_info", null, values );
		System.out.println("insert success !");

		db.close();
	}
	

	
	public Map<String,String> query_recru_info(String id){		
		String sql = "select _id,company,position,pos_num,salary_lowest,salary_highest,workplace,welfare," +
				"com_uri,requirement,interview_place,interview_date,career_talk_place,career_talk_date," +
				"other_management,mytime from recru_info where _id=?";
		List<Map<String, String>> data = new ArrayList<Map<String,String>>();
		SQLiteDatabase db = getReadableDatabase();
		Cursor c =db.rawQuery(sql, new String[]{id});
		Map<String,String> u = null; 
		while(c.moveToNext()){
			u = new HashMap<String, String>();
			u.put("_id",c.getString(0));
			u.put("company", c.getString(1));
			u.put("position", c.getString(2));
			u.put("pos_num", c.getString(3));
			u.put("salary_lowest", c.getString(4));
			u.put("salary_highest", c.getString(5));
			u.put("workplace", c.getString(6));
			u.put("welfare", c.getString(7));
			u.put("com_uri", c.getString(8));
			u.put("requirement", c.getString(9));
			u.put("interview_place", c.getString(10));
			u.put("interview_date", c.getString(11));
			u.put("career_talk_place", c.getString(12));
			u.put("career_talk_date", c.getString(13));
			u.put("other_management", c.getString(14));
			u.put("mytime", c.getString(15));
			
			
		}
		c.close();
		db.close();
		return u;
	}
	
	public List<Map<String, String>> query_recru_someinfo(){		
		String sql = "select _id,company,position,interview_date from recru_info";
		List<Map<String, String>> data = new ArrayList<Map<String,String>>();
		SQLiteDatabase db = getReadableDatabase();
		Cursor c =db.rawQuery(sql, null);
		Map<String,String> u = null; 
		while(c.moveToNext()){
			u = new HashMap<String, String>();
			u.put("_id",c.getString(0));
			u.put("company", c.getString(1));
			u.put("position", c.getString(2));
			u.put("interview_date", c.getString(3));
			
			data.add(u);
		}
		c.close();
		db.close();
		return data;
	}
	
	
		public Cursor getTime(){
			String sql = "select _id,mytime from recru_info";
			SQLiteDatabase database = getReadableDatabase();			
			Cursor c = database.rawQuery(sql,null);			
			return c;
		
	}


}
 





















