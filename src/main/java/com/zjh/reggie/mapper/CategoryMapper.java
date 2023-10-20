package com.zjh.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjh.reggie.entity.Category;
import com.zjh.reggie.entity.Employee;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
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


    @Insert("insert into category(id, type, name, sort,create_time, update_time, create_user, update_user) value (#{id},#{type},#{name},#{sort},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    void scave(Category category);


    void cupdate();

    @Delete("delete from category where id = #{id}")
    void cdelete(Long id);

//    @Select("select * from category limit #{pageSize}")
//    List<Category> list(Integer page, Integer pageSize);
}
