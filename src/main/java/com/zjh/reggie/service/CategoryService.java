package com.zjh.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjh.reggie.entity.Category;
import com.zjh.reggie.entity.Employee;

import java.util.List;

/****************************
 * @project reggie
 * @package com.zjh.reggie.service
 * @className CategoryService
 * @author Zjiah
 * @date 2023/10/19 17:54
 * @Description:   *
 ****************************/
public interface CategoryService extends IService<Category> {


    void csave(Category category);

    void cupdate(Category category);

    void cdelete(Long id);
}
