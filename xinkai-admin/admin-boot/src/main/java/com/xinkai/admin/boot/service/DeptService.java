package com.xinkai.admin.boot.service;

import cn.hutool.core.lang.tree.Tree;
import com.xinkai.admin.boot.pojo.dto.DeptInfoDTO;
import com.xinkai.admin.boot.pojo.query.DeptListQuery;
import com.xinkai.admin.boot.pojo.vo.DeptListVO;

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

    /**
     * 搜索
     * 部门搜索
     *
     * @param deptListQuery 部门列表查询
     * @return {@link List}<{@link DeptListVO}>
     */
    List<DeptListVO> search(DeptListQuery deptListQuery);

    /**
     * 添加部门
     *
     * @param deptInfoDTO 部门信息dto
     * @return {@link Boolean}
     */
    Boolean add(DeptInfoDTO deptInfoDTO);

    /**
     * 部门详情
     *
     * @param id id
     * @return {@link DeptInfoDTO}
     */
    DeptInfoDTO detail(Long id);

    /**
     * 修改部门信息
     *
     * @param deptInfoDTO 部门信息dto
     * @return {@link Boolean}
     */
    Boolean update(DeptInfoDTO deptInfoDTO);

    /**
     * 删除部门
     *
     * @param ids id
     * @return {@link Boolean}
     */
    Boolean delete(String ids);
}