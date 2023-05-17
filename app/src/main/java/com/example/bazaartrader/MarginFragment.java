package com.example.bazaartrader;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bazaartrader.databinding.FragmentFavoritesBinding;
import com.example.bazaartrader.databinding.FragmentMarginBinding;

import net.hypixel.api.reply.skyblock.SkyBlockBazaarReply;

import java.util.ArrayList;
import java.util.List;


public class MarginFragment extends Fragment {


    private FragmentMarginBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentMarginBinding.inflate(inflater, container, false);

        // Получаем аргументы фрагмента
        Bundle args = getArguments();
        List<SkyBlockBazaarReply.Product> bestMargin =  (List<SkyBlockBazaarReply.Product>) args.getSerializable("bestMargin");
        binding.marginRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.marginRecyclerView.setAdapter(new MarginRecyclerViewAdapter(getContext(),bestMargin));

        return binding.getRoot();
    }
}