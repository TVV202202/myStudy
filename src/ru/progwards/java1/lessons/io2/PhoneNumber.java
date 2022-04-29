package ru.progwards.java1.lessons.io2;

public class PhoneNumber {
    public static String format(String phone) throws Exception {
        StringBuilder phoneNumber = new StringBuilder();
        for (char c : phone.toCharArray()) {
            if (Character.isDigit(c))
                phoneNumber.append(c);
        }
//        try {
            if (phoneNumber.length() == 11) {
                if (phoneNumber.charAt(0) == '8' || phoneNumber.charAt(0) == '7') {
                    phoneNumber.deleteCharAt(0);
                    phoneNumber.insert(0, "+7");
                } else {
                    throw new Exception("error");
                }
            } else if (phoneNumber.length() == 10) {
                phoneNumber.insert(0, "+7");
            } else {
                throw new Exception("error");
            }
            phoneNumber.insert(2, "(");
            phoneNumber.insert(6, ")");
            phoneNumber.insert(10, "-");
            return phoneNumber.toString();
//        }
//         catch (Exception e) {
//            return "error Phone Number";
//        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println(format("8(318)841312911"));
    }
}
