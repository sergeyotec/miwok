package com.example.android.miwok;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class CategoryAdapter extends FragmentPagerAdapter {

    //добавляем контекст для того чтобы для задания названий вкладок
    //использовать строковые ресурсы
    private Context mContext;

    //менеджер фрагментов, который будет сохранять состояние каждого фрагмента в адаптере через прокрутку
    //еще добавляем контекст чтобы в названии вкладок использов. строковые ресурсы
    public CategoryAdapter(Context context, FragmentManager fragmentManager){
        super(fragmentManager);
        mContext = context;
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

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.category_numbers);
        } else if (position == 1) {
            return mContext.getString(R.string.category_family);
        } else if (position == 2) {
            return mContext.getString(R.string.category_colors);
        } else {
            return mContext.getString(R.string.category_phrases);
        }
    }
}
