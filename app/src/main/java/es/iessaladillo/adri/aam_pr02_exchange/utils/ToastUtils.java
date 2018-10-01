package es.iessaladillo.adri.aam_pr02_exchange.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {

    private ToastUtils() {
    }

    public static void ToastMessage(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
