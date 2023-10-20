package com.zjh.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjh.reggie.entity.Category;
import com.zjh.reggie.entity.Employee;
import com.zjh.reggie.mapper.CategoryMapper;
import com.zjh.reggie.mapper.EmployeeMapper;
import com.zjh.reggie.service.CategoryService;
import com.zjh.reggie.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/****************************
 * @project reggie
 * @package com.zjh.reggie.service.impl
 * @className CategoryServiceimpl
 * @author Zjiah
 * @date 2023/10/19 17:54
 * @Description:   *
 ****************************/
@Service
@Slf4j
public class CategoryServiceimpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public void csave(Category category) {

     categoryMapper.scave(category);

    }

    @Override
    public void cupdate(Category category) {
        categoryMapper.cupdate();
    }

    @Override
    public void cdelete(Long id) {
        categoryMapper.cdelete(id);
    }
}

