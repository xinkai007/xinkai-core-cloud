package com.xinkai.admin.boot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xinkai.admin.boot.pojo.query.RoleListQuery;
import com.xinkai.admin.boot.pojo.query.RoleOptionsQuery;
import com.xinkai.admin.boot.pojo.vo.RoleInfoVO;
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

}