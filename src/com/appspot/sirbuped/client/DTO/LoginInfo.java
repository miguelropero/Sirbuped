package com.appspot.sirbuped.client.DTO;

import java.io.Serializable;

public class LoginInfo implements Serializable 
{

	private static final long serialVersionUID = 1L;
	private boolean estaLogueado = false;
	private String loginUrl;
	private String logoutUrl;
	private String emailAddress;
	private String nickname;
	private boolean estaRegistrado;
	private String keySesion;

	public boolean isLoggedIn() 
	{
	    return estaLogueado;
	}
	
	public void setLoggedIn(boolean loggedIn) 
	{
		this.estaLogueado = loggedIn;
	}
	
	public String getLoginUrl() 
	{
		return loginUrl;
	}
	
	public void setLoginUrl(String loginUrl) 
	{
		this.loginUrl = loginUrl;
	}
	
	public String getLogoutUrl() 
	{
		return logoutUrl;
	}
	
	public void setLogoutUrl(String logoutUrl) 
	{
	    this.logoutUrl = logoutUrl;
	}
	
	public String getEmailAddress() 
	{
	    return emailAddress;
	}
	
	public void setEmailAddress(String emailAddress) 
	{
	    this.emailAddress = emailAddress;
	}
	
	public String getNickname() 
	{
	    return nickname;
	}
	
	public void setNickname(String nickname) 
	{
	    this.nickname = nickname;
	}
	
	public boolean getRegistrado() 
	{
	    return estaRegistrado;
	}
	
	public void setRegistrado(boolean registrado) 
	{
		this.estaRegistrado = registrado;
	}

	public String getKeySesion() 
	{
		return keySesion;
	}

	public void setKeySesion(String key) 
	{
		this.keySesion = key;
	}
}
