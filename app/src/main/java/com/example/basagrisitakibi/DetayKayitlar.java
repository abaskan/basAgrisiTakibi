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
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class DetayKayitlar extends AppCompatActivity {

    Toolbar detayToolbar;
    EditText detayStartEditText, detayStopEditText, detaySiddetEditText;
    Kayitlar kayit;
    private veriTabani vt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detay_kayitlar);

        vt = new veriTabani(this);

        detayToolbar = findViewById(R.id.detayToolbar);
        detayToolbar.setTitle("        Kayıt Detayları");
        setSupportActionBar(detayToolbar);
        detayStartEditText = findViewById(R.id.detayStartEditText);
        detayStopEditText = findViewById(R.id.detayStopEditText);
        detaySiddetEditText = findViewById(R.id.detaySiddetEditText);

        kayit = (Kayitlar) getIntent().getSerializableExtra("nesne");
        detayStartEditText.setText(kayit.getStartTime());
        detayStopEditText.setText(kayit.getStopTime());
        detaySiddetEditText.setText(kayit.getAgriSiddeti());


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_guncelle:
                String start = detayStartEditText.getText().toString().trim();
                String stop = detayStopEditText.getText().toString().trim();
                String siddet = detaySiddetEditText.getText().toString().trim();

                if (TextUtils.isEmpty(start)) {
                    Snackbar.make(detayToolbar, "Tarih kısmını boş bıraktınız", Snackbar.LENGTH_SHORT).show();
                    return false;
                }
                if (TextUtils.isEmpty(stop)) {
                    Snackbar.make(detayToolbar, "Başlangıç Saati kısmını boş bıraktınız", Snackbar.LENGTH_SHORT).show();
                    return false;
                }
                if (TextUtils.isEmpty(siddet)) {
                    Snackbar.make(detayToolbar, "Bitiş Saati kısmını boş bıraktınız", Snackbar.LENGTH_SHORT).show();
                    return false;
                }

                new KayitlarDao().kayitDuzenle(vt, kayit.getId(), start, stop, siddet);


                startActivity(new Intent(DetayKayitlar.this, KayitlarListesi.class));
                finish();
                return true;
            case R.id.action_sil:
                Snackbar.make(detayToolbar, "Kayıt Silinsin Mi ?", Snackbar.LENGTH_SHORT)
                        .setAction("Evet", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                new KayitlarDao().kayitSil(vt, kayit.getId());
                                startActivity(new Intent(DetayKayitlar.this, KayitlarListesi.class));
                                finish();
                            }
                        }).show();
                return true;
            default:
                return false;
        }
    }
}
