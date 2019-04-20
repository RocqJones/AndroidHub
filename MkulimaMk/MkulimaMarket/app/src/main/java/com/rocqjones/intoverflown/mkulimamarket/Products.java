package com.rocqjones.intoverflown.mkulimamarket;

import java.util.Date;

public class Products {

    private String locationHolder;
    private String priceHolder;
    private Date dateHolder;
    private String productHolder;
    private String productQuatintyHolder;

    public Products(String location, String price, String productQuatinty, String product, Date date) {
        locationHolder = location;
        priceHolder = price;
        dateHolder = date;
        productHolder = product;
        productQuatintyHolder = productQuatinty;
    }

    public String getLocationHolder() {
        return locationHolder;
    }

    public void setLocationHolder(String locationHolder) {
        this.locationHolder = locationHolder;
    }

    public String getPriceHolder() {
        return priceHolder;
    }

    public void setPriceHolder(String priceHolder) {
        this.priceHolder = priceHolder;
    }

    public Date getDateHolder() {
        return dateHolder;
    }

    public void setDateHolder(Date dateHolder) {
        this.dateHolder = dateHolder;
    }

    public String getProductHolder() {
        return productHolder;
    }

    public void setProductHolder(String productHolder) {
        this.productHolder = productHolder;
    }

    public String getProductQuatintyHolder() {
        return productQuatintyHolder;
    }

    public void setProductQuatintyHolder(String productQuatintyHolder) {
        this.productQuatintyHolder = productQuatintyHolder;
    }
}
