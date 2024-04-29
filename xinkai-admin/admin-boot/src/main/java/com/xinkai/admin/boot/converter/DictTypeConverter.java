package com.xinkai.admin.boot.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinkai.admin.boot.pojo.entity.DictTypeEntity;
import com.xinkai.admin.boot.pojo.from.DictTypeForm;
import com.xinkai.admin.boot.pojo.vo.DictTypePageVO;
import org.mapstruct.Mapper;

/**
 * @author xinkai
 * @className com.xinkai.admin.boot.converter.DictTypeConverter
 * @description 字典类型对象转换器
 * @email xinkai8011@gmail.com
 * @date 2024/04/21
 **/
@Mapper(componentModel = "spring")
public interface DictTypeConverter {

    Page<DictTypePageVO> entity2Page(Page<DictTypeEntity> page);

    DictTypeForm entity2Form(DictTypeEntity entity);

    DictTypeEntity form2Entity(DictTypeForm entity);
}
