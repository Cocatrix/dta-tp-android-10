package fr.codevallee.formation.tp10app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Stack;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {
    private Stack<Float> stack;
    private Float currentNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        Log.d("STATE - ", "Begin");
        // Find 9 number buttons
        Button button_1 = (Button) findViewById(R.id.number_1);
        Button button_2 = (Button) findViewById(R.id.number_2);
        Button button_3 = (Button) findViewById(R.id.number_3);
        Button button_4 = (Button) findViewById(R.id.number_4);
        Button button_5 = (Button) findViewById(R.id.number_5);
        Button button_6 = (Button) findViewById(R.id.number_6);
        Button button_7 = (Button) findViewById(R.id.number_7);
        Button button_8 = (Button) findViewById(R.id.number_8);
        Button button_9 = (Button) findViewById(R.id.number_9);

        Button[] array_number_buttons = {button_1, button_2, button_3, button_4, button_5, button_6, button_7, button_8, button_9};
        // Put listening to the 9 number buttons
        for (Button button : array_number_buttons) {
            button.setOnClickListener(this);
        }
    }
    @Override
    public void onClick(View v) {
        String tag = (String)v.getTag();
        switch(tag) {
            case "number":
                //
            break;
        }
    }

    public void click_1_to_9(View view) {

    }

    public void click_0(View view) {
    }

    public void click_operator(View view) {
    }

    public void click_point(View view) {
    }

    public void click_del(View view) {
    }

    public void click_clear(View view) {
    }

    public void click_pop(View view) {
    }

    public void click_swap(View view) {
    }

    public void click_enter(View view) {
    }
}
