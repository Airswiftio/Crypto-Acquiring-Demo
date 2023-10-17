package com.airswift.restapi.utils;

import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.binary.Base64;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Map;
import java.util.TreeMap;

public class SignRsaUtil {

    /**
     * 对Map中的值按字段名的ASCII码值按升序排序
     *
     * @param map 待排序Map
     * @return 排序后的字符串
     */
    public static String getSignStr(Map<String, String> map) {
        String signStr = null;
        if (map != null) {
            Map<String, String> sortMap = new TreeMap<String, String>();
            //将请求Map中的字段名按ASCII码值进行排序
            for (String key : map.keySet()) {
                if (map.get(key) != null && !"".equals(map.get(key)) && !"null".equals(map.get(key))) {
                    sortMap.put(key, map.get(key).trim());
                }
            }
            //拼接排序后Map中所有的值
            StringBuilder sb = new StringBuilder();
            for (String key : sortMap.keySet()) {
                sb.append(sortMap.get(key));
            }
            signStr = sb.toString();
        }
        System.out.println("签名原文:"+JSON.toJSONString(signStr));
        return signStr;
    }

    /**
     * 数字签名
     * @param data 待签名数据
     * @param pri_key 私钥
     * @return 签名
     * @throws Exception 抛出异常
     */
    public static String sign(byte[] data, String pri_key) throws Exception {
        // Get the private key
        byte[] pri_key_bytes = Base64.decodeBase64(pri_key);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(pri_key_bytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        // Generate private key
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
        // Instantiate Signature
        Signature signature = Signature.getInstance("SHA256withRSA");
        // Initialize Signature
        signature.initSign(priKey);
        // Update
        signature.update(data);
        return Base64.encodeBase64String(signature.sign());
    }
}
