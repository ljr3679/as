<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%><%
	String result ="성적";
	String num = request.getParameter("num");
	    if(Integer.parseInt(num) >= 90){
    		result = "최우수";
    	}else if(Integer.parseInt(num) >= 80){
    		result = "우수";
    	}else{
    		result = "보통보통";
    	}
%><%= result %>