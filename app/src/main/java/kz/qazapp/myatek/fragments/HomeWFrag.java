package kz.qazapp.myatek.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kz.qazapp.myatek.R;
import kz.qazapp.myatek.fragments.homeworkpack.tab_fargment_dz;
import kz.qazapp.myatek.fragments.homeworkpack.zNote;

public class HomeWFrag extends Fragment {
    View view;
    public TabLayout tabLayout;
    public ViewPager viewPager;

    public HomeWFrag() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_hw_main, container, false);

        tabLayout = (TabLayout) view.findViewById(R.id.tablayout_id);
        viewPager = (ViewPager) view.findViewById(R.id.viewpage_id);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        adapter.addFragment(new tab_fargment_dz(), "Домашное задание");
        adapter.addFragment(new zNote(), "Заметки");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {

        private final List<android.support.v4.app.Fragment> fragmentlist = new ArrayList<>();
        private final List<String> FragmentStringTitles = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return fragmentlist.get(position);
        }

        @Override
        public int getCount() {
            return FragmentStringTitles.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return FragmentStringTitles.get(position);
        }

        public void addFragment(android.support.v4.app.Fragment fragment, String title){
            fragmentlist.add(fragment);
            FragmentStringTitles.add(title);
        }
    }
}
