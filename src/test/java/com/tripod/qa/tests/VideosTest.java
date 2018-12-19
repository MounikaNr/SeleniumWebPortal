package com.tripod.qa.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.tripod.qa.pages.Videos;

public class VideosTest {
	
	private Videos videos;

	@BeforeTest
	public void initialize() {
		videos = new Videos();
	}
	@Test(priority = 1)
	public void verifyVideo(){
		videos.loginUser();
		videos.getVideoUrl();
		videos.uploadVideo();
		videos.getVideo();
	}
}
