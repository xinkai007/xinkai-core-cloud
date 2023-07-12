package com.xinkai.admin.boot.utils.dict;

import cn.hutool.core.text.StrFormatter;
import com.xinkai.admin.boot.utils.redis.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.xinkai.common.redis.constant.RedisKeyPrefixConstants.DICT_REDIS_KEY;

/**
 * @className: DictUtil
 * @description: 词典工具类
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023/6/26
 **/
@Component
@RequiredArgsConstructor
public class DictUtil {
    private final RedisUtil redisUtil;

    /**
     * 值标签
     * 值转标签
     *
     * @param value 价值
     * @param code  代码
     * @return {@link String}
     */
    public String valueToLabel(String code, Object value) {
        String key = StrFormatter.format(DICT_REDIS_KEY, code, value);
        return redisUtil.get(key);
    }
}
