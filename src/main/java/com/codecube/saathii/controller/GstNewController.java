package com.codecube.saathii.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.codecube.saathii.entity.GstToken;
import com.codecube.saathii.service.ViewInformationService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@RestController
@RequestMapping("/satthi")
@CrossOrigin
public class GstNewController {
	 	private static final String BASE_URL = "http://devapi.gstsystem.co.in/taxpayerapi/v0.2/authenticate";
	 	private String clientId ="l7xx5edefdd923ad438eb5b332a73269f812";
	    private String clientSecret="383dc1f4835f43ad80f80f6cf284cd7b";
	    private String state = "33";
	    private String ipAddr ="192.168.0.5";
	    private String txnno="LAPN24235325555";    
	    private String userid="balaji.tn.1";
	    //private String password;
	@Autowired
	ViewInformationService viewInformationService;
	

	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "clientid", value = "Client Identification for authentication", required = true, dataType = "string", paramType = "header"),
		@ApiImplicitParam(name = "client-sercret", value = "Secret code for the API client", required = true, dataType = "string", paramType = "header"),
		@ApiImplicitParam(name = "state-cd", value = "State code to which the Tax Payer belongs to", required = true, dataType = "string", paramType = "header"),
		@ApiImplicitParam(name = "ip-usr", value = "Public IP Address of end user", required = true, dataType = "string", paramType = "header"),
		@ApiImplicitParam(name = "txn", value = "Unique Transaction ID associated with the request", required = true, dataType = "string", paramType = "header"),
		@ApiImplicitParam(name = "UserId", value = "User ID of Tax Payer", required = true, dataType = "string", paramType = "header"),
		@ApiImplicitParam(name = "password", value = "password Of Tax Payer", required = true, dataType = "string", paramType = "header")
	})
	@CrossOrigin
	@RequestMapping(value ="/gmail", method= RequestMethod.POST, headers = "Content-type=application/json")
//	public ResponseEntity<?> loadinformations(@RequestBody GstToken gstToken) throws IOException
	
	
	/*sending body */
	
	public String myrequest(@RequestBody GstToken gsttoken,@RequestParam("username") String username, @RequestParam("password") String password) throws Exception
	{
		String result = null;
		try {
		 authenticate(username, password);
		 URL url = new URL(BASE_URL);
		 System.out.println("Before Creating creating HTTP connection");
		 HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		 System.out.println("After creating HTTP connection");
		 /* for method */
		System.out.println("After Connection .......");
		byte[] out = "{\"action\":\"AUTHTOKEN\",\"username\":\"balaji.tn.1\",\"otp\":\"JrRsYBBmQYE9XdXNh+2w6Q==\",\"app_key\":\"R5UxqYG0yAmnE5mGGTliRn2RvmX+AJWAY1fjDfvnnTD/p2GEHQKTPqlFV/qPr1Rp4zs08Pk/AwzKPnzALovqJKSWYqpz4zNua+L5iRz7k/5IY87HuHyB9DDcXPwYBMxjI7Sf0+vOAUDTLrF7l8IAN3J0dTnfTi85TGWZTSH0cqOyR82FEe7smWkBuTmitoE3Q43QJmK5X7musHHFqsGVkNRxuYpz6/f9bbY5dWDlnxl8JXa5s0zhITU0Z/LIZmAo+rfQBlgLnnXs2IftPnkW6OOO9MTfkW3dOGqcgS6pDFqav0xJeWgXcdJ6c2KCRf24xtc7MraTNz9sZqr/R9DIhw==\"}" .getBytes(StandardCharsets.UTF_8);
		conn.setRequestMethod("POST");
		/* for header */
		System.out.println(clientId);
		conn.setRequestProperty("clientid", clientId);
		conn.setRequestProperty("Content-Type", javax.ws.rs.core.MediaType.APPLICATION_JSON);
		conn.setRequestProperty("client-secret", clientSecret);
		conn.setRequestProperty("ip-usr", ipAddr);
		conn.setRequestProperty("state-cd", state);
		conn.setRequestProperty("txn", txnno);
		conn.setRequestProperty("UserId", userid);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		System.out.println("connected........");
		conn.connect();
		System.out.println("connected........");
		OutputStream os = conn.getOutputStream();
//OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
		os.write(out);
		os.flush();
		os.close();
		System.out.println("RESPONSE CODE:--- "+conn.getResponseCode()+  
				"\nCONTENT LENGHT:---"+conn.getContentLength()+
				"\nERROR STREAM:---"+conn.getErrorStream()+
				"\nclientid:---"+conn.getRequestProperty("clientid")+
				"\ncontent-type:---"+conn.getHeaderField("Content-Type")+
				"\nclient-sec:---"+conn.getRequestProperty("client-secret")+
				"\nIpadd:---"+conn.getRequestProperty("ip-usr")+
				"\nstate:---"+conn.getRequestProperty("state-cd")+
				"\nuserid:---"+conn.getRequestProperty("userId")+
				"\ntxn:---"+conn.getRequestProperty("txn")+
				"\nREQUEST METHOD:---"+conn.getRequestMethod()+
				"\nFIELDS:---"+conn.getHeaderFields()+
				"\nResponse Message:---"+conn.getResponseMessage());		
		BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
		ByteArrayOutputStream buf = new ByteArrayOutputStream();
		int result2 = bis.read();
		while(result2 != -1) {
		    buf.write((byte) result2);
		    result2 = bis.read();
		}
		result = buf.toString();
		System.out.println(result);
		
		}
		catch (IOException e) {
			System.err.println("Error creating HTTP connection");
	        e.printStackTrace();
	        throw e;
		}
		return result;
		
	}
	private void authenticate(String username, String password) throws Exception
	{
		
	}
	
    }

	


