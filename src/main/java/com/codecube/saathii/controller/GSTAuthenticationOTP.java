package com.codecube.saathii.controller;

import java.util.Objects;
import java.util.UUID;

import javax.ws.rs.Produces;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.squareup.okhttp.MediaType;
import com.sun.jersey.core.header.MediaTypes;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("http://devapi.gstsystem.co.in/taxpayerapi/v0.2")
public class GSTAuthenticationOTP {
	@Autowired
	GSTAuthenticationOTP viewInformationService;
	 private static final String AUTHTOKEN = "AUTHTOKEN";
	 private static final String OTPREQUEST = "OTPREQUEST";
	 private static final String REFRESHTOKEN = "REFRESHTOKEN";
	 private String userName;
	 private String appKey;
	 private String clientId;
	 private String clientSecret;
	 private String state;
	 private String ipAddr;
	 private String txn=UUID.randomUUID().toString();
	 private static final String APPLICATION_JSON = "application/json";
	@ApiOperation(value = "Request For OTP")
	@RequestMapping(value = "/authentication", method= RequestMethod.POST, produces = "application/json")
	public  boolean otpRequests(String action, String app_key, String username) throws Exception {
	        JSONObject otpRequest = new JSONObject();
	        otpRequest.put("action", OTPREQUEST);
	        otpRequest.put("app_key", appKey);
	        otpRequest.put("username", userName);
	        HttpResponse<JsonNode> otpResp = Unirest.post("http://devapi.gstsystem.co.in/taxpayerapi/v0.2/authenticate")
	                .header("clientid", clientId)
	                .header("state-cd", state)
	                .header("txn", txn)
	                .header("client-secret", clientSecret)
	                .header("ip-usr", ipAddr)
	                .header("Content-Type", APPLICATION_JSON)
	                .body(new JsonNode(otpRequest.toString()))
	                .asJson();
	        if (otpResp.getStatus() == 200) {
	            JSONObject object = otpResp.getBody().getObject();
	            return object.has("status_cd") && Objects.equals(object.getString("status_cd"), "1");
	        }
	        System.out.println(String.format("OTP Request : Status[%s] Response[%s]", otpResp.getStatus(), otpResp.getBody()));
	        return false;
	    }
}
