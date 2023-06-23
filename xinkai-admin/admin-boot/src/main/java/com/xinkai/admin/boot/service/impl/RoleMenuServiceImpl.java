package com.xinkai.admin.boot.service.impl;

import com.xinkai.admin.boot.mapper.RoleMenuMapper;
import com.xinkai.admin.boot.service.RoleMenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @className: RoleMenuServiceImpl
 * @description: 服务实现类
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023-6-21
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class RoleMenuServiceImpl implements RoleMenuService {
    private final RoleMenuMapper roleMenuMapper;
}