package com.github.dyvoker.dialogs.context_menu;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.dyvoker.dialogs.R;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * I recommend to create it right before use!
 */
@SuppressWarnings("unused")
public class ContextMenuWidget implements ContextMenuItemWidget.DismissListener {

	@NonNull
	private final Context context;
	@NonNull
	private final List<ContextMenuItemWidget> items;
	@Nullable
	private final CharSequence title;
	@ColorInt
	private final int titleColor;
	private final boolean isCanceledOnTouchOutside;

	private Dialog dialog;

	/**
	 * Private constructor. Use {@link Builder} instead.
	 */
	private ContextMenuWidget(
		@NonNull Context context,
		@NonNull List<ContextMenuItemWidget> items,
		@Nullable CharSequence title,
		@ColorInt int titleColor,
		boolean isCanceledOnTouchOutside
	) {
		this.context = context;
		this.items = items;
		this.title = title;
		this.titleColor = titleColor;
		this.isCanceledOnTouchOutside = isCanceledOnTouchOutside;
	}

	/**
	 * Show context menu.
	 */
	public void show() {
		dialog = new Dialog(context);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setCanceledOnTouchOutside(isCanceledOnTouchOutside);
		dialog.setContentView(R.layout.dialogs_dyv_context_menu);

		// Title.
		TextView titleView = dialog.findViewById(R.id.dialogs_dyv_title);
		if (title != null) {
			titleView.setText(title);
			titleView.setTextColor(titleColor);
		} else {
			titleView.setVisibility(View.GONE);
		}

		// Items container.
		LinearLayout itemsContainer = dialog.findViewById(R.id.dialogs_dyv_items_container);
		for (ContextMenuItemWidget item : items) {
			item.setDismissListener(this);
			itemsContainer.addView(item.getRoot());
		}
		dialog.show();
	}

	/**
	 * @return Created dialog with context menu.
	 * If you need some specific changes. NonNull only after {@link #show()}!
	 */
	@Nullable
	public Dialog getDialog() {
		return dialog;
	}

	/**
	 * Dismiss context menu.
	 */
	@Override
	public void dismissMenu() {
		dialog.dismiss();
	}

	/**
	 * @param context Context.
	 * @return {@link Builder} for {@link ContextMenuWidget}.
	 */
	public static Builder newBuilder(@NonNull Context context) {
		return new Builder(context);
	}

	/**
	 * Builder for {@link ContextMenuWidget}.
	 */
	@SuppressWarnings({ "UnusedReturnValue", "WeakerAccess" })
	public static class Builder {

		@NonNull
		private final Context context;
		@NonNull
		private final List<ContextMenuItemWidget> items = new ArrayList<>();

		@Nullable
		private CharSequence title;
		@ColorInt
		private int titleColor;
		private boolean isCanceledOnTouchOutside;


		private Builder(@NonNull Context context) {
			this.context = context;
			// Default values.
			titleColorRes(R.color.dialogs_dyv_black);
			isCanceledOnTouchOutside = true;
		}

		/**
		 * @param item Item, that will shown in dialog.
		 * @return {@link Builder} for {@link ContextMenuWidget}.
		 */
		@NonNull
		public Builder addItem(@NonNull ContextMenuItemWidget item) {
			items.add(item);
			return this;
		}

		/**
		 * @param title Title of the dialog. Optional parameter.
		 * @return {@link Builder} for {@link ContextMenuWidget}.
		 */
		@NonNull
		public Builder title(@StringRes int title) {
			return title(context.getString(title));
		}

		/**
		 * @param title Title of the dialog. Optional parameter.
		 * @return {@link Builder} for {@link ContextMenuWidget}.
		 */
		@NonNull
		public Builder title(@NonNull CharSequence title) {
			this.title = title;
			return this;
		}

		/**
		 * @param color Title text color. Optional parameter.
		 * @return {@link Builder} for {@link ContextMenuWidget}.
		 */
		@NonNull
		public Builder titleColorRes(@ColorRes int color) {
			return titleColor(context.getResources().getColor(color));
		}

		/**
		 * @param color Title text color. Optional parameter.
		 * @return {@link Builder} for {@link ContextMenuWidget}.
		 */
		@NonNull
		public Builder titleColor(@ColorInt int color) {
			titleColor = color;
			return this;
		}

		/**
		 * @param isCanceled If true - dialog can be canceled when user touch outside it.
		 * @return {@link Builder} for {@link ContextMenuWidget}.
		 */
		@NonNull
		public Builder isCanceledOnTouchOutside(boolean isCanceled) {
			isCanceledOnTouchOutside = isCanceled;
			return this;
		}

		/**
		 * @return {@link ContextMenuWidget} with set up parameters.
		 */
		@NonNull
		public ContextMenuWidget build() {
			return new ContextMenuWidget(
				context,
				items,
				title,
				titleColor,
				isCanceledOnTouchOutside
			);
		}
	}
}
