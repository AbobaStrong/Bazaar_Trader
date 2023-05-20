package com.example.bazaartrader.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FavoriteProductDao {
    @Insert
    void insert(FavoriteProduct product);

    @Update
    void update(FavoriteProduct product);

    @Delete
    void delete(FavoriteProduct product);

    @Query("SELECT * FROM favorite_products")
    List<FavoriteProduct> getAllProducts();

    @Query("SELECT * FROM favorite_products WHERE product_name = :productId LIMIT 1")
    FavoriteProduct getProductById(String productId);
}

