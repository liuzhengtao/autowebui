package com.yuminsoft.com.autoweb.common.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.yuminsoft.com.autoweb.base.exception.BasicException;

/**
 * 
 * FileName:    JaxbUtil.java  
 * @author: YM10095
 * @version:    1.0  
 * @date:   	2017年7月29日 下午10:45:51
 */
public class JaxbUtil {

    /**
     * 将xml文件转换成bean
     * @param clazz
     * @param xmlPath
     * @param xmlname
     * @return
     */
    public static <T> T transXmlToBean(Class<T> clazz, String xmlPath, String xmlname) {
        T xmlObject = null;
        try {
            xmlObject = transXmlToBean(clazz, xmlPath + xmlname);
        } catch (Exception e) {
            //e.printStackTrace();
        }

        return xmlObject;
        
        /*T xmlObject = null;
        try {
            xmlObject = clazz.newInstance();
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            FileReader fr = null;
            try {
                fr = new FileReader(xmlPath + xmlname);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            xmlObject = (T) unmarshaller.unmarshal(fr);
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (InstantiationException e1) {
            e1.printStackTrace();
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return xmlObject;*/
    }
    
    /**
     * 将xml文件转换成bean
     * @param clazz
     * @param xmlFilePath
     * @return
     * @author: YM10095
     * @date:	2017年7月31日 下午4:20:43
     */
    public static <T> T transXmlToBean(Class<T> clazz, String xmlFilePath) {
        T xmlObject = null;
        try {
            xmlObject = clazz.newInstance();
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            FileReader fr = null;
            try {
                fr = new FileReader(xmlFilePath);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            xmlObject = (T) unmarshaller.unmarshal(fr);
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (InstantiationException e1) {
            e1.printStackTrace();
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return xmlObject;
    }
    
    /**
     * 将bean根据路径转换成xml文件
     * @param obj
     * @param xmlPath
     * @param xmlname
     */
    public static void transBeanToXml(Object obj, String xmlPath, String xmlname) {
        try {
            if(StringUtil.isNull(xmlPath)){
                throw new BasicException("文件路径为空");
            }
            
            //
            File dirFile = new File(xmlPath);
            if (!dirFile.exists()){
                dirFile.mkdirs();
            }
            
            // 利用jdk中自带的转换类实现  
            JAXBContext context = JAXBContext.newInstance(obj.getClass());

            Marshaller marshaller = context.createMarshaller();
            // 格式化xml输出的格式  
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            // 将对象转换成输出流形式的xml  
            // 创建输出流  
            FileWriter fw = null;
            try {
                fw = new FileWriter(xmlPath + xmlname);
                //fw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            marshaller.marshal(obj, fw);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 将bean直接转换成String类型的 XML输出
     * @param obj
     * @return
     */
    public static String transBeanToXmlStr(Object obj) {  
        // 创建输出流  
        StringWriter sw = new StringWriter();  
        try {  
            // 利用jdk中自带的转换类实现  
            JAXBContext context = JAXBContext.newInstance(obj.getClass());  
  
            Marshaller marshaller = context.createMarshaller();  
            // 格式化xml输出的格式  
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);  
            // 将对象转换成输出流形式的xml  
            marshaller.marshal(obj, sw);  
        } catch (JAXBException e) {  
            e.printStackTrace();  
        }  
        return sw.toString();  
    }

}
