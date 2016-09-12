package com.xiaoqiaoli.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.xiaoqiaoli.util.HttpClientUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by hanlei6 on 2016/8/10.
 */
@RestController
@RequestMapping("/common")
public class CommonController extends BaseController {

    @RequestMapping("/cities")
    public Map<String, Object> cities(String province) {
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
            Map<String, Object> result = objectMapper.readValue(citiesCache, Map.class);
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
    public Map<String, Object> provincesAndCities() {

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
