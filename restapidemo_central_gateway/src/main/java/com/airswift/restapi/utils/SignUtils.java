package com.airswift.restapi.utils;

import org.apache.commons.beanutils.PropertyUtilsBean;
import java.beans.PropertyDescriptor;
import java.util.*;

/**
 * Signature utility
 */
public class SignUtils {
    /**
     * Generate online signature
     *
     * @param obj        Input entity
     * @param privateKey privateKey
     * @return Generate online signature
     */
    public static String createOnelineSign(Object obj, String privateKey) {
        HashMap<String, String> map = beanToMap(obj);
        byte[] msg = getSignStr(map).getBytes();
        String signMsg = null;
        try {
            signMsg = RSAUtils.sign(msg, privateKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return signMsg;
    }


    public static HashMap<String, String> beanToMap(Object obj) {
        HashMap<String, String> params = new HashMap<String, String>(0);
        try {
            PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
            PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(obj);
            for (int i = 0; i < descriptors.length; i++) {
                String name = descriptors[i].getName();
                if (!"class".equals(name)) {
                    params.put(name, String.valueOf(propertyUtilsBean.getNestedProperty(obj, name)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return params;
    }

    public static HashMap<String, Object> beanToObjMap(Object obj) {
        HashMap<String, Object> params = new HashMap<String, Object>(0);
        try {
            PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
            PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(obj);
            for (int i = 0; i < descriptors.length; i++) {
                String name = descriptors[i].getName();
                if (!"class".equals(name)) {
                    params.put(name, propertyUtilsBean.getNestedProperty(obj, name));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return params;
    }

    /**
     * Sort, empty, and sort property values alphabetically by property name
     *
     * @param m parameter map
     * @return Clear text before signing
     */
    private static String getSignStr(Map<String, String> m) {
        String signStr = null;
        if (m != null) {
            Map<String, String> map = new TreeMap<String, String>();
            Set<String> ks = m.keySet();
            for (String key : ks) {
                if (key != null && !"".equals(key)) {
                    String value = m.get(key);
                    if (value != null && !"".equals(value) && !"null".equals(value)) {
                        map.put(key, value);
                    }
                }
            }
            StringBuilder sb = new StringBuilder();
            Set<String> keySet = map.keySet();
            for (String key : keySet) {
                String val = map.get(key);
                if (val != null && !"".equals(val) && !"null".equals(val)) {
                    sb.append(val.trim());
                }
            }
            signStr = sb.toString();
            System.out.println("Clear text before signing: " + signStr);

        }
        return signStr;
    }
}
