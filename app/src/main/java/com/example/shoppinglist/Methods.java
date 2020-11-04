package com.example.shoppinglist;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Window;
import android.widget.Toast;

public class Methods
{
    static void Toast(String message, Context context)
    {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
    public static void onAttachedWindows(Activity myActivityReference)
    {
        myActivityReference.onAttachedToWindow();
        Window window = myActivityReference.getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }

}

