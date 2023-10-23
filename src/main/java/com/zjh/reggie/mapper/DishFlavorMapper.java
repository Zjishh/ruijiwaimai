package com.zjh.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjh.reggie.dto.DishDto;
import com.zjh.reggie.entity.DishFlavor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/****************************
 * @project reggie
 * @package com.zjh.reggie.mapper
 * @className DishFlavorMapper
 * @author Zjiah
 * @date 2023/10/22 20:20
 * @Description:   *
 ****************************/
@Mapper
public interface DishFlavorMapper extends BaseMapper<DishFlavor> {

    @Delete("delete from dish_flavor where dish_id = #{id}")
    void deleteDishFlavor(Long id);


    // void addDishFlavor(DishFlavor dishFlavor);
}
