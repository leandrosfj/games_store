package br.com.gamestorebr.form.cliente;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class TableViewComprasItem {

  private final SimpleStringProperty vendedor;
  private final SimpleStringProperty itens;
  private final SimpleStringProperty pagamento;
  private final SimpleDoubleProperty valorTotal;

  public TableViewComprasItem(
      final String vendedor, final String itens, final String pagamento, final Double valorTotal) {
    this.vendedor = new SimpleStringProperty(vendedor);
    this.itens = new SimpleStringProperty(itens);
    this.pagamento = new SimpleStringProperty(pagamento);
    this.valorTotal = new SimpleDoubleProperty(valorTotal);
  }

  public String getVendedor() {
    return vendedor.get();
  }

  public SimpleStringProperty vendedorProperty() {
    return vendedor;
  }

  public void setVendedor(final String vendedor) {
    this.vendedor.set(vendedor);
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
