package com.example.bazaartrader.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bazaartrader.Database.AppDatabase;
import com.example.bazaartrader.Database.FavoriteProduct;
import com.example.bazaartrader.Database.FavoriteProductDao;
import com.example.bazaartrader.RecyclerView.Format;
import com.example.bazaartrader.R;
import com.example.bazaartrader.databinding.FragmentItemBinding;


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ItemFragment extends Fragment {
    private FragmentItemBinding binding;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private FavoriteProductDao productDao;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentItemBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        productDao = AppDatabase.getInstance(requireContext()).favoriteProductDao();

        Bundle bundle = getArguments();
        if (bundle != null) {
            FavoriteProduct selectedProduct = (FavoriteProduct) bundle.getSerializable("selectedCustomProduct");

            if (selectedProduct != null) {

                binding.headerItemPrice.setText(Format.formatNumber(selectedProduct.getSellPrice()));
                binding.itemName.setText(Format.convertString(selectedProduct.getProductName()));
                binding.sellPrice.setText("Sell price: " + Format.formatNumber(selectedProduct.getSellPrice()));
                binding.buyPrice.setText("Buy price: " + Format.formatNumber(selectedProduct.getBuyPrice()));
                binding.margin.setText("Margin: " + Format.formatNumber(selectedProduct.getMargin()));
                binding.buyOrders.setText("Buy orders: " + selectedProduct.getBuyOrders());
                binding.sellOrders.setText("Sell orders: " + selectedProduct.getSellOrders());
                binding.buyVolume.setText("Buy volume: " + selectedProduct.getBuyVolume());
                binding.sellVolume.setText("Sell volume: " + selectedProduct.getSellVolume());

                compositeDisposable.add(
                        Observable.fromCallable(() -> productDao.getAllProducts())
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(allFavorites -> {
                                    boolean inFavorites = false;
                                    for (FavoriteProduct product : allFavorites) {
                                        if (product.getProductName().equals(selectedProduct.getProductName())) {
                                            inFavorites = true;
                                            break;
                                        }
                                    }

                                    if (inFavorites) {
                                        binding.favoritesStar.setImageResource(R.drawable.baseline_star_24);
                                    } else {
                                        binding.favoritesStar.setImageResource(R.drawable.baseline_star_border_24);
                                    }

                                    boolean finalInFavorites = inFavorites;
                                    binding.favoritesStar.setOnClickListener(v -> {
                                        if (finalInFavorites) {
                                            compositeDisposable.add(
                                                    Observable.fromCallable(() -> {
                                                                productDao.delete(productDao.getProductById(selectedProduct.getProductName()));
                                                                return true;
                                                            })
                                                            .subscribeOn(Schedulers.io())
                                                            .observeOn(AndroidSchedulers.mainThread())
                                                            .subscribe(result -> {
                                                                binding.favoritesStar.setImageResource(R.drawable.baseline_star_border_24);
                                                            }, throwable -> {
                                                                // Handle error
                                                            })
                                            );
                                        } else {


                                            compositeDisposable.add(
                                                    Observable.fromCallable(() -> {
                                                                productDao.insert(selectedProduct);
                                                                return true;
                                                            })
                                                            .subscribeOn(Schedulers.io())
                                                            .observeOn(AndroidSchedulers.mainThread())
                                                            .subscribe(result -> {
                                                                binding.favoritesStar.setImageResource(R.drawable.baseline_star_24);
                                                            }, throwable -> {
                                                                // Handle error
                                                            })
                                            );
                                        }
                                    });
                                }, throwable -> {
                                    // Handle error
                                })
                );
            }
        }
    }

        @Override
        public void onDestroyView () {
            super.onDestroyView();
            binding = null;
            compositeDisposable.clear();
        }
    }

