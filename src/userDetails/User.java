package userDetails;

public abstract class User {
    public String userName;
    public int wallet;
    public int userNumber;

    public abstract void initialize();

    public abstract int getWallet();

    public abstract int getUserNumber();

    public void print() {}
}
