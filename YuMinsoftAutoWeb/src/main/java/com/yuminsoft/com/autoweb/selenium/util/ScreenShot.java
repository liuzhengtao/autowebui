package com.yuminsoft.com.autoweb.selenium.util;

import com.google.common.io.Files;
import com.yuminsoft.com.autoweb.common.util.DateUtil;
import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.util.Date;

public class ScreenShot {
	public WebDriver driver;
	private String screenName;
	Logger log = Logger.getLogger(this.getClass());

	public void setscreenName(String screenName)
	{
		this.screenName=screenName;
	}
	public ScreenShot(WebDriver driver)
	{

		this.driver=driver;
	}
	public void takeScreenshot(String screenPath) {
		File scrFile=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			Files.copy(scrFile, new File(screenPath));
			log.error("错误截图："+screenPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public  void takeScreenshot() {
		String screenName =this.screenName+ DateUtil.getDateStr(new Date(),"yyyy-MM-dd") +".jpg";
		File dir = new File("test-output\\snapshot");
		if (!dir.exists())
		{dir.mkdirs();}
		String screenPath = dir.getAbsolutePath() + "\\" + screenName;
		this.takeScreenshot(screenPath);
	}

}
