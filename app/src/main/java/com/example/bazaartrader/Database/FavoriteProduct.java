package com.example.bazaartrader.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "favorite_products")
public class FavoriteProduct implements  Comparable<FavoriteProduct>, Serializable {
    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "sell_price")
    private double sellPrice;

    @ColumnInfo(name = "product_name")
    private String productName;

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    @ColumnInfo(name = "buy_price")
    private double buyPrice;

    @ColumnInfo(name = "margin")
    private double margin;

    @ColumnInfo(name = "sell_orders")
    private long sellOrders;

    @ColumnInfo(name = "buy_orders")
    private long buyOrders;

    @ColumnInfo(name = "sell_volume")
    private long sellVolume;

    @ColumnInfo(name = "buy_volume")
    private long buyVolume;

    public void setId(long id) {
        this.id = id;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public void setMargin(double margin) {
        this.margin = margin;
    }

    public void setSellOrders(long sellOrders) {
        this.sellOrders = sellOrders;
    }

    public void setBuyOrders(long buyOrders) {
        this.buyOrders = buyOrders;
    }

    public void setSellVolume(long sellVolume) {
        this.sellVolume = sellVolume;
    }

    public void setBuyVolume(long buyVolume) {
        this.buyVolume = buyVolume;
    }

    public long getId() {
        return id;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public double getMargin() {
        return margin;
    }

    public long getSellOrders() {
        return sellOrders;
    }

    public long getBuyOrders() {
        return buyOrders;
    }

    public long getSellVolume() {
        return sellVolume;
    }

    public long getBuyVolume() {
        return buyVolume;
    }

    @Override
    public int compareTo(FavoriteProduct o) {
        return this.getProductName().compareTo(o.productName);
    }


}

