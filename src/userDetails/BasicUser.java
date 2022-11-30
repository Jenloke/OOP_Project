package userDetails;

import tools.*;

public class BasicUser extends User {
    public static int numberOfUsers;
    private int userNumber;
    public boolean selectedUser;

    public BasicUser() {
        super();
        initialize();
        userNumber = numberOfUsers++;
        selectedUser = true;
    }

    @Override
    public void initialize() {
        this.userName = Input.string("Enter your UserName: ");
        this.wallet = setWallet();
        Out.line();
    }

    private int setWallet() {
        while(true){
            String input;
            try{
                input = Input.string("Enter an amount to add to your wallet: ");
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

    public int getUserNumber() {
        return userNumber;
    }

    public void updateWallet(int i) {
        wallet += i;
    }
}
