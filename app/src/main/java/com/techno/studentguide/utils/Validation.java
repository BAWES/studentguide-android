package com.techno.studentguide.utils;

import android.content.Context;
import android.text.TextUtils;

import com.techno.studentguide.R;

/**
 * Created by Android on 5/14/2016.
 */
public class Validation {
    private static Validation ourInstance = new Validation();

    public static Validation getInstance() {
        return ourInstance;
    }

    private Validation() {
    }

    public boolean contactUsValidation(Context mContext, String mName, String mContactNo, String mMessage) {

        if (TextUtils.isEmpty(mName)) {
            CommonFunctions.getInstance().showToast(mContext,mContext.getString(R.string.txt_name_required));
            return false;
        }
        else if (!mName.matches(Constants.NAME_REGEX)) {
            CommonFunctions.getInstance().showToast(mContext, mContext.getString(R.string.txt_name_pattern_error));
            return false;
        }
        if (TextUtils.isEmpty(mContactNo)) {
            CommonFunctions.getInstance().showToast(mContext,mContext.getString(R.string.txt_contact_no_required));
            return false;
        }
        else if (mContactNo.length() < 10) {
            CommonFunctions.getInstance().showToast(mContext, mContext.getString(R.string.txt_contact_no_invalid));
            return false;
        }
        if (TextUtils.isEmpty(mMessage)) {
            CommonFunctions.getInstance().showToast(mContext,mContext.getString(R.string.txt_message_required));
            return false;
        }
        return true;
    }

}
