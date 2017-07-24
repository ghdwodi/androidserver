package com.hb.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hb.am.List;
import com.hb.am.ListXml;
import com.hb.am.VO;

/**
 * Servlet implementation class MyController
 */
@WebServlet("/MyController")
public class MyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String type = request.getParameter("type");
		if(type==null){
			List list = new List();
			ArrayList<VO> voList = list.exec();
			out.println("<ul>");
			for (VO k : voList) {
				out.println("<li>");
				out.print(k.getIdx()+"&nbsp;&nbsp;"+k.getId()+"&nbsp;&nbsp;"+k.getPwd()+"&nbsp;&nbsp;"+k.getName()+"&nbsp;&nbsp;"+k.getAge()+"&nbsp;&nbsp;"+k.getAddr());
				out.println("</li>");
			}
			out.println("</ul>");
		}else if(type.equals("xml")){
			ListXml list = new ListXml();
			ArrayList<VO> voList = list.exec();
			out.println("<?xml version='1.0' encoding='UTF-8'?>");
			out.println("<persons>");
			for (VO k : voList) {
				out.println("<person idx=\""+k.getIdx()+"\" id=\""+k.getId()+"\" pwd=\""+k.getPwd()+"\" name=\""+k.getName()+"\" age=\""+k.getAge()+"\" addr=\""+k.getAddr()+"\">");
				out.println("<p>안녕</p>");
				out.println("</person>");
			}
			out.println("</persons>");
		}
	}

}
