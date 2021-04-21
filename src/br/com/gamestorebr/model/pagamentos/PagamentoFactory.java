package br.com.gamestorebr.model.pagamentos;


import br.com.gamestorebr.model.pessoa.Comprador;
import br.com.gamestorebr.model.pessoa.Vendedor;

public class PagamentoFactory {

  public static Pagamento newPagamento(
      final Comprador comprador,
      final Vendedor vendedor,
      final Double valorTotal,
      final String tipoPagamento) {

    return switch (tipoPagamento) {

      case "Boleto" -> new Boleto(comprador, vendedor, valorTotal);
      case "Crédito" -> new Credito(comprador, vendedor, valorTotal);
      case "Débito" -> new Debito(comprador, vendedor, valorTotal);
      case "Pix" -> new Pix(comprador, vendedor, valorTotal);
      default -> null;
    };
  }
}
