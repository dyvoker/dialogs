package com.github.dyvoker.dialogs.context_menu;

import android.support.annotation.NonNull;
import android.view.View;

/**
 * Item for {@link ContextMenuWidget}.
 */
public interface ContextMenuItemWidget {

	/**
	 * @param dismissListener This listener can dismiss context menu, when it needs.
	 */
	void setDismissListener(@NonNull DismissListener dismissListener);

	/**
	 * @return Root view of the item.
	 */
	@NonNull View getRoot();


	/**
	 * Listener for dismissing context menu.
	 */
	interface DismissListener {
		void dismissMenu();
	}
}
