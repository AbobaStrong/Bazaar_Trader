package com.example.bazaartrader.RecyclerView.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bazaartrader.Database.FavoriteProduct;
import com.example.bazaartrader.RecyclerView.Format;
import com.example.bazaartrader.RecyclerView.ItemClickListener;
import com.example.bazaartrader.databinding.FavoritesListItemBinding;

import java.util.List;

public class FavoritesRecyclerViewAdapter extends RecyclerView.Adapter<FavoritesRecyclerViewAdapter.ViewHolder> {

    private List<FavoriteProduct> data;
    private LayoutInflater localInflater;
    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public FavoritesRecyclerViewAdapter(Context context) {
        this.localInflater = LayoutInflater.from(context);
    }

    public void setData(List<FavoriteProduct> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public FavoritesRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FavoritesListItemBinding binding = FavoritesListItemBinding.inflate(localInflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesRecyclerViewAdapter.ViewHolder holder, int position) {
        FavoriteProduct item = data.get(position);
        holder.itemName.setText(Format.convertString(item.getProductName()));
        holder.itemPrice.setText(Format.formatNumber(item.getBuyPrice()));
        holder.itemImage.setImageResource(Format.setImage(item.getProductName()));
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickedPosition = holder.getAdapterPosition();
                if (itemClickListener != null && clickedPosition != RecyclerView.NO_POSITION) {
                    itemClickListener.onItemClick(clickedPosition);
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


        public ViewHolder(@NonNull FavoritesListItemBinding binding) {
            super(binding.getRoot());
            itemImage = binding.itemImage;
            itemName = binding.itemName;
            itemPrice = binding.itemPrice;
            item = binding.item;
        }

    }
}
