package Chiffers;


public interface Encryptor {
    public String readFromFile(String fileName);

    public void writeToFile(String fileName, String content);
    public  String encrypt(String text, String alphabet, String key);
    public   String decrypt(String encryptedText, String alphabet, String key);
}
