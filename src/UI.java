import userDetails.*;
import game.ColorGame;
import tools.*;

public class UI {
    public void init() {
        Out.println("Welcome to Color Game!!!");
        while(true) {
            //users.User method or create new user object
            Out.println("(1) Play Color Game");
            Out.println("(2) Select users.User");
            Out.println("(3) Wallet");
            Out.println("(4) Exit");
            select();
        }
    }

    private void select(){
        while(true){
            String input;
            try{
                input = Input.string("-> ");
                int i = Integer.parseInt(input);
                if(i<1 || i>5){
                    throw new ChoiceException();
                }
                performAction(i);
                break;
            }catch(NumberFormatException e){
                Out.println("Input must be a number");
            }catch(ChoiceException c){
                Out.println("Input must be any of the ff: (1,2,3,4)");
            }
        }
    }

    private void performAction(int choice){
        switch(choice){
            case PLAY:
                play();
                break;
            case USERS:
                 users();
                break;
            case WALLET:
                wallet();
                break;
            case EXIT:
                System.exit(0);
                break;
        }
    }

    private void play() {
        //get beat Amount method
        ColorGame.play(100);
    }
    private void users() {

    }
    private void wallet() {

    }

    private static final int PLAY = 1;
    private static final int USERS = 2;
    private static final int WALLET = 3;
    private static final int EXIT = 4;
}
