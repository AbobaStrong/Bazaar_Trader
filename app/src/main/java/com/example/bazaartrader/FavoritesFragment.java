package com.example.bazaartrader;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bazaartrader.databinding.FragmentFavoritesBinding;

import net.hypixel.api.reply.skyblock.SkyBlockBazaarReply;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import kotlinx.coroutines.channels.ProduceKt;

public class FavoritesFragment extends Fragment implements ItemClickListener {

    private FragmentFavoritesBinding binding;
    private List<SkyBlockBazaarReply.Product> data = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentFavoritesBinding.inflate(inflater, container, false);

        binding.favoritesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        FavoritesRecyclerViewAdapter adapter = new FavoritesRecyclerViewAdapter(getContext(), data);
        binding.favoritesRecyclerView.setAdapter(adapter);

        // Установка слушателя нажатий на адаптере
        adapter.setItemClickListener(this);

        return binding.getRoot();
    }

    @Override
    public void onItemClick(int position) {
        // Получение выбранного предмета из bestMargin
        //TODO
        /*
        SkyBlockBazaarReply.Product selectedProduct = bestMargin.get(position);

        // Создание и передача Bundle
        Bundle bundle = new Bundle();
        bundle.putSerializable("selectedProduct", (Serializable)selectedProduct);

        Navigation.findNavController(binding.getRoot()).navigate(R.id.action_favoritesFragment_to_itemFragment, bundle);
        */
    }

}