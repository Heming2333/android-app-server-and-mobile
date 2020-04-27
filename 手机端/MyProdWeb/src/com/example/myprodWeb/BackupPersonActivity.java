package com.example.myprodWeb;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.example.myprodWeb.R;

import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.RawContacts;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class BackupPersonActivity extends Activity{
	private Button btn;
	private Button btn2;
	    
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_backup);
	        
	        btn=(Button)findViewById(R.id.button1);
	        btn.setOnClickListener(new View.OnClickListener() {
	        	public void onClick(View v)
	        	{
	        		setTitle("backup_ok");
	        		//setTitle(str);
	        		String flag=BackupPersonService.truncateContactSQL();
	        		getContact_Upload();
	        	}
	        });
	        
	        
	        
	        btn2=(Button)findViewById(R.id.button2);
	        btn2.setOnClickListener(new View.OnClickListener() {
	        	public void onClick(View v)
	        	{
	        		setTitle("backup_read");
	        		String num = BackupPersonService.getcount();
	        		int count = Integer.parseInt(num);
	        		if(count==0)
	        			Toast.makeText(BackupPersonActivity.this, "没有联系人可以还原！", 0).show();
	        		else{
	        		for(int i=1;i<=count;i++)
	        		{
	        			String s = String.valueOf(i);
	        			String name=BackupPersonService.getname(s);
	        			String number=BackupPersonService.getnumber(s);
	        			addContacts(name,number);
	        		}
	        		     Toast.makeText(BackupPersonActivity.this, "联系人还原成功！", 0).show();
	        		}
	
	        	}

	        });
	    }
	    
	    public void getContact_Upload(){
	        //获得所有的联系人  
	       Cursor cur = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);  
	       //循环遍历  
	       if (cur.moveToFirst()) {  
	           int idColumn  = cur.getColumnIndex(ContactsContract.Contacts._ID);   
	           int displayNameColumn = cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);  
	           do {  
	               //获得联系人的ID号  
	              final String contactId = cur.getString(idColumn);  
	              //获得联系人姓名  
	              final String disPlayName = cur.getString(displayNameColumn);  
     
	                  //获得联系人的电话号码  
	                  Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID+ " = " + contactId, null, null);
	                  int i=0;
	                  String number = null;
	                  if(phones.moveToFirst()){  
	                      do{  
	                    	  i++;
	                    	  String phoneNumber= phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));                
	                          number=phoneNumber;
	                    	  System.out.println(number);  
	                      }while(phones.moveToNext());  
	                  }  
	                final String pnumber=number;
	              

	              new Thread() {//开启子线程访问网络
	      			public void run() {
	                         //调用方法请求网络获取数据
	      				String result = BackupPersonService.uploadByClient(contactId,disPlayName,pnumber);
	      				result.trim();
	      				if ((result != null)&&result.equals("0")) {
	      				//if ((result != null)) {
	                              //使用UI线程弹出toast
	      					runOnUiThread(new Runnable() {
	      						@Override
	      						public void run() {
	      							Toast.makeText(BackupPersonActivity.this, "备份成功...", 0).show();
	      						}
	      					});
	      				} else {// 请求失败 使用UI线程弹出toast
	      					runOnUiThread(new Runnable() {
	      						@Override
	      						public void run() {
	      							Toast.makeText(BackupPersonActivity.this, "备份失败...", 0).show();
	      						}
	      					});
	      				}
	      			};
	      		}.start();
	              } while (cur.moveToNext());  
	     
	       }  
	    }  
	    
	    private void addContacts(String name,String num) {
	    	ContentValues values = new ContentValues();
	    	Uri rawContactUri = getContentResolver().insert(RawContacts.CONTENT_URI, values);
	    	long rawContactId = ContentUris.parseId(rawContactUri);

	    	values.clear();
	    	values.put(Data.RAW_CONTACT_ID, rawContactId);
	    	values.put(Data.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE);
	    	values.put(StructuredName.GIVEN_NAME, name);

	    	getContentResolver().insert(Data.CONTENT_URI, values);

	    	values.clear();
	    	values.put(Data.RAW_CONTACT_ID, rawContactId);
	    	values.put(Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE);
	    	values.put(Phone.NUMBER, num);
	    	values.put(Phone.TYPE, Phone.TYPE_HOME);

	    	getContentResolver().insert(Data.CONTENT_URI, values);
	    	}

}
