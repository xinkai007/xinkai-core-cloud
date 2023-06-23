package com.xinkai.auth.security.core.client;

import com.xinkai.admin.api.dto.ClientAuthDTO;
import com.xinkai.admin.api.feign.AdminFeign;
import com.xinkai.auth.common.enums.PasswordEncoderTypeEnum;
import com.xinkai.common.core.result.Result;
import com.xinkai.common.core.result.ResultCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

/**
 * @className: com.xinkai.auth.security.core.client.ClientDetailsServiceImpl
 * @description: 客户端详细信息
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023/06/20
 **/
@Slf4j
@Primary
@Service
@RequiredArgsConstructor
public class ClientDetailsServiceImpl implements ClientDetailsService {

    private final AdminFeign adminFeign;

    /**
     * 加载客户端信息
     * 管理系统：admin-web
     * Cacheable 将客户端ID存入到spring缓存中
     *
     * @param clientId 客户端id
     * @return {@link ClientDetails}
     * @throws ClientRegistrationException 客户端注册异常
     */
    @Override
    @Cacheable(cacheNames = "auth", key = "'oauth-client:' + #clientId")
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        try {
            Result<ClientAuthDTO> result = adminFeign.getClientById(clientId);
            //如果服务调用成功则将获取到可客户端信息保存在ClientDetails
            if (ResultCode.SUCCESS.getCode().equals(result.getCode())) {
                ClientAuthDTO client = result.getData();
                BaseClientDetails clientDetails = new BaseClientDetails(
                        client.getClientId(),
                        client.getResourceIds(),
                        client.getScope(),
                        client.getAuthorizedGrantTypes(),
                        client.getAuthorities(),
                        client.getWebServerRedirectUri()
                );
                clientDetails.setClientSecret(PasswordEncoderTypeEnum.NOOP.getPrefix() + client.getClientSecret());
                clientDetails.setAccessTokenValiditySeconds(client.getAccessTokenValidity());
                clientDetails.setRefreshTokenValiditySeconds(client.getRefreshTokenValidity());
                return clientDetails;
            } else {
                throw new NoSuchClientException("No client with requested id: " + clientId);
            }
        } catch (EmptyResultDataAccessException e) {
            throw new NoSuchClientException("No client with requested id: " + clientId);
        }
    }
}
