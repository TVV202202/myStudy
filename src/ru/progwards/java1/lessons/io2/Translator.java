package ru.progwards.java1.lessons.io2;

import java.util.Arrays;

public class Translator {
    private String[] inLang;
    private String[] outLang;

    public Translator(String[] inLang, String[] outLang) {
        this.inLang = Arrays.copyOf(inLang, inLang.length);
        this.outLang = Arrays.copyOf(outLang, outLang.length);
    }

    public String translate(String sentence) {
        if ("".equals(sentence)) return sentence;
        String[] strArray = sentence.split(" ");

        for (int i = 0; i < strArray.length; i++) {
            String tail = "";
            String head = "";
            char chEnd = strArray[i].charAt(strArray[i].length() - 1);
            if (!Character.isAlphabetic(chEnd)) { // проверка последнего символа на не букву
                tail = String.valueOf(chEnd);
                strArray[i] = strArray[i].substring(0, strArray[i].length() - 1);
            }
            char chStart = strArray[i].charAt(0);
            if (!Character.isAlphabetic(chStart)) { // проверка first символа на не букву
                head = String.valueOf(chStart);
                strArray[i] = strArray[i].substring(1);
            }

            if (strArray[i].toLowerCase().equals(inLang[i])) {
                if (Character.isUpperCase(strArray[i].charAt(0))) {
                    strArray[i] = outLang[i];
                    strArray[i] = head + Character.toUpperCase(strArray[i].charAt(0)) + strArray[i].substring(1) + tail;
                } else {
                    strArray[i] = head + outLang[i] + tail;
                }
            }
        }
        return String.join(" ", strArray);
    }

//    public static void main(String[] args) {
//        String[] inLang = {"make", "love", "not", "war"};
//        String[] outLang = {"твори", "любовь", "не", "войну"};
//        Translator translator = new Translator(inLang, outLang);
//        System.out.println(translator.translate("Make Love-not war."));
//    }
}
