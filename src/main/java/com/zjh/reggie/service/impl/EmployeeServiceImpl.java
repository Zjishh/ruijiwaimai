package com.zjh.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjh.reggie.entity.Employee;
import com.zjh.reggie.mapper.EmployeeMapper;
import com.zjh.reggie.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/****************************
 * @project reggie
 * @package com.zjh.reggie.service.impl
 * @className EmployeeServiceImpl
 * @author Zjiah
 * @date 2023/10/18 10:59
 * @Description:   *
 ****************************/
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService{

    @Autowired
    private EmployeeMapper employeeMapper;
    /**
     * 员工登录
     * @param employee
     */
//    @Override
//    public Employee login(Employee employee) {
//        String password = employee.getPassword();
//        employee.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
//        return employeeMapper.login(employee);
//    }
}
