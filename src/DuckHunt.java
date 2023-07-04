import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class DuckHunt {
    private final Stage primaryStage;
    private int curBG = 1;
    private int curFG = 1;
    private int curCross = 1;
    private int currentBulletCount = 0;
    private int ducksToBeShot = 0;
    private int ducksShot = 0;
    private int curLevel = 6;
    private boolean titleSongPlaying = false;
    private ImageView background = new ImageView(String.valueOf(getClass().getResource(Constants.bgPath + curBG + ".png")));
    private ImageView foreground = new ImageView(String.valueOf(getClass().getResource(Constants.fgPath + curFG + ".png")));
    private ImageView cross = new ImageView(String.valueOf(getClass().getResource(Constants.crossPath + curCross + ".png")));


    public DuckHunt(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     * Starts the game by getting title screen
     */
    public void start() {
        Scene titleScreenScene = setTitleScreen();

        primaryStage.setScene(titleScreenScene);
        primaryStage.show();
    }

    /**
     * Creates the title scene and sets it's properties
     *
     * @return The title scene as type Scene
     */
    private Scene setTitleScreen() {
        ImageView titleScreenBG = new ImageView(new Image(String.valueOf(getClass().getResource(Constants.titleScreenImagePath))));
        Label pressEnterToStart = new Label("PRESS ENTER TO START");
        Label pressEscToExit = new Label("PRESS ESC TO EXIT");
        AudioClip titleSong = SetElements.setAudioClip(Constants.titleSong, AudioClip.INDEFINITE);

        SetElements.setImageViewProperties(titleScreenBG, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        SetElements.setLabelProperties(pressEnterToStart, Constants.bold, 16.0, 38.0, 144.0, Constants.textColorCode);
        SetElements.setLabelProperties(pressEscToExit, Constants.bold, 16.0, 57.0, 169.0, Constants.textColorCode);
        AddSchedulers.titleLabelVisibilityChangeToggle(pressEnterToStart);
        AddSchedulers.titleLabelVisibilityChangeToggle(pressEscToExit);

        if(!titleSongPlaying) {
            titleSong.play();
            titleSongPlaying = true;
        }

        Group root = new Group(titleScreenBG, pressEnterToStart, pressEscToExit);
        Scene scene = new Scene(root, Constants.SCREEN_WIDTH * Constants.SCALE, Constants.SCREEN_HEIGHT * Constants.SCALE);

        scene.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
            if(KeyCode.ESCAPE == event.getCode()) {
                Platform.exit();
                System.exit(0);
            } else if (KeyCode.ENTER == event.getCode()) {
                getSelectionScreen(titleSong);
            }
        });

        return scene;
    }

    /**
     * Creates the selection scene and sets it's properties
     *
     * @param titleSong Sound that will be played during scene
     */
    private void getSelectionScreen(AudioClip titleSong) {
        curBG = 1;
        curFG = 1;
        curCross = 1;

        background = FixElements.fixBG(curBG);
        foreground = FixElements.fixFG(curFG);
        cross = FixElements.fixCross(curCross);

        Scene curScene = SetElements.setSelectionScene(background, foreground, cross);
        addSelectionEventHandler(curScene, titleSong);

        primaryStage.setScene(curScene);
    }

    /**
     * Creates 1st level screen and sets it's properties
     */
    private void level1() {
        levelStart(1, 1);

        Duck duck = new Duck(0, Constants.blackDuck, 10);
        Label bulletCount = new Label("Ammo Left: " + currentBulletCount);
        Label levelLabel = new Label("Level 1/6");
        Group root = new Group(background, duck.getDuckSprite(), foreground, bulletCount, levelLabel);
        Scene scene = new Scene(root, Constants.SCREEN_WIDTH * Constants.SCALE, Constants.SCREEN_HEIGHT * Constants.SCALE);
        Image crossImage = cross.getImage();

        SetElements.setLabelProperties(bulletCount, Constants.bold, 8.0, 203.0, 0.0, Constants.textColorCode);
        SetElements.setLabelProperties(levelLabel, Constants.bold, 8.0, 110, 0.0, Constants.textColorCode);
        scene.setCursor(new ImageCursor(crossImage, crossImage.getWidth() / 2, crossImage.getHeight() / 2));
        addHitHandler(scene, bulletCount);

        this.primaryStage.setScene(scene);
    }

    /**
     * Creates 2nd level screen and sets it's properties
     */
    private void level2() {
        levelStart(1, 2);

        Duck duck = new Duck(1, Constants.blackDuck, 10);
        Label bulletCount = new Label("Ammo Left: " + currentBulletCount);
        Label levelLabel = new Label("Level 2/6");
        Group root = new Group(background, duck.getDuckSprite(), foreground, bulletCount, levelLabel);
        Scene scene = new Scene(root, Constants.SCREEN_WIDTH * Constants.SCALE, Constants.SCREEN_HEIGHT * Constants.SCALE);
        Image crossImage = cross.getImage();

        SetElements.setLabelProperties(bulletCount, Constants.bold, 8.0, 203.0, 0.0, Constants.textColorCode);
        SetElements.setLabelProperties(levelLabel, Constants.bold, 8.0, 110, 0.0, Constants.textColorCode);
        scene.setCursor(new ImageCursor(crossImage, crossImage.getWidth() / 2, crossImage.getHeight() / 2));
        addHitHandler(scene, bulletCount);

        this.primaryStage.setScene(scene);
    }

    /**
     * Creates 3rd level screen and sets it's properties
     */
    private void level3() {
        levelStart(2, 3);

        Duck duck1 = new Duck(0, Constants.blackDuck, 10);
        Duck duck2 = new Duck(0, Constants.blueDuck, 50);
        Label bulletCount = new Label("Ammo Left: " + currentBulletCount);
        Label levelLabel = new Label("Level 3/6");
        Group root = new Group(background, duck1.getDuckSprite(), duck2.getDuckSprite(), foreground, bulletCount, levelLabel);
        Scene scene = new Scene(root, Constants.SCREEN_WIDTH * Constants.SCALE, Constants.SCREEN_HEIGHT * Constants.SCALE);
        Image crossImage = cross.getImage();

        SetElements.setLabelProperties(bulletCount, Constants.bold, 8.0, 203.0, 0.0, Constants.textColorCode);
        SetElements.setLabelProperties(levelLabel, Constants.bold, 8.0, 110, 0.0, Constants.textColorCode);
        scene.setCursor(new ImageCursor(crossImage, crossImage.getWidth() / 2, crossImage.getHeight() / 2));
        addHitHandler(scene, bulletCount);

        this.primaryStage.setScene(scene);
    }

    /**
     * Creates 4th level screen and sets it's properties
     */
    private void level4() {
        levelStart(2, 4);

        Duck duck1 = new Duck(0, Constants.blackDuck, 10);
        Duck duck2 = new Duck(1, Constants.blueDuck, 50);
        Label bulletCount = new Label("Ammo Left: " + currentBulletCount);
        Label levelLabel = new Label("Level 4/6");
        Group root = new Group(background, duck1.getDuckSprite(), duck2.getDuckSprite(), foreground, bulletCount, levelLabel);
        Scene scene = new Scene(root, Constants.SCREEN_WIDTH * Constants.SCALE, Constants.SCREEN_HEIGHT * Constants.SCALE);
        Image crossImage = cross.getImage();

        SetElements.setLabelProperties(bulletCount, Constants.bold, 8.0, 203.0, 0.0, Constants.textColorCode);
        SetElements.setLabelProperties(levelLabel, Constants.bold, 8.0, 110, 0.0, Constants.textColorCode);
        scene.setCursor(new ImageCursor(crossImage, crossImage.getWidth() / 2, crossImage.getHeight() / 2));
        addHitHandler(scene, bulletCount);

        this.primaryStage.setScene(scene);
    }

    /**
     * Creates 5th level screen and sets it's properties
     */
    private void level5() {
        levelStart(3, 5);

        Duck duck1 = new Duck(0, Constants.blackDuck, 10);
        Duck duck2 = new Duck(0, Constants.blueDuck, 50);
        Duck duck3 = new Duck(1, Constants.redDuck, 90);
        Label bulletCount = new Label("Ammo Left: " + currentBulletCount);
        Label levelLabel = new Label("Level 5/6");
        Group root = new Group(background, duck1.getDuckSprite(), duck2.getDuckSprite(), duck3.getDuckSprite(), foreground, bulletCount, levelLabel);
        Scene scene = new Scene(root, Constants.SCREEN_WIDTH * Constants.SCALE, Constants.SCREEN_HEIGHT * Constants.SCALE);
        Image crossImage = cross.getImage();

        SetElements.setLabelProperties(bulletCount, Constants.bold, 8.0, 203.0, 0.0, Constants.textColorCode);
        SetElements.setLabelProperties(levelLabel, Constants.bold, 8.0, 110, 0.0, Constants.textColorCode);
        scene.setCursor(new ImageCursor(crossImage, crossImage.getWidth() / 2, crossImage.getHeight() / 2));
        addHitHandler(scene, bulletCount);

        this.primaryStage.setScene(scene);
    }

    /**
     * Creates 6th level screen and sets it's properties
     */
    private void level6() {
        levelStart(3, 6);

        Duck duck1 = new Duck(1, Constants.blackDuck, 10);
        Duck duck2 = new Duck(1, Constants.blueDuck, 50);
        Duck duck3 = new Duck(1, Constants.redDuck, 90);
        Label bulletCount = new Label("Ammo Left: " + currentBulletCount);
        Label levelLabel = new Label("Level 6/6");
        Group root = new Group(background, duck1.getDuckSprite(), duck2.getDuckSprite(), duck3.getDuckSprite(), foreground, bulletCount, levelLabel);
        Scene scene = new Scene(root, Constants.SCREEN_WIDTH * Constants.SCALE, Constants.SCREEN_HEIGHT * Constants.SCALE);
        Image crossImage = cross.getImage();

        SetElements.setLabelProperties(bulletCount, Constants.bold, 8.0, 203.0, 0.0, Constants.textColorCode);
        SetElements.setLabelProperties(levelLabel, Constants.bold, 8.0, 110, 0.0, Constants.textColorCode);
        scene.setCursor(new ImageCursor(crossImage, crossImage.getWidth() / 2, crossImage.getHeight() / 2));
        addHitHandler(scene, bulletCount);

        this.primaryStage.setScene(scene);
    }

    /**
     * Adds KeyEvent handlers to the selection screen for background, foreground and crosshair selections
     *
     * @param scene Selection scene
     * @param titleSong Sound that is being played
     */
    private void addSelectionEventHandler(Scene scene, AudioClip titleSong) {
        scene.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            if(event.getCode() == KeyCode.RIGHT) {
                curBG++;curFG++;
                curBG = FixElements.fixVal(curBG, 6, 1);
                curFG = FixElements.fixVal(curFG, 6, 1);

                background = FixElements.fixBG(curBG);
                foreground = FixElements.fixFG(curFG);

                Scene newScene = SetElements.setSelectionScene(background, foreground, cross);
                addSelectionEventHandler(newScene, titleSong);

                primaryStage.setScene(newScene);
            } else if(event.getCode() == KeyCode.LEFT) {
                curBG--;curFG--;
                curBG = FixElements.fixVal(curBG, 6, 1);
                curFG = FixElements.fixVal(curFG, 6, 1);

                background = FixElements.fixBG(curBG);
                foreground = FixElements.fixFG(curFG);

                Scene newScene = SetElements.setSelectionScene(background, foreground, cross);
                addSelectionEventHandler(newScene, titleSong);

                primaryStage.setScene(newScene);
            } else if(event.getCode() == KeyCode.UP) {
                curCross++;
                curCross = FixElements.fixVal(curCross, 7, 1);

                cross = FixElements.fixCross(curCross);

                Scene newScene = SetElements.setSelectionScene(background, foreground, cross);
                addSelectionEventHandler(newScene, titleSong);

                primaryStage.setScene(newScene);
            } else if(event.getCode() == KeyCode.DOWN) {
                curCross--;
                curCross = FixElements.fixVal(curCross, 7, 1);

                cross = FixElements.fixCross(curCross);

                Scene newScene = SetElements.setSelectionScene(background, foreground, cross);
                addSelectionEventHandler(newScene, titleSong);

                primaryStage.setScene(newScene);
            } else if(event.getCode() == KeyCode.ENTER) {
                AudioClip introSound = SetElements.setAudioClip(Constants.introSound, 1);

                titleSongPlaying = false;
                titleSong.stop();
                introSound.play();
                try {
                    TimeUnit.SECONDS.sleep(6);
                } catch (InterruptedException | IllegalMonitorStateException ignored) {}

                level1();
            } else if(event.getCode() == KeyCode.ESCAPE) {
                start();
            }
        });
    }

    /**
     * Adds MouseEvent handlers to get bullet shots
     *
     * @param scene Level scene
     * @param bulletCount Bullet count lable that will be updated
     */
    private void addHitHandler(Scene scene, Label bulletCount) {
        scene.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            double xClick = event.getSceneX();
            double yClick = event.getSceneY();

            updateBulletCount(bulletCount);

            AudioClip titleSong = SetElements.setAudioClip(Constants.gunShotSound, 1);
            titleSong.play();

            ArrayList<Object> toBeRemoved = new ArrayList<>();
            for(Object curElement : ((Group)scene.getRoot()).getChildren()) {
                String className = curElement.getClass().getSimpleName();

                if(className.equals("ImageView")) {
                    String imageID = ((ImageView)curElement).getId();

                    if(imageID != null && imageID.equals("Duck")) {
                        if(checkCollision((ImageView) curElement, xClick, yClick))
                            toBeRemoved.add(curElement);
                    }
                }
            }

            for(Object curElement : toBeRemoved) {
                ducksShot++;
                ((Group)scene.getRoot()).getChildren().remove(curElement);
            }

            if(ducksShot >= ducksToBeShot) {
                levelWon(scene);
            } else if(currentBulletCount == 0) {
                lostTheGame(scene);
            }
        });
    }

    /**
     * Checks if the point that is shot collides with the duck
     *
     * @param duckSprite Duck's ImageView sprite
     * @param x X coordinate of the point shot
     * @param y Y coordinate of the point shot
     *
     * @return true if point collides with duck, false if point doesn't collide with duck
     */
    private boolean checkCollision(ImageView duckSprite, double x, double y) {
        boolean condition = true;
        double leftLimit = duckSprite.getX();
        double rightLimit = duckSprite.getX() + duckSprite.getFitWidth();
        double upLimit = duckSprite.getY();
        double downLimit = duckSprite.getY() + duckSprite.getFitHeight();

        if(x < leftLimit || x > rightLimit)
            condition = false;
        if(y < upLimit || y > downLimit)
            condition = false;

        return condition;
    }

    /**
     * Updates the bullet count and bullet count label
     *
     * @param bulletCount Bullet count label
     */
    private void updateBulletCount(Label bulletCount) {
        currentBulletCount--;

        bulletCount.setText("Ammo Left: " + currentBulletCount);
    }

    /**
     * Adds labels and handlers that will be added to scene after level completion
     *
     * @param scene Level scene
     */
    private void levelWon(Scene scene) {
        addNextLevelHandler(scene);

        if(curLevel == 6) {
            Label completedTheGame = new Label("You have completed the game!");
            Label pressEnterToPlayAgain = new Label("Press ENTER to play again");
            Label pressEscToExit = new Label("Press ESC to exit");
            AudioClip gameCompleted = SetElements.setAudioClip(Constants.gameCompletedSound, 1);

            SetElements.setLabelProperties(completedTheGame, Constants.bold, 16.0, 12.0, 105.0, Constants.textColorCode);
            SetElements.setLabelProperties(pressEnterToPlayAgain, Constants.bold, 16.0, 30.0, 125.0, Constants.textColorCode);
            SetElements.setLabelProperties(pressEscToExit, Constants.bold, 16.0, 67.5, 145.0, Constants.textColorCode);
            gameCompleted.play();

            ((Group)scene.getRoot()).getChildren().add(completedTheGame);
            ((Group)scene.getRoot()).getChildren().add(pressEnterToPlayAgain);
            ((Group)scene.getRoot()).getChildren().add(pressEscToExit);

            AddSchedulers.titleLabelVisibilityChangeToggle(pressEnterToPlayAgain);
            AddSchedulers.titleLabelVisibilityChangeToggle(pressEscToExit);

            addExitToTitleHandler(scene);
        } else {
            Label youWin = new Label("YOU WIN!");
            Label pressEnterToPlayNext = new Label("Press ENTER to play next level");
            AudioClip levelCompleted = SetElements.setAudioClip(Constants.levelCompletedSound, 1);

            SetElements.setLabelProperties(youWin, Constants.bold, 16.0, 90.0, 115.0, Constants.textColorCode);
            SetElements.setLabelProperties(pressEnterToPlayNext, Constants.bold, 16.0, 16.0, 135.0, Constants.textColorCode);
            levelCompleted.play();

            ((Group)scene.getRoot()).getChildren().add(youWin);
            ((Group)scene.getRoot()).getChildren().add(pressEnterToPlayNext);

            AddSchedulers.titleLabelVisibilityChangeToggle(pressEnterToPlayNext);
        }
    }

    /**
     * Adds a KeyEvent handler to continue to next level
     *
     * @param scene Level scene
     */
    private void addNextLevelHandler(Scene scene) {
        scene.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
            if(KeyCode.ENTER == event.getCode()) {
                playNextLevel();
            }
        });
    }

    /**
     * Adds a KeyEvent handler to go to title scene
     *
     * @param scene Level scene
     */
    private void addExitToTitleHandler(Scene scene) {
        scene.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
            if(KeyCode.ESCAPE == event.getCode()) {
                start();
            }
        });
    }

    /**
     * Calls the next level function
     */
    private void playNextLevel() {
        if(curLevel == 1) {
            level2();
        } else if(curLevel == 2) {
            level3();
        } else if(curLevel == 3) {
            level4();
        } else if(curLevel == 4) {
            level5();
        } else if(curLevel == 5) {
            level6();
        } else if(curLevel == 6) {
            level1();
        }
    }

    /**
     * Adds labels and handlers that will be added to scene after player lost
     *
     * @param scene Level scene
     */
    private void lostTheGame(Scene scene) {
        Label completedTheGame = new Label("GAME OVER!");
        Label pressEnterToPlayAgain = new Label("Press ENTER to play again");
        Label pressEscToExit = new Label("Press ESC to exit");
        AudioClip gameOver = SetElements.setAudioClip(Constants.gameOverSound, 1);

        SetElements.setLabelProperties(completedTheGame, Constants.bold, 16.0, 75.0, 105.0, Constants.textColorCode);
        SetElements.setLabelProperties(pressEnterToPlayAgain, Constants.bold, 16.0, 30.0, 125.0, Constants.textColorCode);
        SetElements.setLabelProperties(pressEscToExit, Constants.bold, 16.0, 67.5, 145.0, Constants.textColorCode);
        gameOver.play();

        ((Group)scene.getRoot()).getChildren().add(completedTheGame);
        ((Group)scene.getRoot()).getChildren().add(pressEnterToPlayAgain);
        ((Group)scene.getRoot()).getChildren().add(pressEscToExit);

        curLevel = 6;
        addExitToTitleHandler(scene);
        addNextLevelHandler(scene);
    }

    /**
     * Updates level, bullet and duck information
     *
     * @param ducksToBeShot Number of ducks that will be in level
     * @param curLevel Current level
     */
    private void levelStart(int ducksToBeShot, int curLevel) {
        this.ducksToBeShot = ducksToBeShot;
        this.curLevel = curLevel;
        ducksShot = 0;
        currentBulletCount = ducksToBeShot * 3;
    }
}
