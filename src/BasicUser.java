import java.util.Scanner;

public class BasicUser extends User {

    BasicUser () {
//        setUserName();
//        setWallet();
        userName = "ape";
        wallet = 0;
    }

    // Setter Cluster
    public void setUserName() {
        Scanner readUserInput = new Scanner(System.in);
        System.out.print("Enter a new username: ");
        userName = readUserInput.nextLine();
    }

    public void setWallet() {
        Scanner readUserInput = new Scanner(System.in);
        System.out.print("Enter an amount you would to add to your wallet: ");
        wallet = readUserInput.nextInt();
    }

    public void display() {
        System.out.println(userName);
        System.out.println(wallet);
    }

    // Getter Cluster
    public String getUserName() {
        return userName;
    }

    public int getWallet() {
        return wallet;
    }

    public void addWallet(int w) {
        wallet += w;
    }
}
