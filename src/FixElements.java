import javafx.scene.image.ImageView;

public class FixElements {
    /**
     * Fixes the value to be in the given range
     *
     * @param value Value that will be fixed
     * @param max Max value
     * @param min Min value
     *
     * @return Fixed value
     */
    public static int fixVal(int value, int max, int min) {
        if(value > max)
            value = min;
        else if(value < min)
            value = max;

        return value;
    }

    /**
     * Fixes the background sprite
     *
     * @param curBG Current background number
     *
     * @return Background as ImageView
     */
    public static ImageView fixBG(int curBG) {
        ImageView newBG = new ImageView(String.valueOf(FixElements.class.getResource("assets/background/" + curBG + ".png")));
        SetElements.setImageViewProperties(newBG, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        return newBG;
    }

    /**
     * Fixes the foreground sprite
     *
     * @param curFG Current foreground number
     *
     * @return Foreground as ImageView
     */
    public static ImageView fixFG(int curFG) {
        ImageView newFG = new ImageView(String.valueOf(FixElements.class.getResource("assets/foreground/" + curFG + ".png")));
        SetElements.setImageViewProperties(newFG, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        return newFG;
    }

    /**
     * Fixes the crosshair sprite
     *
     * @param curCross Current crosshair number
     *
     * @return Crosshair as ImageView
     */
    public static ImageView fixCross(int curCross) {
        ImageView newCross = new ImageView(String.valueOf(FixElements.class.getResource("assets/crosshair/" + curCross + ".png")));

        SetElements.setImageViewProperties(newCross, 11, 11);
        SetElements.setCrossPlace(newCross);

        return newCross;
    }
}
