import java.util.ArrayList;

import userDetails.*;
import game.ColorGame;
import tools.*;

public class UI {
    private static boolean userSelected;
    private static int userNumberSelected;

    public void init() {
        while(true) {
            Out.println("Welcome to Color Game!!!");
            if (users.isEmpty()) {
                Out.println("---No Users Made, Create a new user first.---");
            } else {
                System.out.println();
            }
            Out.println("(1) Play Color Game");
            Out.println("(2) Select / Create User");
            Out.println("(3) Wallet");
            Out.println("(4) Exit");
            select();
        }
    }

//    private void selectUserNumber() {
//        userNumberSelected = getUserNumber();
//    }

    private void select(){
        while(true){
            String input;
            try{
                input = Input.string("-> ");
                int i = Integer.parseInt(input);
                if(i<1 || i>4){
                    throw new ChoiceException();
                }
                performChoice(i);
                break;
            }catch(NumberFormatException e){
                Out.println("Input must be a number");
            }catch(ChoiceException c){
                Out.println("Input must be any of the ff: (1,2,3,4)");
            }
        }
    }

    private void performChoice(int choice){
        switch(choice){
            case PLAY:
                Out.line();
                play();
                break;
            case USERS:
                Out.line();
                 users();
                break;
            case WALLET:
                Out.line();
                wallet();
                break;
            case EXIT:
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

        int bet = ColorGame.play(100);
        //asd.updateWallet(bet);
    }
    private void users() {
        System.out.printf("");

        //Create User
        users.add(new BasicUser());
        //System.out.printf(users)
    }
    private void wallet() {
        if (users.isEmpty()) {
            Out.println("No Users present in database");
            Out.line();
            return;
        }
    }

    private static final int PLAY = 1;
    private static final int USERS = 2;
    private static final int WALLET = 3;
    private static final int EXIT = 4;

    ArrayList<User> users = new ArrayList<>();
}
