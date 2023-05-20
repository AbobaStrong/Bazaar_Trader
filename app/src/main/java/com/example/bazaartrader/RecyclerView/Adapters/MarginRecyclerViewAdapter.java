package com.example.bazaartrader.RecyclerView.Adapters;

import static com.example.bazaartrader.RecyclerView.Format.convertString;
import static com.example.bazaartrader.RecyclerView.Format.formatNumber;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bazaartrader.RecyclerView.ItemClickListener;
import com.example.bazaartrader.databinding.FavoritesListItemBinding;

import net.hypixel.api.reply.skyblock.SkyBlockBazaarReply;

import java.util.List;

public class MarginRecyclerViewAdapter extends RecyclerView.Adapter<MarginRecyclerViewAdapter.ViewHolder> {

    private List<SkyBlockBazaarReply.Product> data;
    private LayoutInflater localInflater;
    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }
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
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemClickListener != null){
                    itemClickListener.onItemClick(position);
                }
            }
        });
    }







    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout item;
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
            item = binding.item;
        }

    }
}
