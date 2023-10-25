package com.zjh.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjh.reggie.entity.AddressBook;
import org.apache.ibatis.annotations.Mapper;

/****************************
 * @project reggie
 * @package com.zjh.reggie.mapper
 * @className AddressBookMapper
 * @author Zjiah
 * @date 2023/10/25 14:12
 * @Description:   *
 ****************************/
@Mapper
public interface AddressBookMapper extends BaseMapper<AddressBook> {
}
