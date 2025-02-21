package com.example.vurimi_app.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.example.vurimi_app.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private ViewPager2 viewPager;
    private ImageSliderAdapter adapter;
    private Button bookSlotButton;

    private Handler handler = new Handler(Looper.getMainLooper());
    private int currentPage = 0;
    private Runnable autoScrollRunnable = new Runnable() {
        @Override
        public void run() {
            if (viewPager != null) {
                if (currentPage == adapter.getItemCount()) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
                handler.postDelayed(this, 3000);  // 3000 ms = 3 seconds
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize ViewModel
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        // Initialize ViewPager2
        viewPager = root.findViewById(R.id.viewPager);

        // Get image resources from ViewModel
        homeViewModel.getImageList().observe(getViewLifecycleOwner(), imageList -> {
            adapter = new ImageSliderAdapter(imageList);
            viewPager.setAdapter(adapter);
        });

        // Start the auto-scrolling for image slider
        handler.postDelayed(autoScrollRunnable, 3000);  // Start after 3 seconds

        // Handle "Book a Slot" button click
        bookSlotButton = root.findViewById(R.id.btn_book_slot);
        bookSlotButton.setOnClickListener(view -> {
            // Action to open a new activity or show a dialog
            // For now, we are just showing a simple Toast
            // You can replace this with any other logic (like navigation or opening another fragment/activity)
            // Toast.makeText(getContext(), "Book a Slot clicked!", Toast.LENGTH_SHORT).show();

            // Example of navigating to a new activity (assuming you have an activity for booking)
            Intent intent = new Intent(getActivity(), BookingActivity.class);
            startActivity(intent);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Remove the auto-scrolling when the fragment is destroyed
        handler.removeCallbacks(autoScrollRunnable);
    }
}
