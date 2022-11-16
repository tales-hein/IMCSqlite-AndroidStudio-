package com.example.imcsqlite;

//import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class BD {



    public SQLiteDatabase bd;

    public BD(Context ctx){

        CriarBD(ctx);
    }

    private void CriarBD(Context ctx) {

        String strSql;

        strSql = "CREATE TABLE IF NOT EXISTS tblImc (" +
                "  data_calculo varchar(20)" +
                ", peso VARCHAR(11)" +
                ", altura VARCHAR(11)" +
                ", imc VARCHAR(11)" +
                ")";

        bd = ctx.openOrCreateDatabase("bdImc", Context.MODE_PRIVATE, null);
        this.bd.execSQL(strSql);
    }

    public String totalLinhas(){
        Cursor meuCursor;
        String strSql;
        int total = -1;

        strSql = "SELECT COUNT(*) as linhas FROM tblImc";
        meuCursor = this.bd.rawQuery(strSql, null);

        try {
            meuCursor.moveToFirst();
            total = meuCursor.getInt(0);
        }catch (Exception e) {
            e.printStackTrace();
        }

        return Integer.toString(total);

    }

    public void inserir(IMC imc){
        String strSql;
        SQLiteStatement stmt;

        strSql = "INSERT INTO tblImc (data_calculo, peso, altura, imc) VALUES (?, ?, ?, ?)";
        stmt = this.bd.compileStatement(strSql);

        stmt.bindString(1, imc.getDataCalculo());
        stmt.bindString(2, String.valueOf(imc.getPeso()));
        stmt.bindString(3, String.valueOf(imc.getAltura()));
        stmt.bindString(4, String.format("%.2f", imc.getImc()));
        stmt.executeInsert();
    }

    public void PopulaArrayCalculos(ArrayList<String> linhas){
        Cursor meuCursor;
        String strSql;

        strSql = "SELECT data_calculo, peso, altura, imc FROM tblImc";
        meuCursor = this.bd.rawQuery(strSql, null);

        meuCursor.moveToFirst();

        try {
            while (meuCursor != null) {
                linhas.add("Data: "+meuCursor.getString(0) + " | " + "Peso: "+meuCursor.getString(1) + " | " + "Altura: "+meuCursor.getString(2)+ " | " + "IMC: "+meuCursor.getString(3));
                meuCursor.moveToNext();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
