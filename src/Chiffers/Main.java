package Chiffers;

import java.util.*;


public class Main {

    public static boolean testGcd(){
        Affine affine = new Affine();
        Hill hill = new Hill();
        int resultAffine1 = affine.gcd(18,30);
        int resultAffine2 = affine.gcd(18,13);

        int resultHill1 = hill.gcd(18,30);
        int resultHill2 = hill.gcd(18,13);

        return resultAffine1 == 6 && resultHill1 == 6 && resultAffine2 == 1 && resultHill2 == 1;
    }

    public static boolean testInverseInt(){
        Affine affine = new Affine();
        Hill hill = new Hill();
        int resultAffine1 = affine.inverseInt(3,26);
        int resultAffine2 = affine.inverseInt(18,13);

        int resultHill1 = hill.inverseInt(3,26);
        int resultHill2 = hill.inverseInt(18,13);

        return resultAffine1 == 9 && resultHill1 == 9 && resultAffine2 == 8 && resultHill2 == 8;
    }

    public static boolean testEncryptionCaesar(){
        Caesar caesar = new Caesar();
        String testText = "СОСИСКА";
        String testkey = "В";
        String testAlphabet = caesar.readFromFile("testalphabet.txt");

        String testEncryptedText = "УРУКУМВ";

        String result = caesar.encrypt(testText,testAlphabet,testkey);


        return Objects.equals(result, testEncryptedText);
    }

    public static boolean dynamicTestCaesar(){
        Caesar caesar = new Caesar();
        String testAlphabet = caesar.readFromFile("testalphabet1.txt");
        int len = (int) (Math.random()*(testAlphabet.length()-1)+1);
        int[] indexesText = new int[len];
        char[] charsAtIndexes = new char[len];
        for (int i = 0; i < len; i++) {
            indexesText[i] = (int) (Math.random()*(testAlphabet.length()-1)+1);
            charsAtIndexes[i] = testAlphabet.charAt(indexesText[i]);
        }
        String testText = Arrays.toString(charsAtIndexes);
        int key_index = (int) (Math.random()*(testAlphabet.length()-1)+1);
        String testkey = String.valueOf(testAlphabet.charAt(key_index));
        //        String testkey = new String(new char[]{testAlphabet.charAt(key_index)});

        String testEncryptedText = caesar.encrypt(testText,testAlphabet,testkey);
        String result = caesar.decrypt(testEncryptedText,testAlphabet,testkey);
        return Objects.equals(testText, result);
    }
    public static boolean testDecryptionCaesar() {
        Caesar caesar = new Caesar();
        String testText = "СОСИСКА";
        String testkey = "В";
        String testAlphabet = caesar.readFromFile("testalphabet.txt");

        String testDecryptedText = "СОСИСКА";

        String result_enc = caesar.encrypt(testText,testAlphabet,testkey);
        String result = caesar.decrypt(result_enc, testAlphabet, testkey);

        return Objects.equals(result, testDecryptedText);

    }

