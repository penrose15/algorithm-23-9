package org.example.pgm;

import java.util.Arrays;

public class pgm_42577_hsj {
    public boolean solution(String[] phone_book) {

        Arrays.sort(phone_book);

        for(int i = 0; i< phone_book.length-1; i++) {
            String first = phone_book[i];
            String last = phone_book[i+1];

            if(first.length() < last.length()) {
                String pre = last.substring(0, first.length());
                if(first.equals(pre)) {
                    return false;
                }

            }
        }
        return true;

    }
}
