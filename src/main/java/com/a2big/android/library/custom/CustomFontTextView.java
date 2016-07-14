package com.a2big.android.library.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;

public class CustomFontTextView extends EditText {

    public CustomFontTextView(Context a_context) {

        super(a_context);

    }

    public CustomFontTextView(Context a_context, AttributeSet a_attributeSet) {

        super(a_context, a_attributeSet);

    }

    public boolean onKeyPreIme(int keyCode, KeyEvent event) {


        if(event.getAction() == KeyEvent.ACTION_DOWN) {

            if(keyCode == KeyEvent.KEYCODE_BACK) {

                return true; // 사용자가 override한 함수 사용
            }
        }

        return super.onKeyPreIme(keyCode, event); // 시스템 default 함수 사용

    }

}
