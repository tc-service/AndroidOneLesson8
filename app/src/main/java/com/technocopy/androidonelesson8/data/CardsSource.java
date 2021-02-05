package com.technocopy.androidonelesson8.data;

public interface CardsSource {
    CardData getCardData(int position);
    int size();

}
