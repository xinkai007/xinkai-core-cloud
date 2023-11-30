package com.xinkai.admin.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinkai.admin.boot.mapper.RoleMenuMapper;
import com.xinkai.admin.boot.pojo.entity.RoleMenuEntity;
import com.xinkai.admin.boot.service.RoleMenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author xinkai
 * @className com.xinkai.admin.boot.service.impl.RoleMenuServiceImpl
 * @description 角色菜单服务实施
 * @email xinkai8011@gmail.com
 * @date 2023/11/30
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenuEntity> implements RoleMenuService {
    private final RoleMenuMapper roleMenuMapper;
}