package com.example.android.neolocmind1;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.android.neolocmind1.utils.SqlWrapper;
import com.google.android.gms.location.places.ui.PlacePicker;

public class AddItemActivity2 extends AppCompatActivity  {
    private Toolbar toolbar;
    private int PLACE_PICKER_REQUEST = 1;
    private FloatingActionButton fab;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item2);
        bundle = getIntent().getExtras();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Test1");
        fab = (FloatingActionButton) findViewById(R.id.save_reminder);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveClick();
            }
        });
    }
    public void onSaveClick(){
        String title, description, latitude, longitude, mode, imagePath;
        long insertResponse = 0 ;
        title = ((EditText) findViewById(R.id.titleField)).getText().toString().trim();
        if(title.length() < 1) {
            ((EditText) findViewById(R.id.titleField)).requestFocus();
            Toast.makeText(getApplicationContext(), "Please enter title", Toast.LENGTH_SHORT)
                    .show();
            return;
        }
        description = ((EditText) findViewById(R.id.descriptionField)).getText().toString();
        mode = ((Switch) findViewById(R.id.modeSwitch)).isChecked() ? "true" : "false";
        latitude = bundle.getString("latitude");
        longitude = bundle.getString("longitude");
        imagePath = "";
        Log.i("NEOLOCMINDAPP LAT", latitude);
        Log.i("NEOLOCMINDAPP LONG", longitude);
        Log.i("NEOLOCMINDAPP mode", mode);
        Log.i("NEOLOCMINDAPP Desc", description);
        Log.i("NEOLOCMINDAPP title", title);
        insertResponse = SqlWrapper.insertReminder(title,description,latitude,longitude,mode,imagePath);
        if(insertResponse == -1){
            Toast.makeText(getApplicationContext(), "Something went wrong! Please try again", Toast.LENGTH_SHORT)
                    .show();
        }else{
            Toast.makeText(getApplicationContext(), "Successfully added reminder", Toast.LENGTH_SHORT)
                    .show();
            SqlWrapper.getAllReminders();
            SqlWrapper.cursorChanged = true;
            finish();
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.additem_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.action_add_location){
            PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
            try {
                startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
            }catch(Exception e) {

            }
        }else if(item.getItemId() == R.id.action_add_image){

        }
        return true;
    }
}
