package pers.crayon.user.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/10/10 11:41
 * @since JDK 1.8
 */
@Service
@Slf4j
public class RedisCacheService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 对keySerializer进行初始化
     * 防止 hGet方法获取不到数据
     *
     * @param redisTemplate
     */
    @Autowired(required = false)
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        this.redisTemplate = redisTemplate;
    }

    /**
     * 设置key的过期时间，单位为秒s
     *
     * @param key
     * @param time
     * @return
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0L) {
                this.redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }

            return true;
        } catch (Exception var5) {
            log.error(var5.getMessage(), var5);
            return false;
        }
    }

    /**
     * 获取key的有效时间
     *
     * @param key
     * @return
     */
    public long getExpire(String key) {
        return this.redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断是否存在key
     *
     * @param key
     * @return
     */
    public boolean hasKey(String key) {
        try {
            return this.redisTemplate.hasKey(key);
        } catch (Exception var3) {
            log.error(var3.getMessage(), var3);
            return false;
        }
    }

    /**
     * 删除一些key
     *
     * @param key
     */
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                this.redisTemplate.delete(key[0]);
            } else {
                this.redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }

    }

    /**
     * 删除指定key
     *
     * @param pattern
     */
    public void delAll(String pattern) {
        this.redisTemplate.delete(this.redisTemplate.keys(pattern));
    }

    /**
     * 获取指定key-values
     *
     * @param key
     * @return
     */
    public Object get(String key) {
        try {
            return this.redisTemplate.opsForValue().get(key);
        } catch (Exception var3) {
            log.error(var3.getMessage());
            return null;
        }
    }

    /**
     * 获取所有
     *
     * @param pattern
     * @return
     */
    public Set<Object> getAll(String pattern) {
        Set<Object> values = new HashSet();
        Set<String> keys = this.redisTemplate.keys(pattern);
        Iterator var4 = keys.iterator();

        while (var4.hasNext()) {
            String key = (String) var4.next();
            values.add(this.redisTemplate.opsForValue().get(key));
        }

        return values;
    }

    /**
     * key-value 存储
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key, Object value) {
        try {
            this.redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception var4) {
            log.error(var4.getMessage(), var4);
            return false;
        }
    }

    /**
     * @param key
     * @param value
     * @param time
     * @return
     */
    public boolean set(String key, Object value, long time) {
        try {
            if (time > 0L) {
                this.redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                this.set(key, value);
            }

            return true;
        } catch (Exception var6) {
            log.error(var6.getMessage(), var6);
            return false;
        }
    }

    public long incr(String key, long delta) {
        if (delta < 0L) {
            throw new RuntimeException("递增因子必须大于0");
        } else {
            return this.redisTemplate.opsForValue().increment(key, delta);
        }
    }

    public long decr(String key, long delta) {
        if (delta < 0L) {
            throw new RuntimeException("递减因子必须大于0");
        } else {
            return this.redisTemplate.opsForValue().increment(key, -delta);
        }
    }

    public Object hget(String key, String item) {
        return this.redisTemplate.opsForHash().get(key, item);
    }

    public Map<Object, Object> hmget(String key) {
        return this.redisTemplate.opsForHash().entries(key);
    }

    public boolean hmset(String key, Map<String, Object> map) {
        try {
            this.redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception var4) {
            log.error(var4.getMessage(), var4);
            return false;
        }
    }

    public boolean hmset(String key, Map<String, Object> map, long time) {
        try {
            this.redisTemplate.opsForHash().putAll(key, map);
            if (time > 0L) {
                this.expire(key, time);
            }

            return true;
        } catch (Exception var6) {
            log.error(var6.getMessage(), var6);
            return false;
        }
    }

    public boolean hset(String key, String item, Object value) {
        try {
            this.redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception var5) {
            log.error(var5.getMessage(), var5);
            return false;
        }
    }

    public boolean hset(String key, String item, Object value, long time) {
        try {
            this.redisTemplate.opsForHash().put(key, item, value);
            if (time > 0L) {
                this.expire(key, time);
            }

            return true;
        } catch (Exception var7) {
            log.error(var7.getMessage(), var7);
            return false;
        }
    }

    public void hdel(String key, Object... item) {
        this.redisTemplate.opsForHash().delete(key, item);
    }

    public boolean hHasKey(String key, String item) {
        return this.redisTemplate.opsForHash().hasKey(key, item);
    }

    public double hincr(String key, String item, double by) {
        return this.redisTemplate.opsForHash().increment(key, item, by);
    }

    public double hdecr(String key, String item, double by) {
        return this.redisTemplate.opsForHash().increment(key, item, -by);
    }

    public Set<Object> sGet(String key) {
        try {
            return this.redisTemplate.opsForSet().members(key);
        } catch (Exception var3) {
            log.error(var3.getMessage(), var3);
            return null;
        }
    }

    public boolean sHasKey(String key, Object value) {
        try {
            return this.redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception var4) {
            log.error(var4.getMessage(), var4);
            return false;
        }
    }

    public long sSet(String key, Object... values) {
        try {
            return this.redisTemplate.opsForSet().add(key, values);
        } catch (Exception var4) {
            log.error(var4.getMessage(), var4);
            return 0L;
        }
    }

    /**
     * @param key
     * @param time
     * @param values
     * @return
     */
    public long sSetAndTime(String key, long time, Object... values) {
        try {
            Long count = this.redisTemplate.opsForSet().add(key, values);
            if (time > 0L) {
                this.expire(key, time);
            }
            return count;
        } catch (Exception var6) {
            log.error(var6.getMessage(), var6);
            return 0L;
        }
    }

    public long sGetSetSize(String key) {
        try {
            return this.redisTemplate.opsForSet().size(key);
        } catch (Exception var3) {
            log.error(var3.getMessage(), var3);
            return 0L;
        }
    }

    public long setRemove(String key, Object... values) {
        try {
            return this.redisTemplate.opsForSet().remove(key, values);
        } catch (Exception var4) {
            log.error(var4.getMessage(), var4);
            return 0L;
        }
    }

    public List<Object> lGet(String key, long start, long end) {
        try {
            return this.redisTemplate.opsForList().range(key, start, end);
        } catch (Exception var7) {
            log.error(var7.getMessage(), var7);
            return null;
        }
    }

    public long lGetListSize(String key) {
        try {
            return this.redisTemplate.opsForList().size(key);
        } catch (Exception var3) {
            log.error(var3.getMessage(), var3);
            return 0L;
        }
    }

    public Object lGetIndex(String key, long index) {
        try {
            return this.redisTemplate.opsForList().index(key, index);
        } catch (Exception var5) {
            log.error(var5.getMessage(), var5);
            return null;
        }
    }

    public boolean lSet(String key, Object value) {
        try {
            this.redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception var4) {
            log.error(var4.getMessage(), var4);
            return false;
        }
    }

    public boolean lSet(String key, Object value, long time) {
        try {
            this.redisTemplate.opsForList().rightPush(key, value);
            if (time > 0L) {
                this.expire(key, time);
            }

            return true;
        } catch (Exception var6) {
            log.error(var6.getMessage(), var6);
            return false;
        }
    }

    public boolean lSet(String key, List<Object> value) {
        try {
            this.redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception var4) {
            log.error(var4.getMessage(), var4);
            return false;
        }
    }

    public boolean lSet(String key, List<Object> value, long time) {
        try {
            this.redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0L) {
                this.expire(key, time);
            }

            return true;
        } catch (Exception var6) {
            log.error(var6.getMessage(), var6);
            return false;
        }
    }

    public boolean lUpdateIndex(String key, long index, Object value) {
        try {
            this.redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception var6) {
            log.error(var6.getMessage(), var6);
            return false;
        }
    }

    public long lRemove(String key, long count, Object value) {
        try {
            return this.redisTemplate.opsForList().remove(key, count, value);
        } catch (Exception var6) {
            log.error(var6.getMessage(), var6);
            return 0L;
        }
    }

    public Set<String> keys(String pattern) {
        return this.redisTemplate.keys(pattern);
    }

    /**
     * TODO 利用代码选择 redis 中 key 以A开头的数据项
     */
    public Set<String> searchKeys(String pattern) {
        return null;
    }
}
