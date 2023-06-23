package com.xinkai.admin.boot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import com.xinkai.admin.api.dto.ClientAuthDTO;
import com.xinkai.admin.boot.mapper.AuthClientMapper;
import com.xinkai.admin.boot.pojo.entity.AuthClientEntity;
import com.xinkai.admin.boot.service.AuthClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @className: AuthClientServiceImpl
 * @description: 服务实现类
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023-6-21
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthClientServiceImpl implements AuthClientService {
    private final AuthClientMapper authClientMapper;

    @Override
    public ClientAuthDTO getClientById(String clientId) {
        AuthClientEntity client = new AuthClientEntity().selectById(clientId);
        Assert.isTrue(client != null, "OAuth2 客户端不存在！");
        return BeanUtil.copyProperties(client, ClientAuthDTO.class);
    }
}