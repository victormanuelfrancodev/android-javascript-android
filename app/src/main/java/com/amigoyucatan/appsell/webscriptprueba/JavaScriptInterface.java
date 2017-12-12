package com.amigoyucatan.appsell.webscriptprueba;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by victor.lagunas on 05/12/17.
 */

public class JavaScriptInterface {


        Context mContext;

        /** Instantiate the interface and set the context */
        JavaScriptInterface(Context c) {
            mContext = c;
        }

        @android.webkit.JavascriptInterface
        public void textFromWeb(String informacion)
        {
            Toast toast = Toast.makeText(mContext,
                    informacion, Toast.LENGTH_SHORT);
            toast.show();
        }

}
