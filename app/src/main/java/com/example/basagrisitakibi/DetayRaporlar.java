package com.example.basagrisitakibi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;


public class DetayRaporlar extends AppCompatActivity {


    TextView raporDetayTextView;
    Toolbar raporDetayToolbar;
    Raporlar rapor;
    private raporlarVeriTabanı rvt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detay_raporlar);

        rvt = new raporlarVeriTabanı(this);

        raporDetayTextView = findViewById(R.id.raporDetayEditText);

        raporDetayToolbar = findViewById(R.id.raporDetayToolbar);
        raporDetayToolbar.setTitle("        Rapor Detayları");
        setSupportActionBar(raporDetayToolbar);


        rapor = (Raporlar)getIntent().getSerializableExtra("rapor");
        String raporLoglari ="----------------------Başağrısı Logları---------------------\n\n";
        raporLoglari       +="**********************************************\n\n";
        raporLoglari+= "Kayıt -> " + rapor.getRaporId() + "\n";
        raporLoglari+= rapor.kayitTarihi + "\n\n";

        if (!TextUtils.isEmpty(rapor.getTetikleyiciListesi())){
            raporLoglari+= "--------------------------Tetikleyiciler------------------------\n";
            raporLoglari+= rapor.getTetikleyiciListesi() + "\n\n";
        }

        if (!TextUtils.isEmpty(rapor.getSemptomListesi())){
            raporLoglari+= "--------------------------Semptomlar------------------------\n";
            raporLoglari+= rapor.getSemptomListesi() + "\n\n";

        }

        if (!TextUtils.isEmpty(rapor.getIlacListesi())){
            raporLoglari+= "-------------------------------İlaçlar-----------------------------\n";
            raporLoglari+= rapor.getIlacListesi() + "\n\n\n";
        }

        if (!TextUtils.isEmpty(rapor.getNotlar())){
            raporLoglari+= "-------------------------------Notlar-----------------------------\n";
            raporLoglari+= rapor.getNotlar() + "\n\n\n";
        }

        raporLoglari+= "**********************************************\n\n";
        raporDetayTextView.setText(raporLoglari);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detay_rapor_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_sil:
                Snackbar.make(raporDetayToolbar,"Rapor Silinsin Mi ?",Snackbar.LENGTH_SHORT)
                        .setAction("Evet", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                new RaporlarDao().raporSil(rvt,rapor.getRaporId());
                                startActivity(new Intent(DetayRaporlar.this,RaporlarListesi.class));
                                finish();
                            }
                        }).show();
                return true;
            default:
                return false;
        }
    }
}
