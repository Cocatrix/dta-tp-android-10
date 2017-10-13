package fr.codevallee.formation.tp10app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Stack;

public class CalculatorActivity extends AppCompatActivity {
    private Stack<Float> stack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        Log.d("STATE - ", "Begin");
    }

    public void click_1_to_9(View view) {
        String number = (String) view.getTag();
        Log.d("STATE - ", "Clicked on " + number);
        TextView currentNumber = (TextView) findViewById(R.id.current_number);
        /* Converting to Integer and then String allows to remove unwanted zeros. Indeed
         * "0"+"3" = "03"
         * String(Integer("0"+"3") = "3"
         */
        Integer totalPrintedNumber = Integer.parseInt(currentNumber.getText().toString() + number);
        currentNumber.setText(totalPrintedNumber.toString());
        Log.d("STATE - ", number + " added to currentText");
    }

    public void click_0(View view) {
        String number = "0";
        Log.d("STATE - ", "Clicked on " + number);
        TextView currentNumber = (TextView) findViewById(R.id.current_number);
        /* Converting to Integer and then String allows to remove unwanted zeros. Indeed
         * "0"+"0" = "00"
         * String(Integer("0"+"0") = "0"
         */
        Integer totalPrintedNumber = Integer.parseInt(currentNumber.getText().toString() + number);
        currentNumber.setText(totalPrintedNumber.toString());
        Log.d("STATE - ", number + " added to currentText");
    }

    public void click_plus(View view) {
    }

    public void click_minus(View view) {
    }

    public void click_times(View view) {
    }

    public void click_divide(View view) {
    }

    public void click_point(View view) {
    }

    public void click_del(View view) {
    }

    public void click_clear(View view) {
        String tag = (String) view.getTag();
        Log.d("STATE - ", "Clicked on " + tag);
        TextView currentNumber = (TextView) findViewById(R.id.current_number);
        currentNumber.setText("");
        // TODO - Empty the stack (in Java and in TextViews)
        Log.d("STATE - ", "Emptied");
    }

    public void click_pop(View view) {
    }

    public void click_swap(View view) {
    }

    public void click_enter(View view) {
    }
}
