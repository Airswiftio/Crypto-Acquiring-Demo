package com.taiyi.simulation.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.spark.support.common.entity.MessageResult;
import com.taiyi.simulation.utils.HttpClientUtil;
import com.taiyi.simulation.utils.SignUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.bouncycastle.util.encoders.Base64;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;


@Slf4j
public class Demo {

    public static void main(String[] args) throws Exception{

    }

    /**
     * 请求下单签名demo
     * @throws Exception
     */
    public static void createOrderReq() throws Exception{
        char[] chars = generateRandomArray(6);
        String nonce = String.valueOf(chars);
        Map<String, String> reqMap = new HashMap<>();
        reqMap.put("clientOrderSn", "123456");
        reqMap.put("tradeType", "0");
        reqMap.put("coinUnit", "USDT");
        reqMap.put("basicsType", "1");
        reqMap.put("amount", "0.1");
        reqMap.put("remarks", "remark");
        reqMap.put("appKey", "123456");
        reqMap.put("nonce", nonce);
        reqMap.put("timestamp", ""+System.currentTimeMillis());
        String base64PriKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDA5hi/IrnprfXSdvczI5uyBB9gg0eknzVWYnM/zIUerY1Jubf3MX3T+sX3hAfYITbro8+x5/v8nKiJJaMQFVw5Z1eDtgp4KLDyYS0Jp/KysGWWlrOAz2c071CMHN1zy9Dk1slK13a4+Gk9KF+wnD/LvrTEq7sIUCzx2wBl3gFRz2osFDzbCcBaNJ/DJlulk2vcDqyXs+4AeaW3dlCMTtSXYJuPBOWetMYKY9qouxaJdOQ5Ylu6Y2luU0lhjzMK2EvoO89vcjTSTP14LwtxUzhzXI3RuDk9QjP7o2NPIkBxfKdzHKWtR7ptamKHFXeB5RkE0h0adOXI677mk5SVGOLRAgMBAAECggEBALQ4xjodibEHtLn0KDUBOb/yFhan0NG/ZafVx3N984mcAHs3Jueut67LhxYfpuyI67TwkWXYJhf3jYbFQToPmuCO6rbF57Yn3lo17EwvO5MzN1agagkYvYY5aPnfJg/ABuUmv15LPELI5BodlHl/IiOLg3yULeQwdC1qvU5vQrBZBdP6lzA0TazZvYuVX1pVN76oLkVaMVxCC3U/Hqc6TDOhD4EsI4GRQt+lU4n6i3of8dW+j/PMkRf4PkMNFAhvhGTxQBnCeBo8eXzCHM8KklBZRMruGRI19g8oKEz339JNeS93pAlc8u344AUbhgIutGjbQ6POLKCyTVPTBG38y4ECgYEA9Pt1GMWZSra951ule3TW0fCTTUtKwcIJKTsv7awbUnEz/+lzKupkMFZIdwCZ19z1NkNqht8peEGl3KnmVhbQglo1Nk/34k4ItO/aGHFM0zspQTUAxpD1j2ISZkZXy4s2KXUA8kZJ8KB25F0o9Hnmq7hRSuTEV1bIZjRL5rgaHukCgYEAyZL9TLG5wJCX5hR1GSco8j0pUj9bHzBm+LrPsHTHs1v6VvxxUvWVrWwpBcnvzZfFoIqOwcXgTpdx9Cqp7cvR4glbbOajtuG72fuehOb989psWy+oIyQ/KZVEdmLW7eduqBUiKqfeCXBz1N5XqkQ9C/46i6fheMjsKGF4EYh3w6kCgYEAqDp1Msmedpv+1H0uqhSV4BzgSlfFwkBIojOuuftR18888iORnorOuhLGOFIFUyK8kMLCyFM+2wHZsCpLrhqEnL7tg5puks0bVYTPs7E6HGOn9Ms8+R5aQlgKYokziObUDqv2NmjmJYNLoY4Hrbp4GLu9tzxZR9CkVOrcpFcQOOECgYBIcIqXBUe77DqqdK+gWnJcm5498IbOrpZSzM/WmiEqU+OjXxVauucPa89SRT/ooqJjD2g+Ot5hYAuTs8Fds+G5WaOBf2FWV8kqjUJqgJjDCbvnCTljZ6NLTllMMjkLRiB+RxoAOlORENiPp1fZ2KwXo0Vm6u4cehhkaqK7qk9gAQKBgCh3+z3SQpARiZ1k5t+pL3/z005UqsBHIy67YytR3m1tCM5SRAsDYFTZZW1F7ETgIg2zCrS3Ov9520i4VsyCow+l2OC5srMKnpnPi3fDyyRjBFBQ1pSLUb5oAg+d1KFIjaz4GhY58i1AHDJhBKwUu4n6X45jhnMv6Z6j6p1ZJGVC";
        String waitSignStr = getSignStr(reqMap);
        String sign = SignUtil.sign(waitSignStr.getBytes("UTF-8"), base64PriKey);
        String jsonStr = JSON.toJSONString(reqMap);
        List<NameValuePair> paramsList = new ArrayList<>();
        paramsList.add(new BasicNameValuePair("signStr", sign));
        paramsList.add(new BasicNameValuePair("bizContent", jsonStr));
        String body = post("https://order.airswift.io/docking/order/create", paramsList);
    }

