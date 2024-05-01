package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class ProductPageInfoTest extends BaseTest {

	//AAA -->
	//TC - 1 Tc have only one hard assertion but it can have multiple sof assertion
	 @BeforeClass
	    public void infoPageSetup() {
	    	accPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));	
	    }
	    	 
		@DataProvider
		public Object[][] getProductSearchData() {
			return new Object[][] {
				{"macbook","MacBook Pro"},
				{"imac","iMac"},
				{"samsung","Samsung SyncMaster 941BW"},
				{"samsung","Samsung Galaxy Tab 10.1"},
			};
		}
		
		

	 
	@Test(dataProvider = "getProductSearchData")
	public void productHeaderTest(String searchKey, String productName) {
		searchResultsPage = accPage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(productName);
		Assert.assertEquals(productInfoPage.getProductHeader(),productName);
	}
	
	@DataProvider
	public Object[][] getProductImagesData() {
		return new Object[][] {
			{"macbook","MacBook Pro", 4},
			{"imac","iMac", 3},
			{"samsung","Samsung SyncMaster 941BW", 1},
			{"samsung","Samsung Galaxy Tab 10.1", 7},
		};
	}
	
	@DataProvider
	public Object[][] getProductImagesDataFromExcel() {
	return ExcelUtil.getTestData(AppConstants.PRODUCT_SHEET_NAME);
	}
	
	
	
	@Test (dataProvider = "getProductImagesDataFromExcel")
	public void productImagesCountTest(String searchKey, String producteName, String imagesCount) {
		searchResultsPage = accPage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(producteName);
		Assert.assertEquals(productInfoPage.getProductImagesCount(), Integer.parseInt(imagesCount));
	}
	
//	@Test
//	public void productImagesCountTest1() {
//		searchResultsPage = accPage.doSearch("imac");
//		productInfoPage = searchResultsPage.selectProduct("iMac");
//		Assert.assertEquals(productInfoPage.getProductImagesCount(),3);
//	}
	
	@Test
	public void productInfoTest() {
		searchResultsPage = accPage.doSearch("macBook Pro");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		Map<String, String> productActDetailsMap = productInfoPage.getProductDetailsMap();
		softAssert.assertEquals(productActDetailsMap.get("Brand"),"Apple");
		softAssert.assertEquals(productActDetailsMap.get("Product Code"),"Product 18");
		softAssert.assertEquals(productActDetailsMap.get("Availability"),"In Stock");
		softAssert.assertEquals(productActDetailsMap.get("productprice"),"$2,000.00");
		softAssert.assertEquals(productActDetailsMap.get("extaxprice"),"$2,000.00");
		softAssert.assertAll();
	}
}
