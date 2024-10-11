package dev.team.utils;

import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public class FileUtils {

    public static String getNameFile() {
        Random random = new Random();
        int i = random.nextInt(1, 20);
        return "moveResponse" + i + ".json";
    }
}