    public static boolean testEncryptionAffine(){
        Affine affine = new Affine();
        String testText = "СОСИСКА";
        String testkey = "БД";
        String testAlphabet = affine.readFromFile("testalphabet.txt");

        String testEncryptedText = "ХТХМХОД";

        String result = affine.encrypt(testText,testAlphabet,testkey);
        //System.out.println(result);
        return Objects.equals(result, testEncryptedText);
    }
    public static boolean dynamicTestAffine(){
        Affine affine = new Affine();
        String testAlphabet = affine.readFromFile("testalphabet1.txt");
        int len = (int) (Math.random()*(50)+1);
        int[] indexesText = new int[len];
        char[] charsAtIndexes = new char[len];
        for (int i = 0; i < len; i++) {
            indexesText[i] = (int) (Math.random()*(testAlphabet.length()-1)+1);
            charsAtIndexes[i] = testAlphabet.charAt(indexesText[i]);
        }
        String testText = new String(charsAtIndexes);
        int[] key_indexes = new int[2];
        char[] charsAtKeyIndexes = new char[2];
        for (int j = 0; j < 2; j++) {
            key_indexes[j] = (int) (Math.random() * (testAlphabet.length()-1)+1);
            while(key_indexes[j] < 0){
                key_indexes[j] += (testAlphabet.length());
            }
            while(affine.gcd(key_indexes[0], testAlphabet.length()) != 1){
                key_indexes[0] = (int) (Math.random() * (testAlphabet.length()-1)+1);
            }
            charsAtKeyIndexes[j] = testAlphabet.charAt(key_indexes[j]);
        }
        String testkey = new String(charsAtKeyIndexes);

        String testEncryptedText = affine.encrypt(testText,testAlphabet,testkey);
        String result = affine.decrypt(testEncryptedText,testAlphabet,testkey);
        return Objects.equals(testText, result);
    }
    public static boolean testDecryptionAffine() {
        Affine affine = new Affine();
        String testText = "СОСИСКА";
        String testkey = "БД";
        String testAlphabet = affine.readFromFile("testalphabet.txt");

        String testDecryptedText = "СОСИСКА";

        String result_enc = affine.encrypt(testText,testAlphabet,testkey);
        String result = affine.decrypt(result_enc, testAlphabet, testkey);

        return Objects.equals(result, testDecryptedText);

    }
    public static boolean testEncryptionHill(){
        Hill hill = new Hill();
        String testText = "СОСИСКА";
        String testkey = "СЕНО";
        String testAlphabet = hill.readFromFile("testalphabet.txt");

        String testEncryptedText = "ЁСФЪПЧАА";

        String result = hill.encrypt(testText,testAlphabet,testkey);
        //System.out.println(result);


        return Objects.equals(result, testEncryptedText);
    }
    public static boolean dynamicTestHill(){
        Hill hill = new Hill();
        String testAlphabet = hill.readFromFile("testalphabet1.txt");
        int len = (int) (Math.random()*(50)+1);
        int[] indexesText = new int[len];
        char[] charsAtIndexes = new char[len];

        for (int i = 0; i < len; i++) {
            indexesText[i] = (int) (Math.random()*(testAlphabet.length()-1)+1);
            charsAtIndexes[i] = testAlphabet.charAt(indexesText[i]);
        }
        String testText = new String(charsAtIndexes);

        int[] key_indexes = new int[4];
        char[] keycharsAtIndexes = new char[4];
        for (int i = 0; i < 4; i++) {
            key_indexes[i] = (int) (Math.random()*(testAlphabet.length()-1)+1);
//            charsAtIndexes[i] = testAlphabet.charAt(indexesText[i]);
        }
        int k11 = key_indexes[0];
        int k12 = key_indexes[1];
        int k21 = key_indexes[2];
        int k22 = key_indexes[3];
        int detK = (k11 * k22 - k12 * k21 + (testAlphabet.length())) % (testAlphabet.length());
        while (detK == 0 || (hill.gcd(Math.abs(detK), (testAlphabet.length())) != 1)){
            for (int i = 0; i < 4; i++) {
                key_indexes[i] = (int) (Math.random()*(testAlphabet.length()-1)+1);
            }
            k11 = key_indexes[0];
            k12 = key_indexes[1];
            k21 = key_indexes[2];
            k22 = key_indexes[3];
            detK = (k11 * k22 - k12 * k21 + (testAlphabet.length())) % (testAlphabet.length());
        }
        for (int i = 0; i < 4; i++) {
            keycharsAtIndexes[i] = testAlphabet.charAt(key_indexes[i]);
        }

        String testkey = String.valueOf(keycharsAtIndexes);

        String testEncryptedText = hill.encrypt(testText,testAlphabet,testkey);
        String result = hill.decrypt(testEncryptedText,testAlphabet,testkey);

         String testTextFinal;

        if(testText.length()%2 == 1){
            testTextFinal = testText + testText.charAt(testText.length() - 1);
        } else {
            testTextFinal = testText;
        }

        return Objects.equals(testTextFinal, result);
    }
    public static boolean testDecryptionHill() {
        Hill hill = new Hill();
        String testText = "СОСИСКА";
        String testkey = "СЕНО";
        String testAlphabet = hill.readFromFile("testalphabet.txt");

        String testDecryptedText = "СОСИСКАА";

        String result_enc = hill.encrypt(testText,testAlphabet,testkey);
        String result = hill.decrypt(result_enc, testAlphabet, testkey);

        return Objects.equals(result, testDecryptedText);

    }
    public static boolean testEncryptionPermutation(){
        Permutation permutation = new Permutation();
        String testText = "СОСИСКА";
        String testkey = "ЗЕМЛЯ";
        String testAlphabet = permutation.readFromFile("testalphabet.txt");

        String testEncryptedText = "ОСИССАКААА";

        String result = permutation.encrypt(testText,testAlphabet,testkey);


        return Objects.equals(result, testEncryptedText);
    }

