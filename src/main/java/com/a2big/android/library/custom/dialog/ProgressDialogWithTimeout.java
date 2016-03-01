package com.a2big.android.library.custom.dialog;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.a2big.android.library.utils.DevLog;
import com.a2big.android.library.R;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by a2big on 15. 12. 29..
 */
public class ProgressDialogWithTimeout {


    private static Timer mTimer = new Timer();
    private static Context mContext;
    // private static ProgressDialog dialog;
    private static A2bigDialog mDialog;
    private static int mTimeout = 2000;

    private DialogTimeOutEventListener mListener;


    CallbackEvent callbackEvent;

    public ProgressDialogWithTimeout() {
        DevLog.defaultLogging("JH ProgressDialogWithTimeout.......");


        // TODO Auto-generated method stub
         callbackEvent = new CallbackEvent(){

            @Override
            public void callbackMethod() {
                // TODO Auto-generated method stub
                DevLog.defaultLogging("JH call callback method from callee.......");
                mTimer.cancel();

                mListener.onDialogTimeOut();
            }

        };


    }

    public A2bigDialog show(Context context, CharSequence title, CharSequence message, int timeout)
    {
        mTimeout = timeout;
        mContext = context;

        // Run task after 10 seconds
        MyTask task = new MyTask(callbackEvent);
        mTimer.schedule(task, 0, mTimeout);


        setMessage("Test");
        //dialog = ProgressDialog.show(context, title, message);
        return mDialog;
    }

    public  A2bigDialog show (Context context, CharSequence message, int timeout)
    {
        mTimeout = timeout;
        mContext = context;
        // Run task after 10 seconds
        MyTask task = new MyTask(callbackEvent);
        mTimer.schedule(task, 0, mTimeout);

        setMessage(message.toString());
        //dialog = ProgressDialog.show(context, title, message);
        return mDialog;
    }

    private static void setMessage(String pRes) {
        mDialog = new A2bigDialog(mContext, R.layout.progress_dialog_layout, null, 0);
        View view = mDialog.getParentView();
        mDialog.setCancelable(false);
        TextView progressContent = (TextView)view.findViewById(R.id.progressContent);
      //  progressContent.setText(R.string.app_name);
        progressContent.setText(pRes);
        mDialog.getButtonContainer().setVisibility(View.GONE);
        mDialog.show();
        mDialog.show();
      ////  timerDelayRemoveDialog(2000,mDialog);

    }

    public void close(){
        mDialog.dismiss();
    }
    public void setDialogEventListener(DialogTimeOutEventListener listener) {
        mListener = listener;
    }

    static class MyTask extends TimerTask  {
        private CallbackEvent callbackEvent;

        public MyTask(CallbackEvent event) {
            callbackEvent = event;

        }

        public void run() {
            DevLog.defaultLogging("MyTask TimeOut...");

            // Do what you wish here with the dialog
            if (mDialog != null)
            {
                mDialog.cancel();
                callbackEvent.callbackMethod();
            }
        }


    }



}
