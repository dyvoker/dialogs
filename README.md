# dialogs
[![API](https://img.shields.io/badge/API-14%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=14)

Utils and classes for comfort work with android dialogs.

### Usage ([sample](https://github.com/dyvoker/dialogs/tree/master/sample))
```gradle
dependencies {
    implementation 'com.github.dyvoker:dialogs:0.2.2'
}
```

# SimpleHintDialogWidget
<table style="width:100%">
  <tr>
    <th>
      <img src="https://raw.githubusercontent.com/dyvoker/dialogs/master/screen_simple.png" width="300">
    </th>
    <th>
      <img src="https://raw.githubusercontent.com/dyvoker/dialogs/master/screen_custom.png" width="300">
    </th>
  </tr>
</table>

Simple:
```java
SimpleHintDialogWidget
    .newBuilder(
        context,
        R.string.simple_hint_title,
        R.string.simple_hint_text
    ).build().show();
```

Custom:
```java
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
```

# ContextMenuWidget
<table style="width:100%">
  <tr>
    <th>
      <img src="https://raw.githubusercontent.com/dyvoker/dialogs/master/screen_simple_menu.png" width="300">
    </th>
    <th>
      <img src="https://raw.githubusercontent.com/dyvoker/dialogs/master/screen_custom_menu.png" width="300">
    </th>
  </tr>
</table>

Simple:
```java
ContextMenuWidget
    .newBuilder(context)
    .addItem(SimpleMenuItem.newBuilder(context, "First").build())
    .addItem(SimpleMenuItem.newBuilder(context, "Second").build())
    .addItem(SimpleMenuItem.newBuilder(context, "Third").build())
    .isCanceledOnTouchOutside(false)
    .build()
    .show();
```

Custom:
```java
IconMenuItem edit = IconMenuItem
    .newBuilder(context, R.string.edit, R.drawable.ic_edit)
    .clickListener(new ClickListener() {
        @Override
        public void onClick() {
            // Some action.
        }
    })
    .dismissOnClick(false)
    .build();
ContextMenuWidget
    .newBuilder(context)
    .title(R.string.some_menu_title)
    .addItem(edit)
    .addItem(IconMenuItem.newBuilder(context, R.string.remove, R.drawable.ic_remove).build())
    .build()
    .show();
```
