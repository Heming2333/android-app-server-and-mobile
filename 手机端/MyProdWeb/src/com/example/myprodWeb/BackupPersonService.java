package com.example.myprodWeb;

import java.io.InputStream;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class BackupPersonService {
	public static String uploadByClient(final String contactId, final String disPlayName,final String phoneNumber) 
	{
		try {
			//1 ������HttpClient����
			HttpClient client = new DefaultHttpClient();
			//2��ƴװ·��,ע�⽫��������
			String path = "http://10.0.2.2:8080/myProdServ/BackUpServlet?ACTION="
					+ URLEncoder.encode("0", "UTF-8")
					+ "&ID="
					+ URLEncoder.encode(contactId, "UTF-8")
					+ "&NAME="
					+ URLEncoder.encode(disPlayName, "UTF-8")
					+ "&NUMBER="
					+ URLEncoder.encode(phoneNumber, "UTF-8");
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
		public static String truncateContactSQL()
		{
			try {
				//1 ������HttpClient����
				HttpClient client = new DefaultHttpClient();
				//2��ƴװ·��,ע�⽫��������
				String path = "http://10.0.2.2:8080/myProdServ/BackUpServlet?ACTION="
						+ URLEncoder.encode("4", "UTF-8");
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
	
		public static String getcount()
		{
			try {
				//1 ������HttpClient����
				HttpClient client = new DefaultHttpClient();
				//2��ƴװ·��,ע�⽫��������
				String path = "http://10.0.2.2:8080/myProdServ/BackUpServlet?ACTION="
						+ URLEncoder.encode("1", "UTF-8");
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
		
		public static String getname(String id)
		{
			try {
				//1 ������HttpClient����
				HttpClient client = new DefaultHttpClient();
				//2��ƴװ·��,ע�⽫��������
				String path = "http://10.0.2.2:8080/myProdServ/BackUpServlet?ACTION="
						+ URLEncoder.encode("2", "UTF-8")
						+ "&ID="
						+ URLEncoder.encode(id, "UTF-8");		
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
		public static String getnumber(String id)
		{
			try {
				//1 ������HttpClient����
				HttpClient client = new DefaultHttpClient();
				//2��ƴװ·��,ע�⽫��������
				String path = "http://10.0.2.2:8080/myProdServ/BackUpServlet?ACTION="
						+ URLEncoder.encode("3", "UTF-8")
						+ "&ID="
						+ URLEncoder.encode(id, "UTF-8");		
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
	
	
	


