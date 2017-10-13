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

    public void click_0_to_9(View view) {
        String number = (String) view.getTag();
        Log.d("STATE - ", "Clicked on " + number);
        TextView currentNumber = (TextView) findViewById(R.id.current_number);
        /* Converting to Integer and then String allows to remove unwanted zeros. Indeed
         * "0"+"3" = "03"
         * String(Integer("0"+"3") = "3"
         */
        Float totalPrintedNumber = Float.parseFloat(currentNumber.getText().toString() + number);
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
        // Empty TextView currentNumber
        TextView currentNumber = (TextView) findViewById(R.id.current_number);
        currentNumber.setText("");
        // Empty the stack in Java and TextViews
        this.stack.empty();
        this.refreshUIStack();
        Log.d("STATE - ", "Emptied");
    }

    public void click_pop(View view) {
    }

    public void click_swap(View view) {
    }

    public void click_enter(View view) {
        TextView TextCurrentNumber = (TextView) findViewById(R.id.current_number);
        String currentNumber = TextCurrentNumber.getText().toString();
        if (currentNumber.equals("")&&currentNumber.equals(Float.parseFloat("0"))) {
            this.stack.add(Float.parseFloat(currentNumber));
            this.refreshUIStack();
        }
    }
    
    public void refreshUIStack() {
        /**
         * Rewrites in TextViews every value of the stack, up to the 4 first ones.
         * If the size is less than 4, empty the rest.
         */
        // this.stack is regarded as up-to-date here
        TextView stackNumber4 = (TextView) findViewById(R.id.stack_number_4);
        TextView stackNumber3 = (TextView) findViewById(R.id.stack_number_3);
        TextView stackNumber2 = (TextView) findViewById(R.id.stack_number_2);
        TextView stackNumber1 = (TextView) findViewById(R.id.stack_number_1);
        TextView[] stackNumbers = {stackNumber1,stackNumber2,stackNumber3,stackNumber4};
        // We write 4 times maximum, or just the size of the stack if 4 or less elements
        Integer numberWritings = ((this.stack.size()<4)?this.stack.size():4);
        Integer iWritings,iDeletings;
        for (iWritings=0;iWritings<numberWritings;iWritings++) {
            stackNumbers[iWritings].setText(this.stack.elementAt(iWritings).toString());
        }
        // We empty the rest
        for (iDeletings=iWritings;iDeletings<4;iDeletings++) {
            stackNumbers[iWritings].setText("");
        }

    }
}
