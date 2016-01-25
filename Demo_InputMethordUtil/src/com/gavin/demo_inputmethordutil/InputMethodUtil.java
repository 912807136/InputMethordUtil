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
	 * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
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
