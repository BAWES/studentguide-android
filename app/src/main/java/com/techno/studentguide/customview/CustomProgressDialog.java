package com.techno.studentguide.customview;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;

import dmax.dialog.SpotsDialog;

/**
 * Created by tech on 8/13/2015.
 */
public class CustomProgressDialog {
    private static CustomProgressDialog ourInstance = new CustomProgressDialog();
    ProgressDialog progress;
    AlertDialog mDialog;
    private boolean showing;

    public static CustomProgressDialog getInstance() {
        return ourInstance;
    }

    private CustomProgressDialog() {

    }

    public void show(Activity mContext, String Title,String Msg) {
        if(mDialog!=null)
            mDialog.dismiss();
        mDialog = new SpotsDialog(mContext);
        mDialog.setTitle(Title);
        mDialog.setMessage(Msg);
        mDialog.show();
        mDialog.setCancelable(false);


    }

    public void dismiss() {
        if(mDialog !=null)
            mDialog.dismiss();
    }
    public void ChangeMessage(String title, String msg)
    {
        mDialog.setMessage(msg);
        mDialog.setTitle(title);
    }

    public boolean isShowing() {
        if(mDialog !=null && mDialog.isShowing())
        return true;
        else return false;
    }
}
