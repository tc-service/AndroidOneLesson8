package com.technocopy.androidonelesson8.data;

import com.technocopy.androidonelesson8.R;

import java.util.Random;

public class PictureIndexConverter {

    private static Random rnd = new Random();
    private static Object syncObj = new Object();

    private static int[] picIndex = {
            R.drawable.petropavl_krep,
            R.drawable.hermitage,
            R.drawable.kazansk_sobor,
            R.drawable.letny_sad,
            R.drawable.isak_sobor,
            R.drawable.save_on_blood,
    };

    public static int randomPictureIndex(){
        synchronized (syncObj){
            return rnd.nextInt(picIndex.length);
        }
    }

    public static int getPictureByIndex(int index){
        if (index < 0 || index >= picIndex.length){
            index = 0;
        }
        return picIndex[index];
    }

    public static int getIndexByPicture(int picture){
        for(int i = 0; i < picIndex.length; i++){
            if (picIndex[i] == picture){
                return i;
            }
        }
        return 0;
    }
}
