import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);

        /*
        posible var in driver code
            betsize should be taken here
        */

        //should only be possible when creating user
        //this object should be newUser instead of user1
        // default constructor should be

        System.out.println("Are you a new user?");
        String newUserAnswer = read.nextLine();

        if (newUserAnswer == "Yes") {
            String inputUserName;
            System.out.println("UserName:");
            inputUserName = read.nextLine();


            //BasicUser user1 = new BasicUser();
            System.out.println("Want to play now?");
        }
        //the user that is currently playing should be currentUser not user1
        BasicUser user1 = new BasicUser();

        user1.display();

        ColorGame game = new ColorGame(); // to access ColorGame Class Methods

        System.out.print("Enter bet amount: ");
        int betAmount = read.nextInt();

        //should be in Color Game Class
        int[] colorList;
        colorList = new int[3];
        for (int i = 0; i<3; i++) {
            int a = game.getRandomColor();
            colorList[i] = a;
            System.out.println(colorList[i]+1 + " " + game.getRandomColorName(a));
        }

        // getter lang naman ito
        int betColor = read.nextInt() - 1;

        int winMultiplier = 1;
        boolean win = false;

        // hedge chosen color against colorList
        // if true then multiplier 2,3 or 4
        for (int i=0; i < 3; i++) {
            if (betColor == colorList[i]) {
                winMultiplier++;
                win = true;
            }
        }

        //determine if win or lose
        if (win) {
            System.out.println("Win");
            int amountWon = betAmount*winMultiplier;
            user1.addWallet(amountWon);
        }else {
            System.out.println("Lose");
            user1.addWallet(betAmount*-1);
        }

        System.out.println(user1.getWallet());

        /*
            Game Logic
               enter bet amount
               select color

               store in a varColor  game.getRandomColor()

               evaluate input against varColor
                 if win wallet

               need a transaction class
        */
    }
}