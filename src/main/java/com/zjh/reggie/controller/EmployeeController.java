package com.zjh.reggie.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjh.reggie.entity.Employee;
import com.zjh.reggie.service.EmployeeService;
import com.zjh.reggie.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.util.DigestUtils;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.time.LocalDateTime;

/****************************
 * @project reggie
 * @package com.zjh.reggie.controller
 * @className EmployeeController
 * @author Zjiah
 * @date 2023/10/18 11:01
 * @Description:   *
 ****************************/
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    /**
     * 员工登录
     *
     * @param employee
     */
    @PostMapping("/login")
    public Result<Employee> login(HttpServletRequest httpRequest, @RequestBody Employee employee) {
        String password = employee.getPassword();
        employee.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));

        //查询
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername, employee.getUsername());
        Employee login = employeeService.getOne(queryWrapper);

        if (login == null) {
            log.info("用户名未找到" + employee.getUsername());
            return Result.error("登陆失败，用户名不匹配");
        }
        if (!employee.getPassword().equals(login.getPassword())) {
            log.info("密码错误" + employee.getPassword());
            return Result.error("登陆失败，密码错误");
        }
        if (login.getStatus() == 0) {
            log.info("账号已禁用" + employee.getPassword());
            return Result.error("账号已禁用");
        }

        httpRequest.getSession().setAttribute("employee", login.getId());
        long id = login.getId();
        log.info(String.valueOf(id)+"-------------------------------");
        return Result.success(login);
    }

    /**
     * 退出
     *
     * @param httpServletRequest
     * @return {@code Result<Object>}
     */
    @PostMapping("/logout")
    public Result<Object> logout(HttpServletRequest httpServletRequest) {
        httpServletRequest.getSession().removeAttribute("employee");
        return Result.success("退出成功");
    }

    /**
     * 保存
     *
     * @param httpServletRequest
     * @param employee
     * @return {@code Result<Object>}
     */
    @PostMapping
    public Result<Object> save(HttpServletRequest httpServletRequest, @RequestBody Employee employee) {
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
//        employee.setCreateTime(LocalDateTime.now());
//        employee.setUpdateTime(LocalDateTime.now());
//        employee.setCreateUser((Long) httpServletRequest.getSession().getAttribute("employee"));
//        employee.setUpdateUser((Long) httpServletRequest.getSession().getAttribute("employee"));

        employeeService.save(employee);
        return Result.success("新增成功");
    }

    @GetMapping("/page")
    public Result<Page> list(Integer page, Integer pageSize, String name) {

        //构造分页构造器
        Page pageinfo = new Page(page, pageSize);

        //添加构造器
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        //添加条件
        queryWrapper.like(StringUtils.isNotEmpty(name), Employee::getName, name);
        //添加排序条件
        queryWrapper.orderByDesc(Employee::getUpdateTime);


        employeeService.page(pageinfo, queryWrapper);

        return Result.success(pageinfo);
    }


    @PutMapping
    public Result<String> updatastatus(@RequestBody Employee employee, HttpServletRequest request) {
        log.info("设置状态*******" + employee.getId());
//        employee.setUpdateUser((Long) request.getSession().getAttribute("employee"));
//        employee.setUpdateTime(LocalDateTime.now());
        employeeService.updateById(employee);
        return Result.success("状态修改成功");
    }

    @GetMapping("/{id}")
    public Result<Employee> getbyid(@PathVariable Long id) {

        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getId,id);
        Employee login = employeeService.getOne(queryWrapper);
        if (login != null){
            return Result.success(login);
        }
        return Result.error("操作失败");


    }
}
