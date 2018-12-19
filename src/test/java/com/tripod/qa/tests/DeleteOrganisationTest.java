package com.tripod.qa.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.tripod.qa.pages.DeleteOrganisation;

public class DeleteOrganisationTest extends InitializeDriver{
	private DeleteOrganisation deleteOrganisationData;
	public DeleteOrganisationTest() {
		super();
	}
	@BeforeTest
	public void init() {
		deleteOrganisationData = new DeleteOrganisation();		
	}
	@Test
	public void deleteOrganisaton() {
		deleteOrganisationData.deleteOrganisationData();
	}
}
