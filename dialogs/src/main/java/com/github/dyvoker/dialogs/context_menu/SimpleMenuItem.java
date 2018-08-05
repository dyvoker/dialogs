package com.github.dyvoker.dialogs.context_menu;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.github.dyvoker.dialogs.R;

/**
 * Simple implementation of the {@link ContextMenuItemWidget}.
 */
@SuppressWarnings("unused")
public class SimpleMenuItem implements ContextMenuItemWidget {

	@NonNull
	private final View root;

	private DismissListener dismissListener;

	/**
	 * Private constructor. Use {@link Builder} instead.
	 */
	private SimpleMenuItem(
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
	 * Default builder. Using {@link TextView} for showing text.
	 *
	 * @param context Context.
	 * @param text Caption on item.
	 * @return Builder for {@link SimpleMenuItem}.
	 */
	@NonNull
	public static Builder newBuilder(
		@NonNull Context context,
		@NonNull CharSequence text
	) {
		return new Builder(context, text);
	}

	/**
	 * @param root Root view of the item.
	 * @return Builder for {@link SimpleMenuItem}.
	 */
	@NonNull
	public static Builder newBuilder(
		@NonNull View root
	) {
		return new Builder(root);
	}

	/**
	 * @param context Context.
	 * @param layoutId Id of layout for item.
	 * @return Builder for {@link SimpleMenuItem}.
	 */
	@NonNull
	public static Builder newBuilder(
		@NonNull Context context,
		@LayoutRes int layoutId
	) {
		return new Builder(context, layoutId);
	}

	/**
	 * Listener for clicks on items root view.
	 */
	public interface ClickListener {
		void onClick();
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

		private Builder(@NonNull Context context, @NonNull CharSequence text) {
			LayoutInflater inflater = LayoutInflater.from(context);
			root = inflater.inflate(R.layout.dialogs_dyv_text_item, null, false);
			TextView textView = root.findViewById(R.id.dialogs_dyv_text);
			textView.setText(text);
		}

		private Builder(@NonNull View root) {
			this.root = root;
		}

		private Builder(@NonNull Context context, @LayoutRes int layoutId) {
			LayoutInflater inflater = LayoutInflater.from(context);
			root = inflater.inflate(layoutId, null, false);
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
		public SimpleMenuItem build() {
			return new SimpleMenuItem(
				root,
				clickListener,
				dismissOnClick
			);
		}
	}
}
