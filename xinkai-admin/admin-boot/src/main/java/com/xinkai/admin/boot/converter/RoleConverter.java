package com.xinkai.admin.boot.converter;

import com.xinkai.admin.boot.pojo.entity.RoleEntity;
import com.xinkai.admin.boot.pojo.from.RoleForm;
import org.mapstruct.Mapper;

/**
 * 角色对象转换器
 *
 * @author xinkai
 * @date 2022/5/29
 */
@Mapper(componentModel = "spring")
public interface RoleConverter {

    RoleEntity form2Entity(RoleForm roleForm);
}