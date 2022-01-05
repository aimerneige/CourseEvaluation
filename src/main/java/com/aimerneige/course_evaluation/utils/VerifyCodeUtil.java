package com.aimerneige.course_evaluation.utils;

import java.util.Random;

public class VerifyCodeUtil {

    private static final Random random = new Random();

    private VerifyCodeUtil() {
    }

    public static String generateVerifyCode(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

}
