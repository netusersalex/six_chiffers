package Chiffers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Arrays;

public class ROT implements Encryptor {

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

        StringBuilder encryptedText = new StringBuilder();
        for (char c : text.toCharArray()) {
                int charIndex = alphabet.indexOf(c);
                encryptedText.append(key.charAt(charIndex));
        }
        return encryptedText.toString();
    }

    @Override
    public String decrypt(String encryptedText, String alphabet, String key) {
        StringBuilder decryptedText = new StringBuilder();
        for (char c : encryptedText.toCharArray()) {
                int charIndex = key.indexOf(c);
                decryptedText.append(alphabet.charAt(charIndex));

        }
        return decryptedText.toString();
    }
}

