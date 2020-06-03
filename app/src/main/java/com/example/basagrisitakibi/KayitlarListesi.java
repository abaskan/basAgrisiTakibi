package com.example.basagrisitakibi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class KayitlarListesi extends AppCompatActivity {
    Toolbar kayitlarToolbar;
    RecyclerView rv;

    ArrayList<Kayitlar> kayitlarArrayList;
    KayitlarAdaptor adaptor;
    veriTabani vt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayitlar_listesi);

        kayitlarToolbar=findViewById(R.id.kayitlarToolbar);
        rv = findViewById(R.id.rv);

        vt = new veriTabani(this);

        kayitlarToolbar.setTitle("        Başağrısı Kayıtları");
        setSupportActionBar(kayitlarToolbar);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        kayitlarArrayList = new KayitlarDao().tumKayitlar(vt);

        adaptor = new KayitlarAdaptor(this,kayitlarArrayList);
        rv.setAdapter(adaptor);
    }
}
