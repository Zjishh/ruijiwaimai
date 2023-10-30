package com.zjh.reggie.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.zjh.reggie.entity.User;
import com.zjh.reggie.service.UserService;
import com.zjh.reggie.utils.MailUtil;
import com.zjh.reggie.utils.Result;
import com.zjh.reggie.utils.ValidateCodeUtils;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MailUtil mailUtil;
    
    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 发送手机短信验证码
     * @param user
     * @return
     */
    @PostMapping("/sendMsg")
    public Result<String> sendMsg(@RequestBody User user, HttpServletRequest servletRequest){
//        用户账号缓存
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(User::getId,User::getPhone);
        List<User> list = userService.list(queryWrapper);
        redisTemplate.opsForValue().set("phone", list);
        //获取手机号
        String phone = user.getPhone();
        log.info(phone);
        if(StringUtils.isNotEmpty(phone)){
            //生成随机的4位验证码
            String code = ValidateCodeUtils.generateValidateCode(4).toString();
            log.info("code={}",code);

            //调用阿里云提供的短信服务API完成发送短信
            //SMSUtils.sendMessage("瑞吉外卖","",phone,code);
            //需要将生成的验证码保存到Session
            mailUtil.sendTextMailMessage(phone,"验证码",code);

//            验证码放到redis中 并且设置有效期
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);

            return Result.success("验证码短信发送成功");
        }

        return Result.error("验证码发送失败");
    }

    /**
     * 移动端用户登录
     * @param map
     * @param map
     * @return
     */

    @PostMapping("/login")
    public Result<User> login(@RequestBody Map map, HttpServletRequest servletRequest){


        //获取验证码
        String code = map.get("code").toString();
        log.info(code+"输入");
        //获取手机号
        String phone = map.get("phone").toString();
        log.info(phone+"输入");

        String codeInSession = redisTemplate.opsForValue().get(phone).toString();

        log.info("读出的code{}",codeInSession);


        //进行验证码的比对（页面提交的验证码和Session中保存的验证码比对）
        if(codeInSession != null && codeInSession.equals(code)){
            //如果能够比对成功，说明登录成功

            List<User> l2 = (List<User>) redisTemplate.opsForValue().get("phone");
            for (User user : l2) {
                if (user.getPhone().equals(phone)){
                    servletRequest.getSession().setAttribute("user",user.getId());
                    redisTemplate.delete(phone);
                    return Result.success(user);
                }
            }
            User newuser = new User();
            newuser.setPhone(phone);
            newuser.setStatus(1);
            userService.save(newuser);
            LambdaQueryWrapper<User> objectLambdaQueryWrapper = new LambdaQueryWrapper<>();
            objectLambdaQueryWrapper.eq(User::getPhone,phone);
            User one = userService.getOne(objectLambdaQueryWrapper);
            servletRequest.getSession().setAttribute("user",one.getId());
            return Result.success(one);



//            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
//            queryWrapper.eq(User::getPhone,phone);
//
//            User user = userService.getOne(queryWrapper);
//            if(user == null){
//                //判断当前手机号对应的用户是否为新用户，如果是新用户就自动完成注册
//                user = new User();
//                user.setPhone(phone);
//                user.setStatus(1);
//                userService.save(user);
//            }
//            servletRequest.getSession().setAttribute("user",user.getId());
//            redisTemplate.delete(phone);
//            return Result.success(user);
        }
        return Result.error("登录失败");
    }

    @PostMapping("/loginout")
    public Result<String> login(HttpServletRequest servletRequest){
        servletRequest.getSession().removeAttribute("user");
        return Result.success("退出成功");
    }
}