    public static boolean dynamicTestPermutation(){
        Permutation permutation = new Permutation();
        String testAlphabet = permutation.readFromFile("testalphabet1.txt");
        int len = (int) (Math.random()*(50)+1);
        int[] indexesText = new int[len];
        char[] charsAtIndexes = new char[len];
        for (int i = 0; i < len; i++) {
            indexesText[i] = (int) (Math.random()*(testAlphabet.length()-1)+1);
            charsAtIndexes[i] = testAlphabet.charAt(indexesText[i]);
        }

        String testText = new String(charsAtIndexes);

        int len_key = (int) (Math.random()*(10)+3);
        Set<Integer> tmp = new LinkedHashSet<>();

        while (tmp.size() < len_key){
            int a = (int) (Math.random() * (testAlphabet.length()-1)+1);
            tmp.add(a);

        }
        Iterator<Integer> iterate = tmp.iterator();
        char[] charsAtKeyIndexes = new char[len_key];
        int j = 0;
        while(iterate.hasNext()){
            charsAtKeyIndexes[j] = testAlphabet.charAt(iterate.next());
            j++;
            if(j > len_key) break;
        }
        String testkey = new String(charsAtKeyIndexes);

        String testEncryptedText = permutation.encrypt(testText,testAlphabet,testkey);
        String result = permutation.decrypt(testEncryptedText,testAlphabet,testkey);

        String testTextFinal = testText;

        int n = tmp.size();
        int m = indexesText.length;
        if (m % n != 0) {
            char lastChar = testText.charAt(m - 1);
            int additionalChars = n - (m % n);
            for (int u = 0; u < additionalChars; u++) {
                testTextFinal = testTextFinal + lastChar;
            }
            //m = testTextFinal.length();
        }
        else{
            testTextFinal = testText;
        }
        return Objects.equals(testTextFinal, result);
    }

    public static boolean testDecryptionPermutation() {
        Permutation permutation = new Permutation();
        String testText = "СОСИСКА";
        String testkey = "ЗЕМЛЯ";
        String testAlphabet = permutation.readFromFile("testalphabet.txt");

        String testDecryptedText = "СОСИСКАААА";

        String result_enc = permutation.encrypt(testText,testAlphabet,testkey);
        String result = permutation.decrypt(result_enc, testAlphabet, testkey);

        return Objects.equals(result, testDecryptedText);

    }
    public static boolean testEncryptionROT(){
        ROT rot = new ROT();
        String testText = "СОСИСКА";
        String testkey = "ЙЦУКЕНГШЩЗХЪФЫВАПРОЛДЖЭЯЧСМИТЬБЮЁ";
        String testAlphabet = rot.readFromFile("testalphabet.txt");

        String testEncryptedText = "ОАОЗОЪЙ";

        String result = rot.encrypt(testText,testAlphabet,testkey);


        return Objects.equals(result, testEncryptedText);
    }
    public static boolean dynamicTestROT(){
        ROT rot = new ROT();
        String testAlphabet = rot.readFromFile("testalphabet1.txt");
        int len = (int) (Math.random()*(50)+1);
        int[] indexesText = new int[len];
        char[] charsAtIndexes = new char[len];
        for (int i = 0; i < len; i++) {
            indexesText[i] = (int) (Math.random()*(testAlphabet.length()-1)+1);
            charsAtIndexes[i] = testAlphabet.charAt(indexesText[i]);
        }
        String testText = new String(charsAtIndexes);

        List<Integer> fill = new ArrayList<>();
        for ( int i = 0; i < testAlphabet.length(); i++ ) {
            fill.add(i);
        }
        Collections.shuffle(fill);

        char[] charsAtKeyIndexes = new char[testAlphabet.length()];
        for (int j = 0; j < testAlphabet.length(); j++) {
            charsAtKeyIndexes[j] = testAlphabet.charAt(fill.get(j));
        }
        String testkey = new String(charsAtKeyIndexes);

        String testEncryptedText = rot.encrypt(testText,testAlphabet,testkey);
        String result = rot.decrypt(testEncryptedText,testAlphabet,testkey);
        return Objects.equals(testText, result);
    }
    public static boolean testDecryptionROT() {
        ROT rot = new ROT();
        String testText = "СОСИСКА";
        String testkey = "ЙЦУКЕНГШЩЗХЪФЫВАПРОЛДЖЭЯЧСМИТЬБЮЁ";
        String testAlphabet = rot.readFromFile("testalphabet.txt");

        String testDecryptedText = "СОСИСКА";

        String result_enc = rot.encrypt(testText,testAlphabet,testkey);
        String result = rot.decrypt(result_enc, testAlphabet, testkey);

        return Objects.equals(result, testDecryptedText);

    }
    public static boolean testEncryptionVigenere(){
        Vigenere vigenere = new Vigenere();
        String testText = "СОСИСКА";
        String testkey = "ЛИМОН";
        String testAlphabet = vigenere.readFromFile("testalphabet.txt");

        String testEncryptedText = "ЭЧЮЧЯЦИ";

        String result = vigenere.encrypt(testText,testAlphabet,testkey);


        return Objects.equals(result, testEncryptedText);
    }

