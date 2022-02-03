package com.TRDZ.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
//region обьявление переменных
    TextView T_Result;
    TextView T_Memory;
    Button B_num_0;
    Button B_num_1;
    Button B_num_2;
    Button B_num_3;
    Button B_num_4;
    Button B_num_5;
    Button B_num_6;
    Button B_num_7;
    Button B_num_8;
    Button B_num_9;
    Button B_save;
    Button B_load;
    Button B_eqw;
    Button B_add;
    Button B_sub;
    Button B_mul;
    Button B_step;
    Button B_stepx;
    Button B_fac;
    Button B_div;
    Button B_mod;
    Button B_cor;
    Button B_cory;
    Button B_ln;
    Button B_log;
    Button B_sin;
    Button B_cos;
    Button B_tan;
    Button B_rev;
    Button B_sep;
    Button B_delete_1s;
    Button B_delete_1w;
    Button B_delete_All;
    Calculate math;
//endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        create_buttons();
        }

    protected void initialize() {
        T_Result = findViewById(R.id.T_Result);
        T_Memory = findViewById(R.id.T_Memory);
        math = new Calculate(T_Memory, T_Result);
        B_num_0 = findViewById(R.id.B_num_0);
        B_num_1 = findViewById(R.id.B_num_1);
        B_num_2 = findViewById(R.id.B_num_2);
        B_num_3 = findViewById(R.id.B_num_3);
        B_num_4 = findViewById(R.id.B_num_4);
        B_num_5 = findViewById(R.id.B_num_5);
        B_num_6 = findViewById(R.id.B_num_6);
        B_num_7 = findViewById(R.id.B_num_7);
        B_num_8 = findViewById(R.id.B_num_8);
        B_num_9 = findViewById(R.id.B_num_9);
        B_save= findViewById(R.id.B_save);
        B_load= findViewById(R.id.B_load);
        B_eqw= findViewById(R.id.B_eqw);
        B_add= findViewById(R.id.B_add);
        B_sub= findViewById(R.id.B_sub);
        B_mul= findViewById(R.id.B_mul);
        B_step= findViewById(R.id.B_step);
        B_stepx= findViewById(R.id.B_stepx);
        B_fac= findViewById(R.id.B_fac);
        B_div= findViewById(R.id.B_div);
        B_mod= findViewById(R.id.B_mod);
        B_cor= findViewById(R.id.B_cor);
        B_cory= findViewById(R.id.B_cory);
        B_ln= findViewById(R.id.B_ln);
        B_log= findViewById(R.id.B_log);
        B_sin= findViewById(R.id.B_sin);
        B_cos= findViewById(R.id.B_cos);
        B_tan= findViewById(R.id.B_tan);
        B_rev= findViewById(R.id.B_rev);
        B_sep= findViewById(R.id.B_sep);
        B_delete_1s= findViewById(R.id.B_delete_1s);
        B_delete_1w= findViewById(R.id.B_delete_1w);
        B_delete_All= findViewById(R.id.B_delete_All);
        }
    protected void create_buttons() {
        B_num_0.setOnClickListener(this);
        B_num_1.setOnClickListener(this);
        B_num_2.setOnClickListener(this);
        B_num_3.setOnClickListener(this);
        B_num_4.setOnClickListener(this);
        B_num_5.setOnClickListener(this);
        B_num_6.setOnClickListener(this);
        B_num_7.setOnClickListener(this);
        B_num_8.setOnClickListener(this);
        B_num_9.setOnClickListener(this);
        B_save.setOnClickListener(this);
        B_load.setOnClickListener(this);
        B_eqw.setOnClickListener(this);
        B_add.setOnClickListener(this);
        B_sub.setOnClickListener(this);
        B_mul.setOnClickListener(this);
        B_div.setOnClickListener(this);
        B_mod.setOnClickListener(this);
        B_cor.setOnClickListener(this);
        B_rev.setOnClickListener(this);
        B_sep.setOnClickListener(this);
        B_delete_1s.setOnClickListener(this);
        B_delete_1w.setOnClickListener(this);
        B_delete_All.setOnClickListener(this);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            B_fac.setOnClickListener(this);
            B_cory.setOnClickListener(this);
            B_ln.setOnClickListener(this);
            B_log.setOnClickListener(this);
            B_sin.setOnClickListener(this);
            B_cos.setOnClickListener(this);
            B_tan.setOnClickListener(this);
            B_step.setOnClickListener(this);
            B_stepx.setOnClickListener(this);
            }
        }

    @Override
    public void onClick(View view) { math.get_and_run(view);}

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        math.pakeje(outState);
        outState.putString("T_Result", T_Result.getText().toString());
        outState.putString("T_Memory", T_Memory.getText().toString());
        super.onSaveInstanceState(outState);
        }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        T_Result.setText(savedInstanceState.getString("T_Result"));
        T_Memory.setText(savedInstanceState.getString("T_Memory"));
        math.Depakeje(savedInstanceState);
        }

}