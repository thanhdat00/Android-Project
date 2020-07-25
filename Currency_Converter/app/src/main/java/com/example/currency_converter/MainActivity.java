package com.example.currency_converter;

import android.content.Intent;
import android.content.pm.ConfigurationInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.nio.BufferUnderflowException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText expressionText;
    TextView resultText;
    TextView targetText;
    String result;
    Button addBtn;
    Intent intent;
    private static final int REQ_CODE = 0;
    ArrayList<Country> countryArrayList = new ArrayList<>();
    ArrayList<Country> targetArrayList = new ArrayList<>();
    int screenState;
    int[] idButtons = {
            R.id.num0, R.id.num1, R.id.num2, R.id.num3, R.id.num4,
            R.id.num5, R.id.num6, R.id.num7, R.id.num8, R.id.num9,
            R.id.plus, R.id.sub, R.id.divine, R.id.mullti, R.id.Dot, R.id.delete, R.id.equal,
            R.id.add_btn
    };

//    @Override
//    protected void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
//        Log.d("TAG", "onSaveInstanceState: ");
//        //outState.putString("expression", expressionText.getText().toString());
//        outState.putString("baseCurrency", resultText.getText().toString());
//        outState.putString("targetCurrency", targetText.getText().toString());
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TAG", "onCreate: ");

        setContentView(R.layout.activity_main);
        createObj();
//
//        if (savedInstanceState != null)
//        {
//            resultText.setText(savedInstanceState.getString("baseCurrency"));
//            targetText.setText(savedInstanceState.getString("targetCurrency"));
//        }

        createList();
        setList();
       inputExpression();
    }

    // Do setOnClickListener for all buttons
    private void inputExpression() {
        for (int i = 0; i < idButtons.length; i++) {
            findViewById(idButtons[i]).setOnClickListener(this);
        }
    }

    private void createObj() {
        //targetText = (TextView) findViewById(R.id.targetCurrency);
        expressionText = (EditText) findViewById(R.id.expression);
        resultText = (TextView) findViewById(R.id.baseCurrency);
        screenState =0;
    }


    private void setList() {
        final ListView listView = findViewById(R.id.CountryList);

        final CountryAdapter countryAdapter = new CountryAdapter(this,targetArrayList);

        listView.setAdapter(countryAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                countryAdapter.remove(targetArrayList.get(position));
                countryArrayList.add(targetArrayList.get(position));
                targetArrayList.remove(targetArrayList.get(position));

                countryAdapter.notifyDataSetChanged();
            }
        });
    }


    private void createList() {
        Country US = new Country("USA", "us", "USA Dollar",0,true);
        final Country japan = new Country("Japan", "japan", "Yen",0,true);
        Country EU = new Country("EU", "euu", "Euro",0,false);
        Country Russia = new Country("Russia", "ru", "Rouble",0,false);
        Country US1 = new Country("USA", "us", "USA Dollar",0,false);
        Country US2 = new Country("USA", "us", "USA Dollar",0,false);
        Country US3 = new Country("USA", "us", "USA Dollar",0,false);
        targetArrayList.add(US);
        targetArrayList.add(japan);
        countryArrayList.add(EU);
        countryArrayList.add(Russia);
        countryArrayList.add(US1);
        countryArrayList.add(US2);
        countryArrayList.add(US3);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        for (int i = 0; i < idButtons.length; i++)
        {
            if (id == R.id.delete) {
                expressionText.setText("");
                return;
            }
            if (id == R.id.add_btn)
            {
                intentExecute();
            }

            if (id == idButtons[i] && id != R.id.equal && id != R.id.add_btn) {
                String text = ((Button) findViewById(id)).getText().toString();
                expressionText.append(text);
                return;
            }
            if (id == R.id.equal) {
                //evaluate();
            }
        }
    }

    private void intentExecute() {
        intent = new Intent(this, currency_selection.class);

        intent.putExtra("CountryList",countryArrayList);

        intent.putExtra("TargetList", targetArrayList);

        startActivityForResult(intent, REQ_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK)
        {
            if (requestCode == REQ_CODE && data != null)
            {
                //countryArrayList = (ArrayList<Country>) data.getSerializableExtra("ListBack");
               // targetArrayList = (ArrayList<Country>) data.getSerializableExtra("targetListChange");
            }
        }
    }

//    public void evaluate() {
//
//        String s = expressionText.getText().toString();
//        CurrencyConverter helper = new CurrencyConverter(s);
//        result = helper.calculate(s);
//        if (result.equals("BAD EXPRESSION"))
//        {
//            expressionText.setText("BAD EXPRESSION");
//            return;
//        }
//        resultText.setText(result);
//        TextView convertCountry = (TextView) findViewById(R.id.CountryName);
//        s = convertCountry.getText().toString();
//        TextView targetCurrency = (TextView) findViewById(R.id.targetCurrency);
//
//        double convertResult;
//        switch (s) {
//            case "USA" :
//                convertResult = Double.parseDouble(result) * 0.000043;
//                targetCurrency.setText(String.valueOf(convertResult));
//                break;
//            case "Japan" :
//                convertResult = Double.parseDouble(result) * 0.0046;
//                targetCurrency.setText(String.valueOf(convertResult));
//                break;
//            case "EU" :
//                convertResult = Double.parseDouble(result) * 0.000038;
//                targetCurrency.setText(String.valueOf(convertResult));
//                break;
//            case "Russia" :
//                convertResult = Double.parseDouble(result) * 0.0031;
//                targetCurrency.setText(String.valueOf(convertResult));
//                break;
//        }
//    }

}