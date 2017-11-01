package com.example.android.neolocmind1;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.android.neolocmind1.utils.SqlWrapper;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int[] tabIcons = {
            R.drawable.ic_action_home,
            R.drawable.ic_action_place
    };
    private int PLACE_PICKER_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle("Test1");
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                    if(position == 0){
                        getSupportActionBar().setTitle("Home");
                    }else{
                        getSupportActionBar().setTitle("Track");
                    }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
        SqlWrapper.initDB(getApplicationContext());
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.addItem){
            PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
            try {
                startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
            }catch(Exception e) {

            }
        }else if(item.getItemId() == R.id.settingsItem){

        }
        return true;
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);
                String toastMsg = String.format("Place: %s", place.getName());
                Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();
                LatLng latLng = place.getLatLng();
                Bundle bundle = new Bundle();
                bundle.putString("latitude", Double.toString(latLng.latitude));
                bundle.putString("longitude", Double.toString(latLng.longitude));
                Intent intent = new Intent(this,AddItemActivity2.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }
    }
    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new OneFragmentOne(), "HOME");
        adapter.addFrag(new TwoFragment(), "TRACK");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            Log.d("neo","calling getitem");
            Log.d("neo",mFragmentTitleList.get(position));
            //getSupportActionBar().setTitle(mFragmentTitleList.get(position));
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }
    }
}
