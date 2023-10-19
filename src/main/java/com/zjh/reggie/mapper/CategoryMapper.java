package com.zjh.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjh.reggie.entity.Category;
import com.zjh.reggie.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/****************************
 * @project reggie
 * @package com.zjh.reggie.mapper
 * @className CategoryMapper.xml
 * @author Zjiah
 * @date 2023/10/19 17:52
 * @Description:   *
 ****************************/
@Mapper
public interface CategoryMapper  extends BaseMapper<Category> {

//    @Select("select * from category limit #{pageSize}")
//    List<Category> list(Integer page, Integer pageSize);
}
