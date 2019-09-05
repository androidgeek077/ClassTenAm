package app.infosys.threepmclassdemoapp;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;


public class PreferencesManager {

    private Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private static final String FIL_ENAME = "MyPreferences";
    private static final String USER_LOGIN_STATE = "login_state";
    private static final String USER_NAME = "user number";
    private static final String USER_NUMBER = "user password";
    private static final String USER_ID = "userNo";


    private static final int PRIVATE_MODE = 0;

    public PreferencesManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(FIL_ENAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void SetLogin(String uid, boolean isLoggedin) {

        editor.putBoolean(USER_LOGIN_STATE, isLoggedin);

        editor.putString(USER_ID, uid);
        editor.commit();
    }




    public boolean userIsLogged() {
        return sharedPreferences.getBoolean(USER_LOGIN_STATE, false);

    }
    public String getUserId(){
        return sharedPreferences.getString(USER_ID, "");

    }



    public void logoutUser(Context context) {
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);


    }
}

