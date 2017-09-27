package com.yuminsoft.com.autoweb.common.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Administrator on 2017/9/27.
 */
public class TestProperities {

    private static String chromedriver;

    static  {
        Properties prop =  new  Properties();
        InputStream in = TestProperities.class.getResourceAsStream( "/config/application.properties" );
        try  {
            prop.load(in);
            chromedriver = prop.getProperty( "spring.selenium.chromedriver" );
        }  catch  (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getChromedriver() {
        return chromedriver;
    }

    public static void main(String[] args){
        System.out.println(TestProperities.getChromedriver());
    }


}
