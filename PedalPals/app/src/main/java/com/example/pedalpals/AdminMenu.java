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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class AdminMenu extends AppCompatActivity {
    private DrawerLayout drawer;

    Database db;
    TextView nav_head_name, nav_head_email;

    Button user, cycle, transaction, location, query;
    TextView welcome;
    private String username = "";
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        db = new Database(this);

        user = findViewById(R.id.user_admin_button);
        cycle = findViewById(R.id.cycle_admin_button);
        transaction = findViewById(R.id.transaction_admin_button);
        location = findViewById(R.id.location_admin_button);
        welcome = findViewById(R.id.text_welcome);
        query = findViewById(R.id.contact_query_button);

        prefs = this.getSharedPreferences("PedalPals", 0);
        username = prefs.getString("username", "UNKNOWN");

        new Thread(() -> {
            Cursor res = db.getData_Admin_username(username);
            StringBuffer nav_head = new StringBuffer();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            while (res.moveToNext()) {
                nav_head.append(res.getString(1)).append(" ").append(res.getString(2)).append(";");
                nav_head.append(res.getString(3));
            }

            String[] str_nav_head = nav_head.toString().split(";");

            runOnUiThread(() -> {
                welcome.setText("Welcome " + username);
                Toast.makeText(AdminMenu.this, "Admin details loaded", Toast.LENGTH_SHORT).show();
            });
        }).start();

        user.setOnClickListener(v -> {
            Toast.makeText(AdminMenu.this, "User Management clicked", Toast.LENGTH_SHORT).show();
        });

        cycle.setOnClickListener(v -> {
            Intent i = new Intent(AdminMenu.this, ViewCycleAdmin.class);
            startActivity(i);
        });

        transaction.setOnClickListener(v -> {
            Toast.makeText(AdminMenu.this, "Transactions clicked", Toast.LENGTH_SHORT).show();
        });

        location.setOnClickListener(v -> {
            Toast.makeText(AdminMenu.this, "Location clicked", Toast.LENGTH_SHORT).show();
        });

        query.setOnClickListener(v -> {
            Toast.makeText(AdminMenu.this, "Queries clicked", Toast.LENGTH_SHORT).show();
        });
    }
}
