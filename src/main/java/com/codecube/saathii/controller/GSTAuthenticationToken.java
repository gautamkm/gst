package com.codecube.saathii.controller;

import java.util.Objects;
import java.util.UUID;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

import io.swagger.annotations.ApiOperation;

public class GSTAuthenticationToken {
	 private static final String BASE_URL = "http://devapi.gstsystem.co.in";
	 private static final String AUTH_PATH = "/taxpayerapi/v0.2/authenticate";
	 private static final String AUTHTOKEN = "AUTHTOKEN";
	 private static final String OTPREQUEST = "OTPREQUEST";
	 private static final String REFRESHTOKEN = "REFRESHTOKEN";
	 private String userName;
	 private String appKey;
	 private String clientId;
	 private String clientSecret;
	 private String state;
	 private String ipAddr;
	 private String txn;
	 private String otp;
	 private static final String APPLICATION_JSON = "application/json";
	 @ApiOperation(value = "Request forAuthorization token")
	 @RequestMapping(value = "/authentication", method= RequestMethod.POST, produces = "application/json")
	 public void authTokenRequest() throws Exception {
		 JSONObject authTokenReq = new JSONObject();
	        authTokenReq.put("action", AUTHTOKEN);
	        authTokenReq.put("app_key", appKey);
	        authTokenReq.put("username", userName);
	        authTokenReq.put("otp", otp);
	        HttpResponse<JsonNode> authTokenResp = Unirest.post("http://devapi.gstsystem.co.in/taxpayerapi/v0.2/authenticate")
	        		.header("Content-Type",APPLICATION_JSON)
	                .header("state-cd", state)
	                .header("clientid", clientId)
	                .header("client-secret", clientSecret)
	                .header("ip-usr", ipAddr)
	                .header("app_key", appKey)
	                .header("txn", txn)
	                .body(new JsonNode(authTokenReq.toString()))
	                .asJson();
	 }
}
