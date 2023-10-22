package folder;

import folder.SceneManager.AppUI;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

  private static Scene scene;

  public static void main(final String[] args) {
    launch();
  }

  public static void setRoot(String fxml) throws IOException {
    scene.setRoot(loadFxml(fxml));
  }

  private static Parent loadFxml(final String fxml) throws IOException {
    return new FXMLLoader(App.class.getResource("/fxml/" + fxml + ".fxml")).load();
  }

  @Override
  public void start(final Stage stage) throws IOException {
    SceneManager.addAppUI(AppUI.CHESS_BOARD, loadFxml("chess"));
    Parent root = SceneManager.getUIRoot(AppUI.CHESS_BOARD);
    scene = new Scene(root);
    stage.setScene(scene);
    stage.setResizable(false);
    stage.show();
    root.requestFocus();
  }
}
