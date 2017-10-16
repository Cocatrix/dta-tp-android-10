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
        if (this.stack.size() < 1) {
            Log.d("STATE", "Short stack, no operation done");
            return;
        }
        this.click_enter(view);
        if (this.stack.size() < 2) {
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
        this.stack.push(fRes.toString());
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
        /**
         * If the entry is not empty, removes the last entered number (or point).
         */
         Log.d("ACTION", "Clicked on DEL");
        // Get TextView currentNumber and remove last character
        TextView currentNumber = (TextView) findViewById(R.id.current_number);
        String currentNb = currentNumber.getText().toString();
        if (currentNb.length() < 2) {
            currentNumber.setText("");
        } else {
            currentNumber.setText(currentNb.substring(0,currentNb.length()-1));
        }
        Log.d("STATE", "DEL done");
    }

    public void click_clear(View view) {
        /**
         * Clears the entry and the stack.
         */
        Log.d("ACTION", "Clicked on CLEAR");
        // Empty TextView currentNumber
        TextView currentNumber = (TextView) findViewById(R.id.current_number);
        currentNumber.setText("");
        // Empty the stack in Java and TextViews
        this.stack.clear();
        this.refreshUIStack();
        Log.d("STATE", "Emptied");
    }

    public void click_pop(View view) {
        /**
         * If the stack has one element or more, the first is removed.
         */
        Log.d("ACTION", "Clicked on POP");
        if (stack.empty()) {
            Log.d("STATE", "Nothing to pop");
        } else {
            this.stack.pop();
            this.refreshUIStack();
            Log.d("STATE", "Poped one element");
        }
    }

    public void click_swap(View view) {
        /**
         * If the stack has two or more elements, swaps the two first elements.
         */
        Log.d("ACTION", "Clicked on SWAP");
        if (stack.size() < 2) {
            Log.d("STATE", "Nothing to swap");
        } else {
            String s1 = this.stack.pop();
            String s2 = this.stack.pop();
            this.stack.push(s1);
            this.stack.push(s2);
            this.refreshUIStack();
            Log.d("STATE", "Swapped first two elements");
        }
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
                this.stack.push(currentNumber + "0");
            } else {
                this.stack.push(currentNumber);
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
            stackNumbers[numberWritings-1-iWritings].setText(this.stack.elementAt(iWritings));
        }
        // We empty the rest
        Log.d("ACTION","iWritings = " + iWritings.toString());
        for (iDeletions=iWritings;iDeletions<4;iDeletions++) {
            Log.d("ACTION","Removing element" + iDeletions.toString());
            stackNumbers[iDeletions].setText("");
        }
        Log.d("STATE", "Refreshing done");
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        TextView currentNumberWritten = (TextView) findViewById(R.id.current_number);
        currentNumberWritten.setText(savedInstanceState.getString("current"));
        this.stack = (Stack) savedInstanceState.getSerializable("stack");
        super.onRestoreInstanceState(savedInstanceState);
        refreshUIStack();
    }

    protected void onSaveInstanceState(Bundle outState) {
        TextView currentNumberWritten = (TextView) findViewById(R.id.current_number);
        outState.putSerializable("stack", this.stack);
        outState.putString("current", currentNumberWritten.getText().toString());
        super.onSaveInstanceState(outState);
        refreshUIStack();
    }
}
