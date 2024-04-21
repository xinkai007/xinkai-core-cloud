package com.xinkai.admin.boot.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinkai.admin.boot.pojo.entity.MenuEntity;
import com.xinkai.admin.boot.pojo.vo.MenuPermVO;
import com.xinkai.admin.boot.pojo.vo.MenuVO;
import com.xinkai.common.mybatis.base.Option;

import java.util.List;

/**
 * @className: MenuService
 * @description: 菜单服务接口
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023-6-21
 **/
public interface MenuService extends IService<MenuEntity> {

    /**
     * 列表
     *
     * @param name 名字
     * @return {@link List}<{@link MenuVO}>
     */
    List<MenuVO> list(String name);

    /**
     * 获取路线
     * 获取用户导航栏菜单信息
     *
     * @return {@link List}<{@link Tree}<{@link Long}>>
     */
    List<Tree<Long>> routes();

    /**
     * 获取菜单资源
     *
     * @return {@link MenuPermVO}
     */
    MenuPermVO getMenuResources();

    /**
     * 获取菜单选项
     *
     * @return {@link List}<{@link Option}<{@link Long}>>
     */
    List<Option<Long>> getMenuOptions();

    /**
     * 添加菜单
     *
     * @param menuEntity 菜单实体
     * @return boolean
     */
    boolean saveMenu(MenuEntity menuEntity);
}