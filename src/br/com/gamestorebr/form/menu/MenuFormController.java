package br.com.gamestorebr.form.menu;

import br.com.gamestorebr.GameStoreBrApplication;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Window;

public class MenuFormController {

  @FXML private TextField nameField;

  @FXML private TextField emailField;

  @FXML private PasswordField passwordField;

  @FXML private Button submitButton;

  @FXML
  protected void handleCadastrarCompraButtonAction(final ActionEvent event) throws IOException {
    GameStoreBrApplication.changeScene("registration_form.fxml");
  }

  @FXML
  protected void handleConsultarVendedoresButtonAction(final ActionEvent event) throws IOException {
    GameStoreBrApplication.changeScene("form/vendedor/vendedor_form.fxml");
  }

  @FXML
  protected void handleConsultarCompradoresButtonAction(final ActionEvent event)
      throws IOException {
    GameStoreBrApplication.changeScene("form/cliente/cliente_form.fxml");
  }

  @FXML
  protected void handleSubmitButtonAction(final ActionEvent event) throws IOException {

    final Window owner = submitButton.getScene().getWindow();

    GameStoreBrApplication.changeScene("registration_form.fxml");
  }
}
