package org.exotic.dto;

/**
 * Created by gaozs on 2017/10/23.
 */
public class OrderModel {
    private String orderId;
    private String price;
    private String number;
    private String serialNum;
    private String content;
    private String port;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getPort() {
        return port;
    }
}
