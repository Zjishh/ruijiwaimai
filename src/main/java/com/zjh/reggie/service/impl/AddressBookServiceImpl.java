package com.zjh.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjh.reggie.entity.AddressBook;
import com.zjh.reggie.mapper.AddressBookMapper;
import com.zjh.reggie.service.AddressBookService;
import org.springframework.stereotype.Service;

/****************************
 * @project reggie
 * @package com.zjh.reggie.service.impl
 * @className AddressBookServiceImpl
 * @author Zjiah
 * @date 2023/10/25 14:13
 * @Description:   *
 ****************************/
@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {
}
