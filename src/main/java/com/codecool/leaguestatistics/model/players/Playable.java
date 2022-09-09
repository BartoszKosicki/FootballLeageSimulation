package com.codecool.leaguestatistics.model.players;

import lombok.Getter;

@Getter
public class Playable {
    private int counter;

    public Playable() {
    }

    public Playable(int counter) {
        this.counter = counter;
    }

    public void decreaseCounter() {
        if (counter >0) {
            this.counter--;
        }
    }
}
