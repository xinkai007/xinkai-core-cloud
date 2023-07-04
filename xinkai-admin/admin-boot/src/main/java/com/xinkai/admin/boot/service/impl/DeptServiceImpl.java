package com.xinkai.admin.boot.service.impl;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeUtil;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.xinkai.admin.boot.mapper.DeptMapper;
import com.xinkai.admin.boot.pojo.entity.DeptEntity;
import com.xinkai.admin.boot.service.DeptService;
import com.xinkai.common.core.constant.GlobalConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className: DeptServiceImpl
 * @description: 服务实现类
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023-6-21
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class DeptServiceImpl implements DeptService {
    private final DeptMapper deptMapper;

    /**
     * 部门列表
     *
     * @return {@link List}<{@link Tree}<{@link Long}>>
     */
    @Override
    public List<Tree<Long>> list() {
        List<DeptEntity> deptList = deptMapper.selectList(new MPJLambdaWrapper<DeptEntity>()
                .eq(DeptEntity::getStatus, GlobalConstants.STATUS_YES)
                .orderByAsc(DeptEntity::getSort)
        );
        return TreeUtil.<DeptEntity, Long>
                build(deptList, 0L, (treeNode, tree) -> {
            tree.setId(treeNode.getId());
            tree.putExtra("value", treeNode.getId());
            tree.putExtra("label", treeNode.getName());
            tree.setParentId(treeNode.getParentId());
        });
    }
}