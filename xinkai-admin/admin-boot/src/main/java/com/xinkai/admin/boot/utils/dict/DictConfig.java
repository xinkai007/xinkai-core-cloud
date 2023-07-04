package com.xinkai.admin.boot.utils.dict;

import cn.hutool.core.text.StrFormatter;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.xinkai.admin.boot.mapper.DictItemMapper;
import com.xinkai.admin.boot.pojo.entity.DictItemEntity;
import com.xinkai.admin.boot.utils.redis.RedisUtil;
import com.xinkai.common.core.constant.GlobalConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.xinkai.common.redis.constant.RedisKeyPrefixConstants.DICT_REDIS_KEY;

/**
 * @className: DictConfig
 * @description: 词典数据配置
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023/6/26
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class DictConfig implements ApplicationListener<ContextRefreshedEvent> {

    private final DictItemMapper dictItemMapper;

    private final RedisUtil redisUtil;

    /**
     * 加载词典列表
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("DictConfig.onApplicationEvent start");
        List<DictItem> dictItems = dictItemMapper.selectJoinList(DictItem.class, new MPJLambdaWrapper<DictItemEntity>()
                .selectAs(DictItemEntity::getTypeCode, DictItem::getCode)
                .selectAs(DictItemEntity::getValue, DictItem::getValue)
                .selectAs(DictItemEntity::getName, DictItem::getName)
                .ne(DictItemEntity::getIsDelete, GlobalConstants.STATUS_YES)
        );
        log.info("DictConfig.onApplicationEvent end dictItems:{}", dictItems);
        this.initDict(dictItems);
    }

    public void initDict(List<DictItem> dictItemList) {
        log.info("DictConfig.initDict start dictItemList:{}", dictItemList);
        dictItemList.stream().parallel().forEach(e -> {
            String code = e.getCode();
            String value = e.getValue();
            String name = e.getName();
            String key = StrFormatter.format(DICT_REDIS_KEY, code, value).toUpperCase();
            log.info("DictConfig.initDict key:{} value:{} name:{}", key, value, name);
            redisUtil.set(key, name);
        });
    }
}
