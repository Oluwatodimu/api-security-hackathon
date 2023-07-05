package io.todimu.compete.apisecurity.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class ValueGenerator {

    private static final int LENGTH = 7;

    private static final int MATRIC_NUMBER_LENGTH = 6;

    public static String generateActivationKey() {
        StringBuilder generatedToken = new StringBuilder();
        try {
            SecureRandom number = SecureRandom.getInstance("SHA1PRNG");
            for (int i = 0; i < LENGTH; i++) {
                generatedToken.append(number.nextInt(9));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedToken.toString();
    }

    public static String generateMatricNumber() {
        String chars = "0123456789";
        Random randomChar = new Random();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < MATRIC_NUMBER_LENGTH; i++) {
            stringBuilder.append(chars.charAt(randomChar.nextInt(chars.length())));
        }
        return stringBuilder.toString();
    }
}
