package com.xinkai.admin.boot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinkai.admin.boot.pojo.entity.PermissionEntity;
import com.xinkai.admin.boot.pojo.query.PermPageQuery;
import com.xinkai.admin.boot.pojo.vo.PermPageVO;

/**
 * @author xinkai
 * @className com.xinkai.admin.boot.service.PermissionService
 * @description 权限服务
 * @email xinkai8011@gmail.com
 * @date 2024/04/21
 **/
public interface PermissionService extends IService<PermissionEntity> {

    /**
     * 刷新Redis缓存中角色菜单的权限规则
     * 角色和菜单信息变更调用
     *
     * @return {@link Boolean}
     */
    Boolean refreshPermRolesRules();

    /**
     * 获取权限分页列表
     *
     * @param permPageQuery 权限分页列表查询
     * @return {@link IPage}<{@link PermPageVO}>
     */
    IPage<PermPageVO> listPermPages(PermPageQuery permPageQuery);
}