package com.example.currency_converter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class currency_selection extends AppCompatActivity {

    ArrayList<Country> countryArrayList = new ArrayList<>();
    ArrayList<Country> targetArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("TAG", "onCreate22: ");

        setContentView(R.layout.activity_currency_selection);

        Intent intent = getIntent();

        countryArrayList =
                (ArrayList<Country>) intent.getSerializableExtra("CountryList");

        targetArrayList =
                (ArrayList<Country>) intent.getSerializableExtra("TargetList");

        ListView listView = (ListView) findViewById(R.id.selection_list);

        final CountryAdapter selectionCountryAdapter =
                new CountryAdapter(this, countryArrayList);

        listView.setAdapter(selectionCountryAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                targetArrayList.add(countryArrayList.get(position));
                countryArrayList.remove(position);

                selectionCountryAdapter.notifyDataSetChanged();
            }
        });

        Button button = findViewById(R.id.back_btn);
        button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent1 = new Intent();

                    intent1.putExtra("ListBack",countryArrayList);

                     intent1.putExtra("targetListChange", targetArrayList);

                    setResult(RESULT_OK, intent1);
                    finish();  // calls on Destroy
            }
        });
    }

}