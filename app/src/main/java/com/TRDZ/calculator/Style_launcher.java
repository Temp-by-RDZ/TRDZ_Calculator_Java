package com.TRDZ.calculator;

import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.ToggleButton;


public class Style_launcher extends AppCompatActivity implements View.OnClickListener {
	CheckBox F_Alt;
	Button B_go;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.launcher);
		initialize();
		style_toggle();
		}

	protected void initialize() {
		F_Alt = findViewById(R.id.F_Alternative);
		B_go = findViewById(R.id.B_go);
		}

	protected void style_toggle() {
		B_go.setOnClickListener(this);
		}

	@Override
	public void onClick(View view) {
		Intent intent = new Intent(Style_launcher.this, MainActivity.class);
		if (F_Alt.isChecked()) intent.putExtra("STYLE", R.style.Theme_Alternative);
		else intent.putExtra("STYLE", R.style.Theme_Task02AS_Calculator);
		startActivity(intent);
		}
	}
