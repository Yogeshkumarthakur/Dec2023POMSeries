package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.CSVUtil;
import com.qa.opencart.utils.ExcelUtil;
import com.qa.opencart.utils.StringUtils;

import io.qameta.allure.Step;

public class RegPageTest extends BaseTest {

	@BeforeClass
	public void regSetup() {
		registrationPage = loginPage.navigateToRegisterPage();
	}

	@DataProvider
	public Object[][] getUserRegTestData() {
		return new Object[][] { { "gaurav", "gupta", "9879879879", "g2324b@123", "yes" },
				{ "shilpa", "gautam", "9779879879", "shilpa@123", "no" },
				{ "om", "sharma", "9679879879", "gb@123", "yes" } };
	}

	// from excel sheet
	@DataProvider
	public Object[][] getUserRegTestDataFromExcel() {
		return ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
	}

	// from csv sheet
	@DataProvider
	public Object[][] getUserRegTestDataFromCSV() {
		return CSVUtil.csvData(AppConstants.REGISTER_SHEET_NAME);

	}

	
	@Step("checking user registration")
	@Test(dataProvider = "getUserRegTestData")
	public void userRegTest(String firstName, String lastName, String telephone, String password, String subscribe) {
		Assert.assertTrue(registrationPage.userRegister(firstName, lastName, StringUtils.getRandomEmailId(), telephone,
				password, subscribe));

	}

}
