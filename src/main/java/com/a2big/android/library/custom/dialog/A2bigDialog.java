package com.a2big.android.library.custom.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.a2big.android.library.R;


/**
 * a2bigDialog is template dialog for A2big application
 * It is easy to customize dialog border style, frame layout, title, icon etc
 *
 * @author manyeon
 */
public class A2bigDialog extends Dialog {
    private TextView mDialogTitle;
    private View mLine, mParentView;
    private ImageView mDialogIcon, mSeperator;
    private LinearLayout mTitleContainer, mButtonContainer;
    private TextView mConfirmBtn, mCancelBtn;
    private LinearLayout mFrame;

    public A2bigDialog(Context pContext, int pContentResId, int pTitle, int pTitleIcon) {
        this(pContext, R.style.creamDialogNoBorder, R.layout.dialog_message_frame, pContentResId, pTitle, pTitleIcon);
    }

    public A2bigDialog(Context pContext, int pContentResId, String pTitle, int pTitleIcon) {
        this(pContext, R.style.creamDialogNoBorder, R.layout.dialog_message_frame, pContentResId, pTitle, pTitleIcon);
    }

    public A2bigDialog(Context pContext, int pStyle, int pContentResId, int pTitle, int pTitleIcon) {
        this(pContext, pStyle, R.layout.dialog_message_frame, pContentResId, pTitle, pTitleIcon);
    }

    public A2bigDialog(Context pContext, int pStyle, int pContentResId, String pTitle, int pTitleIcon) {
        this(pContext, pStyle, R.layout.dialog_message_frame, pContentResId, pTitle, pTitleIcon);
    }

    public A2bigDialog(Context pContext, int pStyle, int pFrameResId, int pContentResId, int pTitle, int pTitleIcon) {
        super(pContext, pStyle);
        setContentView(pFrameResId);

        initRes(pContext, pContentResId);

        setTitle(pTitle);
        setIcon(pTitleIcon);
    }

    public A2bigDialog(Context pContext, int pStyle, int pFrameResId, int pContentResId, String pTitle, int pTitleIcon) {
        super(pContext, pStyle);
        setContentView(pFrameResId);

        initRes(pContext, pContentResId);

        setTitle(pTitle);
        setIcon(pTitleIcon);
    }


    /**
     * Get widget resources
     *
     * @param pContext
     * @param pContentResId
     */
    private void initRes(Context pContext, int pContentResId) {
        mFrame = (LinearLayout)findViewById(R.id.frame);
        mTitleContainer = (LinearLayout) findViewById(R.id.titleContainer);
        mButtonContainer = (LinearLayout) findViewById(R.id.buttonContainer);
        mDialogIcon = (ImageView) findViewById(R.id.dialog_icon);
        mSeperator = (ImageView) findViewById(R.id.topSeparator);
        mDialogTitle = (TextView) findViewById(R.id.dialog_title);
        mConfirmBtn = (TextView) findViewById(R.id.okBtn);
        mCancelBtn = (TextView) findViewById(R.id.cancelBtn);
        mLine = findViewById(R.id.topSeparator);

        LinearLayout contentView = (LinearLayout) findViewById(R.id.content);
        LayoutInflater inflate = (LayoutInflater) pContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mParentView = inflate.inflate(pContentResId, contentView);
    }

    public View getParentView(){
        return mParentView;
    }

    public View getButtonContainer(){
        return mButtonContainer;
    }

    public View getConfirmView(){
        return mConfirmBtn;
    }

    public View getCancelView(){
        return mCancelBtn;
    }

    public View getSeperator() {
        return mSeperator;
    }

    public void setConfirmButton(int pText){
        mConfirmBtn.setText(pText);
    }

    public void setCancelButton(int pText) {
        mCancelBtn.setText(pText);
    }

    public void setFrame(LayoutParams pParams) {
        mFrame.setLayoutParams(pParams);
    }

    @Override
    public void setTitle(CharSequence pTitle) {
        if (mDialogTitle != null) {
            if (pTitle == null || TextUtils.isEmpty(pTitle)) {
                mDialogTitle.setVisibility(View.GONE);
                hideTitleContainer();
            } else {
                mDialogTitle.setText(pTitle);
                mDialogTitle.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void setTitle(int pTitleResId) {
        if (pTitleResId > 0) {
            this.setTitle(getContext().getString(pTitleResId));
        } else {
            this.setTitle(null);
        }
    }

    public void setIcon(int pTitleIcon) {
        if (mDialogIcon != null) {
            if (pTitleIcon > 0) {
                mDialogIcon.setVisibility(View.VISIBLE);
                mDialogIcon.setImageResource(pTitleIcon);
            } else {
                mDialogIcon.setVisibility(View.GONE);
                hideTitleContainer();
                positionCenter(mDialogTitle);
            }

        }
    }

    private void hideTitleContainer() {
        if (mDialogTitle.getVisibility() == View.GONE && mDialogIcon.getVisibility() == View.GONE) {
            if (mLine != null) {
                mLine.setVisibility(View.GONE);
            }
            if (mTitleContainer != null) {
                mTitleContainer.setVisibility(View.GONE);
            }
        }
    }

    private void positionCenter(TextView pView){
        if(pView != null){
            pView.setGravity(Gravity.CENTER);
        }
    }


}
