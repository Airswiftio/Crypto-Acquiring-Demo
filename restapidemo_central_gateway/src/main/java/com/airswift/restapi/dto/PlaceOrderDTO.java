package com.airswift.restapi.dto;


import java.math.BigDecimal;

/**
 * Input parameters for  Place Order
 */
public class PlaceOrderDTO {

    private String merchantId;

    private String merchantOrderId;

    private String merchantOrderTime;

    private String orderCurrency;

    private BigDecimal orderAmount;

    private String productDetail;

    private String serverUrl;

    private String browserUrl;

    private String sign;

    public String getMerchantId() {
        return merchantId;
    }

    public String getMerchantOrderId() {
        return merchantOrderId;
    }

    public String getMerchantOrderTime() {
        return merchantOrderTime;
    }

    public String getOrderCurrency() {
        return orderCurrency;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public String getProductDetail() {
        return productDetail;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public void setMerchantOrderId(String merchantOrderId) {
        this.merchantOrderId = merchantOrderId;
    }

    public void setMerchantOrderTime(String merchantOrderTime) {
        this.merchantOrderTime = merchantOrderTime;
    }

    public void setOrderCurrency(String orderCurrency) {
        this.orderCurrency = orderCurrency;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public void setProductDetail(String productDetail) {
        this.productDetail = productDetail;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public void setBrowserUrl(String browserUrl) {
        this.browserUrl = browserUrl;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getBrowserUrl() {
        return browserUrl;
    }

    public String getSign() {
        return sign;
    }




    public PlaceOrderDTO() {
    }


}
