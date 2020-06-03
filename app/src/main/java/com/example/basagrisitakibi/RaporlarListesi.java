package com.example.basagrisitakibi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class RaporlarListesi extends AppCompatActivity {
    ArrayList<Raporlar> raporlarArrayList;
    RaporlarAdaptor adaptor;
    raporlarVeriTabanı rvt;
    Toolbar raporToolbar;
    RecyclerView raporRv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raporlar_listesi);
        raporRv = findViewById(R.id.raporRv);
        rvt = new raporlarVeriTabanı(this);

        raporToolbar = findViewById(R.id.raporToolbar);
        raporToolbar.setTitle("        Rapor Kayıtları");
        setSupportActionBar(raporToolbar);

        raporRv.setHasFixedSize(true);
        raporRv.setLayoutManager(new LinearLayoutManager(this));

        raporlarArrayList = new RaporlarDao().tumRaporlar(rvt);

        adaptor = new RaporlarAdaptor(this,raporlarArrayList);
        raporRv.setAdapter(adaptor);
    }
}