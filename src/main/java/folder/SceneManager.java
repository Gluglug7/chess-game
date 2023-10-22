package folder;

import java.util.HashMap;
import javafx.scene.Parent;

public class SceneManager {
  public enum AppUI {
    CHESS_BOARD
  }

  public static HashMap<AppUI, Parent> appUIs = new HashMap<>();

  public static void addAppUI(AppUI appUI, Parent root) {
    appUIs.put(appUI, root);
  }

  public static Parent getUIRoot(AppUI appUI) {
    return appUIs.get(appUI);
  }
}
