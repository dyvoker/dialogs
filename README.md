# dialogs
[![API](https://img.shields.io/badge/API-14%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=14)

Utils and classes for comfort work with android dialogs.

### Usage ([sample](https://github.com/dyvoker/dialogs/tree/master/sample))
```gradle
dependencies {
    implementation 'com.github.dyvoker:dialogs:0.1.0'
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
