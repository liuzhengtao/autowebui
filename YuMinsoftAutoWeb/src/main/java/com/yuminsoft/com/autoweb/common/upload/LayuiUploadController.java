package com.yuminsoft.com.autoweb.common.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.yuminsoft.com.autoweb.common.util.StringUtil;

/**
 * layui文件上传
 * FileName:    LayuiUploadController.java  
 * @author: YM10095
 * @version:    1.0  
 * @date:   	2017年7月29日 下午10:30:43
 */
@RestController
@RequestMapping("/layui")
public class LayuiUploadController {
    private final String UPLOAD_PATH = "upload" + File.separator + "layui";
    
    /**
     * 上传单个文件
     * @param request
     * @param multirequest
     * @return
     * @author: YM10095
     * @date:	2017年7月10日 上午9:26:42
     */
    @RequestMapping(value="/upload", method=RequestMethod.POST)
    public Object upload(HttpServletRequest request, MultipartHttpServletRequest multirequest) {
        // 获取上传文件的路径
        String uploadFilePath = multirequest.getFile("file0").getOriginalFilename();
        // 截取上传文件的文件名
        String uploadFileName = uploadFilePath.substring(uploadFilePath.lastIndexOf('\\') + 1, uploadFilePath.indexOf('.'));
        // 截取上传文件的后缀
        String uploadFileSuffix = uploadFilePath.substring(uploadFilePath.indexOf('.') + 1, uploadFilePath.length());
        
        Map<String,Object> retmap = new LinkedHashMap<String,Object>();
        retmap.put("code", 0);
        retmap.put("msg", "上传文件失败");
        
        FileOutputStream fos = null;
        FileInputStream fis = null;
        try {
            fis = (FileInputStream) multirequest.getFile("file0").getInputStream();
            //获取项目绝对路径
            //String realProjectPath = request.getSession().getServletContext().getRealPath("/");
            //System.err.println("项目绝对路径:" + realProjectPath);
            //获取项目相对路径
            String relativeProjectPath = System.getProperty("user.dir");
            //System.err.println("项目相对路径:" + relativeProjectPath);
            String relativeFilePath = relativeProjectPath.substring(0,relativeProjectPath.lastIndexOf(File.separator));
            //文件保存真实路径
            String realFilePath = relativeFilePath + File.separator + UPLOAD_PATH;

            //System.err.println("文件保存真实路径:" + realFilePath);
            //根据真实路径创建目录
            File saveFile = new File(realFilePath);
            if (!saveFile.exists()){
                saveFile.mkdirs();
            }
            
            //新的文件名
            String newFileName = StringUtil.getGuid() + "." + uploadFileSuffix;
            //完整的文件保存路径加文件名
            String saveFileName = realFilePath + File.separator + newFileName;
            
            //写文件
            fos = new FileOutputStream(new File(saveFileName));
            byte[] temp = new byte[1024];
            while (true) { //从来源档案读取资料至缓冲区 
                if (fis.available() < 1024) {
                    int remain;
                    while ((remain = fis.read()) != -1) {
                        fos.write(remain);
                    }
                    break;
                } else {
                    fis.read(temp);
                    //将阵列资料写入目的档案 
                    fos.write(temp);
                }
            }
            
            //
            retmap.put("code", 1);
            retmap.put("msg", "上传文件成功");
            //文件名
            retmap.put("filename", uploadFilePath);
            //文件相对路径
            String filepath = UPLOAD_PATH + File.separator + newFileName;
            retmap.put("filepath", filepath);
            
            return retmap;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return retmap;
    }
    
}
