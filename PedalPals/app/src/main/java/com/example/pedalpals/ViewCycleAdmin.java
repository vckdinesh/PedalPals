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
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TableRow.LayoutParams;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class ViewCycleAdmin extends AppCompatActivity{
    private DrawerLayout drawer;

    Database db;

    TextView nav_head_name, nav_head_email;

    TableLayout tableLayout;
    TableRow tableRow;
    boolean hasData = false;
    StringBuffer bf;
    String username;
    SharedPreferences prefs;

    TextView regno_tv, model_tv, color_tv, owner_tv,nodata;

    public void getData() {
        Cursor result = db.getAllData_Cycle();
        if (result.getCount() == 0) {
            hasData = false;
        }
        else {
            bf = new StringBuffer();
            while (result.moveToNext()) {
                bf.append(result.getString(0) + ";");
                bf.append(result.getString(1) + ";");
                bf.append(result.getString(2) + ";");
                bf.append(result.getString(3) + ";");
                bf.append(result.getString(4) + ";");
                bf.append(result.getString(5) + ";");
                bf.append(result.getString(6) + ";");
                bf.append(result.getString(7) + "\n");
            }
            bf.deleteCharAt(bf.length()-1);
            hasData = true;
        }
    }

    public void addHeaders() {
        tableRow = new TableRow(this);
        tableRow.setLayoutParams(new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT));


        TextView header = new TextView(this);
        header.setText("Reg. No.");
        header.setTextColor(Color.WHITE);
        header.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark,null));
        header.setTextSize(15);
        header.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        header.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
        header.setPadding(15, 25, 15, 25);
        header.setTypeface(Typeface.SERIF, Typeface.BOLD);
        tableRow.addView(header);

        TextView header2 = new TextView(this);
        header2.setText("Model");
        header2.setTextColor(Color.WHITE);
        header2.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark,null));
        header2.setTextSize(15);
        header2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        header2.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        header2.setPadding(15, 25, 15, 25);
        header2.setTypeface(Typeface.SERIF, Typeface.BOLD);
        tableRow.addView(header2);

        TextView header3 = new TextView(this);
        header3.setText("Colour");
        header3.setTextColor(Color.WHITE);
        header3.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark,null));
        header3.setTextSize(15);
        header3.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        header3.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        header3.setPadding(15, 25, 15, 25);
        header3.setTypeface(Typeface.SERIF, Typeface.BOLD);
        tableRow.addView(header3);

        TextView header4 = new TextView(this);
        header4.setText("Owner Username");
        header4.setTextColor(Color.WHITE);
        header4.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark,null));
        header4.setTextSize(15);
        header4.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        header4.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        header4.setPadding(15, 25, 15, 25);
        header4.setTypeface(Typeface.SERIF, Typeface.BOLD);
        tableRow.addView(header4);

        tableLayout.addView(tableRow, new TableLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT));
    }


    private void showMessage(String title, String message, final String reg_no, final String usrname) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);



        builder.setNeutralButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert=builder.create();
        alert.show();
    }

    public class AdminMenu extends AppCompatActivity {
        private HashMap<String, String> cycleMap; // HashMap to store cycle data
        TextView cycleDetails;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_admin_menu);

            // Initialize the HashMap
            cycleMap = new HashMap<>();

        }
        private void fetchCycleDetails(String cycleId) {
            // Fetch cycle details from HashMap
            if (cycleMap.containsKey(cycleId)) {
                String details = cycleMap.get(cycleId);
                cycleDetails.setText(details);
                Toast.makeText(this, "Cycle details fetched successfully!", Toast.LENGTH_SHORT).show();
            } else {
                cycleDetails.setText("Cycle not found!");
                Toast.makeText(this, "No cycle found with ID: " + cycleId, Toast.LENGTH_SHORT).show();
            }
        }
    }

}

