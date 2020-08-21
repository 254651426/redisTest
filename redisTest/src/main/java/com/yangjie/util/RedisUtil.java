package com.yangjie.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

/**
 * Redis工具类
 * 
 * @author WangFan
 * @date 2018-02-24 下午03:09:50
 * @version 1.1 (GitHub文档: https://github.com/whvcse/RedisUtil )
 */
@Component
public class RedisUtil {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	public StringRedisTemplate getStringRedisTemplate() {
		return stringRedisTemplate;
	}

	public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
		this.stringRedisTemplate = stringRedisTemplate;
	}


	/**
	 * 获取指定 key 的值
	 * 
	 * @param key
	 * @return
	 */
	public String get(String key) {
		return stringRedisTemplate.opsForValue().get(key);
	}

	/**
	 * 设置指定 key 的值
	 * 
	 * @param key
	 * @param value
	 */
	public void set(String key, String value) {
		stringRedisTemplate.opsForValue().set(key, value);
	}

	/**
	 * 查找匹配的key
	 * 
	 * @param pattern
	 * @return
	 */
	public Set<String> keys(String pattern) {
		return stringRedisTemplate.keys(pattern);
	}

}