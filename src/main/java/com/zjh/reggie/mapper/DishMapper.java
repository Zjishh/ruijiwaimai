package com.zjh.reggie.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/****************************
 * @project reggie
 * @package com.zjh.reggie.mapper
 * @className DishMapper
 * @author Zjiah
 * @date 2023/10/20 13:18
 * @Description:   *
 ****************************/
@Mapper
public interface DishMapper {


    void ddelete(Long ids);

    @Select("SELECT count(*) from dish where category_id = #{ids}")
    Integer dselectcount(Long ids);
}