    /**
     * 验签demo
     * @param request
     * @throws Exception
     */
    public static void verifyResponse(HttpServletRequest request) throws Exception{
        String signStr = request.getParameter("signStr");
        String dataContent = request.getParameter("bizContent");
        Map<String, String> reqMap = JSON.parseObject(dataContent, Map.class);
        String base64PubKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDA5hi/IrnprfXSdvczI5uyBB9gg0eknzVWYnM/zIUerY1Jubf3MX3T+sX3hAfYITbro8+x5/v8nKiJJaMQFVw5Z1eDtgp4KLDyYS0Jp/KysGWWlrOAz2c071CMHN1zy9Dk1slK13a4+Gk9KF+wnD/LvrTEq7sIUCzx2wBl3gFRz2osFDzbCcBaNJ/DJlulk2vcDqyXs+4AeaW3dlCMTtSXYJuPBOWetMYKY9qouxaJdOQ5Ylu6Y2luU0lhjzMK2EvoO89vcjTSTP14LwtxUzhzXI3RuDk9QjP7o2NPIkBxfKdzHKWtR7ptamKHFXeB5RkE0h0adOXI677mk5SVGOLRAgMBAAECggEBALQ4xjodibEHtLn0KDUBOb/yFhan0NG/ZafVx3N984mcAHs3Jueut67LhxYfpuyI67TwkWXYJhf3jYbFQToPmuCO6rbF57Yn3lo17EwvO5MzN1agagkYvYY5aPnfJg/ABuUmv15LPELI5BodlHl/IiOLg3yULeQwdC1qvU5vQrBZBdP6lzA0TazZvYuVX1pVN76oLkVaMVxCC3U/Hqc6TDOhD4EsI4GRQt+lU4n6i3of8dW+j/PMkRf4PkMNFAhvhGTxQBnCeBo8eXzCHM8KklBZRMruGRI19g8oKEz339JNeS93pAlc8u344AUbhgIutGjbQ6POLKCyTVPTBG38y4ECgYEA9Pt1GMWZSra951ule3TW0fCTTUtKwcIJKTsv7awbUnEz/+lzKupkMFZIdwCZ19z1NkNqht8peEGl3KnmVhbQglo1Nk/34k4ItO/aGHFM0zspQTUAxpD1j2ISZkZXy4s2KXUA8kZJ8KB25F0o9Hnmq7hRSuTEV1bIZjRL5rgaHukCgYEAyZL9TLG5wJCX5hR1GSco8j0pUj9bHzBm+LrPsHTHs1v6VvxxUvWVrWwpBcnvzZfFoIqOwcXgTpdx9Cqp7cvR4glbbOajtuG72fuehOb989psWy+oIyQ/KZVEdmLW7eduqBUiKqfeCXBz1N5XqkQ9C/46i6fheMjsKGF4EYh3w6kCgYEAqDp1Msmedpv+1H0uqhSV4BzgSlfFwkBIojOuuftR18888iORnorOuhLGOFIFUyK8kMLCyFM+2wHZsCpLrhqEnL7tg5puks0bVYTPs7E6HGOn9Ms8+R5aQlgKYokziObUDqv2NmjmJYNLoY4Hrbp4GLu9tzxZR9CkVOrcpFcQOOECgYBIcIqXBUe77DqqdK+gWnJcm5498IbOrpZSzM/WmiEqU+OjXxVauucPa89SRT/ooqJjD2g+Ot5hYAuTs8Fds+G5WaOBf2FWV8kqjUJqgJjDCbvnCTljZ6NLTllMMjkLRiB+RxoAOlORENiPp1fZ2KwXo0Vm6u4cehhkaqK7qk9gAQKBgCh3+z3SQpARiZ1k5t+pL3/z005UqsBHIy67YytR3m1tCM5SRAsDYFTZZW1F7ETgIg2zCrS3Ov9520i4VsyCow+l2OC5srMKnpnPi3fDyyRjBFBQ1pSLUb5oAg+d1KFIjaz4GhY58i1AHDJhBKwUu4n6X45jhnMv6Z6j6p1ZJGVC";
        String verifyStr = getSignStr(reqMap);
        log.info("待验签字符串 {}", verifyStr);
        boolean flag = verify(base64PubKey, verifyStr.getBytes("UTF-8"), Base64.decode(signStr));
    }

