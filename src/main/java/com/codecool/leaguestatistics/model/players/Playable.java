package com.codecool.leaguestatistics.model.players;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class Playable {
    private int counter;

    public Playable() {
    }

    public Playable(int counter) {
        this.counter = counter;
    }

    public void setCounter() {
        if (counter >0) {
            this.counter--;
        }
    }
}
