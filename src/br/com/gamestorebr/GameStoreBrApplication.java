package br.com.gamestorebr;

import br.com.gamestorebr.core.exception.NotFoundException;
import br.com.gamestorebr.form.cliente.VisualizarClienteFormController;
import br.com.gamestorebr.form.vendedor.VisualizarVendedorFormController;
import br.com.gamestorebr.util.AlertHelper;
import br.com.gamestorebr.util.DataBase;
import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class GameStoreBrApplication extends Application {

  private static Stage primaryStageasdadasd;

  @Override
  public void start(final Stage stage) {

    Thread.setDefaultUncaughtExceptionHandler(
        (t, e) -> Platform.runLater(() -> showErrorDialog(t, e)));
    Thread.currentThread().setUncaughtExceptionHandler(this::showErrorDialog);

    try {
      DataBase.init();

      final Parent root = FXMLLoader.load(getClass().getResource("form/menu/menu_form.fxml"));

      primaryStageasdadasd = stage;
      primaryStageasdadasd.setTitle("Game Store BR");
      primaryStageasdadasd.setScene(new Scene(root, 1024, 768));
      primaryStageasdadasd.setResizable(false);
      primaryStageasdadasd.show();

    } catch (final Throwable t) {
      showErrorDialog(Thread.currentThread(), t);
    }
  }

  public static void changeScene(final String fxml) throws IOException {

    final FXMLLoader loader = new FXMLLoader(GameStoreBrApplication.class.getResource(fxml));
    final Parent pane = FXMLLoader.load(GameStoreBrApplication.class.getResource(fxml));
    primaryStageasdadasd.getScene().setRoot(pane);
  }

  @FXML
  public static void visualizarVendedor(final String documento) throws IOException {

    final FXMLLoader loader =
        new FXMLLoader(
            GameStoreBrApplication.class.getResource(
                "form/vendedor/visualizar_vendedor_form.fxml"));

    final Parent pane = loader.load();

    final VisualizarVendedorFormController controller = loader.getController();

    controller.carregarVendedor(documento);
    primaryStageasdadasd.getScene().setRoot(pane);
  }

  @FXML
  public static void visualizarCliente(final String documento) throws IOException {

    final FXMLLoader loader =
        new FXMLLoader(
            GameStoreBrApplication.class.getResource("form/cliente/visualizar_cliente_form.fxml"));

    final Parent pane = loader.load();

    final VisualizarClienteFormController controller = loader.getController();

    controller.carregarCliente(documento);
    primaryStageasdadasd.getScene().setRoot(pane);
  }

  public static void main(final String[] args) {
    launch(args);
  }

  private void showErrorDialog(final Thread t, final Throwable e) {

    e.printStackTrace();

    if (e instanceof NotFoundException) {
      AlertHelper.showAlert(
          AlertType.WARNING,
          primaryStageasdadasd.getScene().getWindow(),
          "Atenção!",
          e.getLocalizedMessage());
      return;
    }

    AlertHelper.showAlert(
        Alert.AlertType.ERROR,
        primaryStageasdadasd.getScene().getWindow(),
        "Erro!",
        "Não foi possível realizar operação! " + e.getMessage());
  }
}
