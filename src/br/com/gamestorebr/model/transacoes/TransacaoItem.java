package br.com.gamestorebr.model.transacoes;

import br.com.gamestorebr.model.produtos.Produto;

public class TransacaoItem {

  private Produto produto;
  private Integer quantidade;

  public TransacaoItem(final Produto produto, final Integer quantidade) {
    this.produto = produto;
    this.quantidade = quantidade;
  }

  public Produto getProduto() {
    return produto;
  }

  public void setProduto(final Produto produto) {
    this.produto = produto;
  }

  public Integer getQuantidade() {
    return quantidade;
  }

  public void setQuantidade(final Integer quantidade) {
    this.quantidade = quantidade;
  }

  @Override
  public String toString() {
    return "Produto: " + produto + " - Qtde: " + quantidade;
  }
}
