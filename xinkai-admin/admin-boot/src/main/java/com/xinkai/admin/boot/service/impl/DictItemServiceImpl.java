package com.xinkai.admin.boot.service.impl;

import com.xinkai.admin.boot.mapper.DictItemMapper;
import com.xinkai.admin.boot.service.DictItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @className: DictItemServiceImpl
 * @description: 服务实现类
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023-6-21
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class DictItemServiceImpl implements DictItemService {
    private final DictItemMapper dictItemMapper;
}