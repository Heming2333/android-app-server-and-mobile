package com.example.myProdServ;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

public class DBUtil 
{
  //与 MySql数据库建立连接
    public static Connection getConnection()
	{
    	Connection con=null;
		try
		{			
			Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
			String url="jdbc:mysql://localhost:3306/test?user=root&password=root&useUnicode=true&characterEncoding=UTF-8";//链接数据库语句
            con= (Connection) DriverManager.getConnection(url); //链接数据库
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
    
    public static void delete(String sql)
    {
    	try
		{
			Connection con=getConnection();			
			Statement st=con.createStatement();			
			st.executeUpdate(sql);						
			st.close();
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }
     //登录密码验证
    public static String selectUPwd(String uname)
    {
		String result=null;
		try
		{
			Connection con=getConnection();
			Statement st=con.createStatement();
			String sql="select U_pswd from user where U_name='"+uname+"'";
			ResultSet rs=st.executeQuery(sql);
			if(rs.next())
			{
				result=rs.getString(1);
				//out.print(result); 
			}
			rs.close();
			st.close();
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    
		return result;
    	
    }
  //添加用户信息到MySql数据库
    public static int insertUser(String name,String password,String birthday,String sex)
    {
    	int falg=0;
    	try{
    		Connection con=getConnection();
			Statement st=con.createStatement();
			String sql="select count(*) from user where U_name='"+name+"'";
			ResultSet rs=st.executeQuery(sql);
			if(rs.next())
			{
				falg=2;
				//out.print(result); 
			}
			rs.close();
			st.close();
			con.close();
    	}
    	catch(Exception e)
		{
			e.printStackTrace();
		}
    	
    	try{
    		Connection con=getConnection();
    		Statement st=con.createStatement();
            String sql="insert into user(U_name,U_pswd,U_bird,U_sex) value('"+name+"','"+password+"','"+birthday+"','"+sex+"')";
            st.execute("set char set 'gbk'");
            st.executeUpdate(sql);
    		st.close();
    		con.close();
        	falg=1;
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	
		return falg;
    	
    }
    
    public static Boolean updateUser(String name,String password,String birthday)
    {
    	Boolean falg=false;
    	try{
    		Connection con=getConnection();
    		Statement st=con.createStatement();
            String sql="update user set U_pswd='"+password+"'where U_name='"+name+"'and U_bird='"+birthday+"'";
            st.execute("set char set 'gbk'");
            st.executeUpdate(sql);
    		st.close();
    		con.close();
        	falg=true;
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	
		return falg;
    }
  //添加产品信息到MySql数据库
    public static Boolean insertProdDB(String sId,String sName,String sBa,String stock)
    {
    	Boolean falg=false;
    	
		//String sId=String.valueOf(id);
		//String sBa=String.valueOf(account.getBalance());
		try{
    		Connection con=getConnection();
    		Statement st=con.createStatement();
            String sql="insert into account(_id,name,balance,stock) value('"+sId+"','"+sName+"','"+sBa+"','"+stock+"')";
            st.execute("set char set 'gbk'");
            st.executeUpdate(sql);
    		st.close();
    		con.close();
    		falg=true;
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
		return falg;
    }
    public static Boolean deleteProdDB(String sId)
    {
    	Boolean falg=false;
    	try{
    		Connection con=getConnection();
        	Statement st=con.createStatement();
        	String sql="delete from account where _id='"+sId+"'";
        	st.execute(sql);
        	st.close();
    		con.close();
        	falg=true;
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	
		return falg;
    	
    }
    public static Boolean updateProdDB(String sId,String sName,String sBa,String stock)
    {
       Boolean falg=false;
    	
		//String sId=String.valueOf(id);
		//String sBa=String.valueOf(account.getBalance());
		try{
			
    		Connection con=getConnection();
        	Statement st=con.createStatement();
        	String sql="update account set name='"+sName+"',balance='"+sBa+"',stock='"+stock+"'where _id='"+sId+"'";
        	st.executeUpdate(sql);
        	st.close();
    		con.close();
    		falg=true;
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
		return falg;
    }
    public static void sqlTruncateContact()
    {
    	try{
    		Connection con=getConnection();
        	Statement st=con.createStatement();
            st.execute("truncate contact");
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
    public static Boolean insertContactDB(String Id,String Name,String Number)
    {
         Boolean falg=false;
    	
		try{
			
    		Connection con=getConnection();
        	Statement st=con.createStatement();
        	String sql="insert into contact(id,name,number) value('"+Id+"','"+Name+"','"+Number+"')";
            st.execute("set char set 'gbk'");
        	st.executeUpdate(sql);
        	st.close();
    		con.close();
    		falg=true;
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
		return falg;
    }
    public static int getBackupCount()
    {
    	int count = 0;
    	try{
    		Connection con=getConnection();
    		Statement st=con.createStatement();
    		String sql="select count(*) as totalCount from contact ";
    		ResultSet rs=st.executeQuery(sql);
		if(rs.next())
		{
			count=rs.getInt("totalCount");
		}
		rs.close();
		st.close();
		con.close();
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return count;
    }
    
    public static String getBackupName(String Id)
    {
    	String name="";
    	try{
    		Connection con=getConnection();
    		Statement st=con.createStatement();
    		String sql="select name from contact where id='"+Id+"' ";
    		ResultSet rs=st.executeQuery(sql);
		if(rs.next())
		{
			name=rs.getString("name");
		}
		rs.close();
		st.close();
		con.close();
		}
	catch(Exception e)
	{
		e.printStackTrace();
	}
    	return name;
    }
    
    public static String getBackupNumber(String Id)
    {
    	String number="";
    	try{
    		Connection con=getConnection();
    		Statement st=con.createStatement();
    		String sql="select number from contact where id='"+Id+"' ";
    		ResultSet rs=st.executeQuery(sql);
		if(rs.next())
		{
			number=rs.getString("number");
		}
		rs.close();
		st.close();
		con.close();
		}
	catch(Exception e)
	{
		e.printStackTrace();
	}
    	return number;
    }
}


