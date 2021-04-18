package br.com.gamestorebr.form.vendedor;

import br.com.gamestorebr.form.components.EditButton;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class VendedorForm {

  private final SimpleStringProperty nome;
  private final SimpleStringProperty cnpj;
  private final SimpleStringProperty action;
  private final SimpleObjectProperty<EditButton> editButton;

  public VendedorForm(String nome, String cnpj, String action) {
    this.nome = new SimpleStringProperty(nome);
    this.cnpj = new SimpleStringProperty(cnpj);
    this.action = new SimpleStringProperty(action);
    editButton = new SimpleObjectProperty(new EditButton(nome));
  }

  public String getNome() {
    return nome.get();
  }

  public SimpleStringProperty nomeProperty() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome.set(nome);
  }

  public String getCnpj() {
    return cnpj.get();
  }

  public SimpleStringProperty cnpjProperty() {
    return cnpj;
  }

  public void setCnpj(String cnpj) {
    this.cnpj.set(cnpj);
  }

  public String getAction() {
    return action.get();
  }

  public SimpleStringProperty actionProperty() {
    return action;
  }

  public void setAction(String action) {
    this.action.set(action);
  }

  public EditButton getEditButton() {
    return editButton.get();
  }

  public SimpleObjectProperty<EditButton> editButtonProperty() {
    return editButton;
  }

  public void setEditButton(EditButton editButton) {
    this.editButton.set(editButton);
  }
}
