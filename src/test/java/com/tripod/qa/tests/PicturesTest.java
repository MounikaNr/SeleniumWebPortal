package com.tripod.qa.tests;


import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.tripod.qa.pages.Pictures;

public class PicturesTest {
	private Pictures pictures;

	@BeforeTest
	public void initialize() {
		pictures = new Pictures();
	}
	@Test(priority = 1)
	public void verifyPicture(){
		pictures.loginUser();
		pictures.getPictureUrl();
		pictures.uploadPicture();
		pictures.getPicture();
	}
}

