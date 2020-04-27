package com.example.myprodWeb;

import java.io.InputStream;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class GetBackService {
	public static String GetbackByClientGet(final String username,final String password,final String birthday) {
		try {
			//1 ������HttpClient����
			HttpClient client = new DefaultHttpClient();
			//2��ƴװ·��,ע�⽫��������
			String path = "http://10.0.2.2:8080/myProdServ/GetBackServlet?NAME="
					+ URLEncoder.encode(username, "UTF-8")
					+ "&PASSWORD="
					+ URLEncoder.encode(password, "UTF-8")
					+ "&BIRTHDAY="
					+ URLEncoder.encode(birthday, "UTF-8");
			//3��GET��ʽ����
			HttpGet httpGet = new HttpGet(path);
              //4���õ����������ص�HttpResponse����
			HttpResponse response = client.execute(httpGet);
              //5���õ�״̬��
			int code = response.getStatusLine().getStatusCode(); 
			if (code == 200) { 
                   //��ȡ������
				InputStream is = response.getEntity().getContent();
                    //��������ת�����ַ���
				String text = StreamTools.readInputStream(is);			
	              return text;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
