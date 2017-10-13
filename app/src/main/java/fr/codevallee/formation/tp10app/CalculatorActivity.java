package fr.codevallee.formation.tp10app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Stack;

public class CalculatorActivity extends AppCompatActivity {
    private Stack<Float> stack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
    }
}
