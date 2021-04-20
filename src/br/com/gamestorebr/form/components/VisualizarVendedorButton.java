package br.com.gamestorebr.form.components;

import br.com.gamestorebr.GameStoreBrApplication;
import java.io.IOException;
import javafx.scene.control.Button;

public class VisualizarVendedorButton extends Button {

  public VisualizarVendedorButton(final String documento) {
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
