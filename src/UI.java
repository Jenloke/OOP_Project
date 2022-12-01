// https://github.com/Jenloke/OOP_Project
import java.util.ArrayList;

import userDetails.*;
import game.ColorGame;
import tools.*;

public class UI {
    private static boolean userSelected = false;
    private static int userNumberSelected = 0;

    public void init() {
        while (true) {
            Out.println("Welcome to Color Game!!!");
            Out.println("Number of Color Game Users: " + users.size());
            evalUserBase();

            Out.println("(1) Play Color Game");
            Out.println("(2) Select / Create User");
            Out.println("(3) Wallet");
            Out.println("(4) Exit");
            selectUI();
        }
    }

    private void selectUI() {
        while (true) {
            String input;
            try {
                input = Input.string("-> ");
                int i = Integer.parseInt(input);
                if (i<1 || i>4) {
                    throw new ChoiceException();
                }
                performChoiceUI(i);
                break;
            } catch (NumberFormatException e) {
                Out.println("Input must be a number");
            } catch (ChoiceException c) {
                Out.println("Input must be any of the ff: (1,2,3,4)");
            }
        }
    }

    private void performChoiceUI (int choice) {
        switch (choice) {
            case PLAY_UI:
                Out.line();
                if (users.isEmpty()) {
                    Out.println("No Users present in database");
                    Out.line();
                    break;
                } else if (!userSelected) {
                    Out.println("No user currently logged in.");
                    Out.line();
                    break;
                }
                play();
                break;
            case USERS_UI:
                Out.line();
                usersMenu();
                break;
            case WALLET_UI:
                Out.line();
                if (users.isEmpty()) {
                    Out.println("No Users present in database");
                    Out.line();
                    break;
                } else if (!userSelected) {
                    Out.println("No user currently logged in.");
                    Out.line();
                    break;
                }
                walletMenu();
                break;
            case EXIT_UI:
                Out.line();
                Out.println("Thanks for playing Color Game!!!");
                System.exit(0);
                break;
        }
    }

    private void evalUserBase() {
        if (userNumberSelected > 0) {
            Out.println("Currently Logged in User:");
            users.get(userNumberSelected-1).print();
        } else {
            if (users.isEmpty()) {
                Out.println("---No Users Made, Create a new user first.---");
            } else {
                Out.println("No user currently logged in.");
            }
        }
    }

    private void play() {
        if (users.get(userNumberSelected-1).getWallet() == 0) {
            Out.println("User #" + users.get(userNumberSelected-1).getUserNumber() + " has no funds inside wallet to play.");
        } else {
            Out.println("Your wallet has " + users.get(userNumberSelected-1).getUserNumber());
            int bet = betAmount();
            users.get(userNumberSelected-1).updateWallet(-bet);
            Out.line();

            int resultAmount = ColorGame.play(bet);
            users.get(userNumberSelected-1).updateWallet(resultAmount);
        }
        Out.line();
    }

    private int betAmount() {
        while (true) {
            String input;
            try {
                input = Input.string("Enter your bet amount: ");
                int i = Integer.parseInt(input);
                if (i < 1 || i > users.get(userNumberSelected-1).getWallet()) {
                    throw new ChoiceException();
                }
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                Out.println("Input must be a number");
            } catch (ChoiceException c) {
                Out.println("Amount you have entered is greater than what is in your wallet.");
            }
        }
    }

    private void usersMenu() {
        Out.println("Users Menu");
        evalUserBase();
        Out.println("(1) Logout Current User");
        Out.println("(2) Change to existing User");
        Out.println("(3) Create new User");
        Out.println("(4) Back to Menu");
        Out.println("(5) View Users");
        selectUsers();
    }

    private void selectUsers() {
        while (true) {
            String input;
            try {
                input = Input.string("-> ");
                int i = Integer.parseInt(input);
                if(i<1 || i>5){
                    throw new ChoiceException();
                }
                performChoiceUsers(i);
                break;
            } catch (NumberFormatException e) {
                Out.println("Input must be a number");
            } catch (ChoiceException c) {
                Out.println("Input must be any of the ff: (1,2,3,4,5)");
            }
        }
    }

    private void performChoiceUsers(int choice) {
        switch (choice) {
            case LOGOUT_USER:
                if (users.isEmpty() || !userSelected) {
                    Out.println("No users to logout.");
                    Out.line();
                    break;
                }
                logoutUser();
                break;
            case CHANGE_USER:
                Out.line();
                if (users.isEmpty()) {
                    Out.println("No users available to change into.");
                    Out.line();
                    break;
                }
                changeUser();
                break;
            case CREATE_USER:
                createUsers();
                break;
            case BACK_USER:
                Out.line();
                break;
            case VIEW_USER:
                Out.line();
                if (users.isEmpty()) {
                    Out.println("No users available.");
                }
                viewUsers();
                break;
        }
    }

