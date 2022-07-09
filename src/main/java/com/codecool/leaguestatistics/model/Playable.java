package com.codecool.leaguestatistics.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Playable {
    private int counter=0;

    public void setCounter() {
        if (counter >0) {
            this.counter--;
        }
    }
}
