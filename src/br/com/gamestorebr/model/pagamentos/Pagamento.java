package br.com.gamestorebr.model.pagamentos;

import br.com.gamestorebr.model.pessoa.Comprador;
import br.com.gamestorebr.model.pessoa.Vendedor;

public abstract class Pagamento {

  Comprador comprador;

  Vendedor vendedor;

  Double valorTotal;

  public Pagamento(final Comprador comprador, final Vendedor vendedor, final Double valorTotal) {
    this.comprador = comprador;
    this.vendedor = vendedor;
    this.valorTotal = valorTotal;
  }

  protected Double getValorTotal() {
    return valorTotal;
  }

  protected boolean compradorPossuiSaldoNecessarioParaTransacao() {

    if (meioPagamentoPrecisaValidarSaldoComprador()) {

      return (comprador.getSaldo() > (valorTotal + getValorTaxa()));

    } else {

      return true;
    }
  }

  public boolean realizarTransacaoFinanceira() {

    if (!compradorPossuiSaldoNecessarioParaTransacao()) {

      return false;
    }

    debitarValoresComprador();

    creditoValoresVendedor();

    return true;
  }

  protected abstract Double getValorTaxa();

  protected abstract void debitarValoresComprador();

  protected abstract void creditoValoresVendedor();

  protected abstract boolean meioPagamentoPrecisaValidarSaldoComprador();
}
