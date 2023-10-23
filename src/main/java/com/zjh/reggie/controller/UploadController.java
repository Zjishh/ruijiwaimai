package com.zjh.reggie.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.zjh.reggie.exception.GlobeExceptionHandler;
import com.zjh.reggie.utils.AliOSSUtils;
import com.zjh.reggie.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;


/****************************
 * @project empservice
 * @package com.zjh.emp.controller
 * @className UploadController
 * @author Zjiah
 * @date 2023/9/26 20:55
 * @Description:   *
 ****************************/

@RestController
@Slf4j
@RequestMapping("/common")
public class UploadController {


//    @PostMapping("/upload")
//    public Result upload(String username, Integer age, MultipartFile image) throws IOException {
//        log.info("文件上传：{}，{}，{}", username, age, image);
//        String name = image.getOriginalFilename();
//        String substring = name.substring(name.lastIndexOf("."));
//
//        //uuid
//        String s = UUID.randomUUID().toString();
//        log.info("存储文件"+s+substring);
//        image.transferTo(new File("A:\\empfile\\"+s+substring));
//        return Result.success();
//    }

    //注入ALIOSS对象
    @Value("${reggie.imgpath}")
    private String reggieimgpath;

    @Autowired
    private AliOSSUtils aliOSSUtils;

    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));

        File filepath = new File(reggieimgpath);
        if (!filepath.exists()) {
            filepath.mkdirs();
        }
        file.transferTo(new File(reggieimgpath + fileName));
        log.info("/-/-/-/-/-/-/-/-文件上传 = = = " + reggieimgpath + fileName);

        return Result.success(fileName);
    }

    @GetMapping ("/download")
    public void download(String name, HttpServletResponse response) throws IOException {
        log.info("加载");
        FileInputStream fileInputStream = new FileInputStream(reggieimgpath + name);
        ServletOutputStream outputStream = response.getOutputStream();
        byte[] b = new byte[1024];
        response.setContentType("image/jpeg");
        int len = 0;
        while ((len = fileInputStream.read(b)) != -1){
            outputStream.write(b,0,len);
        }
        outputStream.close();
        fileInputStream.close();


    }


    @PostMapping("/OSSupload")
    public Result<String> OSSupload(MultipartFile file) throws IOException {
        log.info("文件上传OSS");
        String url = aliOSSUtils.upload(file);
        log.info("文件上传完成" + url);
        return Result.success(url);
    }

    @PostMapping("/OSSdownload")
    public Result<String> OSSdownload(MultipartFile file) throws IOException {
        log.info("文件上传OSS");
        String url = aliOSSUtils.upload(file);
        log.info("文件上传完成" + url);
        return Result.success(url);
    }

}
