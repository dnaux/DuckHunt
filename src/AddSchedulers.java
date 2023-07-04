import javafx.scene.control.Label;
import javafx.scene.transform.Rotate;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class AddSchedulers {
    /**
     * Adds scheduler to lable to toggle it's visibility
     *
     * @param lb1 Label that will get it's visibility toggled
     */
    public static void titleLabelVisibilityChangeToggle(Label lb1) {
        Runnable textSwitch = () -> lb1.setVisible(!lb1.isVisible());

        ScheduledExecutorService textSwitchScheduler = Executors.newScheduledThreadPool(1);
        ScheduledFuture<?> textSwitchScheduledFuture = textSwitchScheduler.scheduleAtFixedRate(textSwitch, 1, 1, TimeUnit.SECONDS);
    }

    /**
     * Adds scheduler to simulate duck animation
     *
     * @param duck Duck that will get animated
     */
    public static void duckMotion(Duck duck) {
        Runnable textSwitch = () -> {
            double newX = duck.getDuckSprite().getX() + duck.getHorizontalVelocity();
            double newY = duck.getDuckSprite().getY() + duck.getVerticalVelocity();
            double checkX = newX + duck.getHorizontalVelocity() + duck.getDuckSprite().getFitWidth();
            double checkY = newY + duck.getVerticalVelocity() + duck.getDuckSprite().getFitHeight();

            duck.getDuckSprite().setX(newX);
            duck.getDuckSprite().setY(newY);
            duck.nextAnimationStep();

            if(checkX < 0 || checkX > Constants.SCREEN_WIDTH * Constants.SCALE) {
                duck.switchHorizontalVelocity();

                if(duck.getDirection() == 0) {
                    duck.getDuckSprite().setRotationAxis(Rotate.Y_AXIS);
                    duck.getDuckSprite().setRotate(duck.getDuckSprite().getRotate() + 180);
                } else {
                    duck.getDuckSprite().setRotationAxis(Rotate.Z_AXIS);
                    duck.fixRotation();
                }
            }

            if(checkY < 0 || checkY > Constants.SCREEN_HEIGHT * Constants.SCALE) {
                duck.switchVerticalVelocity();
                duck.getDuckSprite().setRotationAxis(Rotate.Z_AXIS);
                duck.fixRotation();
            }
        };
        ScheduledExecutorService textSwitchScheduler = Executors.newScheduledThreadPool(1);
        ScheduledFuture<?> textSwitchScheduledFuture = textSwitchScheduler.scheduleAtFixedRate(textSwitch, 10, 250, TimeUnit.MILLISECONDS);
    }
}
