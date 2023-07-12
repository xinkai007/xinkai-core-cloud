package com.xinkai.admin.boot.utils.dict.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xinkai.admin.boot.pojo.vo.UserListVO;
import com.xinkai.admin.boot.utils.dict.DictConvert;
import com.xinkai.admin.boot.utils.dict.DictEnums;
import com.xinkai.admin.boot.utils.dict.DictUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @className: DictConvert
 * @description:
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023/6/30
 **/
@Component
@RequiredArgsConstructor
public class UserDictConvert extends DictConvert<UserListVO> {
    private final DictUtil dictUtil;

    /**
     * dict转换列表
     *
     * @param records 记录
     * @return {@link List}<{@link UserListVO}>
     */
    @Override
    public List<UserListVO> dictConvertList(List<UserListVO> records) {
        return null;
    }

    /**
     * dict转换分页数据
     *
     * @param page 页面
     * @return {@link IPage}<{@link UserListVO}>
     */
    @Override
    public IPage<UserListVO> dictConvertPage(IPage<UserListVO> page) {
        return page.setRecords(page.getRecords().stream().map(e -> {
            Integer gender = e.getGender();
            e.setGenderLabel(dictUtil.valueToLabel(DictEnums.GENDER.getValue(), gender));
            Long deptId = e.getDeptId();
            e.setDeptName(dictUtil.valueToLabel(DictEnums.DEPT.getValue(), deptId));
            return e;
        }).collect(Collectors.toList()));
    }
}
