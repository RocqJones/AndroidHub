package com.rocqjones.intoverflown.mkulimamarket;

public class RetriveData {

    public RetriveData() {
    }

    public String productPrice;
    public String sellername;
    public String productQty;
    public String productName;
    public String date;
    public  String location;

    public RetriveData(String productPrice, String sellername, String productQty, String productName, String date, String location) {
        this.productPrice = productPrice;
        this.sellername = sellername;
        this.productQty = productQty;
        this.productName = productName;
        this.date = date;
        this.location = location;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getSellername() {
        return sellername;
    }

    public void setSellername(String sellername) {
        this.sellername = sellername;
    }

    public String getProductQty() {
        return productQty;
    }

    public void setProductQty(String productQty) {
        this.productQty = productQty;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
