package userDetails;

import tools.*;

public class UserExtension extends User {
    public static int totalUsers;

    public UserExtension() {
        super();
        totalUsers++;
        initialize();
    }

    @Override
    public void initialize() {
        this.userName = Input.string("Enter your UserName: ");
        this.wallet = setWallet();
        userNumber = totalUsers;
        Out.line();
    }

    private int setWallet() {
        while (true) {
            String input;
            try{
                input = Input.string("Enter an initial amount to add to your wallet: ");
                int i = Integer.parseInt(input);
                if(i<1){
                    throw new ChoiceException();
                }
                return Integer.parseInt(input);
            }catch(NumberFormatException e){
                Out.println("Input must be a number");
            }catch(ChoiceException c){
                Out.println("Input must be greater than zero.");
            }
        }
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public int getWallet() {
        return wallet;
    }

    @Override
    public int getUserNumber() {
        return userNumber;
    }

    @Override
    public void print() {
        Out.println( "User #" + userNumber + ", " + userName + " has " + wallet);
    }
}