package game;

import tools.*;

public class ColorGame extends RandomGenerator {
    private static final String[] colors = {"Red", "Green", "Blue", "Yellow", "Black", "White"};

    // For Game Proper
    private static final String[] rolledColors = new String[3];
    private static final int[] rolledColorsValue = new int[3];
    private static final int roll = 3;
    private static int selectedColor;
    private static int betAmount;
    private static int winAmount;

    public static int play(int inputBetAmount) {
        setBetAmount(inputBetAmount);
        printColorList();
        selectColor();

        set3RandomColor();
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
                break;
            } catch(NumberFormatException e) {
                System.out.println("Input must be a number");
            } catch(ChoiceException c) {
                System.out.println("Input must be any of the ff: (1,2,3,4,5,6)");
            }
        }
    }

    private static void printColorList() {
        Out.println("Pick your color based on its corresponding number.");
        for (int i=0; i < colors.length; i++) {
            int order = i + 1;
            System.out.printf("[%d]%s \n", order, colors[i]);
        }
    }

    private static void set3RandomColor() {
        for (int i=0; i < roll; i++) {
            int roll = generateRandomNum(colors.length);
            rolledColors[i] = colors[roll];
            rolledColorsValue[i] = roll;
        }
        print3RandomColor();
    }

    private static void print3RandomColor() {
        Out.line();
        Out.println("The 3 random colors rolled are:");
        for (int i=0; i < roll; i++) {
            int displayVal = rolledColorsValue[i] + 1;
            System.out.printf("[%d] %s\n", displayVal, rolledColors[i]);
        }
        Out.line();
    }

    private static void evalWin() {
        boolean win = false;
        int winMultiplier = 1;
        for (int i = 0; i < 3; i++) {
            if (selectedColor == rolledColorsValue[i]) {
                winMultiplier++;
                win = true;
            }
        }
        printWin(win, winMultiplier);
    }

    private static void printWin(boolean win, int winMultiplier) {
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