package br.com.gamestorebr;


import br.com.gamestorebr.core.exception.NotFoundException;
import br.com.gamestorebr.form.menu.MenuFormController;
import br.com.gamestorebr.repository.CompradorRepository;
import br.com.gamestorebr.repository.TransacaoRepository;
import br.com.gamestorebr.repository.VendedorRepository;
import br.com.gamestorebr.util.AlertHelper;
import br.com.gamestorebr.util.DataBase;
import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class GameStoreBrApplication extends Application {

  private static Stage primaryStage;

  @Override
  public void start(Stage stage) {



    Thread.setDefaultUncaughtExceptionHandler((t, e) -> Platform.runLater(() -> showErrorDialog(t, e)));
    Thread.currentThread().setUncaughtExceptionHandler(this::showErrorDialog);

    try{
      DataBase.init();

      Parent root = FXMLLoader.load(getClass().getResource("form/menu/menu_form.fxml"));

      primaryStage = stage;
      primaryStage.setTitle("Game Store BR");
      primaryStage.setScene(new Scene(root, 1024, 768));
      primaryStage.setResizable(false);
      primaryStage.show();

    } catch (Throwable t) {
      showErrorDialog(Thread.currentThread(), t);
    }
  }

  public static void changeScene(String fxml) throws IOException {
      Parent pane = FXMLLoader.load(GameStoreBrApplication.class.getResource(fxml));
      primaryStage.getScene().setRoot(pane);
  }

  public static void main(String[] args) {
    launch(args);
  }

  private void showErrorDialog(Thread t, Throwable e) {

    e.printStackTrace();

    if(e instanceof NotFoundException){
      AlertHelper.showAlert(AlertType.WARNING, primaryStage.getScene().getWindow(), "Atenção!",
          e.getLocalizedMessage());
      return;
    }

    AlertHelper.showAlert(Alert.AlertType.ERROR, primaryStage.getScene().getWindow(), "Erro!",
        "Não foi possível realizar operação! " + e.getMessage());
  }
}
