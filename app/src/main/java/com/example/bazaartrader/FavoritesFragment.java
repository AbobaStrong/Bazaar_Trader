package com.example.bazaartrader;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bazaartrader.databinding.FragmentFavoritesBinding;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment {

    private FragmentFavoritesBinding binding;
    private List<FavoritesListItem> data = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentFavoritesBinding.inflate(inflater,container,false);

        FavoritesListItem item = new FavoritesListItem("Carrot",
                R.drawable.carrot,
                54,
                R.drawable.arrow_up_right,
                43,1,1,1,1,1,1);

        data.add(item);
        data.add(item);
        data.add(item);
        data.add(item);
        data.add(item);
        data.add(item);
        data.add(item);
        data.add(item);
        data.add(item);
        data.add(item);
        data.add(item);
        data.add(item);
        data.add(item);
        data.add(item);

        binding.favoritesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.favoritesRecyclerView.setAdapter(new FavoritesRecyclerViewAdapter(getContext(),data));

        return binding.getRoot();
    }
}