package br.com.gamestorebr.model.transacoes;

import br.com.gamestorebr.model.pagamentos.Pagamento;
import br.com.gamestorebr.model.pessoa.Comprador;
import br.com.gamestorebr.model.pessoa.Vendedor;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

public class Transacao {

  private String codigo;
  private Comprador comprador;
  private Vendedor vendedor;
  private Pagamento pagamento;
  private List<TransacaoItem> transacoesItens = new ArrayList<>();

  public Transacao() {
    super();
    codigo = UUID.randomUUID().toString();
  }

  public Transacao(
      final Comprador comprador,
      final Vendedor vendedor,
      final List<TransacaoItem> transacoesItens) {

    this.comprador = comprador;
    this.vendedor = vendedor;
    this.transacoesItens = transacoesItens;
  }

  public Double getValorTotal() {

    final AtomicReference<Double> valorTotal = new AtomicReference<>(0d);

    transacoesItens.forEach(
        transacaoItem ->
            valorTotal.updateAndGet(
                valorTotalAcumulado ->
                    valorTotalAcumulado
                        + (transacaoItem.getProduto().getPrecoUnitario()
                            * transacaoItem.getQuantidade())));

    return valorTotal.get();
  }

  public Comprador getComprador() {
    return comprador;
  }

  public void setComprador(final Comprador comprador) {
    this.comprador = comprador;
  }

  public Vendedor getVendedor() {
    return vendedor;
  }

  public void setVendedor(final Vendedor vendedor) {
    this.vendedor = vendedor;
  }

  public Pagamento getPagamento() {
    return pagamento;
  }

  public void setPagamento(final Pagamento pagamento) {
    this.pagamento = pagamento;
  }

  public List<TransacaoItem> getTransacoesItens() {
    return transacoesItens;
  }

  public void setTransacoesItens(final List<TransacaoItem> transacoesItens) {
    this.transacoesItens = transacoesItens;
  }

  public String getCodigo() {
    return codigo;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final Transacao transacao = (Transacao) o;
    return comprador.equals(transacao.comprador)
        && vendedor.equals(transacao.vendedor)
        && pagamento.equals(transacao.pagamento)
        && transacoesItens.equals(transacao.transacoesItens);
  }

  @Override
  public int hashCode() {
    return Objects.hash(comprador, vendedor, pagamento, transacoesItens);
  }

  @Override
  public String toString() {
    return "Transacao{"
        + "codigo='"
        + codigo
        + '\''
        + ", comprador="
        + comprador
        + ", vendedor="
        + vendedor
        + ", pagamento="
        + pagamento
        + ", produtosSet="
        + transacoesItens
        + '}';
  }
}
