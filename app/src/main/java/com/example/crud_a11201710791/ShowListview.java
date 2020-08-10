package com.example.crud_a11201710791;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowListview extends AppCompatActivity {
    DatabaseHelper dataHelp;
    public static ListView listView;
    public static EditText editText;
    Button addButton;

    ArrayAdapter adapter;
    ArrayList<String> drugListview; // listviewku

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_listview);

        listView = findViewById(R.id.listDrug);
        editText = findViewById(R.id.inputDrug);
        addButton= findViewById(R.id.addDrug);
        dataHelp = new DatabaseHelper(this);
        drugListview = new ArrayList<>();
        showDrug(); // Menampilkan list obat
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final String noId = drugListview.get(position); //Ambil data pada posisi baris di listview
                final String extractId = noId.substring(0, 2);
                editText.setText(extractId.toString());
                new AlertDialog.Builder(ShowListview.this)
                        .setTitle("Warning")
                        .setMessage("Are you sure want to delete this, doctor?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dataHelp.deleteData(Integer.parseInt(extractId)); // String -> Integer
                                drugListview.remove(position);
                                listView.invalidateViews();
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .show();
                return false;
            }
        });
        addButton.setOnClickListener(new View.OnClickListener() { // Aksi jika add button di klik
            @Override
            public void onClick(View v) {
                dataHelp.dataAdd(editText.getText().toString());
                Toast.makeText(ShowListview.this, "Data Recorded", Toast.LENGTH_SHORT).show();
                adapter.clear(); // Menghapus record, jika tidak ada maka record lama tidak akan terefresh
                showDrug(); // Refresh list obat saat terinput
            }
        });

    }

    private void showDrug() {
        Cursor cursor = dataHelp.showDrug();
        if(cursor.getCount()==0) {
            Toast.makeText(this, "Record from database is empty, please add some data", Toast.LENGTH_SHORT).show();
        }else {
            while(cursor.moveToNext()){
                drugListview.add(String.valueOf(cursor.getInt(0))+" "+cursor.getString(1));
            }
            adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, drugListview);
            listView.setAdapter(adapter);
        }
    }
}
