package com.xinkai.admin.boot.utils.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

/**
 * @className: RedisUtil
 * @description: Redis工具类
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023/7/4
 **/
@Component
@RequiredArgsConstructor
public class RedisUtil {

    private final StringRedisTemplate redisTemplate;
    
    /*-------------------key相关操作--------------------- */

    /**
     * 删除key
     *
     * @param key 关键
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 批量删除key
     *
     * @param keys 键
     */
    public void delete(Collection<String> keys) {
        redisTemplate.delete(keys);
    }

    /**
     * 序列化key
     *
     * @param key 关键
     * @return {@link byte[]}
     */
    public byte[] dump(String key) {
        return redisTemplate.dump(key);
    }

    /**
     * 是否存在key
     *
     * @param key 关键
     * @return {@link Boolean}
     */
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 设置过期时间
     *
     * @param key     关键
     * @param timeout 超时
     * @param unit    单位
     * @return {@link Boolean}
     */
    public Boolean expire(String key, long timeout, TimeUnit unit) {
        return redisTemplate.expire(key, timeout, unit);
    }

    /**
     * 设置过期时间
     *
     * @param key  关键
     * @param date 日期
     * @return {@link Boolean}
     */
    public Boolean expireAt(String key, Date date) {
        return redisTemplate.expireAt(key, date);
    }

    /**
     * 查找匹配的key
     *
     * @param pattern 模式
     * @return {@link Set}<{@link String}>
     */
    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    /**
     * 将当前数据库的 key 移动到给定的数据库 db 当中
     *
     * @param key     关键
     * @param dbIndex 数据库索引
     * @return {@link Boolean}
     */
    public Boolean move(String key, int dbIndex) {
        return redisTemplate.move(key, dbIndex);
    }

    /**
     * 移除 key 的过期时间，key 将持久保持
     *
     * @param key 关键
     * @return {@link Boolean}
     */
    public Boolean persist(String key) {
        return redisTemplate.persist(key);
    }

    /**
     * 返回 key 的剩余的过期时间
     *
     * @param key  关键
     * @param unit 单位
     * @return {@link Long}
     */
    public Long getExpire(String key, TimeUnit unit) {
        return redisTemplate.getExpire(key, unit);
    }

    /**
     * 返回 key 的剩余的过期时间
     *
     * @param key 关键
     * @return {@link Long}
     */
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * 从当前数据库中随机返回一个 key
     *
     * @return {@link String}
     */
    public String randomKey() {
        return redisTemplate.randomKey();
    }

    /**
     * 修改 key 的名称
     *
     * @param oldKey 旧关键
     * @param newKey 新密钥
     */
    public void rename(String oldKey, String newKey) {
        redisTemplate.rename(oldKey, newKey);
    }

    /**
     * 仅当 newKey 不存在时，将 oldKey 改名为 newKey
     *
     * @param oldKey 旧关键
     * @param newKey 新密钥
     * @return {@link Boolean}
     */
    public Boolean renameIfAbsent(String oldKey, String newKey) {
        return redisTemplate.renameIfAbsent(oldKey, newKey);
    }

    /**
     * 返回 key 所储存的值的类型
     *
     * @param key 关键
     * @return {@link DataType}
     */
    public DataType type(String key) {
        return redisTemplate.type(key);
    }

    /*-------------------string相关操作--------------------- */

    /**
     * 设置指定 key 的值
     *
     * @param key   关键
     * @param value 价值
     */
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 获取指定 key 的值
     *
     * @param key 关键
     * @return {@link String}
     */
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 返回 key 中字符串值的子字符
     *
     * @param key   关键
     * @param start 开始
     * @param end   结束
     * @return {@link String}
     */
    public String getRange(String key, long start, long end) {
        return redisTemplate.opsForValue().get(key, start, end);
    }

    /**
     * 将给定 key 的值设为 value ，并返回 key 的旧值(old value)
     *
     * @param key   关键
     * @param value 价值
     * @return {@link String}
     */
    public String getAndSet(String key, String value) {
        return redisTemplate.opsForValue().getAndSet(key, value);
    }