    public static boolean dynamicTestVigenere(){
        Vigenere vigenere = new Vigenere();
        String testAlphabet = vigenere.readFromFile("testalphabet1.txt");
        int len = (int) (Math.random()*(50)+1);
        int[] indexesText = new int[len];
        char[] charsAtIndexes = new char[len];
        for (int i = 0; i < len; i++) {
            indexesText[i] = (int) (Math.random()*(testAlphabet.length()-1)+1);
            charsAtIndexes[i] = testAlphabet.charAt(indexesText[i]);
        }
        String testText = new String(charsAtIndexes);
        int len_key = (int) (Math.random()*(testAlphabet.length()-1)+1);
        while(len_key > len){
            len_key--;
        }
        int[] key_indexes = new int[len_key];


        char[] charsAtKeyIndexes = new char[len_key];
        for (int j = 0; j < len_key; j++) {
              key_indexes[j] = (int) (Math.random()*(testAlphabet.length()-1)+1);
            charsAtKeyIndexes[j] = testAlphabet.charAt(key_indexes[j]);
        }
        String testkey = new String(charsAtKeyIndexes);

        String testEncryptedText = vigenere.encrypt(testText,testAlphabet,testkey);
        String result = vigenere.decrypt(testEncryptedText,testAlphabet,testkey);
        return Objects.equals(testText, result);
    }
    public static boolean testDecryptionVigenere() {
        Vigenere vigenere = new Vigenere();
        String testText = "СОСИСКА";
        String testkey = "ЛИМОН";
        String testAlphabet = vigenere.readFromFile("testalphabet.txt");

        String testDecryptedText = "СОСИСКА";

        String result_enc = vigenere.encrypt(testText,testAlphabet,testkey);
        String result = vigenere.decrypt(result_enc, testAlphabet, testkey);

        return Objects.equals(result, testDecryptedText);

    }

