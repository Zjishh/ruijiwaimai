package com.zjh.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjh.reggie.entity.SetmealDish;
import org.apache.ibatis.annotations.Mapper;

/****************************
 * @project reggie
 * @package com.zjh.reggie.mapper
 * @className SetMealDishMapper
 * @author Zjiah
 * @date 2023/10/23 20:30
 * @Description:   *
 ****************************/
@Mapper
public interface SetMealDishMapper extends BaseMapper<SetmealDish> {

//    @Insert("INSERT INTO setmeal (id, category_id, name, price, status, code, description, image, create_time, update_time, create_user, update_user)" +
//            " VALUE (#{id},#{categoryId},#{name},#{price},#{status},#{code},#{description},#{image},#{createTime},#{updateTime},#{createUser},#{updateUser})")
//    void addSetMeal(SetmealDto setmealDto);
}