    /**
     * 对 key 所储存的字符串值，获取指定偏移量上的位(bit)
     *
     * @param key    关键
     * @param offset 抵消
     * @return {@link Boolean}
     */
    public Boolean getBit(String key, long offset) {
        return redisTemplate.opsForValue().getBit(key, offset);
    }

    /**
     * 批量获取
     *
     * @param keys 键
     * @return {@link List}<{@link String}>
     */
    public List<String> multiGet(Collection<String> keys) {
        return redisTemplate.opsForValue().multiGet(keys);
    }

    /**
     * 设置ASCII码, 字符串'a'的ASCII码是97, 转为二进制是'01100001', 此方法是将二进制第offset位值变为value
     *
     * @param key    位置
     * @param value  值,true为1, false为0
     * @param offset 抵消
     * @return boolean
     */
    public Boolean setBit(String key, long offset, boolean value) {
        return redisTemplate.opsForValue().setBit(key, offset, value);
    }

    /**
     * 将值 value 关联到 key ，并将 key 的过期时间设为 timeout
     *
     * @param timeout 过期时间
     * @param unit    时间单位, 天:TimeUnit.DAYS 小时:TimeUnit.HOURS 分钟:TimeUnit.MINUTES
     *                秒:TimeUnit.SECONDS 毫秒:TimeUnit.MILLISECONDS
     * @param key     关键
     * @param value   价值
     */
    public void setEx(String key, String value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    /**
     * 只有在 key 不存在时设置 key 的值
     *
     * @param key   关键
     * @param value 价值
     * @return 之前已经存在返回false, 不存在返回true
     */
    public Boolean setIfAbsent(String key, String value) {
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    /**
     * 用 value 参数覆写给定 key 所储存的字符串值，从偏移量 offset 开始
     *
     * @param offset 从指定位置开始覆写
     * @param key    关键
     * @param value  价值
     */
    public void setRange(String key, String value, long offset) {
        redisTemplate.opsForValue().set(key, value, offset);
    }

    /**
     * 获取字符串的长度
     *
     * @param key 关键
     * @return {@link Long}
     */
    public Long size(String key) {
        return redisTemplate.opsForValue().size(key);
    }

    /**
     * 批量添加
     *
     * @param maps 地图
     */
    public void multiSet(Map<String, String> maps) {
        redisTemplate.opsForValue().multiSet(maps);
    }

    /**
     * 同时设置一个或多个 key-value 对，当且仅当所有给定 key 都不存在
     *
     * @param maps 地图
     * @return 之前已经存在返回false, 不存在返回true
     */
    public Boolean multiSetIfAbsent(Map<String, String> maps) {
        return redisTemplate.opsForValue().multiSetIfAbsent(maps);
    }

    /**
     * 增加(自增长), 负数则为自减
     *
     * @param key       关键
     * @param increment 增量
     * @return {@link Long}
     */
    public Long incrBy(String key, long increment) {
        return redisTemplate.opsForValue().increment(key, increment);
    }

    /**
     * 增加浮动
     *
     * @param key       关键
     * @param increment 增量
     * @return {@link Double}
     */
    public Double incrByFloat(String key, double increment) {
        return redisTemplate.opsForValue().increment(key, increment);
    }

    /**
     * 追加到末尾
     *
     * @param key   关键
     * @param value 价值
     * @return {@link Integer}
     */
    public Integer append(String key, String value) {
        return redisTemplate.opsForValue().append(key, value);
    }

    /*-------------------hash相关操作------------------------- */

    /**
     * 获取存储在哈希表中指定字段的值
     *
     * @param key   关键
     * @param field 场
     * @return {@link Object}
     */
    public Object hGet(String key, String field) {
        return redisTemplate.opsForHash().get(key, field);
    }

    /**
     * 获取所有给定字段的值
     *
     * @param key 关键
     * @return {@link Map}<{@link Object}, {@link Object}>
     */
    public Map<Object, Object> hGetAll(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 获取所有给定字段的值
     *
     * @param key    关键
     * @param fields 字段
     * @return {@link List}<{@link Object}>
     */
    public List<Object> hMultiGet(String key, Collection<Object> fields) {
        return redisTemplate.opsForHash().multiGet(key, fields);
    }

    /**
     * 塞入值
     *
     * @param key     关键
     * @param hashKey 散列键
     * @param value   价值
     */
    public void hPut(String key, String hashKey, String value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    /**
     * 批量塞入值
     *
     * @param key  关键
     * @param maps 地图
     */
    public void hPutAll(String key, Map<String, String> maps) {
        redisTemplate.opsForHash().putAll(key, maps);
    }

    /**
     * 仅当hashKey不存在时才设置
     *
     * @param key     关键
     * @param hashKey 散列键
     * @param value   价值
     * @return {@link Boolean}
     */
    public Boolean hPutIfAbsent(String key, String hashKey, String value) {
        return redisTemplate.opsForHash().putIfAbsent(key, hashKey, value);
    }

    /**
     * 删除一个或多个哈希表字段
     *
     * @param key    关键
     * @param fields 字段
     * @return {@link Long}
     */
    public Long hDelete(String key, Object... fields) {
        return redisTemplate.opsForHash().delete(key, fields);
    }

    /**
     * 查看哈希表 key 中，指定的字段是否存在
     *
     * @param key   关键
     * @param field 场
     * @return boolean
     */
    public Boolean hExists(String key, String field) {
        return redisTemplate.opsForHash().hasKey(key, field);
    }

    /**
     * 为哈希表 key 中的指定字段的整数值加上增量 increment
     *
     * @param key       关键
     * @param field     场
     * @param increment 增量
     * @return {@link Long}
     */
    public Long hIncrBy(String key, Object field, long increment) {
        return redisTemplate.opsForHash().increment(key, field, increment);
    }

    /**
     * 为哈希表 key 中的指定字段的整数值加上增量 increment
     *
     * @param key   关键
     * @param field 场
     * @param delta δ
     * @return {@link Double}
     */
    public Double hIncrByFloat(String key, Object field, double delta) {
        return redisTemplate.opsForHash().increment(key, field, delta);
    }

    /**
     * 获取所有哈希表中的字段
     *
     * @param key 关键
     * @return {@link Set}<{@link Object}>
     */
    public Set<Object> hKeys(String key) {
        return redisTemplate.opsForHash().keys(key);
    }

    /**
     * 获取哈希表中字段的数量
     *
     * @param key 关键
     * @return {@link Long}
     */
    public Long hSize(String key) {
        return redisTemplate.opsForHash().size(key);
    }

    /**
     * 获取哈希表中所有值
     *
     * @param key 关键
     * @return {@link List}<{@link Object}>
     */
    public List<Object> hValues(String key) {
        return redisTemplate.opsForHash().values(key);
    }

    /**
     * 迭代哈希表中的键值对
     *
     * @param key     关键
     * @param options 选项
     * @return {@link Cursor}<{@link Entry}<{@link Object}, {@link Object}>>
     */
    public Cursor<Entry<Object, Object>> hScan(String key, ScanOptions options) {
        return redisTemplate.opsForHash().scan(key, options);
    }

    /* ------------------------list相关操作---------------------------- */

    /**
     * 通过索引获取列表中的元素
     *
     * @param key   关键
     * @param index 指数
     * @return {@link String}
     */
    public String listIndex(String key, long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    /**
     * 获取列表指定范围内的元素
     *
     * @param start 开始位置, 0是开始位置
     * @param end   结束位置, -1返回所有
     * @param key   关键
     * @return {@link List}<{@link String}>
     */
    public List<String> listRange(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 存储在list头部
     *
     * @param key   关键
     * @param value 价值
     * @return {@link Long}
     */
    public Long listLeftPush(String key, String value) {
        return redisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * 批量存储在list头部
     *
     * @param key   关键
     * @param value 价值
     * @return {@link Long}
     */
    public Long listLeftPushAll(String key, String... value) {
        return redisTemplate.opsForList().leftPushAll(key, value);
    }

    /**
     * 批量存储在list头部
     *
     * @param key   关键
     * @param value 价值
     * @return {@link Long}
     */
    public Long listLeftPushAll(String key, Collection<String> value) {
        return redisTemplate.opsForList().leftPushAll(key, value);
    }

    /**
     * 当list存在的时候才加入
     *
     * @param key   关键
     * @param value 价值
     * @return {@link Long}
     */
    public Long listLeftPushIfPresent(String key, String value) {
        return redisTemplate.opsForList().leftPushIfPresent(key, value);
    }

    /**
     * 如果pivot存在,再pivot前面添加
     *
     * @param key   关键
     * @param pivot 主
     * @param value 价值
     * @return {@link Long}
     */
    public Long listLeftPush(String key, String pivot, String value) {
        return redisTemplate.opsForList().leftPush(key, pivot, value);
    }

    /**
     * 存储在list尾部
     *
     * @param key   关键
     * @param value 价值
     * @return {@link Long}
     */
    public Long listRightPush(String key, String value) {
        return redisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * 批量存储在list尾部
     *
     * @param key   关键
     * @param value 价值
     * @return {@link Long}
     */
    public Long listRightPushAll(String key, String... value) {
        return redisTemplate.opsForList().rightPushAll(key, value);
    }

    /**
     * 批量存储在list尾部
     *
     * @param key   关键
     * @param value 价值
     * @return {@link Long}
     */
    public Long listRightPushAll(String key, Collection<String> value) {
        return redisTemplate.opsForList().rightPushAll(key, value);
    }

    /**
     * 为已存在的列表添加值
     *
     * @param key   关键
     * @param value 价值
     * @return {@link Long}
     */
    public Long listRightPushIfPresent(String key, String value) {
        return redisTemplate.opsForList().rightPushIfPresent(key, value);
    }

    /**
     * 在pivot元素的右边添加值
     *
     * @param key   关键
     * @param pivot 主
     * @param value 价值
     * @return {@link Long}
     */
    public Long listRightPush(String key, String pivot, String value) {
        return redisTemplate.opsForList().rightPush(key, pivot, value);
    }

    /**
     * 通过索引设置列表元素的值
     *
     * @param index 位置
     * @param key   关键
     * @param value 价值
     */
    public void listSet(String key, long index, String value) {
        redisTemplate.opsForList().set(key, index, value);
    }

    /**
     * 移出并获取列表的第一个元素
     *
     * @param key 关键
     * @return 删除的元素
     */
    public String listLeftPop(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    /**
     * 移出并获取列表的第一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
     *
     * @param timeout 等待时间
     * @param unit    时间单位
     * @param key     关键
     * @return {@link String}
     */
    public String listFirstLeftPop(String key, long timeout, TimeUnit unit) {
        return redisTemplate.opsForList().leftPop(key, timeout, unit);
    }

    /**
     * 移除并获取列表最后一个元素
     *
     * @param key 关键
     * @return 删除的元素
     */
    public String listRightPop(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    /**
     * 移出并获取列表的最后一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
     *
     * @param timeout 等待时间
     * @param unit    时间单位
     * @param key     关键
     * @return {@link String}
     */
    public String listLastRightPop(String key, long timeout, TimeUnit unit) {
        return redisTemplate.opsForList().rightPop(key, timeout, unit);
    }

    /**
     * 移除列表的最后一个元素，并将该元素添加到另一个列表并返回
     *
     * @param sourceKey      源键
     * @param destinationKey 目地关键
     * @return {@link String}
     */
    public String listRightPopAndLeftPush(String sourceKey, String destinationKey) {
        return redisTemplate.opsForList().rightPopAndLeftPush(sourceKey,
                destinationKey);
    }

    /**
     * 从列表中弹出一个值，将弹出的元素插入到另外一个列表中并返回它； 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
     *
     * @param sourceKey      源键
     * @param destinationKey 目地关键
     * @param timeout        超时
     * @param unit           单位
     * @return {@link String}
     */
    public String listFirstRightPopAndLeftPush(String sourceKey, String destinationKey,
                                               long timeout, TimeUnit unit) {
        return redisTemplate.opsForList().rightPopAndLeftPush(sourceKey,
                destinationKey, timeout, unit);
    }

    /**
     * 删除集合中值等于value得元素
     *
     * @param index index=0, 删除所有值等于value的元素; index>0, 从头部开始删除第一个值等于value的元素;
     *              index<0, 从尾部开始删除第一个值等于value的元素;
     * @param key   关键
     * @param value 价值
     * @return {@link Long}
     */
    public Long listRemove(String key, long index, String value) {
        return redisTemplate.opsForList().remove(key, index, value);
    }

    /**
     * 数组修剪
     *
     * @param key   关键
     * @param start 开始
     * @param end   结束
     */
    public void listTrim(String key, long start, long end) {
        redisTemplate.opsForList().trim(key, start, end);
    }

    /**
     * 获取列表长度
     *
     * @param key 关键
     * @return {@link Long}
     */
    public Long listLen(String key) {
        return redisTemplate.opsForList().size(key);
    }

    /*--------------------set相关操作-------------------------- */

    /**
     * set添加元素
     *
     * @param key    关键
     * @param values 值
     * @return {@link Long}
     */
    public Long sAdd(String key, String... values) {
        return redisTemplate.opsForSet().add(key, values);
    }

    /**
     * set移除元素
     *
     * @param key    关键
     * @param values 值
     * @return {@link Long}
     */
    public Long sRemove(String key, Object... values) {
        return redisTemplate.opsForSet().remove(key, values);
    }

    /**
     * 移除并返回集合的一个随机元素
     *
     * @param key 关键
     * @return {@link String}
     */
    public String sPop(String key) {
        return redisTemplate.opsForSet().pop(key);
    }

    /**
     * 将元素value从一个集合移到另一个集合
     *
     * @param key     关键
     * @param value   价值
     * @param destKey 关键不在座位上
     * @return {@link Boolean}
     */
    public Boolean sMove(String key, String value, String destKey) {
        return redisTemplate.opsForSet().move(key, value, destKey);
    }

    /**
     * 获取集合的大小
     *
     * @param key 关键
     * @return {@link Long}
     */
    public Long sSize(String key) {
        return redisTemplate.opsForSet().size(key);
    }

    /**
     * 判断集合是否包含value
     *
     * @param key   关键
     * @param value 价值
     * @return {@link Boolean}
     */
    public Boolean sIsMember(String key, Object value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    /**
     * 获取两个集合的交集
     *
     * @param key      关键
     * @param otherKey 其他关键
     * @return {@link Set}<{@link String}>
     */
    public Set<String> sIntersect(String key, String otherKey) {
        return redisTemplate.opsForSet().intersect(key, otherKey);
    }

    /**
     * 获取key集合与多个集合的交集
     *
     * @param key       关键
     * @param otherKeys 其他键
     * @return {@link Set}<{@link String}>
     */
    public Set<String> sIntersect(String key, Collection<String> otherKeys) {
        return redisTemplate.opsForSet().intersect(key, otherKeys);
    }

    /**
     * key集合与otherKey集合的交集存储到destKey集合中
     *
     * @param key      关键
     * @param otherKey 其他关键
     * @param destKey  关键不在座位上
     * @return {@link Long}
     */
    public Long sIntersectAndStore(String key, String otherKey, String destKey) {
        return redisTemplate.opsForSet().intersectAndStore(key, otherKey,
                destKey);
    }

    /**
     * key集合与多个集合的交集存储到destKey集合中
     *
     * @param key       关键
     * @param otherKeys 其他键
     * @param destKey   关键不在座位上
     * @return {@link Long}
     */
    public Long sIntersectAndStore(String key, Collection<String> otherKeys,
                                   String destKey) {
        return redisTemplate.opsForSet().intersectAndStore(key, otherKeys,
                destKey);
    }

    /**
     * 获取两个集合的并集
     *
     * @param key       关键
     * @param otherKeys 其他键
     * @return {@link Set}<{@link String}>
     */
    public Set<String> sUnion(String key, String otherKeys) {
        return redisTemplate.opsForSet().union(key, otherKeys);
    }

    /**
     * 获取key集合与多个集合的并集
     *
     * @param key       关键
     * @param otherKeys 其他键
     * @return {@link Set}<{@link String}>
     */
    public Set<String> sUnion(String key, Collection<String> otherKeys) {
        return redisTemplate.opsForSet().union(key, otherKeys);
    }

    /**
     * key集合与otherKey集合的并集存储到destKey中
     *
     * @param key      关键
     * @param otherKey 其他关键
     * @param destKey  关键不在座位上
     * @return {@link Long}
     */
    public Long sUnionAndStore(String key, String otherKey, String destKey) {
        return redisTemplate.opsForSet().unionAndStore(key, otherKey, destKey);
    }

    /**
     * key集合与多个集合的并集存储到destKey中
     *
     * @param key       关键
     * @param otherKeys 其他键
     * @param destKey   关键不在座位上
     * @return {@link Long}
     */
    public Long sUnionAndStore(String key, Collection<String> otherKeys,
                               String destKey) {
        return redisTemplate.opsForSet().unionAndStore(key, otherKeys, destKey);
    }

    /**
     * 获取两个集合的差集
     *
     * @param key      关键
     * @param otherKey 其他关键
     * @return {@link Set}<{@link String}>
     */
    public Set<String> sDifference(String key, String otherKey) {
        return redisTemplate.opsForSet().difference(key, otherKey);
    }

    /**
     * 获取key集合与多个集合的差集
     *
     * @param key       关键
     * @param otherKeys 其他键
     * @return {@link Set}<{@link String}>
     */
    public Set<String> sDifference(String key, Collection<String> otherKeys) {
        return redisTemplate.opsForSet().difference(key, otherKeys);
    }

    /**
     * key集合与otherKey集合的差集存储到destKey中
     *
     * @param key      关键
     * @param otherKey 其他关键
     * @param destKey  关键不在座位上
     * @return {@link Long}
     */
    public Long sDifference(String key, String otherKey, String destKey) {
        return redisTemplate.opsForSet().differenceAndStore(key, otherKey,
                destKey);
    }

    /**
     * key集合与多个集合的差集存储到destKey中
     *
     * @param key       关键
     * @param otherKeys 其他键
     * @param destKey   关键不在座位上
     * @return {@link Long}
     */
    public Long sDifference(String key, Collection<String> otherKeys, String destKey) {
        return redisTemplate.opsForSet().differenceAndStore(key, otherKeys, destKey);
    }

    /**
     * 获取集合所有元素
     *
     * @param key 关键
     * @return {@link Set}<{@link String}>
     */
    public Set<String> setMembers(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 随机获取集合中的一个元素
     *
     * @param key 关键
     * @return {@link String}
     */
    public String sRandomMember(String key) {
        return redisTemplate.opsForSet().randomMember(key);
    }

    /**
     * 随机获取集合中count个元素
     *
     * @param key   关键
     * @param count 数
     * @return {@link List}<{@link String}>
     */
    public List<String> sRandomMembers(String key, long count) {
        return redisTemplate.opsForSet().randomMembers(key, count);
    }

    /**
     * 随机获取集合中count个元素并且去除重复的
     *
     * @param key   关键
     * @param count 数
     * @return {@link Set}<{@link String}>
     */
    public Set<String> sDistinctRandomMembers(String key, long count) {
        return redisTemplate.opsForSet().distinctRandomMembers(key, count);
    }

    /**
     * 扫描
     *
     * @param key     关键
     * @param options 选项
     * @return {@link Cursor}<{@link String}>
     */
    public Cursor<String> sScan(String key, ScanOptions options) {
        return redisTemplate.opsForSet().scan(key, options);
    }

    /*------------------zSet相关操作--------------------------------*/

    /**
     * 添加元素,有序集合是按照元素的score值由小到大排列
     *
     * @param key   关键
     * @param value 价值
     * @param score 分数
     * @return {@link Boolean}
     */
    public Boolean zAdd(String key, String value, double score) {
        return redisTemplate.opsForZSet().add(key, value, score);
    }

    /**
     * 添加
     *
     * @param key    关键
     * @param values 值
     * @return {@link Long}
     */
    public Long zAdd(String key, Set<TypedTuple<String>> values) {
        return redisTemplate.opsForZSet().add(key, values);
    }

    /**
     * 删除
     *
     * @param key    关键
     * @param values 值
     * @return {@link Long}
     */
    public Long zRemove(String key, Object... values) {
        return redisTemplate.opsForZSet().remove(key, values);
    }

    /**
     * 增加元素的score值，并返回增加后的值
     *
     * @param key   关键
     * @param value 价值
     * @param delta δ
     * @return {@link Double}
     */
    public Double zIncrementScore(String key, String value, double delta) {
        return redisTemplate.opsForZSet().incrementScore(key, value, delta);
    }

    /**
     * 返回元素在集合的排名,有序集合是按照元素的score值由小到大排列
     *
     * @param key   关键
     * @param value 价值
     * @return 0表示第一位
     */
    public Long zRank(String key, Object value) {
        return redisTemplate.opsForZSet().rank(key, value);
    }

    /**
     * 返回元素在集合的排名,按元素的score值由大到小排列
     *
     * @param key   关键
     * @param value 价值
     * @return {@link Long}
     */
    public Long zReverseRank(String key, Object value) {
        return redisTemplate.opsForZSet().reverseRank(key, value);
    }

    /**
     * 获取集合的元素, 从小到大排序
     *
     * @param start 开始位置
     * @param end   结束位置, -1查询所有
     * @param key   关键
     * @return {@link Set}<{@link String}>
     */
    public Set<String> zRange(String key, long start, long end) {
        return redisTemplate.opsForZSet().range(key, start, end);
    }

    /**
     * 获取集合元素, 并且把score值也获取
     *
     * @param key   关键
     * @param start 开始
     * @param end   结束
     * @return {@link Set}<{@link TypedTuple}<{@link String}>>
     */
    public Set<TypedTuple<String>> zRangeWithScores(String key, long start,
                                                    long end) {
        return redisTemplate.opsForZSet().rangeWithScores(key, start, end);
    }

    /**
     * 根据Score值查询集合元素
     *
     * @param min 最小值
     * @param max 最大值
     * @param key 关键
     * @return {@link Set}<{@link String}>
     */
    public Set<String> zRangeByScore(String key, double min, double max) {
        return redisTemplate.opsForZSet().rangeByScore(key, min, max);
    }

    /**
     * 根据Score值查询集合元素, 从小到大排序
     *
     * @param min 最小值
     * @param max 最大值
     * @param key 关键
     * @return {@link Set}<{@link TypedTuple}<{@link String}>>
     */
    public Set<TypedTuple<String>> zRangeByScoreWithScores(String key,
                                                           double min, double max) {
        return redisTemplate.opsForZSet().rangeByScoreWithScores(key, min, max);
    }

    /**
     * @param key   关键
     * @param min   最小值
     * @param max   马克斯
     * @param start 开始
     * @param end   结束
     * @return {@link Set}<{@link TypedTuple}<{@link String}>>
     */
    public Set<TypedTuple<String>> zRangeByScoreWithScores(String key,
                                                           double min, double max, long start, long end) {
        return redisTemplate.opsForZSet().rangeByScoreWithScores(key, min, max,
                start, end);
    }

    /**
     * 获取集合的元素, 从大到小排序
     *
     * @param key   关键
     * @param start 开始
     * @param end   结束
     * @return {@link Set}<{@link String}>
     */
    public Set<String> zReverseRange(String key, long start, long end) {
        return redisTemplate.opsForZSet().reverseRange(key, start, end);
    }

    /**
     * 获取集合的元素, 从大到小排序, 并返回score值
     *
     * @param key   关键
     * @param start 开始
     * @param end   结束
     * @return {@link Set}<{@link TypedTuple}<{@link String}>>
     */
    public Set<TypedTuple<String>> zReverseRangeWithScores(String key,
                                                           long start, long end) {
        return redisTemplate.opsForZSet().reverseRangeWithScores(key, start,
                end);
    }

    /**
     * 根据Score值查询集合元素, 从大到小排序
     *
     * @param key 关键
     * @param min 最小值
     * @param max 最大值
     * @return {@link Set}<{@link String}>
     */
    public Set<String> zReverseRangeByScore(String key, double min,
                                            double max) {
        return redisTemplate.opsForZSet().reverseRangeByScore(key, min, max);
    }

    /**
     * 根据Score值查询集合元素, 从大到小排序
     *
     * @param key 关键
     * @param min 最小值
     * @param max 最大值
     * @return {@link Set}<{@link TypedTuple}<{@link String}>>
     */
    public Set<TypedTuple<String>> zReverseRangeByScoreWithScores(
            String key, double min, double max) {
        return redisTemplate.opsForZSet().reverseRangeByScoreWithScores(key,
                min, max);
    }

    /**
     * @param key   关键
     * @param min   最小值
     * @param max   最大值
     * @param start 开始
     * @param end   结束
     * @return {@link Set}<{@link String}>
     */
    public Set<String> zReverseRangeByScore(String key, double min,
                                            double max, long start, long end) {
        return redisTemplate.opsForZSet().reverseRangeByScore(key, min, max,
                start, end);
    }

    /**
     * 根据score值获取集合元素数量
     *
     * @param key 关键
     * @param min 最小值
     * @param max 马克斯
     * @return {@link Long}
     */
    public Long zCount(String key, double min, double max) {
        return redisTemplate.opsForZSet().count(key, min, max);
    }

    /**
     * 获取集合大小
     *
     * @param key 关键
     * @return {@link Long}
     */
    public Long zSize(String key) {
        return redisTemplate.opsForZSet().size(key);
    }

    /**
     * 获取集合大小
     *
     * @param key 关键
     * @return {@link Long}
     */
    public Long zZCard(String key) {
        return redisTemplate.opsForZSet().zCard(key);
    }

    /**
     * 获取集合中value元素的score值
     *
     * @param key   关键
     * @param value 价值
     * @return {@link Double}
     */
    public Double zScore(String key, Object value) {
        return redisTemplate.opsForZSet().score(key, value);
    }

    /**
     * 移除指定索引位置的成员
     *
     * @param key   关键
     * @param start 开始
     * @param end   结束
     * @return {@link Long}
     */
    public Long zRemoveRange(String key, long start, long end) {
        return redisTemplate.opsForZSet().removeRange(key, start, end);
    }

    /**
     * 根据指定的score值的范围来移除成员
     *
     * @param key 关键
     * @param min 最小值
     * @param max 马克斯
     * @return {@link Long}
     */
    public Long zRemoveRangeByScore(String key, double min, double max) {
        return redisTemplate.opsForZSet().removeRangeByScore(key, min, max);
    }

    /**
     * 获取key和otherKey的并集并存储在destKey中
     *
     * @param key      关键
     * @param otherKey 其他关键
     * @param destKey  关键不在座位上
     * @return {@link Long}
     */
    public Long zUnionAndStore(String key, String otherKey, String destKey) {
        return redisTemplate.opsForZSet().unionAndStore(key, otherKey, destKey);
    }

    /**
     * @param key       关键
     * @param otherKeys 其他键
     * @param destKey   关键不在座位上
     * @return {@link Long}
     */
    public Long zUnionAndStore(String key, Collection<String> otherKeys,
                               String destKey) {
        return redisTemplate.opsForZSet()
                .unionAndStore(key, otherKeys, destKey);
    }

    /**
     * 交集
     *
     * @param key      关键
     * @param otherKey 其他关键
     * @param destKey  关键不在座位上
     * @return {@link Long}
     */
    public Long zIntersectAndStore(String key, String otherKey,
                                   String destKey) {
        return redisTemplate.opsForZSet().intersectAndStore(key, otherKey,
                destKey);
    }

    /**
     * 交集
     *
     * @param key       关键
     * @param otherKeys 其他键
     * @param destKey   关键不在座位上
     * @return {@link Long}
     */
    public Long zIntersectAndStore(String key, Collection<String> otherKeys,
                                   String destKey) {
        return redisTemplate.opsForZSet().intersectAndStore(key, otherKeys,
                destKey);
    }

    /**
     * 扫描
     *
     * @param key     关键
     * @param options 选项
     * @return {@link Cursor}<{@link TypedTuple}<{@link String}>>
     */
    public Cursor<TypedTuple<String>> zScan(String key, ScanOptions options) {
        return redisTemplate.opsForZSet().scan(key, options);
    }
}

