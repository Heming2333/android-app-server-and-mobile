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
			//1 、创建HttpClient对象
			HttpClient client = new DefaultHttpClient();
			//2、拼装路径,注意将参数编码
			String path = "http://10.0.2.2:8080/myProdServ/BackUpServlet?ACTION="
					+ URLEncoder.encode("0", "UTF-8")
					+ "&ID="
					+ URLEncoder.encode(contactId, "UTF-8")
					+ "&NAME="
					+ URLEncoder.encode(disPlayName, "UTF-8")
					+ "&NUMBER="
					+ URLEncoder.encode(phoneNumber, "UTF-8");
			//3、GET方式请求
			HttpGet httpGet = new HttpGet(path);
              //4、拿到服务器返回的HttpResponse对象
			HttpResponse response = client.execute(httpGet);
              //5、拿到状态码
			int code = response.getStatusLine().getStatusCode(); 
			if (code == 200) { 
                   //获取输入流
				InputStream is = response.getEntity().getContent();
                    //将输入流转换成字符串
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
				//1 、创建HttpClient对象
				HttpClient client = new DefaultHttpClient();
				//2、拼装路径,注意将参数编码
				String path = "http://10.0.2.2:8080/myProdServ/BackUpServlet?ACTION="
						+ URLEncoder.encode("4", "UTF-8");
				//3、GET方式请求
				HttpGet httpGet = new HttpGet(path);
	              //4、拿到服务器返回的HttpResponse对象
				HttpResponse response = client.execute(httpGet);
	              //5、拿到状态码
				int code = response.getStatusLine().getStatusCode(); 
				if (code == 200) { 
	                   //获取输入流
					InputStream is = response.getEntity().getContent();
	                    //将输入流转换成字符串
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
				//1 、创建HttpClient对象
				HttpClient client = new DefaultHttpClient();
				//2、拼装路径,注意将参数编码
				String path = "http://10.0.2.2:8080/myProdServ/BackUpServlet?ACTION="
						+ URLEncoder.encode("1", "UTF-8");
				//3、GET方式请求
				HttpGet httpGet = new HttpGet(path);
	              //4、拿到服务器返回的HttpResponse对象
				HttpResponse response = client.execute(httpGet);
	              //5、拿到状态码
				int code = response.getStatusLine().getStatusCode(); 
				if (code == 200) { 
	                   //获取输入流
					InputStream is = response.getEntity().getContent();
	                    //将输入流转换成字符串
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
				//1 、创建HttpClient对象
				HttpClient client = new DefaultHttpClient();
				//2、拼装路径,注意将参数编码
				String path = "http://10.0.2.2:8080/myProdServ/BackUpServlet?ACTION="
						+ URLEncoder.encode("2", "UTF-8")
						+ "&ID="
						+ URLEncoder.encode(id, "UTF-8");		
				//3、GET方式请求
				HttpGet httpGet = new HttpGet(path);
	              //4、拿到服务器返回的HttpResponse对象
				HttpResponse response = client.execute(httpGet);
	              //5、拿到状态码
				int code = response.getStatusLine().getStatusCode(); 
				if (code == 200) { 
	                   //获取输入流
					InputStream is = response.getEntity().getContent();
	                    //将输入流转换成字符串
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
				//1 、创建HttpClient对象
				HttpClient client = new DefaultHttpClient();
				//2、拼装路径,注意将参数编码
				String path = "http://10.0.2.2:8080/myProdServ/BackUpServlet?ACTION="
						+ URLEncoder.encode("3", "UTF-8")
						+ "&ID="
						+ URLEncoder.encode(id, "UTF-8");		
				//3、GET方式请求
				HttpGet httpGet = new HttpGet(path);
	              //4、拿到服务器返回的HttpResponse对象
				HttpResponse response = client.execute(httpGet);
	              //5、拿到状态码
				int code = response.getStatusLine().getStatusCode(); 
				if (code == 200) { 
	                   //获取输入流
					InputStream is = response.getEntity().getContent();
	                    //将输入流转换成字符串
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
	
	
	


