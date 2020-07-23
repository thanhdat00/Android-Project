package com.example.currency_converter;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText expressionText;
    TextView resultText;
    String result;
    Button addBtn;
    int screenState;
    int[] idButtons = {
            R.id.num0, R.id.num1, R.id.num2, R.id.num3, R.id.num4,
            R.id.num5, R.id.num6, R.id.num7, R.id.num8, R.id.num9,
            R.id.plus, R.id.sub, R.id.divine, R.id.mullti, R.id.Dot, R.id.delete, R.id.equal
    };

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("expression", expressionText.getText().toString());
        TextView textView = (TextView) findViewById(R.id.targetCurrency);
        outState.putString("target", textView.getText().toString());
    }

//    @Override
//    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//
//        expressionText.setText(savedInstanceState.getString("expression").toString());
//
//    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {

        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            setContentView(R.layout.activity_main);
            Log.d("TAG1", "portrait");
        }
        else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            setContentView(R.layout.landscape_layout);
            Log.d("TAG1", "Landscape");
        }
        inputExpression();
        super.onConfigurationChanged(newConfig);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null)
        {
            Log.d("TAG", savedInstanceState.getString("expression"));
            expressionText.setText(savedInstanceState.getString("expression"));
            TextView editText = (TextView) findViewById(R.id.targetCurrency);
            editText.setText(savedInstanceState.getString("target"));
           // Log.d("TAG", savedInstanceState.getString("target"));
        }
        Configuration config = getResources().getConfiguration();

        onConfigurationChanged(config);
        //setContentView(R.layout.activity_main);
            //setList();
    }

    // Do setOnClickListener for all buttons
    private void inputExpression() {
        expressionText = (EditText) findViewById(R.id.expression);
        resultText = (TextView) findViewById(R.id.baseCurrency);
        addBtn = (Button) findViewById(R.id.add_btn);
        for (int i = 0; i < idButtons.length; i++) {
            findViewById(idButtons[i]).setOnClickListener(this);
        }
    }

    private void setList() {
        final ListView listView = (ListView) findViewById(R.id.CountryList);

        ArrayList<Country> countryArrayList = new ArrayList<>();

        createList(countryArrayList);

        CountryAdapter countryAdapter = new CountryAdapter(this, countryArrayList);
        listView.setAdapter(countryAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                TextView textView = (TextView) view.findViewById(R.id.country_name);
                TextView textView1 = (TextView) view.findViewById(R.id.currency_name);
                ImageView imageView = (ImageView) view.findViewById(R.id.country_flag);


                ImageView countryFlag = (ImageView) findViewById(R.id.targetFlag);
                countryFlag.setBackground(imageView.getDrawable());

                TextView targetCountry = (TextView) findViewById(R.id.CountryName);
                targetCountry.setText(textView.getText());

                TextView targetCurrency = (TextView) findViewById(R.id.targetCurrency);
                targetCurrency.setText(textView1.getText());
                TextView targetCurrency2 = (TextView) findViewById(R.id.targetCurrencyBottom);
                targetCurrency2.setText(textView1.getText());

                evaluate();
            }
        });
    }

    private void createList(ArrayList<Country> countryArrayList) {
        Country US = new Country("USA", "us", "USA Dollar",false);
        final Country japan = new Country("Japan", "japan", "Yen",false);
        Country EU = new Country("EU", "euu", "Euro",false);
        Country Russia = new Country("Russia", "ru", "Rouble",false);
        Country US1 = new Country("USA", "us", "USA Dollar",false);
        Country US2 = new Country("USA", "us", "USA Dollar",false);
        Country US3 = new Country("USA", "us", "USA Dollar",false);
        countryArrayList.add(US);
        countryArrayList.add(japan);
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
            if (id == idButtons[i] && id != R.id.equal) {
                String text = ((Button) findViewById(id)).getText().toString();
                expressionText.append(text);
                return;
            }
            if (id == R.id.equal) {
                evaluate();
            }
        }
    }

    public void evaluate() {

        String s = expressionText.getText().toString();
        CurrencyConverter helper = new CurrencyConverter(s);
        result = helper.calculate(s);
        if (result.equals("BAD EXPRESSION"))
        {
            expressionText.setText("BAD EXPRESSION");
            return;
        }
        resultText.setText(result);
        TextView convertCountry = (TextView) findViewById(R.id.CountryName);
        s = convertCountry.getText().toString();
        TextView targetCurrency = (TextView) findViewById(R.id.targetCurrency);

        double convertResult;
        switch (s) {
            case "USA" :
                convertResult = Double.parseDouble(result) * 0.000043;
                targetCurrency.setText(String.valueOf(convertResult));
                break;
            case "Japan" :
                convertResult = Double.parseDouble(result) * 0.0046;
                targetCurrency.setText(String.valueOf(convertResult));
                break;
            case "EU" :
                convertResult = Double.parseDouble(result) * 0.000038;
                targetCurrency.setText(String.valueOf(convertResult));
                break;
            case "Russia" :
                convertResult = Double.parseDouble(result) * 0.0031;
                targetCurrency.setText(String.valueOf(convertResult));
                break;
        }
    }

}