package com.a2big.android.library.custom.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.a2big.android.library.R;
import com.a2big.android.library.custom.adapter.AppAdapter;
import com.a2big.android.library.custom.adapter.ShareApp;
import com.a2big.android.library.custom.inteface.ShareDialogConnector;
import com.a2big.android.library.utils.DevLog;

import java.util.ArrayList;

/**
 * Created by a2big on 16. 2. 24..
 */
public class CustomShareDialog {

   // private Activity mActivity;
    Context mContext;
    private String name;
    AppAdapter adapter=null;
    private ShareDialogConnector mEventListener;

    public CustomShareDialog(Context context){
       // this.mActivity = a;
       this.mContext = context;
    }

    public void show() {
        // TODO Auto-generated method stub
        final Dialog dialog = new Dialog(this.mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams WMLP = dialog.getWindow().getAttributes();
        WMLP.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(WMLP);
        dialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(R.layout.about_dialog);
        dialog.setCancelable(true);
        ListView lv=(ListView)dialog.findViewById(R.id.listView1);

/*
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{"velmurugan@androidtoppers.com"});
        email.putExtra(Intent.EXTRA_SUBJECT, "Hi");
        email.putExtra(Intent.EXTRA_TEXT, "Hi,This is Test");
        email.setType("text/plain");
*/
        adapter=new AppAdapter(this.mContext,R.layout.share_dialog_row,initDialog());

        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {

                DevLog.defaultLogging("itemClick.." + position);
                if(mEventListener!=null)
                    mEventListener.onReceivedMessage(position);

                //feed();
            }
        });
        dialog.show();
    }


    private ArrayList<ShareApp> initDialog(){
        ArrayList<ShareApp> m_orders = new ArrayList<ShareApp>();

        ShareApp p1 = new ShareApp(R.drawable.email, "이메일");
        ShareApp p2 = new ShareApp(R.drawable.kakao, "카카오톡");
        ShareApp p3 = new ShareApp(R.drawable.sms, "문자");

        m_orders.add(p1);
        m_orders.add(p2);
        m_orders.add(p3);
        return m_orders;
    }


    public void setOnReceivedEvent(ShareDialogConnector listener){
        mEventListener = listener;
    }



}
