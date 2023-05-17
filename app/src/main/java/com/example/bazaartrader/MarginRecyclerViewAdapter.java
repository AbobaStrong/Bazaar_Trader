package com.example.bazaartrader;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bazaartrader.databinding.FavoritesListItemBinding;

import net.hypixel.api.reply.skyblock.SkyBlockBazaarReply;

import java.text.DecimalFormat;
import java.util.List;

public class MarginRecyclerViewAdapter extends RecyclerView.Adapter<MarginRecyclerViewAdapter.ViewHolder> {

    private List<SkyBlockBazaarReply.Product> data;
    private LayoutInflater localInflater;

    public MarginRecyclerViewAdapter(Context context, List<SkyBlockBazaarReply.Product> data) {
        this.data = data;
        this.localInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MarginRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FavoritesListItemBinding binding = FavoritesListItemBinding.inflate(localInflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MarginRecyclerViewAdapter.ViewHolder holder, int position) {
        SkyBlockBazaarReply.Product item = data.get(position);
        holder.itemName.setText(convertString(item.getProductId()));
        holder.itemPrice.setText(formatNumber(item.getQuickStatus().getBuyPrice() - item.getQuickStatus().getSellPrice()));

    }

    public static String convertString(String input) {
        // Разделяем строку по символу "_"
        String[] words = input.split("_");

        // Создаем новую строку для результата
        StringBuilder result = new StringBuilder();

        // Проходим по каждому слову
        for (String word : words) {
            // Пропускаем слово "ENCHANTMENT"
            if (word.equals("ENCHANTMENT")) {
                continue;
            }

            // Преобразуем первую букву в верхний регистр
            String capitalizedWord = word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();

            // Добавляем преобразованное слово в результат
            result.append(capitalizedWord).append(" ");
        }

        // Удаляем последний пробел
        result.deleteCharAt(result.length() - 1);

        // Возвращаем результат в виде строки
        return result.toString();
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



    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView itemImage;
        TextView itemName;
        TextView itemPrice;
        ImageView itemArrow;
        TextView dailyChange;
        public ViewHolder(@NonNull FavoritesListItemBinding binding) {
            super(binding.getRoot());
            itemImage = binding.itemImage;
            itemName = binding.itemName;
            itemPrice = binding.itemPrice;
            itemArrow = binding.itemArrow;
            dailyChange = binding.dailyChange;
        }

    }
}
