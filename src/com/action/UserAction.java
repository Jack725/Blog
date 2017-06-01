package com.action;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.bean.User;
import com.dao.UserDAO;

import net.sf.json.JSONObject;

/**
 * by Bwang on 2017/5/31
 * @author asus
 *
 */
public class UserAction {
	private int userID;
	private String userName;
	private String loginName;
	private String password;
	private int type;
	private String userImage;
	private String regTime;
	private String email;
	private String captcha;
	private int state;
	
	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getUserImage() {
		return userImage;
	}
	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}
	public String getRegTime() {
		return regTime;
	}
	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCaptcha() {
		return captcha;
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	private HttpServletRequest getRequest(){
		return ServletActionContext.getRequest();
	}
	private HttpServletResponse getResponse(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		return response;
		
	}
	private HttpSession getSession(){
		return getRequest().getSession();
	}
	
	/**
	 * by Bwang on 2017/5/31
	 * �û�ע��
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String register(){
		HttpServletResponse response = getResponse();
		JSONObject jo;
		UserDAO userDAO = new UserDAO();
		Map data = new HashMap();
		
		//���жϣ�����User����
		label:{
			if(loginName != null && userName != null && password != null){
				//����User����
				User user = new User(loginName, userName, password);
				
				//�ж��Ƿ�����ͬloginName���û�
				List<User> users = userDAO.getByLoginName(loginName);
				//�ж�users��С�Ƿ����0�ж��Ƿ��Ѿ�����ͬ�û�
				if(users.size() > 0){
					data.put("result","fail");
					data.put("message","��¼���ظ�");
					break label;
				}
				//�ж��Ƿ�����ͬuserName���û�
				users = userDAO.getByUserName(userName);
				if(users.size() > 0){
					data.put("result", "fail");
					data.put("message", "�ǳ��ظ�");
					break label;
				}
				//���û���ظ���¼���������ݿ�
				userDAO.add(user);
			}
		}
		
		
		jo = JSONObject.fromObject(data);
		try{
			response.getWriter().write(jo.toString());
		}catch(IOException e){
			e.printStackTrace();
		}
		return null;
		
	}
	public String login(){
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		JSONObject jo;
		if(loginName.equals(password)){
			Map data = new HashMap();
			data.put("code","success");
			jo = JSONObject.fromObject(data);
		}else{
			Map data = new HashMap();
			data.put("code", "fail");
			jo = JSONObject.fromObject(data);
		}
		try{
			response.getWriter().write(jo.toString());
		}catch(IOException e){
			e.printStackTrace();
		}
		return null;
		
	}
}
