import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        Image favicon = new Image(String.valueOf(getClass().getResource(Constants.faviconPath)));
        primaryStage.setTitle(Constants.title);
        primaryStage.getIcons().add(favicon);
        primaryStage.setResizable(false);
        setHaltProgramOnWindowClose(primaryStage);

        DuckHunt game = new DuckHunt(primaryStage);
        game.start();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void setHaltProgramOnWindowClose(Stage primaryStage) {
        primaryStage.setOnCloseRequest(t -> {
            Platform.exit();
            System.exit(0);
        });
    }
}
