package com.zjh.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjh.reggie.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/****************************
 * @project reggie
 * @package com.zjh.reggie.mapper
 * @className EmployeeMapper
 * @author Zjiah
 * @date 2023/10/18 10:58
 * @Description:   *
 ****************************/
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
//    @Select("select * from employee where username = #{username} and password = #{password} and status = 1")
//    Employee login(Employee employee);
}
