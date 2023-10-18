package Chiffers;
import java.io.BufferedReader;
        import java.io.BufferedWriter;
        import java.io.FileReader;
        import java.io.FileWriter;
        import java.io.IOException;

public class Caesar implements Encryptor {


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
    public String encrypt(String input, String alphabet, String key) {
        StringBuilder encryptedText = new StringBuilder();
        int keyIndex = alphabet.indexOf(key, 0);

        for (char c : input.toCharArray()) {
            int charIndex = alphabet.indexOf(c);
            if (charIndex != -1) {
                int encryptedIndex = (charIndex + keyIndex) % alphabet.length();
                encryptedText.append(alphabet.charAt(encryptedIndex));
            } else {
                encryptedText.append(c);
            }
        }
        return encryptedText.toString();
    }

    @Override
    public String decrypt(String encryptedText, String alphabet, String key) {
        StringBuilder decryptedText = new StringBuilder();
        int keyIndex = alphabet.indexOf(key);

        for (char c : encryptedText.toCharArray()) {
            int charIndex = alphabet.indexOf(c);
            if (charIndex != -1) {
                int decryptedIndex = (charIndex - keyIndex + alphabet.length()) % alphabet.length();
                decryptedText.append(alphabet.charAt(decryptedIndex));
            } else {
                decryptedText.append(c);
            }
        }
        return decryptedText.toString();
    }
}