    public static String post(String url, List<NameValuePair> paramsList) throws IOException {
        HttpPost httpPost=new HttpPost(url);
        httpPost.setEntity(new UrlEncodedFormEntity(paramsList, "UTF-8"));
        HttpClient httpClient= HttpClientBuilder.create().build();
        HttpResponse response = httpClient.execute(httpPost);
        String result="";
        if(response != null){
            StatusLine statusLine = response.getStatusLine();
            if(statusLine != null){
                log.info("StatusCode={}",statusLine.getStatusCode());
            }
            HttpEntity resEntity = response.getEntity();
            if(resEntity != null){
                result= EntityUtils.toString(resEntity,"UTF-8");
            }
        }
        log.info(result);
        return result;
    }

    /**
     * 数字签名
     * @param data 待签名数据
     * @param pri_key 私钥
     * @return 签名
     * @throws Exception 抛出异常
     */
    public static String sign(byte[] data, String pri_key) throws Exception {
        // 取得私钥
        byte[] pri_key_bytes = Base64.decode(pri_key);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(pri_key_bytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        // 生成私钥
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
        // 实例化Signature
        Signature signature = Signature.getInstance("SHA256withRSA");
        // 初始化Signature
        signature.initSign(priKey);
        // 更新
        signature.update(data);
        return Base64.toBase64String(signature.sign());
    }

    public static Boolean verify(String publicKey, byte[] sourceData, byte[] signature) throws Exception{
        byte[] keyBytes = Base64.decode(publicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey pubKey = keyFactory.generatePublic(keySpec);
        Signature e = Signature.getInstance("SHA256withRSA");
        e.initVerify(pubKey);
        e.update(sourceData);
        return e.verify(signature);
    }

    /**
     * 对Map中的值按字段名的ASCII码值按升序排序
     *
     * @param map 待排序Map
     * @return 排序后的字符串
     */
    public static String getSignStr(Map<String, String> map) {
        String signStr = null;
        if (map != null) {
            Map<String, String> sortMap = new TreeMap<>(Comparator.naturalOrder());
            //将请求Map中的字段名按ASCII码值进行排序
            for (String key : map.keySet()) {
                if (!StringUtils.isEmpty(key) && !StringUtils.isEmpty(map.get(key)) && !"null".equals(map.get(key))) {
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
        log.info("-----------签名原文-----------str:{}", JSON.toJSONString(signStr));
        return signStr;
    }
    /**
     * 随机生成 num位数字字符数组
     *
     * @param num
     * @return
     */
    public static char[] generateRandomArray(int num) {
        String chars = "0123456789";
        char[] rands = new char[num];
        for (int i = 0; i < num; i++) {
            int rand = (int) (Math.random() * 10);
            rands[i] = chars.charAt(rand);
        }
        return rands;
    }
}
