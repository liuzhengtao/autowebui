package com.yuminsoft.com.autoweb.selenium.util;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.google.gson.Gson;
import com.yuminsoft.com.autoweb.base.exception.BasicException;
import com.yuminsoft.com.autoweb.common.util.TestProperities;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.io.Files;
import com.yuminsoft.com.autoweb.common.util.StringUtil;
import com.yuminsoft.com.autoweb.selenium.model.SlnmLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * 
 * FileName:    SeleniumBaseCase.java  
 * @author: YM10095
 * @version:    1.0  
 * @date:   	2017年7月29日 下午10:46:09
 */
public class SeleniumBaseCase {
    @Autowired
    private TestProperities testProperities;
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public Gson gson = new Gson();
    
    private static SeleniumBaseCase instance;
    private ScreenShot screenShot;


    private String ScreeFile;
    private WebDriver driver;
    private String casename;
    private String browserpath;
    private String driverpath;
    private Integer drivertype;
    //调用方式 1-本地 2-远程
    private Integer invoketype = 1;
    //
    private String clientIp;

    private SeleniumBaseCase() {
        //
    }
    
    public static SeleniumBaseCase getInstance() {
        if (instance == null) {
            instance = new SeleniumBaseCase();
        }
        return instance;
    }
    
    /**
     * 配置浏览器环境
     * @param drivertype
     * @author: YM10095
     * @date:	2017年7月11日 下午2:40:50
     */
    public void setup(Integer drivertype){
        try {
            //设置WebDriver
            this.setDriver(drivertype);
            
            //调整浏览器窗口大小
            try {
                driver.manage().window().maximize();
            } catch (Exception e) {
                //异常，说明浏览被手动关闭了
                this.setNewDriver(drivertype);
            }
            
            logger.info("WebDriver实例：[" + driver + "]");
        } catch (Exception e) {
            e.printStackTrace();
            throw new BasicException("配置浏览器环境错误");
        }
        
        //
        driver.manage().window().maximize();
        screenShot = new ScreenShot(driver);
    }
    
    private void setDriver(Integer drivertype) {
        //
        if(drivertype != this.drivertype || driver == null){
            setNewDriver(drivertype);
        }
    }
    
    private void setNewDriver(Integer drivertype) {
        //先关闭进程
        killTask(drivertype);
        if(this.drivertype != null && drivertype != this.drivertype){
            this.killTask(this.drivertype);
        }
        this.sleep(0.1);
        
        this.drivertype = drivertype;
        //设置驱动
        if(invoketype == 2){
            this.openClientDriver();
        }else{
            this.openServerDriver();
        }
    }
    
    private void openServerDriver() {
        String browsername = "IE";
        switch (drivertype) {
            case 1:
                String iedriver = "IEDriverServer.exe";
                String iedriverpath = driverpath  + File.separator + iedriver;                
                System.setProperty("webdriver.ie.driver",iedriverpath);
                DesiredCapabilities dc = DesiredCapabilities.internetExplorer();
                dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                dc.setCapability("ignoreProtectedModeSettings", true);
                this.driver = new InternetExplorerDriver(dc);
                break;
            case 2:
                browsername = "Firefox";
                String firefoxbin = "C:/Program Files (x86)/Mozilla Firefox/firefox.exe";
                if(StringUtil.isNotNull(browserpath)){
                    firefoxbin = browserpath;
                }
                System.setProperty("webdriver.firefox.bin",firefoxbin);
                FirefoxProfile firefoxProfile = new FirefoxProfile();
                this.driver = new FirefoxDriver(firefoxProfile);
                break;
            case 3:
                browsername = "Chrome";
                //String chromedriver = "chromedriver58.exe";
                String chromedriver=TestProperities.getChromedriver();
                String chromedriverpath = driverpath  + File.separator + chromedriver;
                System.setProperty("webdriver.chrome.driver",chromedriverpath);
                this.driver = new ChromeDriver();
                break;
            default:
                this.driver = new HtmlUnitDriver();
                break;
        }
        
        logger.info("打开浏览器:[" + browsername + "]");
    }

