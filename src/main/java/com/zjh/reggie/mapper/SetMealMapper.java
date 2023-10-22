package com.zjh.reggie.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/****************************
 * @project reggie
 * @package com.zjh.reggie.mapper
 * @className SetMealMapper
 * @author Zjiah
 * @date 2023/10/21 8:40
 * @Description:   *
 ****************************/
@Mapper
public interface SetMealMapper {

    @Select("select count(*) from setmeal where category_id = #{id};")
    Integer sselectcount(Long id);
}
