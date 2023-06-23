package com.xinkai.admin.boot.service.impl;

import com.xinkai.admin.boot.mapper.FieldDictMapper;
import com.xinkai.admin.boot.service.FieldDictService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @className: FieldDictServiceImpl
 * @description: 服务实现类
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023-6-21
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class FieldDictServiceImpl implements FieldDictService {
    private final FieldDictMapper fieldDictMapper;
}