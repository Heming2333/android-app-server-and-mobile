package com.example.myProdServ;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BackUpServlet
 */
public class BackUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BackUpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int ret ;  
		resp.setContentType("text/html;charset=utf-8");  
        req.setCharacterEncoding("utf-8");  
        resp.setCharacterEncoding("utf-8");  
        PrintWriter out = resp.getWriter();  
        //name,password,birthday,sex
        
        String action = req.getParameter("ACTION"); 
        if(action.equals("0"))//insert
        {
        	String Id = req.getParameter("ID"); 
            String Name = req.getParameter("NAME"); 
            String Number = req.getParameter("NUMBER");

            Boolean falg=DBUtil.insertContactDB(Id,Name,Number);
    		if(falg)
    			ret=0;
    		else ret=1;
    			out.print(ret);
    	        out.flush();  
    	        out.close();  
        }
        if(action.equals("1"))//getcount
        {
        	int count=DBUtil.getBackupCount();
        	out.print(count);
	        out.flush();  
	        out.close();  
        }
        if(action.equals("2"))//getname
        {
        	String Id = req.getParameter("ID"); 
        	String name=DBUtil.getBackupName(Id);
        	out.print(name);
	        out.flush();  
	        out.close();  
        }
        if(action.equals("3"))//getnum
        {
        	String Id = req.getParameter("ID"); 
        	String number=DBUtil.getBackupNumber(Id);
        	out.print(number);
	        out.flush();  
	        out.close();  
        }
        if(action.equals("3"))
        {
        	DBUtil.sqlTruncateContact();
        	out.print(true);
	        out.flush();  
	        out.close();  
        }
 	
        		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
