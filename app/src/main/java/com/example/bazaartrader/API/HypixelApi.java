package com.example.bazaartrader.API;


import net.hypixel.api.HypixelAPI;
import net.hypixel.api.http.HypixelHttpClient;
import net.hypixel.api.reply.skyblock.SkyBlockBazaarReply;
import net.hypixel.api.unirest.UnirestHttpClient;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class HypixelApi {

    private String API_KEY;
    private HypixelHttpClient httpClient;
    private HypixelAPI hypixelAPI;

    public HypixelApi(String API_KEY) {
        this.API_KEY = API_KEY;
        httpClient = new UnirestHttpClient(UUID.fromString(API_KEY));
        hypixelAPI = new HypixelAPI(httpClient);
    }


    public CompletableFuture<List<SkyBlockBazaarReply.Product>> getBazaarAsync() {
        CompletableFuture<SkyBlockBazaarReply> future = hypixelAPI.getSkyBlockBazaar();
        return future.thenApply(reply -> new ArrayList<>(reply.getProducts().values()));
    }

    public static List<SkyBlockBazaarReply.Product> getBestMargin(List<SkyBlockBazaarReply.Product> data) {
        // Фильтруем продукты с менее чем 10 ордерами на покупку
        List<SkyBlockBazaarReply.Product> allNeeded = data.stream()
                .filter(item -> item.getQuickStatus().getBuyOrders() > 40)
                .collect(Collectors.toList());

        // Сортируем по убыванию разницы цен
        Comparator<SkyBlockBazaarReply.Product> byMarginDesc = Comparator.comparing(
                item -> {
                    BigDecimal sellPrice = BigDecimal.valueOf(item.getQuickStatus().getSellPrice());
                    BigDecimal buyPrice = BigDecimal.valueOf(item.getQuickStatus().getBuyPrice());
                    BigDecimal margin = sellPrice.subtract(buyPrice);
                    return margin.negate(); // Используем negate() для сортировки по убыванию
                }
        );

        List<SkyBlockBazaarReply.Product> sortedList = allNeeded.stream()
                .sorted(byMarginDesc.reversed())
                .collect(Collectors.toList());

        return sortedList;
    }
}
