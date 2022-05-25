package com.example.travelmania.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class LocalSession {
    private SharedPreferences pref;
    private SharedPreferences.Editor prefEditor;

    public LocalSession(Context ctx){
        pref= PreferenceManager.getDefaultSharedPreferences(ctx);
        prefEditor= pref.edit();
    }

    public boolean getLoginStatus(){

        return pref.getBoolean(AppConstant.LOGIN_STATUS,false);
    }

    public void setLoginStatus(){
        prefEditor.putBoolean(AppConstant.LOGIN_STATUS,true);   //status=true
        prefEditor.commit();
    }

    public void logout(){
        //prefEditor.remove(AppConstant.LOGIN_STATUS);    //Delete particular information
        prefEditor.clear();     //entire clearance of the info present in shared pref related to the app
        prefEditor.commit();
    }
}
