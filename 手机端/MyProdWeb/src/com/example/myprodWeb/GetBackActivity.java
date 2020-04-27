package com.example.myprodWeb;

import com.example.myprodWeb.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class GetBackActivity extends Activity{
	Button btn_send;
	EditText et_name;
	EditText et_newpassword;
	EditText et_confirmpassword;
	EditText et_birthday;
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_getback);
		
		btn_send =(Button)findViewById(R.id.btn_OK);
		et_name=(EditText)findViewById(R.id.et_name);
		et_newpassword=(EditText)findViewById(R.id.et_password1);
		et_confirmpassword=(EditText)findViewById(R.id.et_password2);
		et_birthday=(EditText)findViewById(R.id.et_birthday);
		
		et_birthday.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(GetBackActivity.this,BirthdayActivity.class);
    			startActivityForResult(intent,1);
			}
		});
		
		btn_send.setOnClickListener(new OnClickListener(){
	        
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				 final String name = et_name.getText().toString().trim();
				 final String password = et_newpassword.getText().toString().trim();
				 final String confirmpassword = et_confirmpassword.getText().toString().trim();
				 final String birthday = et_birthday.getText().toString().trim();
			
				// У�����������ʽ�Ƿ���ȷ
				if(TextUtils.isEmpty(name)) {
					Toast.makeText(GetBackActivity.this, "�������ֻ�����", Toast.LENGTH_SHORT).show();
					return;
				}
				if(TextUtils.isEmpty(password)) {
					Toast.makeText(GetBackActivity.this, "������������", Toast.LENGTH_SHORT).show();
					return;
				}
				if(TextUtils.isEmpty(confirmpassword)) {
					Toast.makeText(GetBackActivity.this, "���ٴ�����������", Toast.LENGTH_SHORT).show();
					return;
				}
				if(TextUtils.isEmpty(birthday)) {
					Toast.makeText(GetBackActivity.this, "��ѡ������", Toast.LENGTH_SHORT).show();
					return;
				}
				if(!password.equals(confirmpassword)){
					Toast.makeText(GetBackActivity.this, "������������벻һ��", Toast.LENGTH_SHORT).show();
					return;
				}
				
				new Thread() {//�������̷߳�������
					public void run() 
					{
						final String result = GetBackService.GetbackByClientGet(name,password,birthday);
						if ((result != null)&&result.equals("0")) {
		                        //ʹ��UI�̵߳���toast
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									Toast.makeText
									(
											GetBackActivity.this,
											"�޸�����ɹ�", 
											Toast.LENGTH_SHORT
									).show();
								}
							});
						} 
						else {// ����ʧ�� ʹ��UI�̵߳���toast
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									Toast.makeText(GetBackActivity.this, "�޸�����ʧ��...", 0).show();
								}
							});
						}
					};
				}.start();
				
                Intent intent=new Intent();
				intent.setClassName(GetBackActivity.this,"com.example.myprodWeb.LoginActivity");
				intent.putExtra("name", name);
				intent.putExtra("password", password);
				startActivity(intent);
			}
		});
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		et_birthday=(EditText)findViewById(R.id.et_birthday);
		if(resultCode==1)
		{
			String strdata=data.getStringExtra("birthday_data");
			//spbirthday.
			et_birthday.setText(strdata);
		}
	}
}
			
		

				
				
						
	

		



