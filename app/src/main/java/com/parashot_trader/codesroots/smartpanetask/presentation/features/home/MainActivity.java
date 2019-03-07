package com.parashot_trader.codesroots.smartpanetask.presentation.features.home;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.parashot_trader.codesroots.smartpanetask.R;
import com.parashot_trader.codesroots.smartpanetask.presentation.features.home.tabsfragments.countries.CountriesFragment;
import com.parashot_trader.codesroots.smartpanetask.presentation.features.home.tabsfragments.map.MapFragment;
import com.parashot_trader.codesroots.smartpanetask.presentation.features.home.tabsfragments.phonecontacts.PhoneContactsFragment;
import com.parashot_trader.codesroots.smartpanetask.presentation.features.login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    private ViewPager mViewPager;
    private SectionsPagerAdapter mSectionsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = findViewById(R.id.tabs);
        mViewPager = findViewById(R.id.container);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setTabTextColors(getResources().getColor(R.color.blue_grey_400), getResources().getColor(R.color.colorPrimary));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
                startActivity (new Intent(this, LoginActivity.class));
                finish();
                break;
        }
        return true;
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0:
                    return new MapFragment();
                case 1:
                    return new CountriesFragment();
                case 2:
                    return new PhoneContactsFragment();

            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.maps);
                case 1:
                    return getString(R.string.countries);
                case 2:
                    return getString(R.string.contacts);
            }
            return null;
        }


    }


}
