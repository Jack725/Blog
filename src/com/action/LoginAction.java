package com.action;
import com.bean.User;

import org.apache.struts2.ServletActionContext;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;



public class LoginAction{
	public LoginAction(){
		System.out.println("login Action");
	}
	public String login() throws IOException{
		System.out.println("enter login~~~~");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = request.getSession();
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		//response.getWriter().write("nihao");
		
		User user = new User();
		user.setLoginName("jack");
		
		Map<String, Object> data = new HashMap<String,Object>();
		data.put("name", "hello");
		JSONObject js = JSONObject.fromObject(data);
		try{
			response.getWriter().write(js.toString());
			System.out.println(js.toString());
		}catch(IOException e){
			e.printStackTrace();
		}
		return null;
	}
}
