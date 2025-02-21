package com.example.vurimi_app.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.vurimi_app.R;

import java.util.Arrays;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<List<Integer>> imageList;

    public HomeViewModel() {
        imageList = new MutableLiveData<>();
        // Add your image resources here
        imageList.setValue(Arrays.asList(R.drawable.img_3, R.drawable.img_4, R.drawable.img_2));
    }

    public LiveData<List<Integer>> getImageList() {
        return imageList;
    }
}
