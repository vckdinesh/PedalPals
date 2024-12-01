package com.example.pedalpals;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class RegisterCycle extends AppCompatActivity {
    private DrawerLayout drawer;

    Database db;

    EditText reg_no, model, color, price, condition;
    Spinner location;
    Button register;

    TextView nav_head_name, nav_head_email;

    String location_name;
    String username;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_cycle);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        db = new Database(this);
        reg_no = findViewById(R.id.reg_no);
        model = findViewById(R.id.model);
        color = findViewById(R.id.color);
        location = findViewById(R.id.location);
        price = findViewById(R.id.price);
        condition = findViewById(R.id.condition);
        register = findViewById(R.id.register_button);
        prefs = this.getSharedPreferences("PedalPals", 0);
        username = prefs.getString("username", "");

        Cursor res1 = db.getData_User_username(username);
        StringBuffer nav_head = new StringBuffer();
        while (res1.moveToNext()) {
            nav_head.append(res1.getString(1) + " " + res1.getString(2) + ";");
            nav_head.append(res1.getString(3));
        }
        String[] str_nav_head = nav_head.toString().split(";");
        nav_head_name.setText(str_nav_head[0]);
        nav_head_email.setText(str_nav_head[1]);


        Cursor res = db.getAllData_Location();
        String[] items;
        String[] defaultLocations = {
                "Canteen",
                "Back Entrance",
                "Main Entrance",
                "Girls Hostel",
                "Boys Hostel",
                "Library"
        };
        StringBuffer bf = new StringBuffer();
        bf.append("Select Location;");
        location_name = "Select Location";
        for (String loc : defaultLocations) {
            bf.append(loc + ";");
        }
        if (res.getCount() > 0) {
            while (res.moveToNext()) {
                bf.append(res.getString(0) + ";");
            }
            bf.deleteCharAt(bf.length() - 1);
        }
        items = bf.toString().split(";");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(RegisterCycle.this,
                android.R.layout.simple_spinner_item, items);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        location.setAdapter(adapter);
        location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                Object item = parent.getItemAtPosition(position);
                location_name = String.valueOf(item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


    }
}





