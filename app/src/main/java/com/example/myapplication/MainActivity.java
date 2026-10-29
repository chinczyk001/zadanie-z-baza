package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ZwierzetaDataBase zwierzetaDataBase;
    private EditText editTextGatunek, editTextWiek;
    private Spinner spinnerSrodowisko;
    private CheckBox checkBoxRoslino;
    private Button button;
    private List<Zwierze> listZwierzat;
    private ArrayAdapter<Zwierze> adapter;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        button = findViewById(R.id.button);
        editTextGatunek = findViewById(R.id.editTextGatunek);
        editTextWiek = findViewById(R.id.editTextNumber);
        spinnerSrodowisko = findViewById(R.id.spinner);
        checkBoxRoslino = findViewById(R.id.checkBox);

        zwierzetaDataBase = Room.databaseBuilder(
                getApplicationContext(),
                ZwierzetaDataBase.class,
                "zwierzeta_db"
        )
                .allowMainThreadQueries()
                .build();
//        zwierzetaDataBase.zwrocZwierzeDao().wstawDoBazy(
//                new Zwierze("jeleń","lądowe",true,13)
//        );

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Zwierze zwierze = new Zwierze(
                        editTextGatunek.getText().toString(),
                        spinnerSrodowisko.getSelectedItem().toString(),
                        checkBoxRoslino.isChecked(),
                        Integer.parseInt(editTextWiek.getText().toString())
                );
                zwierzetaDataBase.zwrocZwierzeDao().wstawDoBazy(zwierze);
            }
        });
        listView = findViewById(R.id.listView);
        listZwierzat = zwierzetaDataBase.zwrocZwierzeDao().zwrocWszystkieZwierzeta();
        adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, listZwierzat);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                        zwierzetaDataBase.zwrocZwierzeDao().usunZBazy(listZwierzat.get(i));
                        listZwierzat.remove(i);
                        adapter.notifyDataSetChanged();
                        return false;
                    }
                }
        );
    }
}