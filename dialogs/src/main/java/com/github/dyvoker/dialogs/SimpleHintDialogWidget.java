package com.github.dyvoker.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

/**
 * Simple dialog with title and text.
 */
@SuppressWarnings({ "WeakerAccess", "unused" })
public class SimpleHintDialogWidget {

	@NonNull
	private final Context context;
	@NonNull
	private final CharSequence title;
	@NonNull
	private final CharSequence text;

	@Nullable
	private final CharSequence buttonText;
	@ColorInt
	private final int titleColor;
	@ColorInt
	private final int textColor;
	@ColorInt
	private final int buttonTextColor;
	private final boolean isCanceledOnTouchOutside;

	private Dialog dialog;


	SimpleHintDialogWidget(
		@NonNull Context context,
		@NonNull CharSequence title,
		@NonNull CharSequence text,
		@Nullable CharSequence buttonText,
		@ColorInt int titleColor,
		@ColorInt int textColor,
		@ColorInt int buttonTextColor,
		boolean isCanceledOnTouchOutside
	) {
		this.context = context;
		this.title = title;
		this.text = text;
		this.buttonText = buttonText;
		this.titleColor = titleColor;
		this.textColor = textColor;
		this.buttonTextColor = buttonTextColor;
		this.isCanceledOnTouchOutside = isCanceledOnTouchOutside;
	}

	/**
	 * Creates dialog. Use only {@link #show()} method, if you don't need to change {@link Dialog}.
	 */
	public void create() {
		dialog = new Dialog(context);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setCanceledOnTouchOutside(isCanceledOnTouchOutside);
		dialog.setContentView(R.layout.dialogs_dyv_hint);

		// Title.
		TextView titleView = dialog.findViewById(R.id.dialogs_dyv_title);
		titleView.setText(title);
		titleView.setTextColor(titleColor);

		// Text.
		TextView textView = dialog.findViewById(R.id.dialogs_dyv_text);
		textView.setText(text);
		textView.setTextColor(textColor);

		// "OK" button.
		TextView okButton = dialog.findViewById(R.id.dialogs_dyv_ok);
		if (buttonText != null) {
			okButton.setText(buttonText);
		}
		okButton.setTextColor(buttonTextColor);
		okButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
	}

	/**
	 * Show dialog.
	 */
	public void show() {
		if (dialog == null) {
			create();
		}
		dialog.show();
	}

	/**
	 * Dismiss dialog.
	 */
	public void dismiss() {
		if (dialog != null && dialog.isShowing()) {
			dialog.dismiss();
		}
	}

	/**
	 * @return Created dialog. If you need some specific changes.
	 * NonNull only after {@link #show()} or {@link #create()}!
	 */
	@Nullable
	public Dialog getDialog() {
		return dialog;
	}

	/**
	 * @param context Context.
	 * @param title Title of the dialog from resources.
	 * @param text Text on the dialog from resources.
	 * @return Builder for {@link SimpleHintDialogWidget}.
	 */
	public static Builder newBuilder(
		@NonNull Context context,
		@StringRes int title,
		@StringRes int text
	) {
		return new Builder(
			context,
			context.getResources().getString(title),
			context.getResources().getString(text)
		);
	}

	/**
	 * @param context Context.
	 * @param title Title of the dialog.
	 * @param text Text on the dialog.
	 * @return Builder for {@link SimpleHintDialogWidget}.
	 */
	public static Builder newBuilder(
		@NonNull Context context,
		@NonNull CharSequence title,
		@NonNull CharSequence text
	) {
		return new Builder(context, title, text);
	}

	/**
	 * Builder for {@link SimpleHintDialogWidget}.
	 */
	public static class Builder {

		@NonNull private final Context context;
		@NonNull private final CharSequence title;
		@NonNull private final CharSequence text;

		@Nullable private CharSequence buttonText;
		@ColorInt private int titleColor;
		@ColorInt private int textColor;
		@ColorInt private int buttonTextColor;
		private boolean isCanceledOnTouchOutside = true;


		private Builder(
			@NonNull Context context,
			@NonNull CharSequence title,
			@NonNull CharSequence text
		) {
			this.context = context;
			this.title = title;
			this.text = text;
			titleColor = context.getResources().getColor(R.color.dialogs_dyv_black);
			textColor = context.getResources().getColor(R.color.dialogs_dyv_black);
			buttonTextColor = context.getResources().getColor(R.color.dialogs_dyv_button_color);
		}

		/**
		 * @param buttonText Text on "OK" button.
		 * @return {@link Builder} for {@link SimpleHintDialogWidget}.
		 */
		@NonNull
		public Builder buttonText(@StringRes int buttonText) {
			this.buttonText = context.getResources().getString(buttonText);
			return this;
		}

		/**
		 * @param buttonText Text on "OK" button.
		 * @return {@link Builder} for {@link SimpleHintDialogWidget}.
		 */
		@NonNull
		public Builder buttonText(@NonNull CharSequence buttonText) {
			this.buttonText = buttonText;
			return this;
		}

		/**
		 * @param titleColor Color of the dialog title.
		 * @return {@link Builder} for {@link SimpleHintDialogWidget}.
		 */
		@NonNull
		public Builder titleColor(@ColorInt int titleColor) {
			this.titleColor = titleColor;
			return this;
		}

		/**
		 * @param titleColor Color of the dialog title.
		 * @return {@link Builder} for {@link SimpleHintDialogWidget}.
		 */
		@NonNull
		public Builder titleColorRes(@ColorRes int titleColor) {
			this.titleColor = context.getResources().getColor(titleColor);
			return this;
		}

		/**
		 * @param textColor Color of the dialog text.
		 * @return {@link Builder} for {@link SimpleHintDialogWidget}.
		 */
		@NonNull
		public Builder textColor(@ColorInt int textColor) {
			this.textColor = textColor;
			return this;
		}

		/**
		 * @param textColor Color of the dialog text.
		 * @return {@link Builder} for {@link SimpleHintDialogWidget}.
		 */
		@NonNull
		public Builder textColorRes(@ColorRes int textColor) {
			this.textColor = context.getResources().getColor(textColor);
			return this;
		}

		/**
		 * @param buttonTextColor Text color of the dialog "OK" button.
		 * @return {@link Builder} for {@link SimpleHintDialogWidget}.
		 */
		@NonNull
		public Builder buttonTextColor(@ColorInt int buttonTextColor) {
			this.buttonTextColor = buttonTextColor;
			return this;
		}

		/**
		 * @param buttonTextColor Text color of the dialog "OK" button.
		 * @return {@link Builder} for {@link SimpleHintDialogWidget}.
		 */
		@NonNull
		public Builder buttonTextColorRes(@ColorRes int buttonTextColor) {
			this.buttonTextColor = context.getResources().getColor(buttonTextColor);
			return this;
		}

		/**
		 * @param isCanceledOnTouchOutside If true - user can cancel dialog by clicking on outside.
		 * @return {@link Builder} for {@link SimpleHintDialogWidget}.
		 */
		@NonNull
		public Builder isCanceledOnTouchOutside(boolean isCanceledOnTouchOutside) {
			this.isCanceledOnTouchOutside = isCanceledOnTouchOutside;
			return this;
		}

		/**
		 * @return {@link SimpleHintDialogWidget} with set up parameters.
		 */
		@NonNull
		public SimpleHintDialogWidget build() {
			return new SimpleHintDialogWidget(
				context,
				title,
				text,
				buttonText,
				titleColor,
				textColor,
				buttonTextColor,
				isCanceledOnTouchOutside
			);
		}
	}
}
