package com.example.bazaartrader.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bazaartrader.Database.FavoriteProduct;
import com.example.bazaartrader.RecyclerView.ItemClickListener;
import com.example.bazaartrader.RecyclerView.Adapters.MarginRecyclerViewAdapter;
import com.example.bazaartrader.R;
import com.example.bazaartrader.databinding.FragmentMarginBinding;

import net.hypixel.api.reply.skyblock.SkyBlockBazaarReply;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class MarginFragment extends Fragment implements ItemClickListener {

    private FragmentMarginBinding binding;
    private List<SkyBlockBazaarReply.Product> bestMargin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMarginBinding.inflate(inflater, container, false);

        // Retrieve the fragment arguments
        Bundle args = getArguments();
        if (args != null) {
            bestMargin = (List<SkyBlockBazaarReply.Product>) args.getSerializable("bestMargin");
        } else {
            // Handle the case when arguments are not available
            bestMargin = new ArrayList<>(); // or any other appropriate default value
        }

        binding.marginRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        MarginRecyclerViewAdapter adapter = new MarginRecyclerViewAdapter(getContext(), bestMargin);
        binding.marginRecyclerView.setAdapter(adapter);

        // Set item click listener on the adapter
        adapter.setItemClickListener(this);

        return binding.getRoot();
    }

    @Override
    public void onItemClick(int position) {

        FavoriteProduct selectedProduct = new FavoriteProduct();
        selectedProduct.setProductName(bestMargin.get(position).getProductId());
        selectedProduct.setBuyPrice(bestMargin.get(position).getQuickStatus().getBuyPrice());
        selectedProduct.setSellPrice(bestMargin.get(position).getQuickStatus().getSellPrice());
        selectedProduct.setBuyOrders(bestMargin.get(position).getQuickStatus().getBuyOrders());
        selectedProduct.setSellOrders(bestMargin.get(position).getQuickStatus().getSellOrders());
        selectedProduct.setBuyVolume(bestMargin.get(position).getQuickStatus().getBuyVolume());
        selectedProduct.setSellVolume(bestMargin.get(position).getQuickStatus().getSellVolume());
        selectedProduct.setMargin(bestMargin.get(position).getQuickStatus().getBuyPrice() - bestMargin.get(position).getQuickStatus().getSellPrice());

        Bundle bundle = new Bundle();
        bundle.putSerializable("selectedCustomProduct", (Serializable)selectedProduct);

        Navigation.findNavController(binding.getRoot()).navigate(R.id.action_marginFragment_to_itemFragment, bundle);
    }
}