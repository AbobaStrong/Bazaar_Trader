package com.example.bazaartrader;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import com.example.bazaartrader.databinding.FragmentMainBinding;
import java.io.Serializable;
import java.text.DecimalFormat;


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

        Bundle bundle = new Bundle();
        HypixelApi hypixelApi = new HypixelApi("19336b0d-bc40-403b-a7cf-db2302770ffd");

        // Запускаем получение данных в фоновом потоке
        Observable.fromCallable(() -> hypixelApi.getBazaarAsync().get())
                .map(bazaarData -> hypixelApi.getBestMargin(bazaarData))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bestMargin -> {
                    bundle.putSerializable("bestMargin", (Serializable) bestMargin);
                    binding.marginFirstItemName.setText(bestMargin.get(0).getProductId());
                    binding.marginFirstItemPrice.setText(formatNumber(bestMargin.get(0).getQuickStatus().getBuyPrice() - bestMargin.get(0).getQuickStatus().getSellPrice()));

                    binding.marginSecondItemName.setText(bestMargin.get(1).getProductId());
                    binding.marginSecondItemPrice.setText(formatNumber(bestMargin.get(1).getQuickStatus().getBuyPrice() - bestMargin.get(1).getQuickStatus().getSellPrice()));

                    binding.marginThirdItemName.setText(bestMargin.get(2).getProductId());
                    binding.marginThirdItemPrice.setText(formatNumber(bestMargin.get(2).getQuickStatus().getBuyPrice() - bestMargin.get(2).getQuickStatus().getSellPrice()));
                }, e -> {
                    Log.e("MainFragment", "Ошибка получения данных", e);
                });


        binding.favoritesSeeAll.setOnClickListener(v -> Navigation.findNavController(binding.getRoot()).navigate(R.id.action_mainFragment_to_favoritesFragment));
        binding.marginSeeAll.setOnClickListener(v -> Navigation.findNavController(binding.getRoot()).navigate(R.id.action_mainFragment_to_marginFragment,bundle));

        return binding.getRoot();
    }
    public static String formatNumber(double number) {
        if (number < 1000) {
            return String.valueOf(number); // Возвращаем число как строку
        }

        String suffix = "";
        double value = number;

        // Определяем суффикс
        if (number >= 1_000 && number < 1_000_000) {
            suffix = "K";
            value = number / 1_000.0;
        } else if (number >= 1_000_000 && number < 1_000_000_000) {
            suffix = "M";
            value = number / 1_000_000.0;
        } else if (number >= 1_000_000_000 && number < 1_000_000_000_000L) {
            suffix = "B";
            value = number / 1_000_000_000.0;
        } else if (number >= 1_000_000_000_000L && number < 1_000_000_000_000_000L) {
            suffix = "T";
            value = number / 1_000_000_000_000.0;
        }

        // Округляем число до 1 цифры после запятой
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        String formattedValue = decimalFormat.format(value);

        // Форматируем число с округленным значением и добавляем суффикс
        return formattedValue + suffix;
    }
}



