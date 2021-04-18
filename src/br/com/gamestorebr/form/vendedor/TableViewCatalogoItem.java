package br.com.gamestorebr.form.vendedor;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TableViewCatalogoItem {

  private final SimpleIntegerProperty id;
  private final SimpleStringProperty nome;
  private final SimpleStringProperty precoUnitario;

  public TableViewCatalogoItem(final Integer id, final String nome, final String precoUnitario) {
    this.id = new SimpleIntegerProperty(id);
    this.nome = new SimpleStringProperty(nome);
    this.precoUnitario = new SimpleStringProperty(precoUnitario);
  }

  public int getId() {
    return id.get();
  }

  public SimpleIntegerProperty idProperty() {
    return id;
  }

  public void setId(final int id) {
    this.id.set(id);
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

  public String getPrecoUnitario() {
    return precoUnitario.get();
  }

  public SimpleStringProperty precoUnitarioProperty() {
    return precoUnitario;
  }

  public void setPrecoUnitario(final String precoUnitario) {
    this.precoUnitario.set(precoUnitario);
  }
}
