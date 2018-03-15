package util;

import java.io.*;

/**
 * Created by burak on 2/24/2018.
 */
public class RandomStringGenerator {
    static String[] words;
    static {
        try {
            FileInputStream fileIs = new FileInputStream("src\\main\\resources\\words.bin");
            ObjectInputStream is = new ObjectInputStream(fileIs);
            words = (String[])is.readObject();
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getSentence(int wordCount){
        StringBuilder sb = new StringBuilder();
        int wordIndex;
        for (int i = 0; i < wordCount; i++){
            wordIndex = ((int)(Math.random() * words.length));
            sb.append(words[wordIndex].trim()).append(' ');
        }
        return sb.toString().trim();
    }

    public static void main(String args[]){
        int wordCount = 3;
        for (int i = 0; i < 100; i++){
            System.out.println(getSentence(wordCount));
        }
    }
}
