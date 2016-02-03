package com.a2big.android.library.custom.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.a2big.android.library.R;
import com.a2big.android.library.utils.DevLog;

/**
 * Created by a2big on 16. 2. 3..
 */
public class CustomDialogClass extends Dialog  {
    public Activity c;
    public Dialog d;
    public Button yes, no,close;

    public CustomDialogClass(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        close = (Button)findViewById(R.id.btn_close);
        close.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                dismiss();
            }
        });

        yes = (Button)findViewById(R.id.btn_yes);
        yes.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                c.finish();
            }
        });

        no = (Button)findViewById(R.id.btn_no);
        no.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                chkGpsService();
                dismiss();
            }
        });
    }

    private boolean chkGpsService() {

        String gps = android.provider.Settings.Secure.getString(this.c.getContentResolver(),
                                    android.provider.Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
        DevLog.defaultLogging("check GpsService..");
        if (!(gps.matches(".*gps.*") && gps.matches(".*network.*"))) {
            // GPS설정 화면으로 이동
            Intent intent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            this.c.startActivity(intent);
            return false;
        } else {
            return true;
        }
    }
}
