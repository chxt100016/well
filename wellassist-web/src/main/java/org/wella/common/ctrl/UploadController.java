package org.wella.common.ctrl;

import com.alibaba.fastjson.JSON;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.wella.common.utils.CommonUtil;

@Controller
public class UploadController extends BaseController {
    public UploadController() {
    }

    @RequestMapping({"/uploadFile"})
    public void uploadFileTemp(HttpServletRequest request, HttpServletResponse response, @RequestParam("file") MultipartFile file) throws FileNotFoundException {
        String fileName = file.getOriginalFilename();
        String ext = CommonUtil.getExtention(fileName);
        String dir = "";
        String uploadPath = request.getSession().getServletContext().getRealPath("/");
        uploadPath = uploadPath + "uploads/temp/";
        dir = dir + "uploads/temp/";
        File dirFile = new File(uploadPath);
        if(!dirFile.exists()) {
            dirFile.mkdirs();
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String loadedFileName = sdf.format(new Date());
        String loadedFilePath = uploadPath + loadedFileName + "." + ext;
        File loadedFile = new File(loadedFilePath);
        sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String newFileName = sdf.format(new Date()) + "." + ext;

        try {
            file.transferTo(loadedFile);
        } catch (Exception var15) {
            logger.error("uploadFile: fileName=" + fileName, var15);
            return;
        }

        HashMap result = new HashMap();
        result.put("path", dir + newFileName);
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