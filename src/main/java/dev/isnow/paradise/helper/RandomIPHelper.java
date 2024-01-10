package dev.isnow.paradise.helper;

import java.util.Random;
public class RandomIPHelper {
    private static final Random random = new Random();
    public static String generateRandomIP() {
        return random.nextInt(256) + "." + random.nextInt(256) + "." + random.nextInt(256) + "." + random.nextInt(256);
    }
}
