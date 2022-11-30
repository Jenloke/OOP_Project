package game;

import tools.*;

public class ColorGame extends RandomGenerator {
    private static final String[] colors = {"Red", "Green", "Blue", "Yellow", "Black", "White"};

    // For Game Proper
    private static String[] rolledColors = new String[3];
    private static int[] rolledColorsValue = new int[3];
    private static final int roll = 3;
    private static int selectedColor;
    private static int betAmount;
    private static int winAmount;

    public static int play(int inputBetAmount) {
        setBetAmount(inputBetAmount);
        printColorList();
        set3RandomColor();
        selectColor();
        evalWin();

        return winAmount;
    }

    private static void setBetAmount(int inputBetAmount) {
        betAmount = inputBetAmount;
    }

    private static void selectColor() {
        while (true) {
            String input;
            try{
                input = Input.string("-> ");
                selectedColor = Integer.parseInt(input);
                if (selectedColor<1 || selectedColor>6) {
                    throw new ChoiceException();
                }
                selectedColor--;
                //performAction(i);
                break;
            } catch(NumberFormatException e) {
                System.out.println("Input must be a number");
            } catch(ChoiceException c) {
                System.out.println("Input must be any of the ff: (1,2,3,4,5,6)");
            }
        }
    }

    private static void printColorList() {
        for (int i=0; i < colors.length; i++) {
            int order = i + 1;
            System.out.printf("[%d]%s \n", order, colors[i]);
        }
    }

    private static void set3RandomColor() {
        for (int i=0; i < roll; i++) {
            int roll = generateRandomNum(colors.length);
            rolledColors[i] = colors[roll];
            print3RandomColor(i);
            rolledColorsValue[i] = roll;
        }
    }

    private static void print3RandomColor(int i) {
        System.out.println(rolledColors[i] + " " + i);
    }

    private static void evalWin() {
        int winMultiplier = 1;
        boolean win = false;

        for (int i = 0; i < 3; i++) {
            if (selectedColor == rolledColorsValue[i]) {
                winMultiplier++;
                win = true;
            }
        }

        Out.line();

        if (win) {
            winAmount = betAmount * winMultiplier;
            System.out.printf("You Win %d\n", winAmount);
            System.out.printf("Bet Amount: %d times %d\n", betAmount, winMultiplier);
        } else {
            winAmount = 0;
            System.out.printf("You Lose your %d\n", betAmount);
        }

        Out.line();
    }
}