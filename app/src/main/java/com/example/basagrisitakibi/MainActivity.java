package com.example.basagrisitakibi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    NavigationView navigationView;
    DrawerLayout drawer;

    EditText dateEditText, startEditText, stopEditText;
    Button addPainButton;
    TextView siddetTextView, tetikleyiciTextView, ozellikTextView, ilacTextView, notTextView;
    SeekBar seekBar;
    Toolbar mainToolbar;
    private veriTabani vt;
    private  raporlarVeriTabanı rvt;
    FirebaseAuth firebaseAuth;

    String tetikleyiciString = "";
    String ilacString = "";
    String semptomString = "";
    String notlarString = "";


    String[] listTetikleyici;
    boolean[] checkedTetikleyici;
    ArrayList<Integer> selectedTetikleyici = new ArrayList<>();

    String [] listOzellik;
    boolean[] checkedOzellik;
    ArrayList<Integer> selectedOzellik = new ArrayList<>();

    String[] listIlac;
    boolean[] checkedIlac;
    ArrayList<Integer> selectedIlac = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        vt = new veriTabani(this);
        rvt = new raporlarVeriTabanı(this);

        dateEditText = findViewById(R.id.dateEditText);
        startEditText = findViewById(R.id.startEditText);
        stopEditText = findViewById(R.id.stopEditText);
        siddetTextView = findViewById(R.id.siddetTextView);
        seekBar = findViewById(R.id.seekBar);
        tetikleyiciTextView = findViewById(R.id.tetikleyiciTextView);
        ozellikTextView = findViewById(R.id.ozellikTextView);
        ilacTextView = findViewById(R.id.ilacTextView);
        notTextView = findViewById(R.id.notTextView);
        addPainButton = findViewById(R.id.addPainButton);
        //selectedTextView = findViewById(R.id.selectedTextView);
        mainToolbar = findViewById(R.id.mainToolbar);

        navigationView = findViewById(R.id.navigationView);
        drawer = findViewById(R.id.drawer);

        firebaseAuth = FirebaseAuth.getInstance();
        toolbar();

        addPainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String start = dateEditText.getText().toString().trim() + " " + startEditText.getText().toString().trim();
                String stop = dateEditText.getText().toString().trim() + " " + stopEditText.getText().toString().trim();
                String siddet = siddetTextView.getText().toString().trim();

                String tarih = start + "\n" + stop + "\n" + siddet + "\n";


                if(TextUtils.isEmpty(startEditText.getText().toString())){
                    Snackbar.make(v,"Başlangıç Saati boş bıraktınız",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(stopEditText.getText().toString())){
                    Snackbar.make(v,"Bitiş Saati kısmını boş bıraktınız",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(dateEditText.getText().toString())){
                    Snackbar.make(v," Tarih kısmını boş bıraktınız",Snackbar.LENGTH_SHORT).show();
                    return;
                }

                new KayitlarDao().kayitEkle(vt,start,stop,siddet);


                if (!TextUtils.isEmpty(tetikleyiciString) || !TextUtils.isEmpty(semptomString) || !TextUtils.isEmpty(ilacString) || !TextUtils.isEmpty(notlarString) ){
                    new RaporlarDao().raporEkle(rvt,tarih,tetikleyiciString,semptomString,ilacString,notlarString);
                }
                    new RaporlarDao().raporEkle(rvt,tarih,tetikleyiciString,semptomString,ilacString,notlarString);

                Toast.makeText(getApplicationContext(),"Baş Ağrısı Kaydı Eklendi",Toast.LENGTH_SHORT).show();

                //startActivity(new Intent(MainActivity.this,RaporlarListesi.class));


            }
        });







        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                siddetTextView.setText("Ağrı Şiddeti : " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });




        listTetikleyici = getResources().getStringArray(R.array.tetikleyiciler);
        checkedTetikleyici = new boolean[listTetikleyici.length];

        listOzellik = getResources().getStringArray(R.array.ozellikler);
        checkedOzellik = new boolean[listOzellik.length];

        listIlac = getResources().getStringArray(R.array.ilaclar);
        checkedIlac = new boolean[listIlac.length];


        tetikleyiciTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder tetikleyiciBuiler = new AlertDialog.Builder(MainActivity.this);
                tetikleyiciBuiler.setTitle("Tetikleyiciler");
                tetikleyiciBuiler.setMultiChoiceItems(listTetikleyici, checkedTetikleyici, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position, boolean isChecked) {
                        if (isChecked) {
                            if (!selectedTetikleyici.contains(position)) {
                                selectedTetikleyici.add(position);
                            }
                        } else if (selectedTetikleyici.contains(position)) {
                            selectedTetikleyici.remove(position);
                        }
                    }
                });

                tetikleyiciBuiler.setCancelable(false);
                tetikleyiciBuiler.setPositiveButton("Onayla", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        for (int i = 0; i < selectedTetikleyici.size(); i++) {
                            tetikleyiciString += listTetikleyici[selectedTetikleyici.get(i)];
                            tetikleyiciString+="\n";
                        }
                        //selectedTextView.setText(tetikleyiciString);
                    }
                });

                tetikleyiciBuiler.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                tetikleyiciBuiler.setNeutralButton("Temizle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i = 0; i < checkedTetikleyici.length; i++) {
                            checkedTetikleyici[i] = false;
                            selectedTetikleyici.clear();

                        }
                    }
                });

                AlertDialog tetikleyiciDialog = tetikleyiciBuiler.create();
                tetikleyiciDialog.show();
            }
        });


        ozellikTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ozellikBuilder = new AlertDialog.Builder(MainActivity.this);
                ozellikBuilder.setTitle("Ağrı Özellikleri");
                ozellikBuilder.setMultiChoiceItems(listOzellik, checkedOzellik, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position, boolean isChecked) {
                        if (isChecked) {
                            if (!selectedOzellik.contains(position)) {
                                selectedOzellik.add(position);
                            }
                        } else if (selectedOzellik.contains(position)) {
                            selectedOzellik.remove(position);
                        }
                    }
                });

                ozellikBuilder.setCancelable(false);
                ozellikBuilder.setPositiveButton("Onayla", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        for (int i = 0; i < selectedOzellik.size(); i++) {
                            semptomString += listOzellik[selectedOzellik.get(i)];
                            semptomString += "\n";
                        }

                    }
                });

                ozellikBuilder.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                ozellikBuilder.setNeutralButton("Temizle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i = 0; i < checkedOzellik.length; i++) {
                            checkedOzellik[i] = false;
                            selectedOzellik.clear();

                        }
                    }
                });

                AlertDialog ozellikDialog = ozellikBuilder.create();
                ozellikDialog.show();
            }
        });


        ilacTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ilacBuilder = new AlertDialog.Builder(MainActivity.this);
                ilacBuilder.setTitle("Faydalı Olan İlaçlar");
                ilacBuilder.setMultiChoiceItems(listIlac, checkedIlac, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position, boolean isChecked) {
                        if (isChecked) {
                            if (!selectedIlac.contains(position)) {
                                selectedIlac.add(position);
                            }
                        } else if (selectedIlac.contains(position)) {
                            selectedIlac.remove(position);
                        }
                    }
                });

                ilacBuilder.setCancelable(false);
                ilacBuilder.setPositiveButton("Onayla", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        for (int i = 0; i < selectedIlac.size(); i++) {
                            ilacString += listIlac[selectedIlac.get(i)];
                            if (i != selectedIlac.size() - 1) {
                                ilacString += "\n";
                            }
                        }

                    }
                });

                ilacBuilder.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                ilacBuilder.setNeutralButton("Temizle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i = 0; i < checkedIlac.length; i++) {
                            checkedIlac[i] = false;
                            selectedIlac.clear();

                        }
                    }
                });

                AlertDialog ilacDialog = ilacBuilder.create();
                ilacDialog.show();
            }
        });


        notTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder notBuilder = new AlertDialog.Builder(MainActivity.this);
                notBuilder.setTitle("Notlarınızı Giriniz");
                View mView = getLayoutInflater().inflate(R.layout.notlar_dialog, null);
                final EditText notlarEditText = mView.findViewById(R.id.notlarEditText);
                notBuilder.setCancelable(false);
                notBuilder.setPositiveButton("Kaydet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       notlarString = notlarEditText.getText().toString();
                    }
                });

                notBuilder.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                notBuilder.setView(mView);
                AlertDialog notDialog = notBuilder.create();
                notDialog.show();
            }
        });


        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendarDate = Calendar.getInstance();
                int year = calendarDate.get(Calendar.YEAR);
                int month = calendarDate.get(Calendar.MONTH);
                final int day = calendarDate.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog;
                datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month += 1;
                        String newMonth = dateFormat(month);
                        String newDay = dateFormat(dayOfMonth);
                        dateEditText.setText(newDay + "/" + newMonth + "/" + year);
                    }
                }, year, month, day);

                datePickerDialog.setTitle("Tarih Seçiniz");
                datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Kaydet", datePickerDialog);
                datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "İptal", datePickerDialog);
                datePickerDialog.show();
            }
        });

        startEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog;

                timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        String newHour = dateFormat(hourOfDay);
                        String newMinute = dateFormat(minute);

                        startEditText.setText(newHour + " : " + newMinute);
                    }
                }, hour, minute, true);

                timePickerDialog.setTitle("Başlangıç Saati");
                timePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Kaydet", timePickerDialog);
                timePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "İptal", timePickerDialog);
                timePickerDialog.show();
            }
        });

        stopEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar2 = Calendar.getInstance();
                int hour2 = calendar2.get(Calendar.HOUR_OF_DAY);
                int minute2 = calendar2.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog2;
                timePickerDialog2 = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        String newHour = dateFormat(hourOfDay);
                        String newMinute = dateFormat(minute);
                        stopEditText.setText(newHour + " : " + newMinute);
                    }
                }, hour2, minute2, true);

                timePickerDialog2.setTitle("Bitiş Saati");
                timePickerDialog2.setButton(DialogInterface.BUTTON_POSITIVE, "Kaydet", timePickerDialog2);
                timePickerDialog2.setButton(DialogInterface.BUTTON_NEGATIVE, "İptal", timePickerDialog2);
                timePickerDialog2.show();


            }


        });






    }
    public void toolbar (){
        mainToolbar.setTitle("        Baş Ağrısı Takibi");
        //mainToolbar.setLogo(R.drawable.ic_menu_black_24dp);
        setSupportActionBar(mainToolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,mainToolbar,0,0);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        View baslik = navigationView.inflateHeaderView(R.layout.navigation_baslik);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public String dateFormat(int value){
        if(value>=10){
            return String.valueOf(value);
        }
        return "0" + String.valueOf(value);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if(menuItem.getItemId() == R.id.nav_kayitlar){
            Intent n = new Intent(MainActivity.this,KayitlarListesi.class);
            startActivity(n);
        }

        if(menuItem.getItemId() == R.id.nav_raporlar){
            Intent n = new Intent(MainActivity.this,RaporlarListesi.class);
            startActivity(n);
        }

        if(menuItem.getItemId() == R.id.nav_cikisyap){
            firebaseAuth.signOut();
            Intent n = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(n);
            finish();
        }

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}
