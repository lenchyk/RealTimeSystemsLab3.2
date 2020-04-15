package com.example.perceptron;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    // learning rate options
    String[] learningRate = {"0.001", "0.01", "0.05", "0.1", "0.2", "0.3"};
    float lRate; //for selected one
    // deadline iteration options
    String[] deadline = {"100", "200", "500", "1000"};
    int maxIterations; // selected deadline


    private TextView result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // adapter for the first spinner (learning rate)
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, learningRate);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        // selecting an element
        spinner.setSelection(0);
        // selecting handler
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // показываем позиция нажатого элемента
                Toast.makeText(getBaseContext(), "Position = " + position, Toast.LENGTH_SHORT).show();
                lRate = Float.parseFloat(learningRate[position]);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        // adapter for the second spinner (deadline)
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, deadline);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setAdapter(adapter1);
        // selecting an element with position
        spinner1.setSelection(0);
        // selecting handler
        spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // показываем позиция нажатого элемента
                Toast.makeText(getBaseContext(), "Position = " + position, Toast.LENGTH_SHORT).show();
                maxIterations = Integer.parseInt(deadline[position]);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        Button button = (Button) findViewById(R.id.button);
        result = (TextView) findViewById(R.id.result);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                perceptron();
            }
        });

    }

    // main function
    void perceptron(){
        int epochs = 0;
        int threshold = 4;
        float delta = 0;
        // checking the condition for each point
        boolean[] flags = {false, false, false, false};
        // points
        double inputs[][] = {{0, 6}, {1, 5}, {3, 3}, {2, 4}}; //points A, B, C, D
        // weights
        double[] weights = new double[inputs[0].length];
        // resulted y
        double[] y = new double[inputs.length];

        long startTime = System.nanoTime();
        while (epochs < maxIterations){

            for (int j = 0; j < y.length; j++) {
                y[j] += product(weights, inputs[j]);
                if (y[j] > threshold) flags[j] = true;
                else{
                    flags[j] = false;
                    weights[0] += (threshold - y[j]) * inputs[j][0] * lRate;
                    weights[1] += (threshold - y[j]) * inputs[j][1] * lRate;
                }
            }
            epochs++;

        }
        long endTime = System.nanoTime();
        long time = endTime - startTime;
        String finalResult = (String) getString(R.string.result, weights[0], weights[1], time, epochs);
        result.setText(finalResult);
    }

    public double product(double a[], double b[]) {
        double product = 0;
        for (int i = 0; i < a.length; i++) {
            product += a[i] * b[i];
        }
        return product;
    }
}