    public static void main(String[] args) {
        Encryptor chif;
        System.out.println("Тесты запущены");
        System.out.println();
        System.out.println("Тесты шифрования:");
        System.out.println();
        if (testEncryptionCaesar()) System.out.println("Тест 1.1 завершен успешно");
        else System.out.println("Тест 1.1 завершен с ошибкой");
        if (testEncryptionAffine()) System.out.println("Тест 2.1 завершен успешно");
        else System.out.println("Тест 2.1 завершен с ошибкой");
        if (testEncryptionHill()) System.out.println("Тест 3.1 завершен успешно");
        else System.out.println("Тест 3.1 завершен с ошибкой");
        if (testEncryptionPermutation()) System.out.println("Тест 4.1 завершен успешно");
        else System.out.println("Тест 4.1 завершен с ошибкой");
        if (testEncryptionROT()) System.out.println("Тест 5.1 завершен успешно");
        else System.out.println("Тест 5.1 завершен с ошибкой");
        if (testEncryptionVigenere()) System.out.println("Тест 6.1 завершен успешно");
        else System.out.println("Тест 6.1 завершен с ошибкой");
        System.out.println();
        System.out.println("Тесты дешифрования:");
        System.out.println();

        if (testDecryptionCaesar()) System.out.println("Тест 1.2 завершен успешно");
        else System.out.println("Тест 1.2 завершен с ошибкой");
        if (testDecryptionAffine()) System.out.println("Тест 2.2 завершен успешно");
        else System.out.println("Тест 2.2 завершен с ошибкой");
        if (testDecryptionHill()) System.out.println("Тест 3.2 завершен успешно");
        else System.out.println("Тест 3.2 завершен с ошибкой");
        if (testDecryptionPermutation()) System.out.println("Тест 4.2 завершен успешно");
        else System.out.println("Тест 4.2 завершен с ошибкой");
        if (testDecryptionROT()) System.out.println("Тест 5.2 завершен успешно");
        else System.out.println("Тест 5.2 завершен с ошибкой");
        if (testDecryptionVigenere()) System.out.println("Тест 6.2 завершен успешно");
        else System.out.println("Тест 6.2 завершен с ошибкой");
        System.out.println();
        System.out.println("Тесты вспомогательных методов:");
        System.out.println();

        if (testGcd()) System.out.println("Тест 1 завершен успешно");
        else System.out.println("Тест 1 завершен с ошибкой");
        if (testInverseInt()) System.out.println("Тест 2 завершен успешно");
        else System.out.println("Тест 2 завершен с ошибкой");
        System.out.println();
        System.out.println("Динамические тесты:");
        System.out.println();

        if (dynamicTestCaesar()) System.out.println("Тест 10 завершен успешно");
        else System.out.println("Тест 10 завершен с ошибкой");
        if (dynamicTestAffine()) System.out.println("Тест 11 завершен успешно");
        else System.out.println("Тест 11 завершен с ошибкой");
        if (dynamicTestHill()) System.out.println("Тест 12 завершен успешно");
        else System.out.println("Тест 12 завершен с ошибкой");
        if (dynamicTestROT()) System.out.println("Тест 13 завершен успешно");
        else System.out.println("Тест 13 завершен с ошибкой");
        if (dynamicTestVigenere()) System.out.println("Тест 14 завершен успешно");
        else System.out.println("Тест 14 завершен с ошибкой");
        if (dynamicTestPermutation()) System.out.println("Тест 15 завершен успешно");
        else System.out.println("Тест 15 завершен с ошибкой");
        System.out.println();
        System.out.println("##########");


        Scanner scan = new Scanner(System.in);


        System.out.println("Для выбора операции в файл todo.txt запишите 1 для шифрования или 0 для дешифрования.\n" +
                "Выберите шифр:\n" + "Для выбора введите соответствующее число\n" +
                "шифр Цезаря - 0\n" +
                "Афинный шифр - 1\n" +
                "шифр Хилла - 2\n" +
                "шифр перестановки - 3\n" +
                "шифр простой замены - 4\n" +
                "шифр Виженера - 5\n");
        while (true) {

            switch (scan.nextInt()) {
                case (0):
                    chif = new Caesar();
                    String alphabet = chif.readFromFile("alphabet.txt");
                    String inputText = chif.readFromFile("in.txt");
                    String key = chif.readFromFile("key_caesar.txt");
                    String todo = chif.readFromFile("todo.txt");
                    for (char c : key.toCharArray()) {
                        if (key.length() != 1 || alphabet.indexOf(c) == -1)
                            System.out.println("Ключ задан некорректно");
                        break;
                    }


                    HashSet<Character> alf = new HashSet<Character>();
                    for (char c : alphabet.toCharArray()) {
                        alf.add(c);
                    }
                    if (alf.size() != alphabet.length()) {
                        System.out.println("Алфавит задан некорректно");
                        break;
                    }

                    for (char c : inputText.toCharArray()) {
                        if (alphabet.indexOf(c) == -1) {
                            System.out.println("Входной текст задан некорректно");
                            break;
                        }
                    }

                    String encryptedText = chif.encrypt(inputText, alphabet, key);
                    if (Integer.parseInt(todo) == 1) {

                        chif.writeToFile("encrypt_caesar.txt", encryptedText);
                        System.out.println("Выполнено успешно");
                    } else if (Integer.parseInt(todo) == 0) {
                        String decryptedText = chif.decrypt(encryptedText, alphabet, key);
                        chif.writeToFile("decrypt_caesar.txt", decryptedText);
                        System.out.println("Выполнено успешно");
                    } else {
                        System.out.println("Неверные указания");
                    }
                    break;


                case (1):
                    chif = new Affine();
                    String alphabet1 = chif.readFromFile("alphabet.txt");
                    String inputText1 = chif.readFromFile("in.txt");
                    String key1 = chif.readFromFile("key_affine.txt");
                    String todo1 = chif.readFromFile("todo.txt");

                    for (char c : key1.toCharArray()) {
                        if (key1.length() != 2 || alphabet1.indexOf(c) == -1)
                            System.out.println("Ключ задан некорректно");
                        break;
                    }

                    HashSet<Character> alf1 = new HashSet<>();
                    for (char c : alphabet1.toCharArray()) {
                        alf1.add(c);
                    }
                    if (alf1.size() != alphabet1.length()) {
                        System.out.println("Алфавит задан некорректно");
                        break;
                    }

                    for (char c : inputText1.toCharArray()) {
                        if (alphabet1.indexOf(c) == -1) {
                            System.out.println("Входной текст задан некорректно");
                            break;
                        }
                    }

                    String encryptedText1 = chif.encrypt(inputText1, alphabet1, key1);
                    if (Integer.parseInt(todo1) == 1) {

                        chif.writeToFile("encrypt_affine.txt", encryptedText1);
                        System.out.println("Выполнено успешно");
                    } else if (Integer.parseInt(todo1) == 0) {
                        String decryptedText = chif.decrypt(encryptedText1, alphabet1, key1);
                        chif.writeToFile("decrypt_affine.txt", decryptedText);
                        System.out.println("Выполнено успешно");
                    } else {
                        System.out.println("Неверные указания");
                    }
                    break;

                case (2):
                    chif = new Hill();
                    String alphabet2 = chif.readFromFile("alphabet.txt");
                    String inputText2 = chif.readFromFile("in.txt");
                    String key2 = chif.readFromFile("key_hill.txt");
                    String todo2 = chif.readFromFile("todo.txt");


                    for (char c : key2.toCharArray()) {
                        if (key2.length() != 4 || alphabet2.indexOf(c) == -1)
                            System.out.println("Ключ задан некорректно");
                        break;
                    }

                    HashSet<Character> alf2 = new HashSet<Character>();
                    for (char c : alphabet2.toCharArray()) {
                        alf2.add(c);
                    }
                    if (alf2.size() != alphabet2.length()) {
                        System.out.println("Алфавит задан некорректно");
                        break;
                    }

                    for (char c : inputText2.toCharArray()) {
                        if (alphabet2.indexOf(c) == -1) {
                            System.out.println("Входной текст задан некорректно");
                            break;
                        }
                    }

                    String encryptedText2 = chif.encrypt(inputText2, alphabet2, key2);
                    if (Integer.parseInt(todo2) == 1) {

                        chif.writeToFile("encrypt_hill.txt", encryptedText2);
                        System.out.println("Выполнено успешно");
                    } else if (Integer.parseInt(todo2) == 0) {
                        String decryptedText = chif.decrypt(encryptedText2, alphabet2, key2);
                        chif.writeToFile("decrypt_hill.txt", decryptedText);
                        System.out.println("Выполнено успешно");
                    } else {
                        System.out.println("Неверные указания");
                    }
                    break;

                case (3):
                    chif = new Permutation();
                    String alphabet3 = chif.readFromFile("alphabet.txt");
                    String inputText3 = chif.readFromFile("in.txt");
                    String key3 = chif.readFromFile("key_permutation.txt");
                    String todo3 = chif.readFromFile("todo.txt");


                    for (char c : key3.toCharArray()) {
                        if (key3.length() > alphabet3.length() || alphabet3.indexOf(c) == -1)
                            System.out.println("Ключ задан некорректно");
                        break;
                    }

                    HashSet<Character> alf3 = new HashSet<Character>();
                    for (char c : alphabet3.toCharArray()) {
                        alf3.add(c);
                    }
                    if (alf3.size() != alphabet3.length()) {
                        System.out.println("Алфавит задан некорректно");
                        break;
                    }

                    for (char c : inputText3.toCharArray()) {
                        if (alphabet3.indexOf(c) == -1) {
                            System.out.println("Входной текст задан некорректно");
                            break;
                        }
                    }

                    String encryptedText3 = chif.encrypt(inputText3, alphabet3, key3);
                    if (Integer.parseInt(todo3) == 1) {

                        chif.writeToFile("encrypt_permutation.txt", encryptedText3);
                        System.out.println("Выполнено успешно");
                    } else if (Integer.parseInt(todo3) == 0) {
                        String decryptedText = chif.decrypt(encryptedText3, alphabet3, key3);
                        chif.writeToFile("decrypt_permutation.txt", decryptedText);
                        System.out.println("Выполнено успешно");
                    } else {
                        System.out.println("Неверные указания");
                    }
                    break;

                case (4):
                    chif = new ROT();
                    String alphabet4 = chif.readFromFile("alphabet.txt");
                    String inputText4 = chif.readFromFile("in.txt");
                    String key4 = chif.readFromFile("key_ROT.txt");
                    String todo4 = chif.readFromFile("todo.txt");

                    for (char c : key4.toCharArray()) {
                        if (key4.length() != alphabet4.length() || alphabet4.indexOf(c) == -1)
                            System.out.println("Ключ задан некорректно");
                        break;
                    }


                    HashSet<Character> alf4 = new HashSet<Character>();
                    for (char c : alphabet4.toCharArray()) {
                        alf4.add(c);
                    }
                    if (alf4.size() != alphabet4.length()) {
                        System.out.println("Алфавит задан некорректно");
                        break;
                    }

                    for (char c : inputText4.toCharArray()) {
                        if (alphabet4.indexOf(c) == -1) {
                            System.out.println("Входной текст задан некорректно");
                            break;
                        }
                    }

                    String encryptedText4 = chif.encrypt(inputText4, alphabet4, key4);
                    if (Integer.parseInt(todo4) == 1) {

                        chif.writeToFile("encrypt_ROT.txt", encryptedText4);
                        System.out.println("Выполнено успешно");
                    } else if (Integer.parseInt(todo4) == 0) {
                        String decryptedText = chif.decrypt(encryptedText4, alphabet4, key4);
                        chif.writeToFile("decrypt_ROT.txt", decryptedText);
                        System.out.println("Выполнено успешно");
                    } else {
                        System.out.println("Неверные указания");
                    }
                    break;

                case (5):
                    chif = new Vigenere();
                    String alphabet5 = chif.readFromFile("alphabet.txt");
                    String inputText5 = chif.readFromFile("in.txt");
                    String key5 = chif.readFromFile("key_vigenere.txt");
                    String todo5 = chif.readFromFile("todo.txt");

                    for (char c : key5.toCharArray()) {
                        if (alphabet5.indexOf(c) == -1)
                            System.out.println("Ключ задан некорректно");
                        break;
                    }
                    HashSet<Character> alf5 = new HashSet<Character>();
                    for (char c : alphabet5.toCharArray()) {
                        alf5.add(c);
                    }
                    if (alf5.size() != alphabet5.length()) {
                        System.out.println("Алфавит задан некорректно");
                        break;
                    }

                    for (char c : inputText5.toCharArray()) {
                        if (alphabet5.indexOf(c) == -1) {
                            System.out.println("Входной текст задан некорректно");
                            break;
                        }
                    }

                    String encryptedText5 = chif.encrypt(inputText5, alphabet5, key5);
                    if (Integer.parseInt(todo5) == 1) {

                        chif.writeToFile("encrypt_vigenere.txt", encryptedText5);
                        System.out.println("Выполнено успешно");
                    } else if (Integer.parseInt(todo5) == 0) {
                        String decryptedText = chif.decrypt(encryptedText5, alphabet5, key5);
                        chif.writeToFile("decrypt_vigenere.txt", decryptedText);
                        System.out.println("Выполнено успешно");
                    } else {
                        System.out.println("Неверные указания");
                    }
                    break;
                default:
                    System.out.println("Неверные указания");
                    break;

            }
        }

    }
}

