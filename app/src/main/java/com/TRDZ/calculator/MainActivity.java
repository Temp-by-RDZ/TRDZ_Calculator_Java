package com.TRDZ.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.math.BigInteger;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
//region обьявление переменных
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
    TextView T_Result;
    TextView T_Memory;
    int prev=0;
    int col=0;
    int[] limit;
    int[] is_sep;
    double[] num;
    double saved;
    String symbol="";
    int action;
//endregion

@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        }

    protected void initialize() {
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
        T_Result = findViewById(R.id.T_Result);
        T_Memory = findViewById(R.id.T_Memory);
        num= new double[2];
        is_sep = new int[2];
        limit = new int[4];
        create_buttons();
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
    public void onClick(View view) {
        Button B_pressed = (Button) view;
    //region Разделение кнопок по функционалу
        switch (view.getId()){
        case R.id.B_save:       // save
            saved=num[col];
            T_Memory.setText(Double.toString(saved));
            break;
        case R.id.B_load:       // load
            num[col]=saved;
            break;
        case R.id.B_eqw:        // =
            if (prev<2) {
                T_Result.clearComposingText();
                analyze();
                result(T_Result.getText()+"="+num[0]);
                return;
                }
        case R.id.B_add:        // +
            if (prev==0) {action=1; symbol=B_pressed.getText().toString(); prev=2; col=1;}
            break;
        case R.id.B_sub:        // -
            if (prev==0) {action=2; symbol=B_pressed.getText().toString(); prev=2; col=1;}
            break;
        case R.id.B_mul:        // *
            if (prev==0) {action=3; symbol=B_pressed.getText().toString(); prev=2; col=1;}
            break;
        case R.id.B_stepx:      // ^x
            if (prev==0) {action=4; symbol=B_pressed.getText().toString(); prev=2; col=1;}
            break;
        case R.id.B_div:        // /
            if (prev==0) {action=5; symbol=B_pressed.getText().toString(); prev=2; col=1;}
            break;
        case R.id.B_mod:        // %
            if (prev==0) {action=6; symbol=B_pressed.getText().toString(); prev=2; col=1;}
            break;
        case R.id.B_cory:       // y√
            if (prev==0) {action=7; symbol=B_pressed.getText().toString(); prev=2; col=1;}
            break;
        case R.id.B_step:       // ^10
            if (prev==0) {
                double temp=num[0];
                num[0]=Math.pow(num[0],10);
                result(temp+"^10="+num[0]);
                return;
                }
        case R.id.B_fac:        // !
            if (prev==0) {
            double temp=num[0];
            num[0]=factorial(num[0]);
            result(temp+"!="+num[0]);
            return;
            }
        case R.id.B_cor:        // √
            if (prev==0) {
            double temp=num[0];
            num[0]=Math.sqrt(num[0]);
            result(temp+"√="+num[0]);
            return;
            }
        case R.id.B_ln:         // ln()
            if (prev==0) {
            double temp=num[0];
            num[0]=Math.log(num[0]);
            result("log("+temp+")="+num[0]);
            return;
            }
        case R.id.B_log:        // log()
            if (prev==0) {
            double temp=num[0];
            num[0]=0;
            result("log("+temp+")="+num[0]);
            return;
            }
        case R.id.B_sin:        // sin()
            if (prev==0) {
            double temp=num[0];
            num[0]=Math.sin(num[0]);
            result("sin("+temp+")="+num[0]);
            return;
            }
        case R.id.B_cos:        // cos()
            if (prev==0) {
            double temp=num[0];
            num[0]=Math.cos(num[0]);
            result("sin("+temp+")="+num[0]);
            return;
            }
        case R.id.B_tan:        // tan()
            if (prev==0) {
            double temp=num[0];
            num[0]=Math.tan(num[0]);
            result("tan("+temp+")="+num[0]);
            return;
            }
        case R.id.B_rev:        // ±
            num[col]*=-1;
            break;
        case R.id.B_sep:        // ,
            if (is_sep[col]==0&&prev<2) {
                is_sep[col]=10;
                T_Result.setText(T_Result.getText()+B_pressed.getText().toString());
                }
            break;
        case R.id.B_delete_1s:  // ←
            if (limit[3]>0) {limit[3]--; num[1]=round(1);}
            else if (limit[2]>0) {limit[2]--;
                if (is_sep[1]>0) {is_sep[1]=0; limit[0]++;}
                else num[1]=((int)(num[1])/10);}
            else if (prev>0) {prev=0; symbol=""; col=0;}
            else if (limit[1]>0) {limit[1]--; num[0]=round(0);}
            else if (limit[0]>0) {limit[0]--;
                if (is_sep[0]>0) {is_sep[0]=0; limit[0]++;}
                else num[0]=((int)(num[0])/10);}
            break;
        case R.id.B_delete_1w:  // CE
            num[col]=0;
            is_sep[col]=0;
            limit[col*2]=0;
            limit[col*2+1]=0;
            break;
        case R.id.B_delete_All: // CA
            clear();
            break;
        default: // 1..9
            if (prev==2) prev=1;
            if (num[col]<1000000000) {
                if (is_sep[col]==0) {num[col]=num[col]*10+Double.parseDouble(B_pressed.getText().toString()); limit[col*2]++;}
                else if(is_sep[col]<1000000000) { num[col]=num[col]+Double.parseDouble(B_pressed.getText().toString())/is_sep[col]; is_sep[col]*=10; limit[col*2+1]++;}
                }
            }
    //endregion
    //region Вывод нажатий
        StringBuilder pole = new StringBuilder();
        pole.append(get_number(0));
        pole.append(" "+symbol+" ");
        if (num[1]!=0) pole.append(get_number(1));
        T_Result.setText(pole);
    //endregion
        }

    /**
     * Получение результатов выполнения команды с двумя переменными
     */
    public void analyze() {
        switch (action) {
        case 1: num[0]+=num[1];
            break;
        case 2: num[0]-=num[1];
            break;
        case 3: num[0]*=num[1];
            break;
        case 4: num[0]=Math.pow(num[0],num[1]);
            break;
        case 5: num[0]/=num[1];
            break;
        case 6: num[0]%=num[1];
            break;
        case 7: num[0]=Math.pow(num[0],1/num[1]);
            }
        }

    /**
     * Вычисление факториала числа
     * @param n число
     * @return результат
     */
    public double factorial(double n) {
        if (n <= 1) return n;
        return n * (factorial(n-1));
        }

    /**
     * Округление числа на 1 значение
     * @param seg указатель на число
     * @return результат
     */
    private double round( int seg) {
        is_sep[seg]/=10;
        double result=Math.round(num[seg]*is_sep[seg]/10);
        result/=is_sep[seg];
        return result*10;
        }

    /**
     * Оформление числа на экране
     * @param seg  указатель на число
     * @return строка с числом
     */
    protected String get_number(int seg) {
        StringBuilder number = new StringBuilder();
        if (num[seg]%1>0) number.append(num[0]);
        else if (is_sep[seg]>0) {
            number.append((int) num[seg] + ".");
            for (int i = 10; i < is_sep[seg]; i *= 10) number.append("0");
            }
        else number.append((int) num[seg]);
        return number.toString();
        }

    /**
     * Софт сброс заполнения вычислений
     */
    protected void clear() {
        prev=0;
        col=0;
        limit=new int[4];
        is_sep=new int[2];
        num=new double[2];
        symbol="";
        action=0;
        }

    /**
     * Обработка завершения вычисления
     * @param result сообщение для вывода
     */
    protected void result(String result) {
        T_Result.setText(result);
        prev=0;
        col=0;
        limit=new int[4];
        is_sep=new int[2];
        num[1]=0;
        symbol="";
        action=0;
        }
}