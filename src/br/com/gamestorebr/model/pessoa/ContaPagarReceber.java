package br.com.gamestorebr.model.pessoa;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ContaPagarReceber {

  private final Double valor;
  private final LocalDate dataVencimento;
  private final String tipo;

  public ContaPagarReceber(final Double valor, final LocalDate dataVencimento, final String tipo) {
    this.valor = valor;
    this.dataVencimento = dataVencimento;
    this.tipo = tipo;
  }

  public Double getValor() {
    return valor;
  }

  public LocalDate getDataVencimento() {
    return dataVencimento;
  }

  public String getDataVencimentoFormatada() {
    return dataVencimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
  }

  @Override
  public String toString() {
    return "   Vencimento "
        + dataVencimento.format(DateTimeFormatter.ISO_LOCAL_DATE)
        + " - R$ "
        + valor
        + " - "
        + tipo
        + "\n";
  }

  public String getTipo() {
    return tipo;
  }
}
