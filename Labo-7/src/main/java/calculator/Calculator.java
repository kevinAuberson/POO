package calculator;

import operator.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {

        // class to be able to use the calculator from a terminal / console.
        // all the different operations can be done by the user with the keyboard.

        State state = State.getInstance();
        Scanner scan = new Scanner(System.in);
        HashMap<String, Operator> operatorMap = new HashMap<>();

        operatorMap.put("+", new AddOperator());
        operatorMap.put("-", new SubOperator());
        operatorMap.put("*", new MultOperator());
        operatorMap.put("/", new DividOperator());
        operatorMap.put("1/x", new OneOverX());
        operatorMap.put("sqrt", new SqrtOperator());
        operatorMap.put("x^2", new PowTwoOperator());
        operatorMap.put("ms", new MemoryStore());
        operatorMap.put("mr", new MemoryRecall());
        operatorMap.put("c", new ClearValue());
        operatorMap.put("ce", new ClearError());
        operatorMap.put("enter", new Enter());
        operatorMap.put("§", new Sign());

        // welcome line
        System.out.println("Welcome to the terminal calculator");
        System.out.println("Start your calculations...");

        String line;
        boolean changeSign = false;

        System.out.println("Caculator enter your operations : [Operation] : (command)");
        System.out.println("[addition] : (+)");
        System.out.println("[substraction] (-)");
        System.out.println("[mutliplication] : (*)");
        System.out.println("[divison] : (/)");
        System.out.println("[change sign] : (§)");
        System.out.println("[square root] : (sqrt)");
        System.out.println("[oneOver] : (1/x)");
        System.out.println("[power of two] : (x^2)");
        System.out.println("[memory save] : (ms)");
        System.out.println("[memory recall] : (mr)");
        System.out.println("[clear] : (c)");
        System.out.println("[clear error] : (ce)");
        System.out.println("[push the number into the stack] : (\"enter\")");

        while (true) {

            System.out.print("=> ");
            line = scan.nextLine().trim().toLowerCase();

            Operator key = operatorMap.get(line);

            if (line.equals("exit")) {
                break;
            } else if (key != null) {
                key.execute();
            } else {
                if (!line.isEmpty()) {
                    if (line.charAt(0) == '-') {
                        changeSign = true;
                        line = line.substring(1);
                    }

                    if (line.charAt(0) == '+') {
                        line = line.substring(1);
                    }

                    for (int i = 0; i < line.length(); i++) {
                        char a = line.charAt(i);
                        if (a == '.') {
                            new Dot().execute();
                        } else if (a != ' ') {
                            try {
                                new Digit(Integer.parseInt(a + "")).execute();
                            } catch (NumberFormatException e) {
                                System.err.println("Format not valid");
                                break;
                            }
                        }
                    }

                    if (changeSign) {
                        new Sign().execute();
                    }
                }
            }
            System.out.println(state.getValueString());
            System.out.println(Arrays.toString(state.getStackState()));
        }
        scan.close();
    }
}
