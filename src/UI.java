import tools.*;

public class UI {
    public void init() {
        println("Welcome to Color Game!!!");
        while(true) {
            //User method
            println("(1) Play Color Game");
            println("(2) Select User");
            println("(3) Wallet");
            println("(4) Exit");
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
                println("Input must be a number");
            }catch(ChoiceException c){
                println("Input must be any of the ff: (1,2,3,4)");
            }
        }
    }

    private void performAction(int choice){
        switch(choice){
            case PLAY:
                println("1"); // call a method
                break;
            case USERS:
                println("2"); // call a method
                break;
            case WALLET:
                println("3"); // call a method
                break;
            case EXIT:
                System.exit(0);
                break;
        }
    }


    private static final int PLAY = 1;
    private static final int USERS = 2;
    private static final int WALLET = 3;
    private static final int EXIT = 4;


    private void println(String s) {
        System.out.println(s);
    }
}
