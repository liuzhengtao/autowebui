package com.yuminsoft.com.autoweb.selenium.service;

import com.google.gson.Gson;
import com.yuminsoft.com.autoweb.SeleniumUiApplication;
import com.yuminsoft.com.autoweb.common.util.SqlUtil;
import com.yuminsoft.com.autoweb.common.util.TestProperities;
import com.yuminsoft.com.autoweb.selenium.basic.BaseTest;
import com.yuminsoft.com.autoweb.selenium.dao.SeleniumDao;
import com.yuminsoft.com.autoweb.selenium.form.SlnmChapterVo;
import com.yuminsoft.com.autoweb.selenium.model.SlnmChapter;
import com.yuminsoft.com.autoweb.selenium.service.impl.SlnmCaseServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by 刘正涛 on 2017/9/27.
 */

public class SlnmCaseServiceTest extends BaseTest {

    @Autowired
    public SlnmCaseService slnmCaseService;

    @Autowired
    private TestProperities testProperities;

    @Autowired
    protected SeleniumDao seleniumDao;

    public Gson gson=new Gson();




    public void testGetChapterPage() throws Exception {
        SlnmChapter slnmChapter =  slnmCaseService.getSlnmChapter(2);
        logger(slnmChapter);

    }

    @Test
    public void testGetSlnmChapter() throws Exception {

        /*SlnmChapter bean = slnmCaseService.getSlnmChapter(5);*/
        logger(testProperities.getChromedriver());
    }


    public void testDoAddChapter() throws Exception {
        SlnmChapterVo slnmChapterVo = new SlnmChapterVo();
        slnmChapterVo.setChaptercode("00015");
        slnmChapterVo.setCasecode("0001");
        slnmChapterVo.setChaptername("第三页签测试");
        slnmChapterVo.setIsrun(0);
        slnmChapterVo.setExpectedresults("保存成功");
        slnmChapterVo.setOrderno(2);
        slnmChapterVo.setOptcode("system");
        slnmCaseService.doAddChapter(slnmChapterVo);
    }


    public void testDoAddChapterWithJDBC() throws Exception {
        StringBuilder stringBuilder = new StringBuilder("insert into slnm_chapter(chaptercode,casecode,chaptername,isrun,expectedresults,orderno,optcode)VALUES(\n" + "'00013','0001','第二个页签测试',0,'保存成功',2,'system')");
        execute(stringBuilder.toString());
    }

}