    private void openClientDriver() {
        String browsername = "IE";
        String remoteurl = "http://" + clientIp + ":4444/wd/hub";
        try {
            switch (drivertype) {
                case 1:
                    DesiredCapabilities dc = DesiredCapabilities.internetExplorer();
                    dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                    dc.setCapability("ignoreProtectedModeSettings", true);
                    this.driver = new RemoteWebDriver(new URL(remoteurl), dc);
                    break;
                case 2:
                    this.driver = new RemoteWebDriver(new URL(remoteurl), DesiredCapabilities.firefox());
                    break;
                case 3:
                    this.driver = new RemoteWebDriver(new URL(remoteurl), DesiredCapabilities.chrome());
                    break;
                default:
                    this.driver = new RemoteWebDriver(new URL(remoteurl), DesiredCapabilities.htmlUnit());
                    break;
            }
            
            logger.info("打开浏览器:[" + browsername + "]");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    
    /**
    * 
    * @param pageurl
    * @param chaptername
    * @param pagename
    * @param locatorlist
    * @param cols
    * @author: YM10095
    * @date:	2017年8月10日 下午10:43:46
    */
    public void doRun(String pageurl,String chaptername,String pagename,
                      List<SlnmLocator> locatorlist,Object[] cols){
        logger.info("开始执行测试用例:[" + casename + "][" + chaptername + "][" + pagename + "]");
        
        //打开页面
        String url = pageurl;
        if(StringUtil.isNotNull(url)){
            this.open(url);
            logger.info("访问页面:" + url + "");
        }
        
        logger.info("当前页面:" + driver.getCurrentUrl());
        
        //元素不为空
        if(locatorlist != null){
            int i = 0;
            for(SlnmLocator locator : locatorlist){
                //操作类型 click|type|clear
                if(locator.getOrderno()!=18){
                    String opttype = locator.getOpttype();
                    //等待时间(秒)
                    Double waittime = locator.getWaittime();
                    switch (opttype) {
                        case "click":
                            this.sleep(0.1);
                            this.click(locator);
                            this.sleep(waittime);
                            break;
                        case "sumbit":
                            this.sleep(0.1);
                            this.sumbit(locator);
                            this.sleep(waittime);
                            break;
                        case "type":
                            String value = "";
                            if(cols != null && i < cols.length){
                                if(i < cols.length){
                                    if(StringUtil.isNotNull(cols[i])){
                                        value = (String) cols[i];
                                    }else{
                                        value = "";
                                    }
                                    i++;
                                }
                            }else{
                                value = locator.getDatavalue();
                            }
                            this.clear(locator);
                            this.sleep(waittime);
                            this.type(locator, value);
                            this.sleep(waittime);
                            break;
                        case "clear":
                            this.clear(locator);
                            this.sleep(waittime);
                            break;
                        case "switchToFrame":
                            this.switchToFrame(locator);
                            this.sleep(waittime);
                            break;
                        case "exitFromFrame":
                            this.switchToDefaultContent();
                            this.sleep(waittime);
                            break;
                        case "click_left":
                            this.click_left(locator);
                            this.sleep(waittime);
                            break;
                        case "prompt_type":
                            String value0 = "";
                            if(cols != null && i < cols.length){
                                if(i < cols.length){
                                    if(StringUtil.isNotNull(cols[i])){
                                        value0 = (String) cols[i];
                                    }else{
                                        value0 = "";
                                    }
                                    i++;
                                }
                            }else{
                                value0 = locator.getDatavalue();
                            }
                            this.prompt_type(value0);
                            this.sleep(waittime);
                            break;
                        case "prompt_type_accept":
                            String value1 = "";
                            if(cols != null && i < cols.length){
                                if(i < cols.length){
                                    if(StringUtil.isNotNull(cols[i])){
                                        value1 = (String) cols[i];
                                    }else{
                                        value1 = "";
                                    }
                                    i++;
                                }
                            }else{
                                value1 = locator.getDatavalue();
                            }
                            this.prompt_type_accept(value1);
                            this.sleep(waittime);
                            break;
                        case "exist_click":
                            if(this.isElementsExist(locator, 0)){
                                logger.info("元素[" + locator.getLocatorname() + "]存在，开始点击元素");
                                this.click(locator);
                            }else{
                                logger.info("元素[" + locator.getLocatorname() + "]不存在，跳过元素，页面[" + pagename + "]继续执行");
                            }
                            break;
                        case "exist_stop":
                            if(this.isElementsExist(locator, 0)){
                                logger.info("元素[" + locator.getLocatorname() + "]存在，页面[" + pagename + "]停止执行!");
                                return;
                            }else{
                                logger.info("元素[" + locator.getLocatorname() + "]不存在，页面[" + pagename + "]继续执行...");
                            }
                            break;
                        case "keybord_type":
                            String value2 = "";
                            if(cols != null && i < cols.length){
                                if(i < cols.length){
                                    if(StringUtil.isNotNull(cols[i])){
                                        value2 = (String) cols[i];
                                    }else{
                                        value2 = "";
                                    }
                                    i++;
                                }
                            }else{
                                value2 = locator.getDatavalue();
                            }
                            this.keybord_type(value2);
                            this.sleep(waittime);
                            break;
                        case "wait_type":
                            this.wait_type(locator);
                            this.sleep(waittime);
                            break;
                        default:
                            break;
                    }
                }
            }//用例的所有页面对象控件都执行完成，接下来可以做断言了

        }              
    }
    
    /**
     * 查找元素
     * @param locator
     * @return
     * @author: YM10095
     * @date:	2017年7月11日 下午4:28:24
     */
    private WebElement findElement(final SlnmLocator locator) {
        WebElement webElement = null;
        try {
            webElement = (new WebDriverWait(driver, locator.getTimeout())).until(new ExpectedCondition<WebElement>() {
                @Override
                public WebElement apply(WebDriver driver) {
                    WebElement element = null;
                    element = getElement(locator);
                    return element;
                }
            });
            return webElement;
        } catch (NoSuchElementException e) {
            screenShot.setscreenName(locator.getLocatorname());
            screenShot.takeScreenshot();
            logger.error("无法定位页面元素");
            e.printStackTrace();
        } catch (TimeoutException e) {
            screenShot.setscreenName(locator.getLocatorname());
            screenShot.takeScreenshot();
            logger.error("超时无法定位页面元素");

            e.printStackTrace();
        } catch (ElementNotVisibleException e) {
            screenShot.setscreenName(locator.getLocatorname());
            screenShot.takeScreenshot();
            logger.error("页面元素不可见");
            e.printStackTrace();
        } catch (Exception e) {
            screenShot.setscreenName(locator.getLocatorname());
            screenShot.takeScreenshot();
            logger.error("其他异常:" + e.getMessage());
            e.printStackTrace();
        }
        
        return webElement;
    }
    
    /**
     * 查找一组元素
     * @param locator
     * @return
     * @author: YM10095
     * @date:	2017年9月21日 上午10:08:17
     */
    public List<WebElement> findElements(final SlnmLocator locator) {
        List<WebElement> webElements = null;
        try {
            webElements = (new WebDriverWait(driver, locator.getTimeout()))
                .until(new ExpectedCondition<List<WebElement>>() {
                    @Override
                    public List<WebElement> apply(WebDriver driver) {
                        List<WebElement> element = null;
                        element = getElements(locator);
                        return element;
                    }
                });
            return webElements;
        } catch (NoSuchElementException e) {
            screenShot.setscreenName(locator.getLocatorname());
            screenShot.takeScreenshot();
            logger.info("无法定位页面元素");
            e.printStackTrace();
            return webElements;
        } catch (TimeoutException e) {
            screenShot.setscreenName(locator.getLocatorname());
            screenShot.takeScreenshot();
            logger.info("查找页面元素超时");
            e.printStackTrace();
            return webElements;
        } catch (ElementNotVisibleException e) {
            screenShot.setscreenName(locator.getLocatorname());
            screenShot.takeScreenshot();
            logger.info("查找页面元素超时");
            e.printStackTrace();
            return webElements;
        }
    }
    
    /**
     * 通过定位信息获取元素
     * @param locator
     * @return
     * @author: YM10095
     * @date:	2017年7月11日 下午4:28:18
     */
    private WebElement getElement(SlnmLocator locator) {
        logger.info("查找元素[" + locator.getLocatorname() + "],查找规则[By." + locator.getBytype() +
            "],规则信息[" + locator.getByvalue() + "]");
        WebElement webElement;
        switch (locator.getBytype()) {
            case "xpath":
                webElement = driver.findElement(By.xpath(locator.getByvalue()));
                break;
            case "id":
                webElement = driver.findElement(By.id(locator.getByvalue()));
                break;
            case "cssSelector":
                webElement = driver.findElement(By.cssSelector(locator.getByvalue()));
                break;
            case "name":
                webElement = driver.findElement(By.name(locator.getByvalue()));
                break;
            case "className":
                webElement = driver.findElement(By.className(locator.getByvalue()));
                break;
            case "linkText":
                webElement = driver.findElement(By.linkText(locator.getByvalue()));
                break;
            case "partialLinkText":
                webElement = driver.findElement(By.partialLinkText(locator.getByvalue()));
                break;
            case "tagName":
                webElement = driver.findElement(By.tagName(locator.getByvalue()));
                break;
            default:
                webElement = driver.findElement(By.xpath(locator.getByvalue()));
                break;

        }
        
        return webElement;
    }
    
    /**
     * 通过定位信息获取一组元素
     * @param locator
     * @return
     * @author: YM10095
     * @date:	2017年8月2日 上午10:07:36
     */
    public List<WebElement> getElements(SlnmLocator locator) {
        logger.info("查找一组元素[" + locator.getLocatorname() + "],查找规则[By." + locator.getBytype() +
            "],规则信息[" + locator.getByvalue() + "]");
        List<WebElement> webElements;
        switch (locator.getBytype()) {
            case "xpath":
                webElements = driver.findElements(By.xpath(locator.getByvalue()));
                break;
            case "id":
                webElements = driver.findElements(By.id(locator.getByvalue()));
                break;
            case "cssSelector":
                webElements = driver.findElements(By.cssSelector(locator.getByvalue()));
                break;
            case "name":
                webElements = driver.findElements(By.name(locator.getByvalue()));
                break;
            case "className":
                webElements = driver.findElements(By.className(locator.getByvalue()));
                break;
            case "linkText":
                webElements = driver.findElements(By.linkText(locator.getByvalue()));
                break;
            case "partialLinkText":
                webElements = driver.findElements(By.partialLinkText(locator.getByvalue()));
                break;
            case "tagName":
                webElements = driver.findElements(By.partialLinkText(locator.getByvalue()));
                break;
            default:
                webElements = driver.findElements(By.xpath(locator.getByvalue()));
                break;
        }
        
        return webElements;
    }
    
    /**
     * 判断一组元素是否存在
     * @param locator
     * @param timeOut
     * @return
     * @throws InterruptedException
     * @author: YM10095
     * @date:	2017年8月2日 上午10:09:53
     */
    public boolean isElementsExist(SlnmLocator locator, double timeOut) {
        logger.info("等待[" + timeOut + "]秒,判断元素[" + locator.getLocatorname() + "]是否存在");
        boolean isExist = false;
        this.sleep(timeOut);
        try{
            List<WebElement> list = findElements(locator);
            if (list != null && list.size() > 0) {
                isExist = true;
            }
        }catch(Exception e){
            isExist = false;
        }
        
        return isExist;
    }
    
    /**
     * 打开浏览器
     * @param url
     * @author: YM10095
     * @date:	2017年7月11日 下午5:09:16
     */
    private void open(String url) {
        //
        driver.navigate().to(url);
    }
    
    /**
     * 关闭进程
     * @param drivertype
     * <br>-----------------------------------------------------<br>
     * @author: YM10095
     * @date:	2017年7月16日 下午9:05:36
     */
    private void killTask(Integer drivertype){
        String driver = "";
        switch (drivertype) {
            case 1:
                driver = "IEDriverServer.exe";
                break;
            case 2:
                driver = "geckodriver.exe";
                break;
            case 3:
                driver = "chromedriver.exe";
                break;
            default:
                break;
        }
        if(StringUtil.isNotNull(driver)){
            try {
                String command = "taskkill /f /im " + driver;  
                Runtime.getRuntime().exec(command);
            } catch (IOException e) {
                e.printStackTrace();
                logger.warn(e.getMessage());
            }
        }
        
    }
    
    /**
     * 普通单击操作
     * @param locator
     * @author: YM10095
     * @date:	2017年7月11日 下午5:06:08
     */
    private void click(SlnmLocator locator) {
        try {
            WebElement webElement = findElement(locator);
            webElement.click();
            logger.info("click元素[" + locator.getLocatorname() + "],查找规则[By." + locator.getBytype() + "],规则信息[" + 
                locator.getByvalue() + "],成功！");
        } catch (NoSuchElementException e) {

            logger.error("找不到元素，click元素[" + locator.getLocatorname() + "],查找规则[By." + locator.getBytype() + 
                "],规则信息[" + locator.getByvalue() + "],失败!");

            e.printStackTrace();
            throw e;
        }
    }




    /**
     * 普通表单提交
     * @param locator
     * @author: YM10095
     * @date:	2017年7月11日 下午5:06:08
     */
    private void sumbit(SlnmLocator locator) {
        try {
            WebElement webElement = findElement(locator);
            webElement.submit();
            logger.info("表单提交成功！");
        } catch (NoSuchElementException e) {

            logger.error("找不到提交的表单提交按钮");

            e.printStackTrace();
            throw e;
        }
    }
    
    /**
     * 文本框输入操作
     * @param locator
     * @param value
     * <br>-----------------------------------------------------<br>
     * @author: YM10095
     * @date:	2017年7月11日 下午10:26:01
     */
    private void type(SlnmLocator locator, String value) {
        try {
            WebElement webElement = findElement(locator);
            webElement.sendKeys(value);
            logger.info("input输入[" + locator.getLocatorname() + "],查找规则[By." + locator.getBytype() + "],规则信息[" + 
                locator.getByvalue() + "],输入值[" + value + "],成功！");
        } catch (NoSuchElementException e) {

            logger.error("找不到元素，input输入[" + locator.getLocatorname() + "],查找规则[By." + locator.getBytype() + 
                "],规则信息[" + locator.getByvalue() + "],失败!");
            e.printStackTrace();
            throw e;
        }
    }
    
    /**
     * 清除文本框内容
     * @param locator
     * <br>-----------------------------------------------------<br>
     * @author: YM10095
     * @date:	2017年7月11日 下午10:26:08
     */
    private void clear(SlnmLocator locator) {
        try {
            WebElement webElement = findElement(locator);
            webElement.clear();
            logger.info("清除input值[" + locator.getLocatorname() + "],查找规则[By." + locator.getBytype() + "],规则信息[" + 
                locator.getByvalue() + "],成功！");
        } catch (Exception e) {
            logger.error("清除input值[" + locator.getLocatorname() + "],查找规则[By." + locator.getBytype() + 
                "],规则信息[" + locator.getByvalue() + "],失败!");
            e.printStackTrace();
            throw e;
        }
    }
    
    /**
     * 键盘输入
     * @param value
     * @author: YM10095
     * @date:	2017年8月3日 上午9:40:50
     */
    public void keybord_type(String value) {
        Actions actions = new Actions(driver);        
        actions.sendKeys(value).perform();
        logger.info("键盘输入，输入值[" + value + "],成功！");
    }

    /**
     * Prompt提示框输入
     * @param value
     * @author: YM10095
     * @date:   2017年8月2日 上午9:50:34
     */
    public void prompt_type(String value) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        try {
            Alert alert = wait.until(new ExpectedCondition<Alert>() {
                @Override
                public Alert apply(WebDriver driver) {
                    try {
                        return driver.switchTo().alert();
                    } catch (NoAlertPresentException e) {
                        logger.info("找不到Prompt，继续等待");
                        return null;
                    }
                }
            });
            alert.sendKeys(value);
            logger.info("向Prompt中输入文字,输入值[" + value + "],成功！");
        } catch (NoAlertPresentException e) {
            logger.error("找不到Prompt");
            throw e;
        } catch (TimeoutException e) {
            logger.info("查找Prompt超时");
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        
        /*Alert alert = driver.switchTo().alert();
        try {
            alert.sendKeys(value);
            logger.info("向Prompt中输入文字,输入值[" + value + "],成功！");
        } catch (NoAlertPresentException e) {
            logger.error("找不到Prompt");
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }*/
    }
    
    /**
     * Prompt提示框输入并确认
     * @param value
     * @author: YM10095
     * @date:   2017年8月2日 上午9:50:34
     */
    public void prompt_type_accept(String value) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        try {
            Alert alert = wait.until(new ExpectedCondition<Alert>() {
                @Override
                public Alert apply(WebDriver driver) {
                    try {
                        return driver.switchTo().alert();
                    } catch (NoAlertPresentException e) {
                        logger.info("找不到Prompt，继续等待");
                        return null;
                    }
                }
            });
            alert.sendKeys(value);
            logger.info("向Prompt中输入文字,输入值[" + value + "],成功！");
            this.sleep(0.5);
            alert.accept();
            logger.info("点击Prompt确认按钮,成功！");
        } catch (NoAlertPresentException e) {
            logger.error("找不到Prompt");
            throw e;
        } catch (TimeoutException e) {
            logger.info("查找Prompt超时");
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    /**
     * 鼠标左键单击
     * @param locator
     * @author: YM10095
     * @date:	2017年8月2日 上午9:58:11
     */
    public void click_left(SlnmLocator locator) {
        WebElement webElement = findElement(locator);
        Actions actions = new Actions(driver);
        
        //鼠标左键移动到元素,将鼠标移动至元素的中间位置
        actions = actions.moveToElement(webElement);
        this.sleep(0.5);
        //
        actions.click().perform();
        this.sleep(0.5);
        
        logger.info("鼠标左键单击,成功！");
    }
    
    /**
     * 鼠标双击操作
     * @param locator
     * @author: YM10095
     * @date:	2017年8月2日 上午9:59:52
     */
    public void click_double(SlnmLocator locator) {
        WebElement webElement = findElement(locator);
        Actions actions = new Actions(driver);
        actions.doubleClick(webElement).perform();
    }
    
    /**
     * 等待文本框输入
     * @param locator
     * @author: YM10095
     * @date:	2017年8月4日 下午5:37:13
     */
    public void wait_type(SlnmLocator locator) {
        WebDriverWait wait = new WebDriverWait(driver, locator.getTimeout(),500);
        try {
            wait.until(new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    try {
                        WebElement webElement = getElement(locator);
                        String value = webElement.getAttribute("value");
                        if(StringUtil.isNotNull(value)){
                            return true;
                        }else{
                            logger.info("等待文本框输入,元素[" + locator.getLocatorname() + "]未输入值，继续等待");
                            return false;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        return false;
                    }
                }
            });
        } catch (Exception e) {
            //e.printStackTrace();
            logger.info("等待文本框输入,元素[" + locator.getLocatorname() + "]未输入值，等待超时");
        }
    }
    
    /**
     * 切换frame/iframe框架
     * @param locator
     * @author: YM10095
     * @date:	2017年7月17日 下午7:09:34
     */
    private void switchToFrame(SlnmLocator locator) {
        WebElement frameElement = findElement(locator);
        driver.switchTo().frame(frameElement);
    }
    
    /**
     * 切回默认窗口框架
     * @author: YM10095
     * @date:	2017年7月17日 下午7:09:21
     */
    private void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }
    
    /**
     * 点击Alert确认按钮
     * @author: YM10095
     * @date:	2017年8月2日 上午9:53:54
     */
    public void alertConfirm() {
        Alert alert = driver.switchTo().alert();
        try {
            alert.accept();
            logger.info("点击Alert确认按钮,成功！");
        } catch (NoAlertPresentException e) {
            logger.error("找不到Alert确认按钮");
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    /**
     * 点击Alert取消按钮
     * @author: YM10095
     * @date:   2017年8月2日 上午9:53:54
     */
    public void alertDismiss() {
        Alert alert = driver.switchTo().alert();
        try {
            alert.dismiss();
            logger.info("点击Alert取消按钮,成功！");
        } catch (NoAlertPresentException e) {
            logger.error("找不到Alert取消按钮");
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    /**
     * 
     * @param js
     * @author: YM10095
     * @date:	2017年7月18日 下午8:17:51
     */
    private void executeJS(String js) {
        ((JavascriptExecutor) driver).executeScript(js);
        logger.info("执行JS脚本[" + js + "],成功！");

    }
    
    /**
     * 截屏
     * @param filepath
     * @param filename
     * @throws Exception
     * @author: YM10095
     * @date:	2017年7月18日 下午8:22:22
     */
    private void snapShot(String filepath, String filename) throws Exception {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(scrFile, new File(filepath + filename));
            System.out.println("截图:" + filepath + filename);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 关闭浏览器窗口
     * @author: YM10095
     * @date:   2017年7月11日 下午5:10:30
     */
    public void close() {
        if(driver != null){
            driver.close();
            logger.info("关闭浏览器窗口");
        }
    }
    
    /**
     * 退出浏览器
     * @author: YM10095
     * @date:   2017年7月11日 下午5:11:00
     */
    public void quit() {
        if(driver != null){
            driver.quit();
            logger.info("退出浏览器");
        }
    }
    
    /**
     * 显式等待，程序休眠暂停
     * @param d 以秒为单位
     * @author: YM10095
     * @date:	2017年7月11日 下午4:52:55
     */
    public void sleep(double d) {
        try {
            long sleeptime = (long) (d * 1000);
            Thread.sleep(sleeptime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getCasename() {
        return casename;
    }

    public void setCasename(String casename) {
        this.casename = casename;
    }

    public String getBrowserpath() {
        return browserpath;
    }

    public void setBrowserpath(String browserpath) {
        this.browserpath = browserpath;
    }

    public String getDriverpath() {
        return driverpath;
    }

    public void setDriverpath(String driverpath) {
        this.driverpath = driverpath;
    }

    public Integer getInvoketype() {
        return invoketype;
    }

    public void setInvoketype(Integer invoketype) {
        this.invoketype = invoketype;
    }
    
    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }
    
}
