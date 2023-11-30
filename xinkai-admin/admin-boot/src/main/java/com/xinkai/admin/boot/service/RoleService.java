package com.xinkai.admin.boot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xinkai.admin.boot.pojo.dto.RoleDTO;
import com.xinkai.admin.boot.pojo.dto.RoleMenuPermDTO;
import com.xinkai.admin.boot.pojo.query.RoleListQuery;
import com.xinkai.admin.boot.pojo.query.RoleOptionsQuery;
import com.xinkai.admin.boot.pojo.vo.RoleInfoVO;
import com.xinkai.admin.boot.pojo.vo.RoleMenuPermVO;
import com.xinkai.admin.boot.pojo.vo.RoleOptionsVO;

/**
 * @className: RoleService
 * @description: 服务接口
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023-6-21
 **/
public interface RoleService {

    /**
     * 获取角色列表
     *
     * @param roleOptionsQuery 角色选择查询
     * @return {@link IPage}<{@link RoleOptionsVO}>
     */
    IPage<RoleOptionsVO> list(RoleOptionsQuery roleOptionsQuery);

    /**
     * 角色列表查询
     *
     * @param roleListQuery 角色列表查询
     * @return {@link IPage}<{@link RoleInfoVO}>
     */
    IPage<RoleInfoVO> pages(RoleListQuery roleListQuery);

    /**
     * 角色详情
     *
     * @param roleId 角色ID
     * @return {@link RoleInfoVO}
     */
    RoleInfoVO detail(Long roleId);

    /**
     * 更新角色
     *
     * @param roleDTO 角色Dto
     * @return {@link Boolean}
     */
    Boolean update(RoleDTO roleDTO);

    /**
     * 删除角色
     *
     * @param roleIds 角色ID
     * @return {@link Boolean}
     */
    Boolean delete(String roleIds);

    /**
     * 获取角色资源
     *
     * @param roleId 角色ID
     * @return {@link RoleMenuPermVO}
     */
    RoleMenuPermVO getRoleResources(Long roleId);

    /**
     * 修改角色的资源权限
     *
     * @param roleId          角色ID
     * @param roleMenuPermDTO 角色菜单权限dto
     * @return boolean
     */
    boolean updateRoleResource(Long roleId, RoleMenuPermDTO roleMenuPermDTO);

}