package com.example.salchichonesdiego.LogIn;

import android.content.Context;
import android.content.Intent;

import com.example.salchichonesdiego.DataBase.SessionManager;

public class LogoutHelper {

    public static void logoutUser(Context context) {
        SessionManager sessionManager = SessionManager.getInstance(context);
        sessionManager.logoutUser();

        // Redirect back to LoginActivity
        Intent intent = new Intent(context, LogIn.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }
}
