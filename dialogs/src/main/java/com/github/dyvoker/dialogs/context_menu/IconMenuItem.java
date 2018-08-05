package com.github.dyvoker.dialogs.context_menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.dyvoker.dialogs.R;

/**
 * Implementation of the {@link ContextMenuItemWidget} with text and icon.
 */
@SuppressWarnings("unused")
public class IconMenuItem implements ContextMenuItemWidget {

	@NonNull
	private final View root;

	private DismissListener dismissListener;


	/**
	 * Private constructor. Use {@link Builder} instead.
	 */
	private IconMenuItem(
		@NonNull View root,
		@Nullable final ClickListener clickListener,
		final boolean dismissOnClick
	) {
		this.root = root;
		getRoot().setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (clickListener != null) {
					clickListener.onClick();
				}
				if (dismissOnClick && dismissListener != null) {
					dismissListener.dismissMenu();
				}
			}
		});
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setDismissListener(
		@NonNull DismissListener dismissListener
	) {
		this.dismissListener = dismissListener;
	}

	/**
	 * {@inheritDoc}
	 */
	@NonNull
	@Override
	public View getRoot() {
		return root;
	}

	/**
	 * @param context Context.
	 * @param text Caption on item.
	 * @param icon Icon for item.
	 * @return Builder for {@link SimpleMenuItem}.
	 */
	@NonNull
	public static Builder newBuilder(
		@NonNull Context context,
		@NonNull CharSequence text,
		@NonNull Drawable icon
	) {
		return new Builder(context, text, icon);
	}

	/**
	 *
	 * @param context Context.
	 * @param textId String id for item caption.
	 * @param iconId Drawable id for item icon.
	 * @return Builder for {@link SimpleMenuItem}.
	 */
	@NonNull
	public static Builder newBuilder(
		@NonNull Context context,
		@StringRes int textId,
		@DrawableRes int iconId
	) {
		return new Builder(
			context,
			context.getString(textId),
			context.getResources().getDrawable(iconId)
		);
	}


	/**
	 * Builder for {@link SimpleMenuItem}.
	 */
	@SuppressWarnings("unused")
	public static class Builder {

		@NonNull
		private final View root;
		@Nullable
		private ClickListener clickListener;
		private boolean dismissOnClick = true;

		private Builder(
			@NonNull Context context,
			@NonNull CharSequence text,
			@NonNull Drawable icon
		) {
			LayoutInflater inflater = LayoutInflater.from(context);
			root = inflater.inflate(R.layout.dialogs_dyv_icon_text_item, null, false);
			TextView textView = root.findViewById(R.id.dialogs_dyv_text);
			textView.setText(text);
			ImageView iconView = root.findViewById(R.id.dialogs_dyv_icon);
			iconView.setImageDrawable(icon);
		}

		/**
		 * @param clickListener Listener for clicks on root view of the item.
		 * @return {@link Builder} for {@link SimpleMenuItem}.
		 */
		@NonNull
		public Builder clickListener(@NonNull ClickListener clickListener) {
			this.clickListener = clickListener;
			return this;
		}

		/**
		 * @param dismissOnClick If true - all clicks on items will dismiss dialog. Default value - true.
		 * @return {@link Builder} for {@link SimpleMenuItem}.
		 */
		@NonNull
		public Builder dismissOnClick(boolean dismissOnClick) {
			this.dismissOnClick = dismissOnClick;
			return this;
		}

		/**
		 * @return {@link SimpleMenuItem} with set up parameters.
		 */
		@NonNull
		public IconMenuItem build() {
			return new IconMenuItem(
				root,
				clickListener,
				dismissOnClick
			);
		}
	}
}
