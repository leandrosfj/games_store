package br.com.gamestorebr.model.pagamentos;

import br.com.gamestorebr.model.pessoa.Comprador;
import br.com.gamestorebr.model.pessoa.Vendedor;

public class PagamentoFactory {

  public static Pagamento newPagamento(
      final Comprador comprador,
      final Vendedor vendedor,
      final Double valorTotal,
      final String tipoPagamento) {

    switch (tipoPagamento) {
      case "Boleto":
        return new Boleto(comprador, vendedor, valorTotal);
      case "Crédito":
        return new Credito(comprador, vendedor, valorTotal);
      case "Débito":
        return new Debito(comprador, vendedor, valorTotal);
      case "Pix":
        return new Pix(comprador, vendedor, valorTotal);
      default:
        return null;
    }
  }
}
