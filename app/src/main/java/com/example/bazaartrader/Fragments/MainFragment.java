package com.example.bazaartrader.Fragments;

import static com.example.bazaartrader.RecyclerView.Format.convertString;
import static com.example.bazaartrader.RecyclerView.Format.formatNumber;
import static com.example.bazaartrader.RecyclerView.Format.setImage;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.bazaartrader.Database.FavoriteProduct;
import com.example.bazaartrader.API.HypixelApi;
import com.example.bazaartrader.R;
import com.example.bazaartrader.databinding.FragmentMainBinding;

import net.hypixel.api.reply.skyblock.SkyBlockBazaarReply;

import java.io.Serializable;
import java.util.List;


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainFragment extends Fragment {

    private FragmentMainBinding binding;

    @SuppressLint("CheckResult")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentMainBinding.inflate(inflater, container, false);

        Bundle marginBundle = new Bundle();
        Bundle itemClickBundle = new Bundle();
        HypixelApi hypixelApi = new HypixelApi("19336b0d-bc40-403b-a7cf-db2302770ffd");

        // Запускаем получение данных в фоновом потоке
        Observable.fromCallable(() -> hypixelApi.getBazaarAsync().get())
                .map(bazaarData -> hypixelApi.getBestMargin(bazaarData))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bestMargin -> {
                    marginBundle.putSerializable("bestMargin", (Serializable) bestMargin);
                    binding.marginFirstItemName.setText(convertString(bestMargin.get(0).getProductId()));
                    binding.marginFirstItemPrice.setText(formatNumber(bestMargin.get(0).getQuickStatus().getBuyPrice() - bestMargin.get(0).getQuickStatus().getSellPrice()));
                    binding.marginFirstItemImage.setImageResource(setImage(bestMargin.get(0).getProductId()));


                    binding.marginSecondItemName.setText(convertString(bestMargin.get(1).getProductId()));
                    binding.marginSecondItemPrice.setText(formatNumber(bestMargin.get(1).getQuickStatus().getBuyPrice() - bestMargin.get(1).getQuickStatus().getSellPrice()));
                    binding.marginSecondItemImage.setImageResource(setImage(bestMargin.get(1).getProductId()));

                    binding.marginThirdItemName.setText(convertString(bestMargin.get(2).getProductId()));
                    binding.marginThirdItemPrice.setText(formatNumber(bestMargin.get(2).getQuickStatus().getBuyPrice() - bestMargin.get(2).getQuickStatus().getSellPrice()));
                    binding.marginThirdItemImage.setImageResource(setImage(bestMargin.get(2).getProductId()));
                }, e -> {
                    Log.e("MainFragment", "Ошибка получения данных", e);
                });


        binding.favoritesSeeAll.setOnClickListener(v -> Navigation.findNavController(binding.getRoot()).navigate(R.id.action_mainFragment_to_favoritesFragment));
        binding.marginSeeAll.setOnClickListener(v -> Navigation.findNavController(binding.getRoot()).navigate(R.id.action_mainFragment_to_marginFragment,marginBundle));
        binding.marginFirstItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FavoriteProduct selectedProduct = new FavoriteProduct();
                selectedProduct.setProductName(((List<SkyBlockBazaarReply.Product>) marginBundle.getSerializable("bestMargin")).get(0).getProductId());
                selectedProduct.setBuyPrice(((List<SkyBlockBazaarReply.Product>) marginBundle.getSerializable("bestMargin")).get(0).getQuickStatus().getBuyPrice());
                selectedProduct.setSellPrice(((List<SkyBlockBazaarReply.Product>) marginBundle.getSerializable("bestMargin")).get(0).getQuickStatus().getSellPrice());
                selectedProduct.setBuyOrders(((List<SkyBlockBazaarReply.Product>) marginBundle.getSerializable("bestMargin")).get(0).getQuickStatus().getBuyOrders());
                selectedProduct.setSellOrders(((List<SkyBlockBazaarReply.Product>) marginBundle.getSerializable("bestMargin")).get(0).getQuickStatus().getSellOrders());
                selectedProduct.setBuyVolume(((List<SkyBlockBazaarReply.Product>) marginBundle.getSerializable("bestMargin")).get(0).getQuickStatus().getBuyVolume());
                selectedProduct.setSellVolume(((List<SkyBlockBazaarReply.Product>) marginBundle.getSerializable("bestMargin")).get(0).getQuickStatus().getSellVolume());

                itemClickBundle.putSerializable("selectedCustomProduct",(Serializable) selectedProduct);
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_mainFragment_to_itemFragment, itemClickBundle);

            }
        });

        binding.marginSecondItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FavoriteProduct selectedProduct = new FavoriteProduct();
                selectedProduct.setProductName(((List<SkyBlockBazaarReply.Product>) marginBundle.getSerializable("bestMargin")).get(1).getProductId());
                selectedProduct.setBuyPrice(((List<SkyBlockBazaarReply.Product>) marginBundle.getSerializable("bestMargin")).get(1).getQuickStatus().getBuyPrice());
                selectedProduct.setSellPrice(((List<SkyBlockBazaarReply.Product>) marginBundle.getSerializable("bestMargin")).get(1).getQuickStatus().getSellPrice());
                selectedProduct.setBuyOrders(((List<SkyBlockBazaarReply.Product>) marginBundle.getSerializable("bestMargin")).get(1).getQuickStatus().getBuyOrders());
                selectedProduct.setSellOrders(((List<SkyBlockBazaarReply.Product>) marginBundle.getSerializable("bestMargin")).get(1).getQuickStatus().getSellOrders());
                selectedProduct.setBuyVolume(((List<SkyBlockBazaarReply.Product>) marginBundle.getSerializable("bestMargin")).get(1).getQuickStatus().getBuyVolume());
                selectedProduct.setSellVolume(((List<SkyBlockBazaarReply.Product>) marginBundle.getSerializable("bestMargin")).get(1).getQuickStatus().getSellVolume());

                itemClickBundle.putSerializable("selectedCustomProduct",(Serializable) selectedProduct);
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_mainFragment_to_itemFragment, itemClickBundle);
            }
        });

        binding.marginThirdItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FavoriteProduct selectedProduct = new FavoriteProduct();
                selectedProduct.setProductName(((List<SkyBlockBazaarReply.Product>) marginBundle.getSerializable("bestMargin")).get(2).getProductId());
                selectedProduct.setBuyPrice(((List<SkyBlockBazaarReply.Product>) marginBundle.getSerializable("bestMargin")).get(2).getQuickStatus().getBuyPrice());
                selectedProduct.setSellPrice(((List<SkyBlockBazaarReply.Product>) marginBundle.getSerializable("bestMargin")).get(2).getQuickStatus().getSellPrice());
                selectedProduct.setBuyOrders(((List<SkyBlockBazaarReply.Product>) marginBundle.getSerializable("bestMargin")).get(2).getQuickStatus().getBuyOrders());
                selectedProduct.setSellOrders(((List<SkyBlockBazaarReply.Product>) marginBundle.getSerializable("bestMargin")).get(2).getQuickStatus().getSellOrders());
                selectedProduct.setBuyVolume(((List<SkyBlockBazaarReply.Product>) marginBundle.getSerializable("bestMargin")).get(2).getQuickStatus().getBuyVolume());
                selectedProduct.setSellVolume(((List<SkyBlockBazaarReply.Product>) marginBundle.getSerializable("bestMargin")).get(2).getQuickStatus().getSellVolume());

                itemClickBundle.putSerializable("selectedCustomProduct",(Serializable) selectedProduct);
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_mainFragment_to_itemFragment, itemClickBundle);
            }
        });


        return binding.getRoot();
    }

}



