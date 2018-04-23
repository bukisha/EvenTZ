package com.example.bookee.eventz.utils;

import android.content.Context;

public class ResourceManager {
    public static int getImage(Context context, String imageName) {
        int drawableResourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
        return drawableResourceId;
    }
}
