import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Duck {
    private double horizontalVelocity;
    private double verticalVelocity;
    private int curAnimationStep;
    private int minAnimationStep;
    private int maxAnimationStep;
    private final int direction;
    private final ImageView duckSprite = new ImageView();
    private final String path;

    /**
     * Constructor function
     *
     * @param direction Direction of the duck
     * @param path String path of the ducks sprites
     * @param firstPos Initial vertical position of the duck
     */
    public Duck(int direction, String path, double firstPos) {
        this.direction = direction;
        this.path = path;
        setVelocities();
        setAnimation();
        fixRotation();
        updateImage();

        SetElements.setImageViewProperties(duckSprite, Constants.DUCK_WIDTH, Constants.DUCK_HEIGHT);
        duckSprite.setY(firstPos * Constants.SCALE);

        AddSchedulers.duckMotion(this);
    }

    /**
     * Sets horizontal and vertical velocities
     */
    private void setVelocities() {
        horizontalVelocity = Constants.VELOCITY_CONSTANT * Constants.SCALE;
        verticalVelocity = Constants.VELOCITY_CONSTANT * Constants.SCALE * direction;
    }

    /**
     * Sets animation steps
     */
    private void setAnimation() {
        if(direction == 0) {
            curAnimationStep = 4;
            minAnimationStep = 4;
            maxAnimationStep = 6;
        } else {
            curAnimationStep = 1;
            minAnimationStep = 1;
            maxAnimationStep = 3;
        }
    }

    public double getHorizontalVelocity() {
        return horizontalVelocity;
    }

    public void switchHorizontalVelocity() {
        horizontalVelocity = -horizontalVelocity;
    }

    public double getVerticalVelocity() {
        return verticalVelocity;
    }

    public void switchVerticalVelocity() {
        verticalVelocity = -verticalVelocity;
    }

    public int getDirection() {
        return this.direction;
    }

    public ImageView getDuckSprite() {
        return duckSprite;
    }

    /**
     * Fixes the rotation based on vertical and horizontal velocities
     */
    public void fixRotation() {
        int degree = 0;

        if(horizontalVelocity < 0)
            degree += 90;
        if(verticalVelocity > 0)
            degree -= 270;

        if(degree > 0)
            degree = -degree;

        duckSprite.setRotate(degree);
    }

    /**
     * Updates the duck sprite to the next in animation step
     */
    private void updateImage() {
        duckSprite.setImage(new Image(String.valueOf(getClass().getResource(path + curAnimationStep + ".png"))));
        duckSprite.setId("Duck");
    }

    /**
     * Gets animation to the next step
     */
    public void nextAnimationStep() {
        curAnimationStep++;
        curAnimationStep = FixElements.fixVal(curAnimationStep, maxAnimationStep, minAnimationStep);

        updateImage();
    }
}
