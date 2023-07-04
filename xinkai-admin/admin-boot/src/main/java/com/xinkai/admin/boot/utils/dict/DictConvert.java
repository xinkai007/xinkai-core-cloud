package com.xinkai.admin.boot.utils.dict;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @className: DictConvert
 * @description:
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023/6/30
 **/
public abstract class DictConvert<T> {
    public abstract List<T> dictConvertList(List<T> records);

    public abstract IPage<T> dictConvertPage(IPage<T> page);
}
