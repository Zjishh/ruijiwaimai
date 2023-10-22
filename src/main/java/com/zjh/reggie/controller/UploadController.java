package com.zjh.reggie.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.zjh.reggie.utils.AliOSSUtils;
import com.zjh.reggie.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


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
    @Autowired
    private AliOSSUtils aliOSSUtils;
        @PostMapping("/common/upload")
    public Result<String> uplod(MultipartFile file) throws IOException {
            log.info("文件上传OSS");
            String url = aliOSSUtils.upload(file);
            log.info("文件上传完成"+url);
            return Result.success(url);
        }

}
