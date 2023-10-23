package com.zjh.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjh.reggie.dto.DishDto;
import com.zjh.reggie.entity.Category;
import com.zjh.reggie.entity.Dish;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/****************************
 * @project reggie
 * @package com.zjh.reggie.mapper
 * @className DishMapper
 * @author Zjiah
 * @date 2023/10/20 13:18
 * @Description:   *
 ****************************/
@Mapper
public interface DishMapper extends BaseMapper<Dish> {


    @Insert("insert into dish (id, name, category_id, price, code, image," +
            " description, create_time, update_time, create_user, update_user) " +
            "value (#{id},#{name},#{categoryId},#{price},#{code},#{image}" +
            ",#{description},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    void addDish(DishDto dishDto);

    void ddelete(Long ids);

    @Select("SELECT count(*) from dish where category_id = #{ids}")
    Integer dselectcount(Long ids);


    @Update("update dish set name = #{name} ,category_id = #{categoryId},price=#{price},code=#{code},image=#{image},description=#{description}," +
            "create_time=#{createTime},update_time=#{updateTime},create_user=#{createUser},update_user=#{updateUser} where id = #{id}")
    void updataDish(DishDto dishDto);
}
