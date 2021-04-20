package br.com.gamestorebr.form.vendedor;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class TableViewVendasItem {

  private final SimpleStringProperty cliente;
  private final SimpleStringProperty itens;
  private final SimpleStringProperty pagamento;
  private final SimpleDoubleProperty valorTotal;

  public TableViewVendasItem(
      final String cliente, final String itens, final String pagamento, final Double valorTotal) {
    this.cliente = new SimpleStringProperty(cliente);
    this.itens = new SimpleStringProperty(itens);
    this.pagamento = new SimpleStringProperty(pagamento);
    this.valorTotal = new SimpleDoubleProperty(valorTotal);
  }

  public String getCliente() {
    return cliente.get();
  }

  public SimpleStringProperty clienteProperty() {
    return cliente;
  }

  public void setCliente(final String cliente) {
    this.cliente.set(cliente);
  }

  public String getItens() {
    return itens.get();
  }

  public SimpleStringProperty itensProperty() {
    return itens;
  }

  public void setItens(final String itens) {
    this.itens.set(itens);
  }

  public String getPagamento() {
    return pagamento.get();
  }

  public SimpleStringProperty pagamentoProperty() {
    return pagamento;
  }

  public void setPagamento(final String pagamento) {
    this.pagamento.set(pagamento);
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
