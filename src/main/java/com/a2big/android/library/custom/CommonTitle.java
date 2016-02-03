package com.a2big.android.library.custom;

/**
 * Created by a2big on 15. 6. 8..
 */

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a2big.android.library.custom.inteface.TitleConnector;
import com.a2big.android.library.utils.DevLog;
import com.a2big.android.library.R;

public class CommonTitle extends LinearLayout {
    Context mContext = null;
    ImageView mImageView;
    TextView mActionbarText;
    Button mBtnLeft, mBtnRight1, mBtnRight2;
    private TitleConnector mEventListener;
    private static final String androidns = "http://schemas.android.com/apk/res/android";

    /** Called when the activity is first created. */
    public CommonTitle(Context context, AttributeSet attrs) {
        super(context, attrs);

        String titlename  = attrs.getAttributeValue(androidns, "text");
        DevLog.defaultLogging("CommonTitle...." + titlename);

        initcustomview(context,titlename);
    }

    public CommonTitle(Context context) {
        super(context);
        initcustomview(context, null);
    }

    public void  setLeftButtonBackgroun(int resource){
        mBtnLeft.setBackgroundResource(resource);

    }


    public void setLeftButtonVisible(boolean flag){
        if( flag == true)
            mBtnLeft.setVisibility(View.VISIBLE);
        else
            mBtnLeft.setVisibility(View.INVISIBLE);
    }

    public void  setRight1ButtonBackgroun(int resource){
        mBtnRight1.setBackgroundResource(resource);

    }

    public void setRight1ButtonVisible(boolean flag){
        if( flag == true)
            mBtnRight1.setVisibility(View.VISIBLE);
        else
            mBtnRight1.setVisibility(View.INVISIBLE);
    }

    public void  setRight2ButtonBackgroun(int resource) {
        mBtnRight2.setBackgroundResource(resource);
    }

    public void setRight2ButtonVisible(boolean flag){
        if( flag == true)
            mBtnRight2.setVisibility(View.VISIBLE);
        else
            mBtnRight2.setVisibility(View.INVISIBLE);
    }


    public void setLogoVisible(boolean flag){
        if( flag == true)
            mImageView.setVisibility(View.VISIBLE);
        else
            mImageView.setVisibility(View.INVISIBLE);
    }

    public void setTextVisible(boolean flag){
        if( flag == true)
            mActionbarText.setVisibility(View.VISIBLE);
        else
            mActionbarText.setVisibility(View.INVISIBLE);
    }

    public void  setTitleBackgroun(int resource){
        mImageView.setBackgroundResource(resource);
    }

    void initcustomview(Context context,String titlename) {
        mContext = context;

        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);

        View v = li.inflate(R.layout.common_title, this, false);
        addView(v);

        mImageView = (ImageView) findViewById(R.id.imageLogo);
        mActionbarText = (TextView)findViewById(R.id.action_bar_text);
        if(titlename != null){
            mActionbarText.setText(titlename);
        }

        mBtnLeft = (Button) findViewById(R.id.btnLeft);
        mBtnLeft.setOnClickListener(new OnClickListener(){
            public void onClick(View v){
                if(mEventListener!=null)
                    mEventListener.onReceivedLeft1();
            }
        });

        mBtnRight1 = (Button) findViewById(R.id.btnRight1);
        mBtnRight1.setOnClickListener(new OnClickListener(){
            public void onClick(View v){
                if(mEventListener!=null)
                    mEventListener.onReceivedRight1();
            }
        });

        mBtnRight2 = (Button) findViewById(R.id.btnRight2);
        mBtnRight2.setOnClickListener(new OnClickListener(){
            public void onClick(View v){
                if(mEventListener!=null)
                    mEventListener.onReceivedRight2();
            }
        });

    }

    public void setOnReceivedEvent(TitleConnector listener){
        mEventListener = listener;
    }

}