package folder;

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

  private static Parent loadFxml(final String fxml) throws IOException {
    return new FXMLLoader(App.class.getResource("/fxml/" + fxml + ".fxml")).load();
  }

  @Override
  public void start(final Stage stage) throws IOException {
    scene = new Scene(loadFxml("chess"));
    stage.setScene(scene);
    stage.show();
  }
}
