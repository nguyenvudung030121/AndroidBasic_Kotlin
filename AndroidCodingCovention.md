# **Coding Convention**
---
## **Table of content**
 [**Coding Convention**](#coding-convention)
- [**Coding Convention**](#coding-convention)
  - [**Table of content**](#table-of-content)
  - [**Who use this?**](#who-use-this)
  - [**What is this repository for?**](#what-is-this-repository-for)
  - [**File Naming**](#file-naming)
    - [I. Class files \& Layout files](#i-class-files--layout-files)
    - [II. Resources files](#ii-resources-files)
      - [1. Layout resource IDs](#1-layout-resource-ids)
      - [2. Drawable files](#2-drawable-files)
      - [3. Icons](#3-icons)
      - [4. Selector states](#4-selector-states)
      - [5. Values files](#5-values-files)
      - [6. Packages](#6-packages)
    - [III. Layout files structure](#iii-layout-files-structure)
  - [**Kotlin Language Rules**](#kotlin-language-rules)
    - [I. Coding Style Rules](#i-coding-style-rules)
      - [1. Naming Conventions](#1-naming-conventions)
      - [2. Indentation and Spacing](#2-indentation-and-spacing)
      - [3. Braces](#3-braces)
      - [4. Null safety](#4-null-safety)
      - [5. Imports \& Package](#5-imports--package)
    - [II. Treat Acronyms as Words](#ii-treat-acronyms-as-words)
    - [III. Use TODO comment](#iii-use-todo-comment)
    - [IV. How to Log](#iv-how-to-log)
    - [V. String constants, naming, and values](#v-string-constants-naming-and-values)
  - [**XML style**](#xml-style)
    - [I. Naming](#i-naming)
    - [II. Indentation](#ii-indentation)
    - [III. Strings](#iii-strings)
    - [IV. Self – Closing Tag](#iv-self--closing-tag)
    - [V. Comments](#v-comments)
  - [**References**](#references)

---

## **Who use this?**

- This document use for Android team.

## **What is this repository for?**

- This repository serves as a reference guide for the Android development team to maintain consistent coding practices, specifically tailored for Kotlin programming language, XML layout files, and the treatment of acronyms within the codebase.

---

## **File Naming**

### I. Class files & Layout files

- Class names are written in `UpperCamelCase`.
- Layout names are written in `lowercase_underscore`.
- For classes that extend an Android component, the name of the class should end with the name of the component.
- Layout files should match the name of the Android components that they are intended for but moving the top-level component name to the beginning.

| **Component**      | **Class Name**         | **Layout Name**              |
| ------------------ | ---------------------- | ---------------------------- |
| `Activity`         | `UserProfileActivity`  | `activity_user_profile.xml`  |
| `Fragment`         | `SignUpFragment`       | `fragment_sign_up.xml`       |
| `Dialog`           | `ChangePasswordDialog` | `dialog_change_password.xml` |
| `Adapter`          | `ChatAdapter`          | ---                          |
| `AdapterView item` | ---                    | `item_person.xml `           |

### II. Resources files

- Resources file names are written in `lowercase_underscore`.

#### 1. Layout resource IDs

| **Component**  | **Prefix** | **Example**             |
| -------------- | ---------- | ----------------------- |
| `Button`       | `btn_ `    | `btn_signup_dialog.xml` |
| `TextView`     | `tv_ `     | `tv_user_status.xml`    |
| `EditText`     | `edt_`     | `edt_password.xml`      |
| `ImageView`    | `img_`     | `img_top_logo.xml`      |
| `ListView`     | `lv_`      | `lv_messages.xml`       |
| `RecyclerView` | `rv_`      | `rv_chat.xml`           |
| `Checkbox`     | `chk_`     | `chk_remember_me.xml`   |
| `ProgressBar`  | `pb_ `     | `pb_upload_percent.xml` |
| `RadioButton`  | `rb_ `     | `rb_female.xml`         |
| `ToggleButton` | `tb_`      | `tb_visibility.xml`     |
| `Spinner`      | `spn_ `    | `spn_category.xml`      |
| `Menu`         | `menu_ `   | `menu_country.xml`      |
| `WebView`      | `wv_`      | `wv_preview.xml`        |

#### 2. Drawable files

| **Asset Type** | **Prefix**      | **Example**             |
| -------------- | --------------- | ----------------------- |
| `Tabs`         | `tab_`          | tab\_pressed.png        |
| `Icon`         | `ic_`           | ic\_star.png            |
| `Menu`         | `menu_`         | menu\_submenu\_bg.png   |
| `Button`       | `btn_`          | btn\_send\_pressed.png  |
| `Dialog`       | `dialog_`       | dialog\_top.png         |
| `Divider`      | `divider_`      | divider\_horizontal.png |
| `Action bar`   | `ab_`           | ab\_stacked.png         |
| `Notification` | `notification_` | notification\_bg.png    |

#### 3. Icons

| **Asset Type**                    | **Prefix**       | **Example**                |
| --------------------------------- | ---------------- | -------------------------- |
| `Icons`                           | `ic_`            | ic\_star.png               |
| `Launcher icons`                  | `ic_launcher`    | ic\_launcher\_calendar.png |
| `Menu icons and Action Bar icons` | `ic_menu`        | ic\_menu\_archive.png      |
| `Status bar icons`                | `ic_stat_notify` | ic\_stat\_notify\_msg.png  |
| `Tab icons`                       | `ic_tab`         | ic\_tab\_recent.png        |
| `Dialog icons`                    | `ic_dialog`      | ic\_dialog\_info.png       |

#### 4. Selector states

| **State**  | **Suffix**  | **Example**              |
| ---------- | ----------- | ------------------------ |
| `Normal`   | `_normal`   | `btn_order_normal.xml`   |
| `Pressed`  | `_pressed`  | `btn_order_pressed.xml`  |
| `Focused`  | `_focused`  | `btn_order_focused.xml`  |
| `Disabled` | `_disabled` | `btn_order_disabled.xml` |
| `Selected` | `_selected` | `btn_order_selected.xml` |

#### 5. Values files

- Resource files in the values folder should be plural, e.g: **strings.xml, styles.xml, colors.xml, dimens.xml, attrs.xml**.

#### 6. Packages
- Names of packages are always **lowercase** and do not use **underscores**.
```c
// Okay
package com.example.deepspace
// WRONG!
package com.example.deepSpace
// WRONG!
package com.example.deep_space
```

### III. Layout files structure

![](RackMultipart20230906-1-ez8jlp_html_a993ba108cd95536.png)

---

## **Kotlin Language Rules**

### I. Coding Style Rules

- When writing Kotlin code, adhere to the following style guidelines:

#### 1. Naming Conventions

- Use  `camelCase`  for variable and function names.
- Use  `PascalCase`  for class and interface names.
- Use `ALL_CAPS_WITH_UNDERSCORES` for constant values (e.g., `MAX_COUNT` ).
- The name of a class is usually a noun or a noun phrase explaining what the class _is_:  `List` ,  `PersonReader`.
- The name of a method is usually a verb or a verb phrase saying what the method _does_:  `show` ,  `readPersons`.

#### 2. Indentation and Spacing

- Use 4 spaces for indentation (1 tab).
- Use 8 space indents for line wraps.
- Keep lines no longer than 100 characters. Except as noted below, any line that would exceed this limit must be line-wrapped, as explained below:
  - Lines where obeying the column limit is not possible (for example, a long URL).
  - Package and import statements.
- Use a space after keywords ( `if` ,  `for` ,  `while` , etc.) and before opening parentheses.
- Never put a space after `(`, `[`, or before `]`, `)`.
- Neverput a space around  `.`  or  `?`.

#### 3. Braces

- Use the Java-style braces for control structures:

***This is Bad***:
```c
class myClass1
{
    fun myFunction()
    {
        //Code here
    }
}
```
***This is good:***
```c
class MyClass {
    fun myFunction() {
        if () {
            //Code here
        } else {
            //Code here
        }
    }
}
```


#### 4. Null safety

- Utilize nullable types and the safe call operator (`?.`) for null safety.

#### 5. Imports & Package

- Keep imports organized and grouped.
- Avoid using wildcard imports (`import package.*`).
- Names of packages are always lowercase and do not use **underscores**.

### II. Treat Acronyms as Words

Naming varibales, methods, classes like this:

| Good             | Bad              |
| ---------------- | ---------------- |
| `XmlHttpRequest` | `XMLHTTPRequest` |
| `getCustomerId`  | `getCustomerID`  |
| `String url`     | `String URL`     |
| `long id`        | `long ID`        |

### III. Use TODO comment

Use TODO comments for code that is temporary, a short-term solution, or good enough but not perfect. These comments should include the string TODO in all caps, followed by a colon:

- `// TODO: 08/22/23 remove below logcat later, just for testing purpose`

 ***Should not:***

- `// todo update later`

### IV. How to Log

**Log** is a class that print out error messages to help developer to identify problems:

- `Log.v(tag: String, msg: String)` (verbose)
- `Log.d(tag: String, msg: String)` (debug)
- `Log.i(tag: String, msg: String)` (information)
- `Log.w(tag: String, msg: String)` (warning)
- `Log.e(tag: String, msg: String)` (error)

As a general rule, we need to use class name as a **TAG** at each file :
```c
public class MyClass {
    companion object {
           private const val TAG = "MyClass"
       }

    fun myMethod() {
        Log.e(TAG, "Error message");
    }
}
```

### V. String constants, naming, and values

- Many elements of the Android such as **SharedPreferences** , **Bundle** or **Intent** use a key-value pair.
- When using one of these components, you must define the keys as a `const val` and it must be preceded by an **underscore**:

  | Element            | Field Name Prefix |
  | ------------------ | ----------------- |
  | SharedPreferences  | `PREF_`           |
  | Bundle             | `BUNDLE_`         |
  | Fragment Arguments | `ARGUMENT_`       |
  | Intent Extra       | `EXTRA_`          |
  | Intent Action      | `ACTION_`         |

*For example:*

```c
// Value of the field is the same as the name to avoid duplication
public const val PREF_NAME = "PREF_NAME"
public const val BUNDLE_NAME = "BUNDLE_NAME"
public const val ARGUMENT_NAME = "ARGUMENT_NAME"

// Intent should use full package name as value
public const val EXTRA_SURNAME = "com.myapp.extras.EXTRA_SURNAME"
public const val ACTION_OPEN_USER = "com.myapp.action.ACTION_OPEN_USER"
```
---

## **XML style**

### I. Naming

- Resource IDs are written in `camelCase`.
- IDs should be an abbreviation of the element's name.

*For example:*

| **Element** | **Prefix** |
| ----------- | ---------- |
| `Button`    | `btn`      |
| `TextView`  | `tv`       |
| `EditText`  | `edt`      |
| `ImageView` | `img`      |

### II. Indentation

- Use four spaces for indentation.
- Maintain a consistent structure throughout the XML file.

### III. Strings

String names start with a prefix that identifies the section they belong to.

| **Prefix** | **Description**                      |
| ---------- | ------------------------------------ |
| `msg_ `    | A regular information message        |
| `error_`   | An error message                     |
| `title_`   | A title, i.e., a dialog title        |
| `action_`  | An action such as "Save" or "Create" |

*For example:*

***This is Good:***
```c
<string name="error_message_network">Network error</string>
<string name="error_message_call">Call failed</string>
<string name="error_message_map">Map loading failed</string>
```

***This is Bad:***
```c
<string name="network_error">Network error</string>
<string name="call_failed">Call failed</string>
<string name="map_failed">Map loading failed</string>
```

### IV. Self – Closing Tag

When an XML element doesn't have any contents, you must use self closing tags.

***This is good:***

```c
<TextView
    android:id="@+id/tvProfile"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"/>
```

***This is bad:***

```c
<TextView
	android:id="@+id/text_view_profile"
	android:layout_width="wrap_content" 
	android:layout_height="wrap_content" 
</TextView>
```
### V. Comments

- Include comments for complex layouts or sections to explain the purpose or behavior of the XML.


<br />

> **_By following these guidelines, the Android development team can ensure code consistency, readability, and maintainability across the project. Please refer to this guide when making code contributions or reviewing pull requests._**


---

## **References**

- [Coding conventions | Kotlin Documentation (kotlinlang.org)](https://kotlinlang.org/docs/coding-conventions.html)
- [Kotlin style guide  |  Android Developers](https://developer.android.com/kotlin/style-guide)
- [android-guidelines/project\_and\_code\_guidelines.md at master · ribot/android-guidelines (github.com)](https://github.com/ribot/android-guidelines/blob/master/project_and_code_guidelines.md)
- [mastani/android-convention-cheatsheet: Android Naming Convention Cheat Sheet (github.com)](https://github.com/mastani/android-convention-cheatsheet)
- [Android Coding Style and Guidelines - GeeksforGeeks](https://www.geeksforgeeks.org/android-coding-style-and-guidelines/)



---