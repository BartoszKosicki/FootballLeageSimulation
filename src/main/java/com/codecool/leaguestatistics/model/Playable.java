package com.codecool.leaguestatistics.model;

import lombok.Getter;

@Getter
public class Playable {
    private int counter=0;

    public Playable(int counter) {
        this.counter = counter;
    }

    public void setCounter() {
        if (counter >0) {
            this.counter--;
        }
    }
}
