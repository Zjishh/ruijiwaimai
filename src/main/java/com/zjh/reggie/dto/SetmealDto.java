package com.zjh.reggie.dto;


import com.zjh.reggie.entity.Setmeal;
import com.zjh.reggie.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
