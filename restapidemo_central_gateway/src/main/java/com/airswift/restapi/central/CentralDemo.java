package com.airswift.restapi.central;

import com.airswift.restapi.utils.SignRsaUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 数字货币下单的demo
 */
public class CentralDemo {

    public static void main(String[] args) {
        Map<String,String> dataMap = new HashMap<String,String>();
        dataMap.put("appKey","0d479139-fa3e-457c-bdc3-9b6cc22f6cfa");
        dataMap.put("nonce","421427");
        dataMap.put("timestamp","1658909065813");
        dataMap.put("clientOrderSn","14556111188911217");
        dataMap.put("tradeType","0");
        dataMap.put("coinUnit","USDT");
        dataMap.put("basicsType","1");
        dataMap.put("amount","0.01");
        dataMap.put("remarks","test");
        System.out.println("请求参数bizContent:"+dataMap);
        String dataMapStr = SignRsaUtil.getSignStr(dataMap);
        System.out.println("dataMapStr is:"+dataMapStr);
        Map<String,Object> requestMap = new HashMap<String,Object>();
        requestMap.put("bizContent",dataMap);
        try {
            String sign = SignRsaUtil.sign(dataMapStr.getBytes("UTF-8"),"MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC2PDE90HktDegQDT2KE2hguIJe3QVOOEcjFHWZS9PFYKNiMv6JD7mn4BACwruEnt2gkJWh3C3abdeTZOkucWZhd6JT5ytrrcYtd9ZT5qZD192MCPJ9nZ7srlikLX0gTRhYv3NmsTUDdOFwmpXMm3pOO2be55hzff2jlq+989SESbbEG6cJCza5uCVBu/eYuXXsiRnO47lCOxOvTSxNCHVkR3FupD2UgFRhfNJf4A1JRAF/xewuV/E5cyQ2jW2nblOtQ5vRuGLBeivp8/bGemQQ6qzQ8HcNb/0KAZUOf20q6uT5EqwjoFRSwrGAy91f/xVqy/x5OX3cvflSb54DlwBDAgMBAAECggEAMNseUHYtpniN+I+0KepeF2AgA5K5s2i/GZaImJqNoeLwqokFWHWQzoLxh+vxHfMBc+SPWR+QY3kDTI7HPR5F1meea2QUbf/em53/Jes2NAYRbVOQv0CctNNhDFLtstU4a8KYC7YCgnS5Y9jtxDcCqSqfv67TXlMuxsbFQuW12ej88+HSQD5RWf4rgnBn4Ov6fPoL8mwFASy/re2mCaFCsz5tbr/IqZJovZwAqQfKeMSD8PLU91zSz0NbjKpbVVxeOu6kh0WEFbii1oSVQJdSN3OQwzUoWIVMZn2WwolqBuQDg/DUtLIDhXIB2o3fraiZWBoNzFQ5hlrEzTk/Fbx3sQKBgQDtat2f3FMsz3K3nmVScYd4K79YbacU7ZxiGhpnB/Y9PcnruT0XcBSqHfzqmooEOHVzvoffuOUy2woJx/MZ9oPYcB8NQJZuHSASofjIZMOGfv+xmtKWzG1amBqY3Wd0MOWN4JVsMwLPSXAOpYUqyR6/PctLvyMTPtxLJsmNA8cfhwKBgQDEf6Nn+o9G5t5k+XM6N4uT9Xdrd3FHNYBYjGhcUOIh8Bj6OYIzNOYFlwjroalTkeoUzNQ9js23z5cTQ1wNsxOiRK1wJTlzyyCnjs0c/y6BRUCQZl/qQPyAaWwrIqqg/y2bSQhMmAR4Xj7NOAFl3nh+fwNX9Yi1PMBxeIVWR//wZQKBgQDetaGCRSaM4WimUGKRjMfcdYVACVRzcFWDPSNJRRG67wxMIG7rXosz7X+D/0W6oc+jrLY+qm3uX8Sx2iv0TefuD3dBcjXqw02DzQbXzJYRw25OB4LGTKvEEOzaOs7CEuAzT9o7LjrTgfso8iHSOemq0eezfXAErLHvmUn2CvYuXwKBgQCYeAGvGLg758AA0Z4bOiZPtFPpS1DWVCk2jBweJZSRiN+YqS7h1NX22G7Phyz/72SWpyi4ayosyiMLi2TOLHm7m1aIghnGQ4MfAKsfKNJI9BdY9FbQSYSjjymjfs6vBdG5gD20UPCHBXntett609jjLmLFyXs+Rdl9Mmsju9JYEQKBgBHoCjpFTZ1l6XmzBYCQKXj4n478P8zrJJp6lvyfXmsnsObWPc0TQ/uXZcgFCwM13OUVTz12o/dbulaG0H81fYd0N759N9zfTVdPjdFxbXWDpbp1VNcXOOgEsv4Dfo4cOnjVsTaViBmkbpZRT2+cw9JPG1SI1TDlqzoLOSNVM+DM");
            System.out.println("sign:"+sign);
            requestMap.put("signStr",sign);
        } catch (Exception e) {
            System.out.println("sign error:"+e);
        }
    }
}
