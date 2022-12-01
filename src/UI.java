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
                play();
                break;
            case USERS_UI:
                Out.line();
                users();
                break;
            case WALLET_UI:
                Out.line();
                wallet();
                break;
            case EXIT_UI:
                Out.line();
                Out.println("Thanks for playing Color Game!!!");
                System.exit(0);
                break;
        }
    }

    private void play() {
        if (users.isEmpty()) {
            Out.println("No Users present in database");
            Out.line();
            return;
        }

        int walletUpdate = ColorGame.play(100);
        //asd.walletUpdate(bet);
    }
    private void users() {
        Out.println("Users Menu");
        evalUserBase();
        usersMenu();
    }
    private void wallet() {
        if (users.isEmpty()) {
            Out.println("No Users present in database");
            Out.line();
        }
    }

    private void evalUserBase() {
        if (userNumberSelected != 0) {
            users.get(userNumberSelected).print();
        } else {
            if (users.isEmpty()) {
                Out.println("---No Users Made, Create a new user first.---");
            } else {
                System.out.println("No user currently logged in.");
            }
        }
    }

    private void usersMenu() {
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
                if (users.isEmpty() || userSelected == false) {
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
                if(users.isEmpty()){
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
                if (i < 1 || i > BasicUser.totalUsers-1) {
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
        users.get(userNumberSelected).print();
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

        Out.println(input + " " + users.get(input).getWallet());
        Out.line();
    }

    private void createUsers() {
        Out.line();
        users.add(new BasicUser());

        Out.println("User has been created.");
        Out.println("User #" + users.get(users.size()-1).getUserNumber());
        Out.line();
    }

    private void viewUsers() {
        Out.println("Color Game UserBase");
        for(int i = 0; i < users.size(); i++) {
            System.out.println("User #" + (i+1) + " ");
            users.get(i).print();
        }
        Out.line();
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

    ArrayList<User> users = new ArrayList<>();
}