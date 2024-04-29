package com.xinkai.admin.boot.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinkai.admin.boot.pojo.entity.DictItemEntity;
import com.xinkai.admin.boot.pojo.from.DictItemForm;
import com.xinkai.admin.boot.pojo.vo.DictItemPageVO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

/**
 * 字典数据项对象转换器
 *
 * @author xinkai
 * @date 2022/6/8
 */
@Mapper(componentModel = "spring")
public interface DictItemConverter {

    Page<DictItemPageVO> entity2Page(Page<DictItemEntity> page);

    DictItemForm entity2Form(DictItemEntity entity);

    @InheritInverseConfiguration(name = "entity2Form")
    DictItemEntity form2Entity(DictItemForm entity);
}
