package com.example.crud_a11201710791;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DRUG_DATABASE = "db_drug"; // Database
    private static final String DRUG_TABLE = "drug_table"; // Tabel
    private static final String ID = "code";
    private static final String DRUG_NAME = "drug_name";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DRUG_DATABASE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) { // Buat Tabel
        String table_name = "create table " + DRUG_TABLE + "(" + ID + " integer primary key autoincrement, " + DRUG_NAME + " text)";
        db.execSQL(table_name); // Eksekusi tabel
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean dataAdd(String drugName){
        SQLiteDatabase db = this.getWritableDatabase(); // Aktifkan database
        ContentValues contentValues = new ContentValues(); // Wadah database
        contentValues.put(DRUG_NAME, drugName);

        long result = db.insert(DRUG_TABLE, null, contentValues); // result akan diisikan sebuah nilai angka
        return result != -1; // Pengembali nilai
    }

    public Cursor showDrug(){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "select * from " + DRUG_TABLE;
        Cursor cursor = db.rawQuery(sql, null); // Memposisikan record yg dibaca di database
        return cursor;
    }

    public boolean deleteData(int id){
        ShowListview.editText.setText(""+id);
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(DRUG_TABLE, ID+"="+id, null)>0;
    }
}
