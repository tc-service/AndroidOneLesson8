package com.technocopy.androidonelesson8.observe;

import android.os.Parcel;
import android.os.Parcelable;

import com.technocopy.androidonelesson8.data.CardData;

import java.util.Date;

public interface Observer {
    void updateCardData(CardData cardData);
}