import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println(calc(scanner.nextLine()));
    }

    public static String calc(String input) throws Exception {
        int result = 0;
        String styleOfCalc = "ArabicNums";
        String[] inputParts = input.split(" ");
        if (Main.isRomeNumber(inputParts[0]) && Main.isRomeNumber(inputParts[2])) {
            inputParts[0] = RomeNums.valueOf(inputParts[0]).getArabicNum();
            inputParts[2] = RomeNums.valueOf(inputParts[2]).getArabicNum();
            styleOfCalc = "RomeNums";
        } else if ((!Main.isRomeNumber(inputParts[0]) && Main.isRomeNumber(inputParts[2])) || Main.isRomeNumber(inputParts[0]) && !Main.isRomeNumber(inputParts[2])) {
            throw new IllegalArgumentException("Write numbers in the same style");
        }
        if (Integer.parseInt(inputParts[0]) > 10 || Integer.parseInt(inputParts[2]) > 10) {
            throw new IllegalArgumentException("Nums is too big");
        }
        switch (inputParts[1]) {
            case ("+") -> result = Integer.parseInt(inputParts[0]) + Integer.parseInt(inputParts[2]);
            case ("-") -> result = Integer.parseInt(inputParts[0]) - Integer.parseInt(inputParts[2]);
            case ("*") -> result = Integer.parseInt(inputParts[0]) * Integer.parseInt(inputParts[2]);
            case ("/") -> result = Integer.parseInt(inputParts[0]) / Integer.parseInt(inputParts[2]);
        }
        if (styleOfCalc.equals("RomeNums")) {
            return getRomeNumber(result);
        } else {
            return String.valueOf(result);
        }
    }

    public static boolean isRomeNumber(String num) {
        try {
            Integer.parseInt(num);
        } catch (NumberFormatException e) {
            return true;
        }
        return false;
    }

    public static String getRomeNumber(int number) throws Exception {
        if (number < 1) {
            throw new Exception("Negative number in result cant convert to rome numbers");
        }
        StringBuilder resultBuilder = new StringBuilder();
        int[] arabicNums = new int[]{ 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romeNums = new String[]{"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        for(int i = 0; i< romeNums.length; i++){
            while(arabicNums[i]<=number){
                number-=arabicNums[i];
                resultBuilder.append(romeNums[i]);
            }
        }
        return resultBuilder.toString();
    }
}