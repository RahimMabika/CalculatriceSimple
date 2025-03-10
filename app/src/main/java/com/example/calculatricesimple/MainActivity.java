package com.example.calculatricesimple;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView screen;
    private int op1 = 0;
    private int op2 = 0;
    private Ops operator = null;
    private boolean isOp1 = true;

    private enum Ops {
        PLUS, MOINS, FOIS, DIV
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        screen = findViewById(R.id.screen);

        View.OnClickListener numberListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                int val = Integer.parseInt(b.getText().toString());
                if (isOp1) {
                    op1 = op1 * 10 + val;
                    updateDisplay(op1);
                } else {
                    op2 = op2 * 10 + val;
                    updateDisplay(op2);
                }
            }
        };

        int[] numberIds = { R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9 };
        for (int id : numberIds) {
            findViewById(id).setOnClickListener(numberListener);
        }

        findViewById(R.id.btnPlus).setOnClickListener(v -> setOperator(Ops.PLUS));
        findViewById(R.id.btnMoins).setOnClickListener(v -> setOperator(Ops.MOINS));
        findViewById(R.id.btnFois).setOnClickListener(v -> setOperator(Ops.FOIS));
        findViewById(R.id.btnDiv).setOnClickListener(v -> setOperator(Ops.DIV));

        findViewById(R.id.btnEgal).setOnClickListener(v -> computeResult());
    }

    private void setOperator(Ops op) {
        operator = op;
        isOp1 = false;
    }

    private void computeResult() {
        if (operator != null) {
            switch (operator) {
                case PLUS:
                    op1 += op2;
                    break;
                case MOINS:
                    op1 -= op2;
                    break;
                case FOIS:
                    op1 *= op2;
                    break;
                case DIV:
                    if (op2 != 0) op1 /= op2;
                    else screen.setText("Erreur");
                    break;
            }
            op2 = 0;
            isOp1 = true;
            updateDisplay(op1);
        }
    }
    public void resetCalculator(View v) {
        op1 = 0;
        op2 = 0;
        operator = null;
        isOp1 = true;
        updateDisplay(op1);
    }
    private void updateDisplay(int value) {
        screen.setText(String.valueOf(value));
    }
}
