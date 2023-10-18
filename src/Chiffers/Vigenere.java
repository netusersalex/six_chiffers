package Chiffers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.sql.SQLOutput;
import java.util.Arrays;
public class Vigenere implements Encryptor {


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

        int[] key_indexes = new int[key.length()];
        int[] text_indexes =new int[text.length()];
        int i = 0;

        for (char c : key.toCharArray()) {

            key_indexes[i] = alphabet.indexOf(c);
            i++;
            if (i > key.length()) break;
        }
        int t = 0;
        for (char c : text.toCharArray()) {
            text_indexes[t] = alphabet.indexOf(c);
            t++;
            if (t > text.length()) break;
        }

        int[] array_key_indexes = new int[text.length()];
        int len = 0;
        int j = 0;

        while (len < text.length()){
            if(j > key.length()-1){
                j=0;
            }
            array_key_indexes[len] = key_indexes[j];
            j++;
            len++;
        }
        int[] encrypted_indexes = new int[text.length()];
        for (int k = 0; k < text.length(); k++){
            encrypted_indexes[k] = (text_indexes[k] + array_key_indexes[k])%(alphabet.length());
            encryptedText.append(alphabet.charAt(encrypted_indexes[k]));
        }
        return encryptedText.toString();
    }

    @Override
    public String decrypt(String encryptedText, String alphabet, String key) {
        StringBuilder decryptedText = new StringBuilder();
        int[] key_indexes = new int[key.length()];
        int[] encrypted_text_indexes =new int[encryptedText.length()];
        int i = 0;

        for (char c : key.toCharArray()) {

            key_indexes[i] = alphabet.indexOf(c);
            i++;
            if (i > key.length()) break;
        }
        int t = 0;
        for (char c : encryptedText.toCharArray()) {
            encrypted_text_indexes[t] = alphabet.indexOf(c);
            t++;
            if (t > encryptedText.length()) break;
        }

        int[] array_key_indexes = new int[encryptedText.length()];
        int len = 0;
        int j = 0;

        while (len < encryptedText.length()){
            if(j > key.length()-1){
                j=0;
            }
            array_key_indexes[len] = key_indexes[j];
            j++;
            len++;
        }
        int[] decrypted_indexes = new int[encryptedText.length()];
        for (int k = 0; k < encryptedText.length(); k++){
            int diff = encrypted_text_indexes[k] - array_key_indexes[k];
            while(diff < 0){
                diff += (alphabet.length());
            }
            decrypted_indexes[k] = diff%(alphabet.length());
            decryptedText.append(alphabet.charAt(decrypted_indexes[k]));
        }
        return decryptedText.toString();
    }
}

