package com.xinkai.admin.boot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.xinkai.admin.boot.constant.AdminConstants;
import com.xinkai.admin.boot.mapper.DeptMapper;
import com.xinkai.admin.boot.pojo.dto.DeptInfoDTO;
import com.xinkai.admin.boot.pojo.entity.DeptEntity;
import com.xinkai.admin.boot.pojo.query.DeptListQuery;
import com.xinkai.admin.boot.pojo.vo.DeptListVO;
import com.xinkai.admin.boot.service.DeptService;
import com.xinkai.common.core.constant.GlobalConstants;
import com.xinkai.common.core.result.ResultCode;
import com.xinkai.common.web.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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

    /**
     * 搜索
     * 部门搜索
     *
     * @param deptListQuery 部门列表查询
     * @return {@link List}<{@link DeptListVO}>
     */
    @Override
    public List<DeptListVO> search(DeptListQuery deptListQuery) {
        try {
            Integer status = deptListQuery.getStatus();
            String name = deptListQuery.getName();
            List<DeptEntity> deptList = deptMapper.selectList(new MPJLambdaWrapper<DeptEntity>()
                    .select(DeptEntity::getId,
                            DeptEntity::getName,
                            DeptEntity::getParentId,
                            DeptEntity::getSort,
                            DeptEntity::getStatus,
                            DeptEntity::getTreePath)
                    .eq(!Objects.equals(status, null) && !Objects.equals(status, -1), DeptEntity::getStatus, status)
                    .like(CharSequenceUtil.isNotEmpty(name), DeptEntity::getName, name)
            );
            List<DeptListVO> deptTableList = new ArrayList<>();
            Set<Long> nodeIdSet = deptList.stream()
                    .map(DeptEntity::getId)
                    .collect(Collectors.toSet());
            for (DeptEntity dept : deptList) {
                //不在节点id集合中存在的id即为顶级节点id
                Long parentId = dept.getParentId();
                if (!nodeIdSet.contains(parentId)) {
                    deptTableList.addAll(recurTableDept(parentId, deptList));
                    nodeIdSet.add(parentId);
                }
            }
            if (deptTableList.isEmpty()) {
                return deptList.stream()
                        .map(item -> {
                            DeptListVO deptVO = new DeptListVO();
                            BeanUtil.copyProperties(item, deptVO);
                            return deptVO;
                        })
                        .collect(Collectors.toList());
            }
            return deptTableList;
        } catch (Exception e) {
            throw new BusinessException(ResultCode.SYSTEM_EXECUTION_ERROR);
        }
    }

    /**
     * 添加部门
     *
     * @param deptInfoDTO 部门信息dto
     * @return {@link Boolean}
     */
    @Override
    public Boolean add(DeptInfoDTO deptInfoDTO) {
        Long parentId = deptInfoDTO.getParentId();
        return new DeptEntity()
                .setName(deptInfoDTO.getName())
                .setSort(deptInfoDTO.getSort())
                .setStatus(deptInfoDTO.getStatus())
                .setParentId(parentId)
                .setTreePath(generateDeptTreePath(parentId))
                .insert();
    }

    /**
     * 部门详情
     *
     * @param id id
     * @return {@link DeptInfoDTO}
     */
    @Override
    public DeptInfoDTO detail(Long id) {
        DeptEntity dept = Db.getById(id, DeptEntity.class);
        return new DeptInfoDTO().setId(id)
                .setName(dept.getName())
                .setStatus(dept.getStatus())
                .setSort(dept.getSort())
                .setParentId(dept.getParentId());
    }

    /**
     * 修改部门信息
     *
     * @param deptInfoDTO 部门信息dto
     * @return {@link Boolean}
     */
    @Override
    public Boolean update(DeptInfoDTO deptInfoDTO) {
        Long parentId = deptInfoDTO.getParentId();
        return new DeptEntity()
                .setId(deptInfoDTO.getId())
                .setName(deptInfoDTO.getName())
                .setSort(deptInfoDTO.getSort())
                .setStatus(deptInfoDTO.getStatus())
                .setParentId(parentId)
                .setTreePath(generateDeptTreePath(parentId))
                .updateById();
    }

    /**
     * 删除部门
     *
     * @param deptIds 部门id
     * @return {@link Boolean}
     */
    @Override
    public Boolean delete(String deptIds) {
        Assert.isTrue(CharSequenceUtil.isNotBlank(deptIds), () -> new BusinessException(ResultCode.USER_ID_NULL));
        // 逻辑删除
        List<Long> ids = Arrays.stream(deptIds.split(","))
                .map(Long::parseLong).collect(Collectors.toList());
        return Db.removeByIds(ids, DeptEntity.class);
    }

    /**
     * 递归生成部门
     *
     * @param parentId 父id
     * @param deptList 部门列表
     * @return {@link List}<{@link DeptListVO}>
     */
    public static List<DeptListVO> recurTableDept(Long parentId, List<DeptEntity> deptList) {
        List<DeptListVO> deptTableList = new ArrayList<>();
        Optional.ofNullable(deptList).orElse(new ArrayList<>())
                .stream()
                .filter(dept -> dept.getParentId().equals(parentId))
                .forEach(dept -> {
                    DeptListVO deptVO = new DeptListVO();
                    BeanUtil.copyProperties(dept, deptVO);
                    List<DeptListVO> children = recurTableDept(dept.getId(), deptList);
                    deptVO.setChildren(children);
                    deptTableList.add(deptVO);
                });
        return deptTableList;
    }

    /**
     * 生成部门树路径
     *
     * @param parentId 父id
     * @return {@link String}
     */
    private String generateDeptTreePath(Long parentId) {
        String treePath;
        if (parentId.equals(AdminConstants.ROOT_DEPT_ID)) {
            treePath = String.valueOf(AdminConstants.ROOT_DEPT_ID);
        } else {
            DeptEntity parentDept = Db.getById(parentId, DeptEntity.class);
            treePath = Optional.ofNullable(parentDept).map(item -> item.getTreePath() + "," + item.getId()).orElse(Strings.EMPTY);
        }
        return treePath;
    }
}