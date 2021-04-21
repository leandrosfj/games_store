package br.com.gamestorebr.form.compra;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TableViewProdutoVendaItem {

  private final SimpleStringProperty produto;
  private final SimpleIntegerProperty quantidade;
  private final SimpleDoubleProperty precoUnitario;
  private final SimpleDoubleProperty valorTotal;

  TableViewProdutoVendaItem(
      final String produto,
      final Integer quantidade,
      final Double precoUnitario,
      final Double valorTotal) {

    this.produto = new SimpleStringProperty(produto);
    this.quantidade = new SimpleIntegerProperty(quantidade);
    this.precoUnitario = new SimpleDoubleProperty(precoUnitario);
    this.valorTotal = new SimpleDoubleProperty(valorTotal);
  }

  public String getProduto() {
    return produto.get();
  }

  public SimpleStringProperty produtoProperty() {
    return produto;
  }

  public void setProduto(final String produto) {
    this.produto.set(produto);
  }

  public int getQuantidade() {
    return quantidade.get();
  }

  public SimpleIntegerProperty quantidadeProperty() {
    return quantidade;
  }

  public void setQuantidade(final int quantidade) {
    this.quantidade.set(quantidade);
  }

  public double getPrecoUnitario() {
    return precoUnitario.get();
  }

  public SimpleDoubleProperty precoUnitarioProperty() {
    return precoUnitario;
  }

  public void setPrecoUnitario(final double precoUnitario) {
    this.precoUnitario.set(precoUnitario);
  }

  public double getValorTotal() {
    return valorTotal.get();
  }

  public SimpleDoubleProperty valorTotalProperty() {
    return valorTotal;
  }

  public void setValorTotal(final double valorTotal) {
    this.valorTotal.set(valorTotal);
  }
}
