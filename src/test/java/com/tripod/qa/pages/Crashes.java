package com.tripod.qa.pages;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;

import com.tripod.qa.base.BaseTest;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Crashes extends BaseTest{
	
	private Object tokenValue;
	private Object crashId;
	private Object issuNo;
	private Object issueId;
	private Object environmentKey;
	private Object id;
	private Object summary;
	private Object message;
	private Object logger;
	private Object level;
	private Object appVersion;
	private Object appName;
	private Object locale;
	private Object os;
	private Object osVersion;
	private Object brand;
	private Object board;
	private Object device;
	private Object host;
	private Object model;
	private Object product;
	private Object type;
	private Object display;
	private Object hardware;
	private Object manufacturer;
	private Object cpu;
	private Object totalInternalMemory;
	private Object availableInternalMemory;
	private Object jiraTicketId;
	private Object crashStackTrace;
	private Object timesOccured;

	@SuppressWarnings("unchecked")
	
	public void loginUser() {
		RestAssured.config=RestAssured.config().sslConfig(SSLConfig.sslConfig().allowAllHostnames());
		RestAssured.useRelaxedHTTPSValidation();
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		JSONObject json = new JSONObject();
		json.put("username", prop.getProperty("username"));
		json.put("password", prop.getProperty("password"));
		request.body(json.toJSONString());
		Response response = request.post(prop.getProperty("demoUrl")+"users/token");
		int code = response.getStatusCode();
		String token = response.getBody().asString();
		JSONParser parser = new JSONParser();
		JSONObject newJObject = null;
		try {
			newJObject = (JSONObject) parser.parse(token);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		tokenValue = newJObject.get("token");
		Assert.assertEquals(code, 200);
	}
	
	@SuppressWarnings("unchecked")
	public void createCrashInfo() {
		RequestSpecification reqspec=RestAssured.given();
		reqspec.header("Content-Type", "application/json");
		reqspec.header("access_token", tokenValue);

		JSONObject json = new JSONObject();
		json.put("logger", "dpi");
		json.put("level", "0");
		json.put("environmentKey", prop.getProperty("environmentKey"));
		json.put("appVersion", "1.0");
		json.put("appName", prop.get("appName"));
		json.put("appVersionCode", "1");
		json.put("locale", "en_US");
		json.put("packageName", prop.getProperty("packageName"));
		json.put("deviceModel", "XT1092");
		json.put("os", "ANDROID");
		json.put("osVersion", "8.0.0");
		json.put("brand", "samsung");
		json.put("board", "universal7885");
		json.put("device", "jackpot2lte");
		json.put("host", "SWDG5212");
		json.put("buildId", "null");
		json.put("model", "SM-A730F");
		json.put("product", "jackpot2ltexx");
		json.put("type", "user");
		json.put("display", "R16NW.A730FXXU2BRG6");
		json.put("hardware", "samsungexynos7885");
		json.put("manufacturer", "samsung");
		json.put("cpu", "[arm64-v8a, armeabi-v7a, armeabi]");
		json.put("totalInternalMemory", "57224314880");
		json.put("availableInternalMemory", "53577416704");
		json.put("crashStackTrace", "java.lang.NullPointerException Attempt to invoke virtual method 'java.lang.String com.gendevs.tripodsample.db.Comment.toString()' on a null object ref");
		json.put("carrierName", "");
		json.put("networkType", "WIFI");
		
		reqspec.body(json.toJSONString());
		Response response =reqspec.post(prop.getProperty("demoUrl")+"crash-infos");
		int code = response.getStatusCode();
		System.out.println(response.getBody().asString());
		String data= response.getBody().asString();
		JSONParser parser = new JSONParser();
		JSONObject newJObject = null;
		try {
			newJObject = (JSONObject) parser.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		id = newJObject.get("id");
		crashId=newJObject.get("crashId");
		issuNo = newJObject.get("issueNo");
		issueId = newJObject.get("issueId");
		jiraTicketId = newJObject.get("jiraTicketId");
		environmentKey = newJObject.get("environmentKey");
		appVersion = newJObject.get("appVersion");
		appName = newJObject.get("appName");
		id = newJObject.get("id");
		summary = newJObject.get("summary");
		message = newJObject.get("message");
		logger = newJObject.get("logger");
		level = newJObject.get("level");
		appVersion = newJObject.get("appVersion");
		appName = newJObject.get("appName");
		crashStackTrace = newJObject.get("crashStackTrace");
		locale = newJObject.get("locale");
		os = newJObject.get("os");
		osVersion = newJObject.get("osVersion");
		brand = newJObject.get("brand");
		board = newJObject.get("board");
		device = newJObject.get("device");
		host = newJObject.get("host");
		model = newJObject.get("model");
		product = newJObject.get("product");
		type = newJObject.get("type");
		display = newJObject.get("display");
		hardware = newJObject.get("hardware");
		manufacturer = newJObject.get("manufacturer");
		cpu = newJObject.get("cpu");
		totalInternalMemory = newJObject.get("totalInternalMemory");
		availableInternalMemory = newJObject.get("availableInternalMemory");
		message = newJObject.get("message");
		timesOccured = newJObject.get("timesOccured");
		Assert.assertEquals(code, 201);
	}
	public void getCrashStackTrace() {
		RequestSpecification reqspec = RestAssured.given();
		reqspec.header("Content-Type", "application/json");
		reqspec.header("access_token", tokenValue);
		Response response = reqspec.get(prop.getProperty("demoUrl")+"crash-infos/"+id+"/crash");
		int code = response.getStatusCode();
		String data= response.getBody().asString();
		System.out.println(data);
		JSONParser parser = new JSONParser();
		JSONObject newJObject = null;
		try {
			newJObject = (JSONObject) parser.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(crashStackTrace, newJObject.get("stackTrace"));
		Assert.assertEquals(jiraTicketId, newJObject.get("jiraTicketId"));
		Assert.assertEquals(code, 200);
	}
	public void getCrash() {
		RequestSpecification reqspec = RestAssured.given();
		reqspec.header("Content-Type", "application/json");
		reqspec.header("access_token", tokenValue);
		Response response = reqspec.get(prop.getProperty("demoUrl")+"crashes/"+crashId);
		int code = response.getStatusCode();
		String data= response.getBody().asString();
		System.out.println(data);
		JSONParser parser = new JSONParser();
		JSONObject newJObject = null;
		try {
			newJObject = (JSONObject) parser.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(issueId, newJObject.get("issueId"));
		Assert.assertEquals(environmentKey, newJObject.get("environmentKey"));
		Assert.assertEquals(summary, newJObject.get("summary"));
		Assert.assertEquals(message, newJObject.get("message"));
		Assert.assertEquals(code, 200);
	}
}
