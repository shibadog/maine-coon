package net.shibadog.mainecoon;

import java.util.concurrent.ThreadLocalRandom;

public class QuestionService {
    
    public Question additionCarry(int digit) {
        String x1 = null;
        String x2 = null;
        do {
            x1 = generateNumber(digit);
            x2 = generateNumber(digit);
        } while(!hasCarry(x1, x2));
        
        return new Question(new Siki(x1, x2, "+"));
    }
    
    public Question subtractionBorrow(int digit) {
        String x1 = null;
        String x2 = null;

        do {
            x1 = generateNumber(digit == 1 ? digit + 1 : digit);
            x2 = generateNumber(digit);
        } while(Integer.valueOf(x1) < Integer.valueOf(x2) || !hasBorrow(x1, x2));

        return new Question(new Siki(x1, x2, "-"));
    }

    public Question multiplication() {
        String x1 = generateNumber(1);
        String x2 = generateNumber(1);

        return new Question(new Siki(x1, x2, "*"));
    }

    public Question divisionRemainder() {
        String x1 = null;
        String x2 = null;

        do {
            x1 = generateNumber(2);
            x2 = generateNumber(1);
        } while(Integer.valueOf(x1) % Integer.valueOf(x2) != 0 
                || Integer.toString(Integer.valueOf(x1) / Integer.valueOf(x2)).length() > 1);

        return new Question(new Siki(x1, x2, "/"));
    }

    String generateNumber(int digit) {
        if (digit == 0) throw new IllegalArgumentException();
        String x = "";
        for (int i = 1; i <= digit; i++) {
            x += Integer.toString(ThreadLocalRandom.current().nextInt(i == 1 ? 1 : 0, 9));
        }
        return x;
    }
    
    // 繰り上がりがあるかどうかをチェックするメソッド
    boolean hasCarry(String num1, String num2) {
        int maxLength = Math.max(num1.length(), num2.length());

        // 繰り上がりの初期値はfalse
        boolean carry = false;

        // 桁ごとに比較
        for (int i = 0; i < maxLength; i++) {
            // 各桁の数字を取得
            int digit1 = getDigit(num1, i);
            int digit2 = getDigit(num2, i);

            // 足し算して繰り上がりがあるかどうかを判定
            int sum = digit1 + digit2 + (carry ? 1 : 0);
            carry = sum >= 10;
        }

        return carry;
    }

    // 繰り下がりがあるかどうかをチェックするメソッド
    boolean hasBorrow(String num1, String num2) {
        int maxLength = Math.max(num1.length(), num2.length());

        // 繰り下がりの初期値はfalse
        boolean borrow = false;

        // 桁ごとに比較
        for (int i = 0; i < maxLength; i++) {
            // 各桁の数字を取得
            int digit1 = getDigit(num1, i);
            int digit2 = getDigit(num2, i);

            // 引き算して繰り下がりがあるかどうかを判定
            int difference = digit1 - digit2 - (borrow ? 1 : 0);
            borrow = difference < 0;
        }

        return borrow;
    }

    // 文字列から指定された桁の数字を取得するメソッド
    int getDigit(String num, int index) {
        if (index < 0 || index >= num.length()) {
            return 0; // 桁が存在しない場合は0を返す
        }
        return Character.getNumericValue(num.charAt(index));
    }

    public static record Question(
        Siki siki
    ) {
        public String answer() {
            switch(siki().operation) {
                case "+":
                    return Integer.toString(Integer.valueOf(siki.x1) + Integer.valueOf(siki.x2));
                case "-":
                    return Integer.toString(Integer.valueOf(siki.x1) - Integer.valueOf(siki.x2));
                case "*":
                    return Integer.toString(Integer.valueOf(siki.x1) * Integer.valueOf(siki.x2));
                case "/":
                    return Integer.toString(Integer.valueOf(siki.x1) / Integer.valueOf(siki.x2));
                default:
                    throw new IllegalArgumentException();
            }
        }
    }

    public static record Siki(
        String x1,
        String x2,
        String operation
    ) {
        private static final String space = " ";

        @Override
        public String toString() {
            return x1 + space + operation + space + x2 + space + "=" + space;
        }
    }
}
