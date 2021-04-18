package br.com.gamestorebr.form.components;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

public class EditButton extends Button {

  public EditButton(String fileName) {
    super("Edit");

    setOnAction((event) -> {
      Alert alert = new Alert(AlertType.INFORMATION);
      alert.setTitle("Hey!");
      alert.setHeaderText(null);
      alert.setContentText("You're editing \"" + fileName + "\"");
      alert.showAndWait();
    });
  }
}