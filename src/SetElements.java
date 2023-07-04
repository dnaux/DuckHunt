import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class SetElements {
    /**
     * Sets Label's properties
     *
     * @param label Label that will be changed
     * @param type Label's text type
     * @param size Label's text size
     * @param xLayout Label's X coordinate
     * @param yLayout Label's Y coordinate
     * @param colorCode Label's color code
     */
    public static void setLabelProperties(Label label, String type, double size, double xLayout, double yLayout, String colorCode) {
        label.setFont(new Font(type, size * Constants.SCALE));
        label.setLayoutX(xLayout * Constants.SCALE);
        label.setLayoutY(yLayout * Constants.SCALE);
        label.setTextFill(Paint.valueOf(colorCode));
    }

    /**
     * Sets ImageView's properties
     *
     * @param imageView ImageView that will be changed
     * @param fitWidth ImageView's width
     * @param fitHeight ImageView's height
     */
    public static void setImageViewProperties(ImageView imageView, double fitWidth, double fitHeight) {
        imageView.setFitHeight(fitHeight * Constants.SCALE);
        imageView.setFitWidth(fitWidth * Constants.SCALE);
    }

    /**
     * Sets AudioClips's properties
     *
     * @param path AudioClips's file path
     * @param cycleCount AudioClips's cycle count
     *
     * @return Set AudioClip
     */
    public static AudioClip setAudioClip(String path, int cycleCount) {
        AudioClip audioClip = new AudioClip(String.valueOf(SetElements.class.getResource(path)));
        audioClip.setVolume(Constants.VOLUME);
        audioClip.setCycleCount(cycleCount);

        return audioClip;
    }

    /**
     * Sets crosshair's position
     *
     * @param cross Crosshair that will get it's position set
     */
    public static void setCrossPlace(ImageView cross) {
        cross.setX(123 * Constants.SCALE);
        cross.setY(110 * Constants.SCALE);
    }

    /**
     * Sets the selection scene's properties
     *
     * @param background Selection scene's background
     * @param foreground Selection scene's foreground
     * @param cross Selection scene's crosshair
     *
     * @return Selection scene
     */
    public static Scene setSelectionScene(ImageView background, ImageView foreground, ImageView cross) {
        Label useArrowKeysToNavigate = new Label("USE ARROW KEYS TO NAVIGATE");
        Label pressEnterToStart = new Label("PRESS ENTER TO START");
        Label pressEscToExit = new Label("PRESS ESC TO EXIT");

        SetElements.setLabelProperties(useArrowKeysToNavigate, Constants.bold, 10.0, 51.0, 14.0, Constants.textColorCode);
        SetElements.setLabelProperties(pressEnterToStart, Constants.bold, 10.0, 72.0, 29.0, Constants.textColorCode);
        SetElements.setLabelProperties(pressEscToExit, Constants.bold, 10.0, 82.0, 44.0, Constants.textColorCode);

        Group group = new Group(background, foreground, cross, useArrowKeysToNavigate, pressEnterToStart, pressEscToExit);

        return new Scene(group, 256.0 * Constants.SCALE, 240.0 * Constants.SCALE);
    }
}
