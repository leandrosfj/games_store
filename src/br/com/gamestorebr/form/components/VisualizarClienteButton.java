package br.com.gamestorebr.form.components;

import br.com.gamestorebr.GameStoreBrApplication;
import java.io.IOException;
import javafx.scene.control.Button;

public class VisualizarClienteButton extends Button {

  public VisualizarClienteButton(final String documento) {
    super("Visualizar");

    setOnAction(
        (event) -> {
          try {

            GameStoreBrApplication.visualizarCliente(documento);
          } catch (final IOException e) {

            e.printStackTrace();
          }
        });
  }
}
