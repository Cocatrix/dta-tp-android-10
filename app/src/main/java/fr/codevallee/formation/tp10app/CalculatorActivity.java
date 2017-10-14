package fr.codevallee.formation.tp10app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Stack;

public class CalculatorActivity extends AppCompatActivity {
    private Stack<String> stack = new Stack<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        // TODO - Improve view on landscape
        Log.d("STATE", "Begin");
    }

    public void click_0_to_9(View view) {
        String number = (String) view.getTag();
        Log.d("ACTION", "Clicked on " + number);
        TextView currentNumber = (TextView) findViewById(R.id.current_number);
        /* Adding Strings. Just need not to take zero if that is the only character
         */
        if (currentNumber.getText().equals("0")) {
            currentNumber.setText(number);
        } else {
            currentNumber.setText(currentNumber.getText() + number);
        }

        Log.d("STATE", number + " added to currentText");
    }

    public void click_operator(View view) {
        String point = (String) view.getTag();
        Log.d("ACTION", "Clicked on " + point);
        if(this.stack.size()<1) {
            Log.d("STATE", "Short stack, no operation done");
            return;
        }
        this.click_enter(view);
        if(this.stack.size()<2) {
            Log.d("STATE", "Short stack, no operation done because previous entry invalid");
            return;
        }
        Float f1 = Float.parseFloat(this.stack.pop());
        Float f2 = Float.parseFloat(this.stack.pop());
        Float fRes;

        switch (point) {
            case "+":
                fRes = f2 + f1;

                break;
            case "-":
                fRes = f2 - f1;
                break;
            case "×":
                fRes = f2 * f1;
                break;
            case "÷":
                fRes = f2 / f1;
                break;
            default:
                fRes = null;
        }
        this.stack.add(0,fRes.toString());
        Log.d("STATE", "Operation " + point + "done");
        this.refreshUIStack();
    }

    public void click_point(View view) {
        String point = (String) view.getTag();
        Log.d("ACTION", "Clicked on POINT");
        TextView currentNumber = (TextView) findViewById(R.id.current_number);
        /* Adding Strings. Just need to add zero if nothing has been typed
         */
        if (currentNumber.getText().equals("")) {
            currentNumber.setText("0" + point);
        } else if (currentNumber.getText().toString().contains(point)) {
            Log.d("STATE", "POINT not added, already is last character");
            return;
        }else {
            currentNumber.setText(currentNumber.getText() + point);
        }

        Log.d("STATE", "POINT added to currentText");
    }

    public void click_del(View view) {
        // TODO - Copy CLEAR and do just one character
    }

    public void click_clear(View view) {
        Log.d("ACTION", "Clicked on CLEAR");
        // Empty TextView currentNumber
        TextView currentNumber = (TextView) findViewById(R.id.current_number);
        currentNumber.setText("");
        // Empty the stack in Java and TextViews
        this.stack.clear();
        // TODO - Correct bug : CLEAR only clears head of stack now
        this.refreshUIStack();
        Log.d("STATE", "Emptied");
    }

    public void click_pop(View view) {
        // TODO - Delete last element of stack
    }

    public void click_swap(View view) {
        // TODO - Swap last two elements of stack
    }

    public void click_enter(View view) {
        /**
         * Empty the current number written and puts it in stack, then call refreshUIStack().
         * Does nothing if entry equals "" or "." or "0" or "0.".
         * Adds a zero if entry ends with "." (but not "." or "0." handled before).
         */
        Log.d("ACTION", "Clicked on ENTER");
        TextView TextCurrentNumber = (TextView) findViewById(R.id.current_number);
        String currentNumber = TextCurrentNumber.getText().toString();
        // Check whether we should add the entry
        if (!(currentNumber.equals("")||currentNumber.equals("0")||currentNumber.equals(".")||currentNumber.equals("0."))) {
            if (currentNumber.endsWith(".")) { // Check whether we need to add zero at the end
                this.stack.add(0, currentNumber + "0");
            } else {
                this.stack.add(0,currentNumber);
            }
            this.refreshUIStack();
        }
        TextCurrentNumber.setText("");
        Log.d("STATE", "ENTER managed");
    }
    
    public void refreshUIStack() {
        /**
         * Rewrites in TextViews every value of the stack, up to the 4 first ones.
         * If the size is less than 4, empty the rest.
         */
        Log.d("ACTION", "Refreshing");
        // this.stack is regarded as up-to-date here
        TextView stackNumber4 = (TextView) findViewById(R.id.stack_number_4);
        TextView stackNumber3 = (TextView) findViewById(R.id.stack_number_3);
        TextView stackNumber2 = (TextView) findViewById(R.id.stack_number_2);
        TextView stackNumber1 = (TextView) findViewById(R.id.stack_number_1);
        TextView[] stackNumbers = {stackNumber1,stackNumber2,stackNumber3,stackNumber4};
        // We write 4 times maximum, or just the size of the stack if 4 or less elements
        Integer numberWritings = ((this.stack.size()<4)?this.stack.size():4);
        Integer iWritings = 0,iDeletions;
        for (iWritings=0;iWritings<numberWritings;iWritings++) {
            stackNumbers[iWritings].setText(this.stack.elementAt(iWritings));
        }
        // We empty the rest
        for (iDeletions=iWritings;iDeletions<4;iDeletions++) {
            stackNumbers[iWritings].setText("");
        }
        Log.d("STATE", "Refreshing done");
    }
}
