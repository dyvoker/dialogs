package com.github.dyvoker.dialogs_lib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.github.dyvoker.dialogs.SimpleHintDialogWidget;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		View showDialog = findViewById(R.id.show_dialog);
		showDialog.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				SimpleHintDialogWidget.newBuilder(
					MainActivity.this,
					R.string.simple_hint_title,
					R.string.simple_hint_text
				).build().show();
			}
		});

		View showDialogCustom = findViewById(R.id.show_dialog_custom);
		showDialogCustom.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				SimpleHintDialogWidget
					.newBuilder(
						MainActivity.this,
						R.string.custom_hint_title,
						R.string.custom_hint_text
					)
					.titleColorRes(R.color.pomegranate)
					.textColorRes(R.color.concrete)
					.buttonTextColorRes(R.color.wisteria)
					.buttonText(R.string.custom_hint_button)
					.build()
					.show();
			}
		});
	}
}
