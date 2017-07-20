package org.wella.common.ctrl;

import com.alibaba.fastjson.JSON;
import io.wellassist.entity.SysOssEntity;
import io.wellassist.oss.OSSFactory;
import io.wellassist.service.SysOssService;
import io.wellassist.utils.RRException;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.wella.common.utils.CommonUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@Controller
public class DownloadController extends BaseController {
    @Autowired
    private SysOssService sysOssService;

    public DownloadController() {
    }

    @RequestMapping({"/downloadCrossDomainFile"})
    public void uploadFileTemp(HttpServletResponse response, @RequestParam("targetUrl")String targetUrl,@RequestParam("outputFileName")String outputFileName) throws IOException {
        BufferedInputStream bis=null;
        BufferedOutputStream bos=null;
        try {
            URL website = new URL(targetUrl);
            response.reset();
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/octet-stream; charset=utf-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + new String(outputFileName.getBytes("GB2312"),"ISO_8859_1"));
            bis=new BufferedInputStream(website.openStream());
            bos=new BufferedOutputStream(response.getOutputStream());
            byte [] buf=new byte[2048];
            int bytesRead;
            while(-1!=(bytesRead=bis.read(buf,0,buf.length))){
                bos.write(buf,0,bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}