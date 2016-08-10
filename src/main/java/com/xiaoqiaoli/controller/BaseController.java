package com.xiaoqiaoli.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaoqiaoli.util.Constant;
import com.xiaoqiaoli.util.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by hanlei6 on 2016/8/7.
 */
public abstract class BaseController<T> {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Value("${avatar.url}")
    String avatarUrl;

    @Value("${avatar.key}")
    String avatarKey;

    ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 返回状态码，状态信息和操作的对象
     *
     * @param t
     * @param result
     * @return
     */
    Map<String, Object> buildResponseStatus(T t, Map<String, Object> result) {
        if (t != null) {
            result.put(Constant.RETURN_MAP_KEY_STATUS, Constant.RETURN_MAP_VALUE_STATUS_SUCCESS);
            result.put(Constant.RETURN_MAP_KEY_MESSAGE, Constant.RETURN_MAP_VALUE_MESSAGE_INSERT_SUCCESS);
        } else {
            result.put(Constant.RETURN_MAP_KEY_STATUS, Constant.RETURN_MAP_VALUE_STATUS_FAILURE);
            result.put(Constant.RETURN_MAP_KEY_MESSAGE, Constant.RETURN_MAP_VALUE_MESSAGE_INSERT_FAILURE);
        }
        result.put(Constant.RETURN_MAP_KEY_OBJECT, t);
        return result;
    }

    Map<String, Object> provincesAndCities() {

        Map<String, Object> keyValues = new HashMap<>();
        Map<String, Object> params = new HashMap<>();
        try {
            String provincesCache = redisTemplate.opsForValue().get("provinces");
            params.put("key", avatarKey);
            if (StringUtils.isEmpty(provincesCache)) {
                provincesCache = HttpClientUtil.httpGetRequest(avatarUrl + "SimpleArea/LookUp", params);
                redisTemplate.opsForValue().set("provinces", provincesCache);
                redisTemplate.expire("provinces", 1, TimeUnit.DAYS);
            }
            Map<String, Object> result = objectMapper.readValue(provincesCache, Map.class);
            List<Map<String, Object>> provinces = (List<Map<String, Object>>) result.get("result");
            keyValues.put("provinces", provinces);
            Map<String, Object> firstProvince = provinces.get(0);
            String areaId = firstProvince.get("area_id").toString();
            String citiesCache = redisTemplate.opsForValue().get(areaId);
            if (StringUtils.isEmpty(citiesCache)) {
                params.put("parentId", areaId);
                citiesCache = HttpClientUtil.httpGetRequest(avatarUrl + "SimpleArea/LookUp", params);
                redisTemplate.opsForValue().set(areaId, citiesCache);
                redisTemplate.expire(areaId, 1, TimeUnit.DAYS);
            }
            result = objectMapper.readValue(citiesCache, Map.class);
            List<Map<String, Object>> cities = (List<Map<String, Object>>) result.get("result");
            keyValues.put("cities", cities);

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return keyValues;
    }


}
