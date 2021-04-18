package br.com.gamestorebr.form.menu;


import br.com.gamestorebr.GameStoreBrApplication;
import br.com.gamestorebr.util.AlertHelper;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Window;

public class MenuFormController {

  @FXML
  private TextField nameField;

  @FXML
  private TextField emailField;

  @FXML
  private PasswordField passwordField;

  @FXML
  private Button submitButton;


  @FXML
  protected void handleCadastrarVendaButtonAction(ActionEvent event) throws IOException {
    GameStoreBrApplication.changeScene("registration_form.fxml");
  }

  @FXML
  protected void handleConsultarVendedoresButtonAction(ActionEvent event) throws IOException {
    GameStoreBrApplication.changeScene("form/vendedor/vendedor_form.fxml");
  }

  @FXML
  protected void handleConsultarCompradoresButtonAction(ActionEvent event) throws IOException {
    GameStoreBrApplication.changeScene("registration_form.fxml");
  }

  @FXML
  protected void handleSubmitButtonAction(ActionEvent event) throws IOException {



    Window owner = submitButton.getScene().getWindow();

    GameStoreBrApplication.changeScene("registration_form.fxml");
//
//    if(nameField.getText().isEmpty()) {
//      AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
//          "Please enter your name");
//      return;
//    }
//    if(emailField.getText().isEmpty()) {
//      AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
//          "Please enter your email id");
//      return;
//    }
//    if(passwordField.getText().isEmpty()) {
//      AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
//          "Please enter a password");
//      return;
//    }
//
//    AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration Successful!",
//        "Welcome " + nameField.getText());
  }
}
