package com.example.quocduy;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.quocduy.tab_layout.ChatFragment;
import com.example.quocduy.tab_layout.HomeFragment;
import com.example.quocduy.tab_layout.ShopFragment;

public class TablayoutAdapter extends FragmentStateAdapter{
    public TablayoutAdapter(@NonNull FragmentActivity fragmentActivity){
        super(fragmentActivity);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position){
        switch (position){
            case 0:
                return new HomeFragment();
            case 1:
                return new ShopFragment();
            case 2:
                return new ChatFragment();
            default:
                return new HomeFragment();
        }
    }
    @Override
    public int getItemCount(){
        return 3;
    }
}
