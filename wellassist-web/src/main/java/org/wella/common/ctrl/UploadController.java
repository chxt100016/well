package org.wella.common.ctrl;

import com.alibaba.fastjson.JSON;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.wellassist.entity.SysOssEntity;
import io.wellassist.oss.OSSFactory;
import io.wellassist.service.SysOssService;
import io.wellassist.utils.R;
import io.wellassist.utils.RRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.wella.common.utils.CommonUtil;

@Controller
public class UploadController extends BaseController {
    @Autowired
    private SysOssService sysOssService;

    public UploadController() {
    }

    @RequestMapping({"/uploadFile"})
    public void uploadFileTemp(HttpServletResponse response, MultipartFile file) throws IOException {
        /**
         * 原有业务逻辑，文件为被保存
         */
//        String fileName = file.getOriginalFilename();
//        String ext = CommonUtil.getExtention(fileName);
//        String dir = "";
//        String uploadPath = request.getSession().getServletContext().getRealPath("/");
//        uploadPath = uploadPath + "uploads/temp/";
//        dir = dir + "uploads/temp/";
//        File dirFile = new File(uploadPath);
//        if(!dirFile.exists()) {
//            dirFile.mkdirs();
//        }
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//        String loadedFileName = sdf.format(new Date());
//        String loadedFilePath = uploadPath + loadedFileName + "." + ext;
//        File loadedFile = new File(loadedFilePath);
//        sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//        String newFileName = sdf.format(new Date()) + "." + ext;
//
//        try {
//            file.transferTo(loadedFile);
//        } catch (Exception var15) {
//            logger.error("uploadFile: fileName=" + fileName, var15);
//            return;
//        }
//
//        HashMap result = new HashMap();
//        result.put("path", dir + newFileName);
//        this.echo(response, JSON.toJSONString(result));

        //采用七牛云之后的业务逻辑,将返回的url传回到前台，并与相应的属性绑定
        if (file.isEmpty()) {
            throw new RRException("上传文件不能为空");
        }
        //上传文件
        String url = OSSFactory.build().upload(file.getBytes());
        //保存文件信息
        SysOssEntity ossEntity = new SysOssEntity();
        ossEntity.setUrl(url);
        ossEntity.setCreateDate(new Date());
        sysOssService.save(ossEntity);
        HashMap result = new HashMap();
        result.put("path",url);
        this.echo(response, JSON.toJSONString(result));

    }

    public String uploadFile(HttpServletRequest request, MultipartFile file, String dir) {
        String filePath = null;
        String fileName = file.getOriginalFilename();
        String ext = CommonUtil.getExtention(fileName);
        String uploadPath = request.getSession().getServletContext().getRealPath("/");
        uploadPath = uploadPath + "uploads/";
        File dirFile = new File(uploadPath);
        if(!dirFile.exists()) {
            dirFile.mkdirs();
        }

        uploadPath = uploadPath + dir + "/";
        dirFile = new File(uploadPath);
        if(!dirFile.exists()) {
            dirFile.mkdirs();
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String loadedFileName = sdf.format(new Date());
        String loadedFilePath = uploadPath + loadedFileName + "." + ext;
        File loadedFile = new File(loadedFilePath);

        try {
            file.transferTo(loadedFile);
        } catch (Exception var14) {
            logger.error("uploadFile: fileName=" + fileName, var14);
            return "";
        }

        filePath = "uploads/" + dir + "/" + loadedFileName + "." + ext;
        return filePath;
    }

    public String moveFile(HttpServletRequest request, String source, String targetDir) {
        String uploadsDir = request.getSession().getServletContext().getRealPath("/");
        String sourceFilePath = uploadsDir + source;
        String[] targetDirArr = targetDir.split("/");
        String[] sdf = targetDirArr;
        int targetFileDir = targetDirArr.length;

        for(int dirFile = 0; dirFile < targetFileDir; ++dirFile) {
            String sourceFile = sdf[dirFile];
            uploadsDir = uploadsDir + sourceFile + "/";
            File dirFile1 = new File(uploadsDir);
            if(!dirFile1.exists()) {
                dirFile1.mkdirs();
            }
        }

        SimpleDateFormat var13 = new SimpleDateFormat("yyyyMMdd");
        String var14 = request.getSession().getServletContext().getRealPath("/") + "uploads/" + targetDir + "/";
        var14 = var14 + var13.format(new Date()) + "/";
        targetDir = targetDir + "/" + var13.format(new Date());
        File var15 = new File(var14);
        if(!var15.exists()) {
            var15.mkdirs();
        }

        File var12 = new File(sourceFilePath);
        if(!var12.exists()) {
            return "";
        } else {
            var12.renameTo(new File(var14, var12.getName()));
            return "uploads/" + targetDir + "/" + var12.getName();
        }
    }

    public void deleteFile(HttpServletRequest request, String fileName) {
        String filePath = request.getSession().getServletContext().getRealPath("/");
        filePath = filePath + fileName;
        File file = new File(filePath);
        if(file.exists()) {
            file.delete();
        }

    }
}