    private int selectUserNumber() {
        while (true) {
            String input;
            try {
                input = Input.string("Enter the user number of your changed user : ");
                int i = Integer.parseInt(input);
                if (i < 1 || i > UserExtension.totalUsers) {
                    throw new ChoiceException();
                }
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                Out.println("Input must be a number");
            } catch (ChoiceException c) {
                Out.println("User Number may have not yet been generated.");
            }
        }
    }

    private void logoutUser() {
        users.get(userNumberSelected-1).print();
        Out.line();
        Out.println("User has successfully logged out.");
        Out.line();

        userSelected = false;
        userNumberSelected = 0;
    }

    private void changeUser() {
        int input = selectUserNumber();
        userSelected = true;
        userNumberSelected = input;

        Out.println(input + " " + users.get(userNumberSelected-1).getWallet());
        Out.line();
    }

    private void createUsers() {
        Out.line();
        users.add(new UserExtension());

        userSelected = true;
        userNumberSelected = users.get(UserExtension.totalUsers-1).getUserNumber();

        Out.println("User has been created.");
        Out.println("User #" + users.get(UserExtension.totalUsers-1).getUserNumber());
        Out.line();
    }

    private void viewUsers() {
        Out.println("Color Game UserBase");
        for(int i = 0; i < users.size(); i++) {
            Out.println("User #" + (i+1) + " ");
            users.get(i).print();
        }
        Out.line();
    }

    private void walletMenu() {
        Out.println("Wallet Menu");
        users.get(userNumberSelected-1).print();
        evalUserBase();
        Out.println("(1) Deposit to Wallet");
        Out.println("(2) Withdraw from Wallet");
        Out.println("(3) Back");
        selectWallet();
    }

    private void selectWallet() {
        while (true) {
            String input;
            try {
                input = Input.string("-> ");
                int i = Integer.parseInt(input);
                if(i<1 || i>3){
                    throw new ChoiceException();
                }
                performChoiceWallet(i);
                break;
            } catch (NumberFormatException e) {
                Out.println("Input must be a number");
            } catch (ChoiceException c) {
                Out.println("Input must be any of the ff: (1,2,3)");
            }
        }
    }

    private void performChoiceWallet(int choice) {
        Out.line();
        switch (choice) {
            case ADD_WALLET:
                int addAmount = addWallet();
                users.get(userNumberSelected-1).updateWallet(addAmount);
                Out.line();
                Out.println("You have successfully added " + addAmount + " to your wallet.");
                Out.line();
                break;
            case OUT_WALLET:
                if (users.get(userNumberSelected-1).getWallet() == 0) {
                    Out.println("You can't withdraw from your wallet since it does not have a balance.");
                } else {
                    int outAmount = outWallet();
                    users.get(userNumberSelected-1).updateWallet(-outAmount);
                    Out.line();
                    Out.println("You have successfully withdrawn " + outAmount + " to your wallet.");
                    Out.line();
                }
                break;
            case BACK_WALLET:
                Out.line();
                break;
        }
    }

    private int addWallet() {
        while (true) {
            String input;
            try {
                input = Input.string("Enter the amount you want to add: ");
                int i = Integer.parseInt(input);
                if (i < 1) {
                    throw new ChoiceException();
                }
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                Out.println("Input must be a number");
            } catch (ChoiceException c) {
                Out.println("The amount you're trying to deposit is less than zero.");
            }
        }
    }

    private int outWallet() {
        while (true) {
            String input;
            try {
                input = Input.string("Enter the amount you want to withdraw: ");
                int i = Integer.parseInt(input);
                if (i < 1) {
                    Out.println("The amount you're trying to withdraw is less than zero.");
                    continue;
                }
                if (i > users.get(userNumberSelected-1).getWallet()) {
                    throw new ChoiceException();
                }
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                Out.println("Input must be a number");
            } catch (ChoiceException c) {
                Out.println("The amount you're trying to withdraw is greater than your current wallet value.");
            }
        }
    }

    // UI Choices
    private static final int PLAY_UI = 1;
    private static final int USERS_UI = 2;
    private static final int WALLET_UI = 3;
    private static final int EXIT_UI = 4;

    // USERS Choices
    private static final int LOGOUT_USER = 1;
    private static final int CHANGE_USER = 2;
    private static final int CREATE_USER = 3;
    private static final int BACK_USER = 4;
    private static final int VIEW_USER = 5;

    // WALLET Choices
    public static final int ADD_WALLET = 1;
    public static final int OUT_WALLET = 2;
    public static final int BACK_WALLET = 3;

    ArrayList<User> users = new ArrayList<>();
}