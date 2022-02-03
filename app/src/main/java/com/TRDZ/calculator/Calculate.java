package com.TRDZ.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;


public class Calculate {
	TextView T_Memory;
	TextView T_Result;
	int action;
	int prev;
	int col;
	int[] limit;
	int[] is_sep;
	double[] num;
	double saved;
	String symbol;
	BigDecimal Dangerous_Math;

	public Calculate(TextView T_Memory, TextView T_Result) {
		prev=0;
		col=0;
		symbol="";
		num= new double[2];
		is_sep = new int[2];
		limit = new int[4];
		this.T_Memory = T_Memory;
		this.T_Result = T_Result;
		}

	/**Получаем сигнал о нажатии и начинаем обработку
	 */
	public void get_and_run(View view) {
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
				result(T_Result.getText()+" = "+num[0]);
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
				result(temp+" ^10 = "+num[0]);
				return;
				}
		case R.id.B_fac:        // !
			if (prev==0) {
				double temp=num[0];
				num[0]=factorial(num[0]);
				result(temp+" ! = "+num[0]);
				return;
				}
		case R.id.B_cor:        // √
			if (prev==0) {
				double temp=num[0];
				num[0]=Math.sqrt(num[0]);
				result(temp+" √ = "+num[0]);
				return;
				}
		case R.id.B_ln:         // ln()
			if (prev==0) {
				double temp=num[0];
				num[0]=Math.log(num[0]);
				result("log("+temp+") = "+num[0]);
				return;
				}
		case R.id.B_log:        // log()
			if (prev==0) {
				double temp=num[0];
				num[0]=Math.log10(num[0]);
				result("log("+temp+") = "+num[0]);
				return;
				}
		case R.id.B_sin:        // sin()
			if (prev==0) {
				double temp=num[0];
				num[0]=Math.sin(num[0]);
				result("sin("+temp+") = "+num[0]);
				return;
				}
		case R.id.B_cos:        // cos()
			if (prev==0) {
				double temp=num[0];
				num[0]=Math.cos(num[0]);
				result("sin("+temp+") = "+num[0]);
				return;
				}
		case R.id.B_tan:        // tan()
			if (prev==0) {
				double temp=num[0];
				num[0]=Math.tan(num[0]);
				result("tan("+temp+") = "+num[0]);
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
			if (limit[3]>0) {
				limit[3]--;
				num[1]=round(1);
				}
			else if (limit[2]>0) {
				limit[2]--;
				if (is_sep[1]>0) {is_sep[1]=0; limit[0]++;}
				else num[1]=((int)(num[1])/10);}
			else if (prev>0) {prev=0; symbol=""; col=0;}
			else if (limit[1]>0) {
				limit[1]--;
				num[0]=round(0);}
			else if (limit[0]>0) {
				limit[0]--;
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
			if (num[col]<1000000000&&num[col]>-1000000000) {
				if (is_sep[col]==0) {
				limit[col*2]++;
				if (num[col]<0) num[col]=num[col]*10-Double.parseDouble(B_pressed.getText().toString());
				else num[col]=num[col]*10+Double.parseDouble(B_pressed.getText().toString()); }
				else if(is_sep[col]<1000000000) {
					limit[col*2+1]++;
					if (num[col]<0) Dangerous_Math=BigDecimal.valueOf(num[col]).subtract(BigDecimal.valueOf(Double.parseDouble(B_pressed.getText().toString())).divide(BigDecimal.valueOf(is_sep[col])));
					else Dangerous_Math=BigDecimal.valueOf(num[col]).add(BigDecimal.valueOf(Double.parseDouble(B_pressed.getText().toString())).divide(BigDecimal.valueOf(is_sep[col])));
					num[col]=Dangerous_Math.doubleValue();
					is_sep[col]*=10;
					}
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
		case 5:
			num[0]/=num[1];
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
		double result=Math.floor(num[seg]*is_sep[seg]/10);
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
		if (num[seg]%1!=0) number.append(num[0]);
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

	protected void pakeje(Bundle outState) {
		outState.putInt("action",action);
		outState.putInt("prev",prev);
		outState.putInt("col",col);
		outState.putIntArray("limit",limit);
		outState.putIntArray("is_se",is_sep);
		outState.putDouble("saved",saved);
		outState.putDoubleArray("num",num);
		outState.putString("symbol",symbol);
		}

	protected void Depakeje(Bundle savedInstanceState) {
		action=savedInstanceState.getInt("action");
		prev=savedInstanceState.getInt("prev");
		col=savedInstanceState.getInt("col");
		limit=savedInstanceState.getIntArray("limit");
		is_sep=savedInstanceState.getIntArray("is_se");
		saved=savedInstanceState.getDouble("saved");
		num=savedInstanceState.getDoubleArray("num");
		symbol=savedInstanceState.getString("symbol");
		}
	}
