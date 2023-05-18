package com.example.bazaartrader;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.bazaartrader.JsonUtils;
import com.example.bazaartrader.databinding.FragmentItemBinding;
import com.google.gson.reflect.TypeToken;

import net.hypixel.api.reply.skyblock.SkyBlockBazaarReply;

import java.lang.reflect.Type;

public class ItemFragment extends Fragment {
    private FragmentItemBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentItemBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            // Получение переданных данных из Bundle
            String selectedProductJson = bundle.getString("selectedProductJson");
            SkyBlockBazaarReply.Product selectedProduct = JsonUtils.fromJson(selectedProductJson, SkyBlockBazaarReply.Product.class);

            // Использование данных для дальнейшей обработки или отображения
            binding.headerItemPrice.setText(Format.formatNumber(selectedProduct.getQuickStatus().getSellPrice()));
            binding.itemName.setText(Format.convertString(selectedProduct.getProductId()));
            binding.sellPrice.setText("Sell price:"+ String.valueOf(Format.formatNumber(selectedProduct.getQuickStatus().getSellPrice())));
            binding.buyPrice.setText("Buy price:"+ String.valueOf(Format.formatNumber(selectedProduct.getQuickStatus().getBuyPrice())));
            binding.margin.setText("Margin:"+ String.valueOf(Format.formatNumber(selectedProduct.getQuickStatus().getBuyPrice()-selectedProduct.getQuickStatus().getSellPrice())));
            binding.buyOrders.setText("Buy orders:"+ String.valueOf(selectedProduct.getQuickStatus().getBuyOrders()));
            binding.sellOrders.setText("Sell orders:"+ String.valueOf(selectedProduct.getQuickStatus().getSellOrders()));
            binding.buyVolume.setText("Buy volume:"+ String.valueOf(selectedProduct.getQuickStatus().getBuyVolume()));
            binding.sellVolume.setText("Sell volume:"+ String.valueOf(selectedProduct.getQuickStatus().getSellVolume()));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}