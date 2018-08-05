package com.github.dyvoker.dialogs_lib;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.github.dyvoker.dialogs.SimpleHintDialogWidget;
import com.github.dyvoker.dialogs.context_menu.ContextMenuWidget;
import com.github.dyvoker.dialogs.context_menu.SimpleMenuItem;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final Context context = this;

		View showDialog = findViewById(R.id.show_dialog);
		showDialog.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				SimpleHintDialogWidget.newBuilder(
					context,
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
						context,
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

		View showContextMenu = findViewById(R.id.show_context_menu);
		showContextMenu.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ContextMenuWidget
					.newBuilder(context)
					.addItem(SimpleMenuItem.newBuilder(context, "First").build())
					.addItem(SimpleMenuItem.newBuilder(context, "Second").build())
					.addItem(SimpleMenuItem.newBuilder(context, "Third").build())
					.build()
					.show();
			}
		});
	}
}
