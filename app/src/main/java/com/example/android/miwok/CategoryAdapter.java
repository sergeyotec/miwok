package com.example.android.miwok;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class CategoryAdapter extends FragmentPagerAdapter {

    //менеджер фрагментов, который будет сохранять состояние каждого фрагмента в адаптере через прокрутку
    public CategoryAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }
    //возвращает фрагмент который должен отображаться для данного номера страницы
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new NumbersFragment();
        } else if (position == 1){
            return new FamilyFragment();
        } else if (position == 2){
            return new ColorsFragment();
        }else
            return new PhrasesFragment();
    }
    //возвращаем общее число страниц
    @Override
    public int getCount() {
        return 4;
    }
}
