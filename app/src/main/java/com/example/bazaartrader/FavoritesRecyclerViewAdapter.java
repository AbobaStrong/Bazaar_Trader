package com.example.bazaartrader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bazaartrader.databinding.FavoritesListItemBinding;

import net.hypixel.api.reply.skyblock.SkyBlockBazaarReply;

import java.util.List;

public class FavoritesRecyclerViewAdapter extends RecyclerView.Adapter<FavoritesRecyclerViewAdapter.ViewHolder> {

    private List<SkyBlockBazaarReply.Product> data;
    private LayoutInflater localInflater;

    public FavoritesRecyclerViewAdapter(Context context, List<SkyBlockBazaarReply.Product> data) {
        this.data = data;
        this.localInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public FavoritesRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FavoritesListItemBinding binding = FavoritesListItemBinding.inflate(localInflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesRecyclerViewAdapter.ViewHolder holder, int position) {
        SkyBlockBazaarReply.Product item = data.get(position);
        holder.itemName.setText(item.getProductId());
        //holder.itemImage.setImageResource(item.itemImage); // should be drawable
        holder.itemPrice.setText(String.valueOf(item.getQuickStatus().getBuyPrice()));

        //holder.itemArrow.setImageResource(item.itemArrow); // R.drawable.....
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
