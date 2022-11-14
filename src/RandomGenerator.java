import  java.util.Random;
public class RandomGenerator {
    protected int generateRandomNum(int bound) {
        Random rand = new Random();
        return rand.nextInt(bound);
    }
}
