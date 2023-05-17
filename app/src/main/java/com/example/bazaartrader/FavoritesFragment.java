package com.example.bazaartrader;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bazaartrader.databinding.FragmentFavoritesBinding;

import net.hypixel.api.reply.skyblock.SkyBlockBazaarReply;

import java.util.ArrayList;
import java.util.List;

import kotlinx.coroutines.channels.ProduceKt;

public class FavoritesFragment extends Fragment {

    private FragmentFavoritesBinding binding;
    private List<SkyBlockBazaarReply.Product> data = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentFavoritesBinding.inflate(inflater,container,false);


        binding.favoritesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.favoritesRecyclerView.setAdapter(new FavoritesRecyclerViewAdapter(getContext(),data));



        return binding.getRoot();
    }
}