package com.xinkai.admin.boot.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.base.MPJBaseMapper;
import com.xinkai.admin.boot.pojo.entity.PermissionEntity;
import com.xinkai.admin.boot.pojo.query.PermPageQuery;
import com.xinkai.admin.boot.pojo.vo.PermPageVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @className: PermissionMapper
 * @description: 权限表
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023-6-21
 **/
@Mapper
public interface PermissionMapper extends MPJBaseMapper<PermissionEntity> {

    /**
     * 获取权限分页列表
     *
     * @param page        页面
     * @param queryParams 查询参数
     * @return {@link List}<{@link PermPageVO}>
     */
    List<PermPageVO> listPermPages(Page<PermPageVO> page, PermPageQuery queryParams);
}