package Chiffers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Affine implements Encryptor {



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
        int a = alphabet.indexOf(key.charAt(0));
        int b = alphabet.indexOf(key.charAt(1));

//        while(gcd(a, (alphabet.length())) != 1){
//            a++;
//            System.out.println("значение ключа с индексом один увеличено на 1");
//        }
        if(gcd(a, (alphabet.length())) != 1) {
            String message = "Ключ не является взаимно прост с модулем";
            System.out.println("Сообщение об ошибке записано в выходной файл");
            return message;
        }


        StringBuilder encryptedText = new StringBuilder();

        for (char c : text.toCharArray()) {
                int index = alphabet.indexOf(c);
                int encryptedIndex = (a * index + b) % (alphabet.length());
                encryptedText.append(alphabet.charAt(encryptedIndex));

        }
        return encryptedText.toString();
    }

    public int inverseInt(int d, int m) {
        int inverse = 0;

        while(d > m){
            d=d-m;
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
    public String decrypt(String encryptedText, String alphabet, String key) {
        int a = alphabet.indexOf(key.charAt(0));
        int b = alphabet.indexOf(key.charAt(1));


        if(gcd(a, (alphabet.length())) != 1) {
            String message = "Ключ не является взаимно прост с модулем";
            System.out.println("Сообщение об ошибке записано в выходной файл");
            return message;
        }


        StringBuilder decryptedText = new StringBuilder();
        for (char c : encryptedText.toCharArray()) {
                int index = alphabet.indexOf(c);
                int inverseA = inverseInt(a,(alphabet.length()));
                int decryptedIndex = (inverseA * (index - b )) % (alphabet.length());
                while(decryptedIndex < 0){
                    decryptedIndex += (alphabet.length());
                }
                //char decryptedChar = alphabet.charAt(decryptedIndex);
                decryptedText.append(alphabet.charAt(decryptedIndex));

        }
        return decryptedText.toString();
    }

}
