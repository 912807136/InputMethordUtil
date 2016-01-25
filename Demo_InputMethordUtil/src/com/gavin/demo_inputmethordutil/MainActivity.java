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
