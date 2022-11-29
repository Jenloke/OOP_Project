import java.util.Scanner;

public class BasicUser extends User {

    BasicUser () {
        userName = "ape";
        wallet = 0;
    }

    // Setter Cluster
    public void setUserName() {
        Scanner readUserInput = new Scanner(System.in);
        System.out.print("Enter a new username: ");
        userName = readUserInput.nextLine();
    }

    public void updateWallet(int i) {
        wallet += i;
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
}
