package br.com.gamestorebr.form.cliente;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class TableViewContasAPagarItem {

  private final SimpleDoubleProperty valor;
  private final SimpleStringProperty vencimento;
  private final SimpleStringProperty tipo;

  public TableViewContasAPagarItem(final Double valor, final String vencimento, final String tipo) {
    this.valor = new SimpleDoubleProperty(valor);
    this.vencimento = new SimpleStringProperty(vencimento);
    this.tipo = new SimpleStringProperty(tipo);
  }

  public double getValor() {
    return valor.get();
  }

  public SimpleDoubleProperty valorProperty() {
    return valor;
  }

  public void setValor(final double valor) {
    this.valor.set(valor);
  }

  public String getVencimento() {
    return vencimento.get();
  }

  public SimpleStringProperty vencimentoProperty() {
    return vencimento;
  }

  public void setVencimento(final String vencimento) {
    this.vencimento.set(vencimento);
  }

  public String getTipo() {
    return tipo.get();
  }

  public SimpleStringProperty tipoProperty() {
    return tipo;
  }

  public void setTipo(final String tipo) {
    this.tipo.set(tipo);
  }
}
