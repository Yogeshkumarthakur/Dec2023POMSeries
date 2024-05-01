package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.qa.opencart.logger.Log;

public class OptionsManager {

	private Properties prop;
	
	private ChromeOptions co;
	private FirefoxOptions fo;
	private EdgeOptions eo;
	
	
	public OptionsManager(Properties prop) {
		this.prop = prop;
		
	}
	
	//for chrome
		public ChromeOptions getChromeOptions() {
			co = new ChromeOptions();
			if(Boolean.parseBoolean(prop.getProperty("headless").trim())){
				//System.out.println("running chrome in headless mode");
				Log.info("running chrome in headless mode");
				co.addArguments("--headless");			
			}
			if(Boolean.parseBoolean(prop.getProperty("incognito").trim())){
				Log.info("running chrome in incognito mode");
				co.addArguments("--incognito");			
			}
			return co;
			}
		
		
		
	//for edge
		public EdgeOptions getEdgeOptions() {
			eo = new EdgeOptions();
			if(Boolean.parseBoolean(prop.getProperty("headless").trim())){
				Log.info("running edge in headless mode");

				eo.addArguments("--headless");			
			}
			if(Boolean.parseBoolean(prop.getProperty("incognito").trim())){
				eo.addArguments("--inprivate");			
			}
			return eo;
			}
		
		
		
	//for firefox
		public FirefoxOptions getFirefoxOptions() {
			fo = new FirefoxOptions();
			if(Boolean.parseBoolean(prop.getProperty("headless").trim())){
				System.out.println("running firefox in headless mode");

				fo.addArguments("--headless");			
			}
			if(Boolean.parseBoolean(prop.getProperty("incognito").trim())){
				fo.addArguments("--incognito");			
			}
			return fo;
			}
		
		
	}
	
	
	
	
	
	
	
	

