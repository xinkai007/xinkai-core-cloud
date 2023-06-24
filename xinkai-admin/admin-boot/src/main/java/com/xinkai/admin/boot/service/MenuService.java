package com.xinkai.admin.boot.service;

import cn.hutool.core.lang.tree.Tree;

import java.util.List;

/**
 * @className: MenuService
 * @description: 菜单服务接口
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023-6-21
 **/
public interface MenuService {

    /**
     * 获取路线
     * 获取用户导航栏菜单信息
     *
     * @return {@link List}<{@link Tree}<{@link Long}>>
     */
    List<Tree<Long>> routes();
}