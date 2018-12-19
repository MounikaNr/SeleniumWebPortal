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

public class Pictures extends BaseTest {
	
	
	private Object responseJsonObject;
	private Object tokenValue;
	private Object imageId;
	private Object imageUrl;
	private Object environmentKey;
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
	private Object totalInternalMemory;
	private Object availableInternalMemory;

	@SuppressWarnings("unchecked")
	public void loginUser() {
		RestAssured.config = RestAssured.config().sslConfig(SSLConfig.sslConfig().allowAllHostnames());
		RestAssured.useRelaxedHTTPSValidation();
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		JSONObject json = new JSONObject();
		json.put("username", prop.getProperty("username"));
		json.put("password", prop.getProperty("password"));
		request.body(json.toJSONString());
		Response response = request.post(prop.getProperty("demoUrl") + "users/token");
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

	public void getPictureUrl() {
		Response response = RestAssured.given()
				.config(RestAssured.config().encoderConfig(
						EncoderConfig.encoderConfig().encodeContentTypeAs("multipart/form-data", ContentType.TEXT)))
				.header("access_token", tokenValue)
				.multiPart(new File(System.getProperty("user.dir") + "/src/main/resources/image.png"))
				.formParam("appKey", prop.getProperty("environmentKey")).when()
				.post(prop.getProperty("demoUrl") + "file-upload/crash/754469688e5a4fd?type=imageCrash").then()
				.statusCode(201).extract().response();

		String data = response.asString();
		JSONParser parser = new JSONParser();
		JSONObject newJObject = null;
		try {
			newJObject = (JSONObject) parser.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		imageUrl = newJObject.get("url");
		System.out.println(imageUrl);
	}

	@SuppressWarnings("unchecked")
	public void uploadPicture() {
		RequestSpecification reqspec = RestAssured.given();
		reqspec.header("Content-Type", "application/json");
		reqspec.header("access_token", tokenValue);

		JSONObject json = new JSONObject();
		json.put("environmentKey", prop.getProperty("environmentKey"));
		json.put("imageUrl", imageUrl);
		json.put("summary", "API Testing");
		json.put("message", "API Testing");
		json.put("logger", "hudsoncm");
		json.put("level", 0);
		json.put("appVersion", "1.0");
		json.put("appName", prop.getProperty("appName"));
		json.put("appVersionCode", "1");
		json.put("locale", "en_US");
		json.put("packageName", prop.getProperty("packageName"));
		json.put("deviceModel", "Moto G (5) Plus");
		json.put("os", "ANDROID");
		json.put("osVersion", "7.0");
		json.put("brand", "motorola");
		json.put("board", "msm8953");
		json.put("device", "potter_n");
		json.put("host", "ilclbld30");
		json.put("buildId", null);
		json.put("model", "Moto G (5) Plus");
		json.put("product", "potter_n");
		json.put("type", "user");
		json.put("display", "NPNS25.137-92-14");
		json.put("hardware", "qcom");
		json.put("manufacturer", "motorola");
		json.put("cpu", "[armeabi-v7a, armeabi]");
		json.put("totalInternalMemory", "26401026048");
		json.put("availableInternalMemory", "21057122304");
		json.put("carrierName", null);
		json.put("networkType", null);
		reqspec.body(json.toJSONString());
		Response response = reqspec.post(prop.getProperty("demoUrl") + "images");
		int code = response.getStatusCode();
		System.out.println(code);
		System.out.println(response.getBody().asString());
		String data = response.getBody().asString();
		JSONParser parser = new JSONParser();
		JSONObject newJObject = null;
		try {
			newJObject = (JSONObject) parser.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.responseJsonObject = newJObject;

		imageId = newJObject.get("id");
		environmentKey = newJObject.get("environmentKey");
		imageUrl = newJObject.get("imageUrl");
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
		totalInternalMemory = newJObject.get("totalInternalMemory");
		availableInternalMemory = newJObject.get("availableInternalMemory");
		message = newJObject.get("message");
		System.out.println(imageId);
		Assert.assertEquals(code, 201);
	}

	public void getPicture() {
		RequestSpecification reqspec = RestAssured.given();
		reqspec.header("Content-Type", "application/json");
		reqspec.header("access_token", tokenValue);
		Response response = reqspec.get(prop.getProperty("demoUrl") + "images/" + imageId);
		int code = response.getStatusCode();
		System.out.println(response.asString());
		String data = response.getBody().asString();
		JSONParser parser = new JSONParser();
		JSONObject newJObject = null;
		this.responseJsonObject.equals(newJObject);
		
	}
}
