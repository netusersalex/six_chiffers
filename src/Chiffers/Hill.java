package Chiffers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Hill implements Encryptor {


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
    public int inverseInt(int d, int m) {
        int inverse = 0;

        while(d > m){
            d=d-m;
        }
        while(d<0){
            d=d+m;
        }
        for (int i = 1; i < m; i++) {
            if ((d * i) % m == 1) {
                inverse = i;
                break;
            }
        }
        return inverse;
    }
    public int gcd(int n1, int n2) {
        if (n2 == 0) {
            return n1;
        }
        return gcd(n2, n1 % n2);
    }

    @Override
    public String encrypt(String text, String alphabet, String key) {
        StringBuilder encryptedText = new StringBuilder();

        int[] key_indexes =new int[key.length()];
        int i = 0;
        for (char c : key.toCharArray()) {

            key_indexes[i] = alphabet.indexOf(c);
            i++;
            if (i > key.length()) break;
        }

        int k11 = key_indexes[0];
        int k12 = key_indexes[1];
        int k21 = key_indexes[2];
        int k22 = key_indexes[3];
        int detK = (k11 * k22 - k12 * k21 + (alphabet.length())) % (alphabet.length());
        //проверка ключа
        if ((detK%alphabet.length()) != 0 && (gcd(detK, (alphabet.length())) == 1)){
            //проверка длины алфавита
            if(text.length()%2 == 1) {
                char lastchar = text.charAt(text.length() - 1);
                text = text + lastchar;
            }
            System.out.println("Значение текста изменено. Последний символ текста дублирован в конец");
            } else {
            String message = "Значение ключа не соответвует требованиям";
            return message;
        }
        int[] text_indexes =new int[text.length()];
        int[] encrypted_text_indexes = new int[text_indexes.length];
        int t = 0;
        for (char c : text.toCharArray()) {
            text_indexes[t] = alphabet.indexOf(c);
            t++;
            if (t > text.length()) break;
        }
            for (int j = 0; j <= text_indexes.length-2; j=j+2) {

                encrypted_text_indexes[j] = (text_indexes[j]*k11 + text_indexes[j+1]*k21);
                encrypted_text_indexes[j+1] = (text_indexes[j]*k12 + text_indexes[j+1]*k22);
            }

        for (int h = 0; h < encrypted_text_indexes.length; h++){
            while( encrypted_text_indexes[h] > (alphabet.length()-1)){
                encrypted_text_indexes[h] =encrypted_text_indexes[h] -  (alphabet.length());
            }
            while( encrypted_text_indexes[h] < 0){
                encrypted_text_indexes[h] =encrypted_text_indexes[h] +  (alphabet.length());
            }
            int encryptedindex = encrypted_text_indexes[h];
            encryptedText.append(alphabet.charAt(encryptedindex));
        }


        return encryptedText.toString();
    }
    @Override
    public String decrypt(String encryptedText, String alphabet, String key) {
        StringBuilder decryptedText = new StringBuilder();

        int[] key_indexes = new int[key.length()];
        int i = 0;
        for (char c : key.toCharArray()) {

            key_indexes[i] = alphabet.indexOf(c);
            i++;
            if (i > key.length()) break;
        }
        int k11 = key_indexes[0];
        int k12 = key_indexes[1];
        int k21 = key_indexes[2];
        int k22 = key_indexes[3];
        int detK = (k11 * k22 - k12 * k21 + (alphabet.length())) % (alphabet.length());
        //проверка ключа
        if ((detK%alphabet.length()) != 0 && (gcd(detK, (alphabet.length())) == 1)){
            //проверка длины алфавита
            if(encryptedText.length()%2 == 1) {
                char lastchar = encryptedText.charAt(encryptedText.length() - 1);
                encryptedText = encryptedText + lastchar;
            }
            System.out.println("Значение шифртекста изменено. Последний символ шифртекста дублирован в конец");
        } else {
            String message = "Значение ключа не соответвует требованиям";
            System.out.println(message);
            return message;
        }

        int detK_inverse = inverseInt(detK, alphabet.length());
        int key_matrix_new[] = {k22, (-1) * k12, (-1) * k21, k11};
        int[] key_inverse = new int[key_matrix_new.length];

        for (int q = 0; q < key_matrix_new.length; q++) {
            key_inverse[q] = (key_matrix_new[q] * detK_inverse) % (alphabet.length());
        }
        int[] encrypted_text_indexes = new int[encryptedText.length()];
        int[] decrypted_text_indexes = new int[encryptedText.length()];
        int t = 0;
        for (char c : encryptedText.toCharArray()) {
            encrypted_text_indexes[t] = alphabet.indexOf(c);
            t++;
            if (t > encryptedText.length()) break;
        }
        for (int j = 0; j <= encrypted_text_indexes.length - 2; j = j + 2) {

            decrypted_text_indexes[j] = (encrypted_text_indexes[j] * key_inverse[0] + encrypted_text_indexes[j + 1] * key_inverse[2]);
            decrypted_text_indexes[j + 1] = (encrypted_text_indexes[j] * key_inverse[1] + encrypted_text_indexes[j + 1] * key_inverse[3]);
        }


        for (int h = 0; h < decrypted_text_indexes.length; h++) {
            while (decrypted_text_indexes[h] < 0){
                decrypted_text_indexes[h] = decrypted_text_indexes[h] + (alphabet.length());
            }
            while (decrypted_text_indexes[h] > (alphabet.length()-1)) {
                    decrypted_text_indexes[h] = decrypted_text_indexes[h] - (alphabet.length());
                }
            decryptedText.append(alphabet.charAt(decrypted_text_indexes[h]));
            }

        return decryptedText.toString();
    }
}
