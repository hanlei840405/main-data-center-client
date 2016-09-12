package com.xiaoqiaoli.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaoqiaoli.util.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by hanlei6 on 2016/9/12.
 */
@CrossOrigin
@RestController
@RequestMapping("/api/common")
public class CommonRestController {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Value("${avatar.url}")
    String avatarUrl;

    @Value("${avatar.key}")
    String avatarKey;

    ObjectMapper objectMapper = new ObjectMapper();

    @RequestMapping("/cities")
    public List<Map<String, Object>> cities(String province) {
        Map<String, Object> params = new HashMap<>();
        String citiesCache = redisTemplate.opsForValue().get(province);
        try {
            if (StringUtils.isEmpty(citiesCache)) {
                params.put("parentId", province);
                params.put("key", avatarKey);
                citiesCache = HttpClientUtil.httpGetRequest(avatarUrl + "SimpleArea/LookUp", params);
                redisTemplate.opsForValue().set(province, citiesCache);
            }
            redisTemplate.expire(province, 1, TimeUnit.DAYS);
            List<Map<String, Object>> result = objectMapper.readValue(citiesCache, List.class);
            return result;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/provinces")
    public List<Map<String, Object>> provincesAndCities() {
        Map<String, Object> params = new HashMap<>();
        try {
            String provincesCache = redisTemplate.opsForValue().get("provinces");
            params.put("key", avatarKey);
            if (StringUtils.isEmpty(provincesCache)) {
                String value = HttpClientUtil.httpGetRequest(avatarUrl + "SimpleArea/LookUp", params);
                Map<String,Object> result = objectMapper.readValue(value,Map.class);
                provincesCache = objectMapper.writeValueAsString(result.get("result"));
                redisTemplate.opsForValue().set("provinces", provincesCache);
                redisTemplate.expire("provinces", 1, TimeUnit.DAYS);
            }
            List<Map<String, Object>> result = objectMapper.readValue(provincesCache, List.class);
            return result;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
