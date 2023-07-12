package com.xinkai.admin.boot.service.impl;

import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.xinkai.admin.boot.mapper.RoleMapper;
import com.xinkai.admin.boot.pojo.entity.RoleEntity;
import com.xinkai.admin.boot.pojo.entity.UserEntity;
import com.xinkai.admin.boot.pojo.query.RoleOptionsQuery;
import com.xinkai.admin.boot.pojo.vo.RoleOptionsVO;
import com.xinkai.admin.boot.service.RoleService;
import com.xinkai.common.core.exception.SystemException;
import com.xinkai.common.core.result.ResultCode;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.xinkai.common.core.constant.GlobalConstants.STATUS_YES;

/**
 * @className: RoleServiceImpl
 * @description: 服务实现类
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023-6-21
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleMapper roleMapper;

    /**
     * 获取角色列表
     *
     * @param roleOptionsQuery 角色选择查询
     * @return {@link IPage}<{@link RoleOptionsVO}>
     */
    @Override
    @SneakyThrows
    public IPage<RoleOptionsVO> list(RoleOptionsQuery roleOptionsQuery) {
        try {
            String name = roleOptionsQuery.getName();
            return roleMapper.selectJoinPage(new Page<RoleOptionsVO>(roleOptionsQuery.getPageNum(), roleOptionsQuery.getPageSize()),
                    RoleOptionsVO.class,
                    new MPJLambdaWrapper<RoleEntity>()
                            .selectAs(RoleEntity::getId, RoleOptionsVO::getId)
                            .selectAs(RoleEntity::getName, RoleOptionsVO::getName)
                            .eq(UserEntity::getStatus, STATUS_YES)
                            .and(CharSequenceUtil.isNotEmpty(name), e -> e.like(RoleEntity::getName, name)));
        } catch (Exception e) {
            log.error("RoleServiceImpl.list e:", e);
            throw new SystemException(ResultCode.SYSTEM_EXECUTION_ERROR);
        }
    }
}