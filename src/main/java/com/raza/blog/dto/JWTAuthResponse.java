package com.raza.blog.dto;

public class JWTAuthResponse {
	private String accessToken;
	private String tokentype = "Bearer ";

	public JWTAuthResponse() {
	}

	public JWTAuthResponse(String accessToken, String tokentype) {
		super();
		this.accessToken = accessToken;
		this.tokentype = tokentype;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getTokentype() {
		return tokentype;
	}

	public void setTokentype(String tokentype) {
		this.tokentype = tokentype;
	}

}
