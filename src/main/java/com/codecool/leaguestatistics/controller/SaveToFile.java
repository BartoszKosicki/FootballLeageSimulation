package com.codecool.leaguestatistics.controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class SaveToFile {
    public static void SaveToFile(String data, String pathFile) {
        try {
            FileWriter fw = new FileWriter(pathFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(data);
            bw.newLine();
            bw.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

