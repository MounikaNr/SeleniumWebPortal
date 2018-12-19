package com.tripod.qa.pages;

import java.io.File;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import com.tripod.qa.base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Videos extends BaseTest{
	
	private Object tokenValue;
	private Object videoUrl;
	private Object videoId;
	private Object environmentKey;
	private Object videoThumbnailUrl;
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
	public void getVideoUrl() {
		Response response = RestAssured.given().config(RestAssured.config().encoderConfig(EncoderConfig.encoderConfig().encodeContentTypeAs("multipart/form-data", ContentType.TEXT))).
				header("access_token",tokenValue).
	        	multiPart(new File(System.getProperty("user.dir")+"/src/main/resources/video.mp4")).
	            formParam("appKey", prop.getProperty("environmentKey")).        	
	        when().
	        	post(prop.getProperty("demoUrl")+"file-upload/crash/754469688e5a4fd?type=videoCrash").
			then().
				statusCode(201).
			 extract().
		        response();
			String data = response.asString();
			JSONParser parser = new JSONParser();
			JSONObject newJObject = null;
			try {
				newJObject = (JSONObject) parser.parse(data);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			videoUrl = newJObject.get("url");
	}
	@SuppressWarnings("unchecked")
	public void uploadVideo() {
		RequestSpecification request=RestAssured.given();
		request.header("Content-Type", "application/json");
		request.header("access_token", tokenValue);
		
		JSONObject json = new JSONObject();
		json.put("environmentKey", prop.getProperty("environmentKey"));
		json.put("videoThumbnailUrl", prop.getProperty("videoThumbUrl") );
		json.put("videoUrl", videoUrl);
		json.put("summary", "Testing video");
		json.put("message", "Testing video recording using tripod");
		json.put("logger", "hudsoncm");
		json.put("level", "0");
		json.put("appVersion", "1.1");
		json.put("appName", prop.getProperty("appName"));
		json.put("appVersionCode", "2");
		json.put("locale", "en_IN");
		json.put("packageName", prop.getProperty("packageName"));
		json.put("deviceModel", "XT1092");
		json.put("os", "ANDROID");
		json.put("osVersion", "5.1");
		json.put("brand", "motorola");
		json.put("board", "MSM8974");
		json.put("device", "victara");
		json.put("host", "ilclbld28");
		json.put("model", "XT1092");
		json.put("product", "victara_reteu");
		json.put("type", "user");
		json.put("display", "LPE23.32-25.1");
		json.put("hardware", "qcom");
		json.put("manufacturer", "motorola");
		json.put("cpu", "[armeabi-v7a, armeabi]");
		json.put("totalInternalMemory", "26602225664");
		json.put("availableInternalMemory", "18822848512");
		json.put("message", "Testing Video");
		
		request.body(json.toJSONString());
		Response response =request.post(prop.getProperty("demoUrl")+"videos");
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
		
		videoId=newJObject.get("id");
		environmentKey = newJObject.get("environmentKey");
		videoThumbnailUrl = newJObject.get("videoThumbnailUrl");
		videoUrl = newJObject.get("videoUrl");
		summary = newJObject.get("summary");
		message = newJObject.get("message");
		logger = newJObject.get("logger");
		level = newJObject.get("level");
		appVersion = newJObject.get("appVersion");
		appName = newJObject.get("appName");
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
		Assert.assertEquals(code, 201);
	}
	public void getVideo() {
		RequestSpecification reqspec = RestAssured.given();
		reqspec.header("Content-Type", "application/json");
		reqspec.header("access_token", tokenValue);
		Response response = reqspec.get(prop.getProperty("demoUrl")+"videos/"+videoId);
		int code = response.getStatusCode();
		System.out.println(response.asString());
		String data= response.getBody().asString();
		JSONParser parser = new JSONParser();
		JSONObject newJObject = null;
		try {
			newJObject = (JSONObject) parser.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Assert.assertEquals(environmentKey, newJObject.get("environmentKey"));
		Assert.assertEquals(videoThumbnailUrl, newJObject.get("videoThumbnailUrl"));
		Assert.assertEquals(videoUrl, newJObject.get("videoUrl"));
		Assert.assertEquals(summary, newJObject.get("summary"));
		Assert.assertEquals(message, newJObject.get("message"));
		Assert.assertEquals(logger, newJObject.get("logger"));
		Assert.assertEquals(level, newJObject.get("level"));
		Assert.assertEquals(appVersion, newJObject.get("appVersion"));
		Assert.assertEquals(appName, newJObject.get("appName"));
		Assert.assertEquals(locale, newJObject.get("locale"));
		Assert.assertEquals(os, newJObject.get("os"));
		Assert.assertEquals(osVersion, newJObject.get("osVersion"));
		Assert.assertEquals(brand, newJObject.get("brand"));
		Assert.assertEquals(board, newJObject.get("board"));
		Assert.assertEquals(device, newJObject.get("device"));
		Assert.assertEquals(host, newJObject.get("host"));
		Assert.assertEquals(model, newJObject.get("model"));
		Assert.assertEquals(type, newJObject.get("type"));
		Assert.assertEquals(display, newJObject.get("display"));
		Assert.assertEquals(hardware, newJObject.get("hardware"));
		Assert.assertEquals(product, newJObject.get("product"));
		Assert.assertEquals(manufacturer, newJObject.get("manufacturer"));
		Assert.assertEquals(totalInternalMemory, newJObject.get("totalInternalMemory"));
		Assert.assertEquals(availableInternalMemory, newJObject.get("availableInternalMemory"));
		Assert.assertEquals(message, newJObject.get("message"));	
		Assert.assertEquals(code, 200);	
	}
}
