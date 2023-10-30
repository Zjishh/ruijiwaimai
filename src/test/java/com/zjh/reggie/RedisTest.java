package com.zjh.reggie;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zjh.reggie.entity.User;
import com.zjh.reggie.mapper.UserMapper;
import com.zjh.reggie.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.Map;

/****************************
 * @project reggie
 * @package com.zjh.reggie
 * @className RedisTest
 * @author Zjiah
 * @date 2023/10/30 10:41
 * @Description:   *
 ****************************/
@Slf4j
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;


    @Test
    public void test() {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(User::getPhone);
        List<User> list = userService.list(queryWrapper);

        redisTemplate.opsForValue().set("phone", list);


        List<User> l2 = (List<User>) redisTemplate.opsForValue().get("phone");
        for (User user : l2) {
            System.out.println(user.getPhone());
        }

    }
}
