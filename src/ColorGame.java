public class ColorGame extends RandomGenerator {
    private String[] colors = {"Red", "Green", "Blue", "Yellow", "Black", "White"};
    private int colorsSize = colors.length;

    public int getRandomColor() {
        int num = generateRandomNum(colorsSize);
        return num;
    }

    public String getRandomColorName(int i) {
        return colors[i];
    }

//    public bool getBet() {
//
//    }
}
