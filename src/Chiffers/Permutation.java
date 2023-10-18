package Chiffers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.*;

public class Permutation implements Encryptor {


    @Override
    public String readFromFile(String fileName) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    @Override
    public void writeToFile(String fileName, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    @Override
    public String encrypt(String text, String alphabet, String key) {

        int n = key.length();
        int m = text.length();
        if (m % n != 0) {
            char lastChar = text.charAt(m - 1);
            int additionalChars = n - (m % n);
            for (int u = 0; u < additionalChars; u++) {
                text += lastChar;
                System.out.println("Значение текста изменено. Последний символ дублирован в конец");
            }
            m = text.length();
        }

        HashMap<Character, Integer> alphabetMap = new HashMap<>();
        HashMap<Character, Integer> keyMap = new HashMap<>();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < alphabet.length(); ++i) {
            alphabetMap.put(alphabet.charAt(i), i);
        }
        for (int i = 0; i < key.length(); ++i) {
            keyMap.put(key.charAt(i), i);
        }

        ArrayList<ArrayList<Character>> plaintextMatrix = new ArrayList<>();
        for (int i = 0; i < key.length(); ++i) {
            ArrayList<Character> temp = new ArrayList<>();
            temp.add(key.charAt(i));
            for (int j = i; j < text.length(); j += key.length()) {
                temp.add(text.charAt(j));
            }
            plaintextMatrix.add(temp);
        }

        plaintextMatrix.sort(Comparator.comparing(a -> alphabetMap.get(a.get(0))));
        for (int i = 1; i < plaintextMatrix.get(0).size(); ++i) {
            for (int j = 0; j < key.length(); ++j) {
                result.append(plaintextMatrix.get(j).get(i));
            }
        }
        return result.toString();
    }
    @Override
    public String decrypt(String encryptedText, String alphabet, String key) {

        int n = key.length();
        int m = encryptedText.length();
        if (m % n != 0) {
            char lastChar = encryptedText.charAt(m - 1);
            int additionalChars = n - (m % n);
            for (int u = 0; u < additionalChars; u++) {
                encryptedText += lastChar;
                System.out.println("Значение текста изменено. Последний символ дублирован в конец");
            }
            m = encryptedText.length();
        }

        HashMap<Character, Integer> alphabetMap = new HashMap<>();
        HashMap<Character, Integer> keyMap = new HashMap<>();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < alphabet.length(); ++i) {
            alphabetMap.put(alphabet.charAt(i), i);
        }
        for (int i = 0; i < key.length(); ++i) {
            keyMap.put(key.charAt(i), i);
        }


        // plaintextMatrix.sort(Comparator.comparing(a -> keyMap.get(a.get(0))));
        Character[] sortedKey = new Character[key.length()];
        for (int i = 0; i < key.length(); ++i) {
            sortedKey[i] = key.charAt(i);
        }
        Arrays.sort(sortedKey, Comparator.comparing((a) -> alphabetMap.get(a)));
        sortedKey.toString();

        ArrayList<ArrayList<Character>> plaintextMatrix = new ArrayList<>();
        for (int i = 0; i < key.length(); ++i) {
            ArrayList<Character> temp = new ArrayList<>();
            temp.add(sortedKey[i]);
            for (int j = i; j < encryptedText.length(); j += key.length()) {
                temp.add(encryptedText.charAt(j));
            }
            plaintextMatrix.add(temp);
        }
        int t = 0;
        plaintextMatrix.sort(Comparator.comparing(a -> keyMap.get(a.get(0))));
        for (int i = 1; i < plaintextMatrix.get(0).size(); ++i) {
            for (int j = 0; j < key.length(); ++j) {
                result.append(plaintextMatrix.get(j).get(i));
            }
        }
        return result.toString();

    }
}
