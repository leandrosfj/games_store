package br.com.gamestorebr.form.components;

import br.com.gamestorebr.GameStoreBrApplication;
import java.io.IOException;
import javafx.scene.control.Button;

public class VisualizarButton extends Button {

  public VisualizarButton(final String documento) {
    super("Visualizar");

    setOnAction(
        (event) -> {
          try {

            GameStoreBrApplication.visualizarVendedor(documento);
          } catch (final IOException e) {

            e.printStackTrace();
          }
        });
  }
}
