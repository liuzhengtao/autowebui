package com.yuminsoft.com.autoweb.selenium.basic;

import com.google.gson.Gson;
import com.yuminsoft.com.autoweb.SeleniumUiApplication;
import com.yuminsoft.com.autoweb.common.util.SqlUtil;
import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;

/**
 * Created by 刘正涛 on 2017/9/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SeleniumUiApplication.class)
public class BaseTest {
    public Gson gson = new Gson();


    public void logger(Object t){
        Logger logger = Logger.getLogger(this.getClass());

        if(t instanceof String){
            logger.info("----------------*************---------------------");
            logger.info(t);
            logger.info("----------------*************---------------------");
        }
        logger.info("----------------*************---------------------");
        logger.info(gson.toJson(t));
        logger.info("----------------*************---------------------");
    }

    public void execute(String sql) throws SQLException {
        SqlUtil sqlUtil =new SqlUtil();
        sqlUtil.getConnect();
        sqlUtil.getStatement();
        sqlUtil.excuet(sql);
    }
}
