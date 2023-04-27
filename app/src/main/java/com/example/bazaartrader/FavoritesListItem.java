package com.example.bazaartrader;

import android.media.Image;

public class FavoritesListItem {
    public String itemName;
    public Integer itemImage;
    public Integer itemPrice;
    public Integer itemArrow;
    public Integer dailyChange;

    public Integer sellPrice;
    public Integer buyPrice;
    public Integer margin;
    public Integer buyOrders;
    public Integer sellOrders;
    public Integer buyVolume;
    public Integer sellVolume;

    public FavoritesListItem(String itemName,
                             Integer itemImage,
                             Integer itemPrice,
                             Integer itemArrow,
                             Integer dailyChange,
                             Integer sellPrice,
                             Integer buyPrice,
                             Integer buyOrders,
                             Integer sellOrders,
                             Integer buyVolume,
                             Integer sellVolume) {
        this.itemName = itemName;
        this.itemImage = itemImage;
        this.itemPrice = itemPrice;
        this.itemArrow = itemArrow;
        this.dailyChange = dailyChange;


        this.sellPrice = sellPrice;
        this.buyPrice = buyPrice;
        this.margin = buyPrice - sellPrice;
        this.buyOrders = buyOrders;
        this.sellOrders = sellOrders;
        this.buyVolume = buyVolume;
        this.sellVolume = sellVolume;
    }


}
