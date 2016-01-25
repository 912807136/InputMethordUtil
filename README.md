# InputMethordUtil
实现 隐藏和显示输入法，点击输入法之外区域隐藏输入法<br>
通过重写dispatchTouchEvent方法，判断触摸的点是否在editText范围内，如果不是则隐藏输入法

我这里写的工具类实现这个功能

在Activity的代码如下：

```java
package com.gavin.demo_inputmethordutil;


import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.EditText;

public class MainActivity extends Activity {

	private InputMethodUtil inputMethodUtil;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initView();
	}

	private void initView() {
		EditText editText1 = (EditText) findViewById(R.id.editText1);
		inputMethodUtil = new InputMethodUtil(this, editText1);
		
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		inputMethodUtil.dispatchTouchEvent(ev);
		return super.dispatchTouchEvent(ev);
	}
}
```

<br>
<br>
 工具类InputMethodUtil：

```java
package com.gavin.demo_inputmethordutil;

import android.content.Context;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class InputMethodUtil {
	private EditText editText;
	private InputMethodManager imm;

	public InputMethodUtil(Context context, EditText editText) {
		this.editText = editText;
		imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
	}

	public void show() {
		editText.setFocusable(true);
		editText.setFocusableInTouchMode(true);
		editText.requestFocus();
		imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
	}

	public void hide() {
		imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
	}

	public void dispatchTouchEvent(MotionEvent ev) {
		if (ev.getAction() == MotionEvent.ACTION_DOWN) {
			View v = editText;
			if (!isInEditText(v, ev)) {
				hide();
			}
		}
	}  

	/**
	 * 判断触摸的点是否在EditText范围内
	 */
	private boolean isInEditText(View v, MotionEvent event) {
		if (v != null) {// && (v instanceof EditText)
			int[] l = { 0, 0 };
			v.getLocationInWindow(l);
			int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left + v.getWidth();
			float eventX = event.getX();
			float eventY = event.getY();
			Rect rect = new Rect(left, top, right, bottom);
			return rect.contains((int) eventX, (int) eventY);
		}
		return true;
	}

}
```
