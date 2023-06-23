package com.xinkai.common.web.utils;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.xinkai.common.core.constant.SecurityConstants;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * @className: com.xinkai.common.web.utils.JwtUtils
 * @description: JWT工具类
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023/06/22
 **/
@Slf4j
public class JwtUtils {

    /**
     * 得到jwt载荷
     *
     * @return {@link JSONObject}
     */
    @SneakyThrows
    public static JSONObject getJwtPayload() {
        JSONObject jsonObject = null;
        String payload = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader(SecurityConstants.JWT.PAYLOAD_KEY);
        if (CharSequenceUtil.isNotBlank(payload)) {
            jsonObject = JSONUtil.parseObj(URLDecoder.decode(payload, StandardCharsets.UTF_8.name()));
        }
        return jsonObject;
    }
}
