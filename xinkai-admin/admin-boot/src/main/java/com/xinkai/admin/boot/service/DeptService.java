package com.xinkai.admin.boot.service;

import cn.hutool.core.lang.tree.Tree;

import java.util.List;

/**
 * @className: DeptService
 * @description: 服务接口
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023-6-21
 **/
public interface DeptService {

    /**
     * 部门列表
     *
     * @return {@link List}<{@link Tree}<{@link Long}>>
     */
    List<Tree<Long>> list();
}