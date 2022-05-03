package com.example.demo.until;

import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author Buer
 */
@Component
public class KeyEncode {
    public StringBuffer keyEncode(StringBuffer stringBuffer) {
        Random random = new Random();
        stringBuffer.append("cxzye");
        for (int i = 6; i < 9; i++) {
            stringBuffer.deleteCharAt(i);
        }
        for (int j = 1; j < 4; j++) {
            int i = random.nextInt(10) % 10 + 1;
            stringBuffer.insert(j, i);
        }
        stringBuffer.reverse();
        return stringBuffer;
    }
}
