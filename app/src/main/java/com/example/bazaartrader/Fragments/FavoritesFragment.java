package com.example.bazaartrader.Fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bazaartrader.RecyclerView.Adapters.FavoritesRecyclerViewAdapter;
import com.example.bazaartrader.RecyclerView.ItemClickListener;
import com.example.bazaartrader.R;
import com.example.bazaartrader.databinding.FragmentFavoritesBinding;
import com.example.bazaartrader.Database.AppDatabase;
import com.example.bazaartrader.Database.FavoriteProduct;
import com.example.bazaartrader.Database.FavoriteProductDao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavoritesFragment extends Fragment implements ItemClickListener {

    private FragmentFavoritesBinding binding;
    private FavoritesRecyclerViewAdapter adapter;
    private List<FavoriteProduct> favoritesData = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentFavoritesBinding.inflate(inflater, container, false);

        // Load favorites from the database
        loadFavoritesFromDatabase();
        binding.favoritesNumItems.setText(String.valueOf(favoritesData.size()) + " items");
        binding.favoritesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new FavoritesRecyclerViewAdapter(getActivity());
        binding.favoritesRecyclerView.setAdapter(adapter);

        // Set item click listener on the adapter
        adapter.setItemClickListener(this);



        return binding.getRoot();
    }

    private void loadFavoritesFromDatabase() {
        FavoriteProductDao productDao = AppDatabase.getInstance(requireContext()).favoriteProductDao();

        Disposable disposable = Observable.fromCallable(() -> productDao.getAllProducts())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(favoritesData -> {
                    adapter.setData(favoritesData);
                    this.favoritesData = favoritesData;
                });


    }


    @Override
    public void onItemClick(int position) {
        // Get the selected product from the favorites list
        FavoriteProduct selectedProduct = favoritesData.get(position);

        // Create and pass the Bundle
        Bundle bundle = new Bundle();
        bundle.putSerializable("selectedCustomProduct", (Serializable) selectedProduct);

        Navigation.findNavController(binding.getRoot()).navigate(R.id.action_favoritesFragment_to_itemFragment, bundle);
    }
}
