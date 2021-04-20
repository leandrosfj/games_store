package br.com.gamestorebr.form.cliente;

import br.com.gamestorebr.form.components.VisualizarClienteButton;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class TableViewClienteItem {

  private final SimpleStringProperty nome;
  private final SimpleStringProperty cpf;
  private final SimpleStringProperty saldo;
  private final SimpleObjectProperty<VisualizarClienteButton> visualizarButton;

  public TableViewClienteItem(final String nome, final String cpf, final String saldo) {
    this.nome = new SimpleStringProperty(nome);
    this.cpf = new SimpleStringProperty(cpf);
    this.saldo = new SimpleStringProperty(saldo);
    visualizarButton = new SimpleObjectProperty(new VisualizarClienteButton(cpf));
  }

  public String getNome() {
    return nome.get();
  }

  public SimpleStringProperty nomeProperty() {
    return nome;
  }

  public void setNome(final String nome) {
    this.nome.set(nome);
  }

  public String getCpf() {
    return cpf.get();
  }

  public SimpleStringProperty cpfProperty() {
    return cpf;
  }

  public void setCpf(final String cpf) {
    this.cpf.set(cpf);
  }

  public String getSaldo() {
    return saldo.get();
  }

  public SimpleStringProperty saldoProperty() {
    return saldo;
  }

  public void setSaldo(final String saldo) {
    this.saldo.set(saldo);
  }

  public VisualizarClienteButton getVisualizarButton() {
    return visualizarButton.get();
  }

  public SimpleObjectProperty<VisualizarClienteButton> visualizarButtonProperty() {
    return visualizarButton;
  }

  public void setVisualizarButton(final VisualizarClienteButton visualizarButton) {
    this.visualizarButton.set(visualizarButton);
  }
}
