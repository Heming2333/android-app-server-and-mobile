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
			//1 、创建HttpClient对象
			HttpClient client = new DefaultHttpClient();
			//2、拼装路径,注意将参数编码
			String path = "http://10.0.2.2:8080/myProdServ/GetBackServlet?NAME="
					+ URLEncoder.encode(username, "UTF-8")
					+ "&PASSWORD="
					+ URLEncoder.encode(password, "UTF-8")
					+ "&BIRTHDAY="
					+ URLEncoder.encode(birthday, "UTF-8");
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
