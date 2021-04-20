package br.com.gamestorebr.util;

import br.com.gamestorebr.model.pessoa.Comprador;
import br.com.gamestorebr.model.pessoa.Vendedor;
import br.com.gamestorebr.model.produtos.Produto;
import br.com.gamestorebr.repository.CompradorRepository;
import br.com.gamestorebr.repository.TransacaoRepository;
import br.com.gamestorebr.repository.VendedorRepository;

public class DataBase {

  private static final CompradorRepository compradorRepository = new CompradorRepository();
  private static final VendedorRepository vendedorRepository = new VendedorRepository();
  private static final TransacaoRepository transacaoRepository = new TransacaoRepository();

  public static void init() {
    populateCompradores();
    populateVendedores();
  }

  private static void populateCompradores() {

    final Comprador comprador1 = new Comprador("Leandro", 500, "123.456.789-00");
    final Comprador comprador2 = new Comprador("Ana", 800, "987.654.321-00");
    final Comprador comprador3 = new Comprador("Rafael", 200, "123.123.123-00");

    compradorRepository.add(comprador1);
    compradorRepository.add(comprador2);
    compradorRepository.add(comprador3);
  }

  private static void populateVendedores() {

    final Vendedor vendedor1 = new Vendedor("Felipe", "99.888.777/0001-66");
    final Vendedor vendedor2 = new Vendedor("Marcos", "66.555.444/0001-33");

    vendedorRepository.add(vendedor1);
    vendedorRepository.add(vendedor2);

    populateCatalogo(vendedor1, vendedor2);
  }

  private static void populateCatalogo(final Vendedor vendedor1, final Vendedor vendedor2) {

    final Produto produto1 = new Produto("God Of War", 198.90);
    final Produto produto2 = new Produto("Mortal Kombat", 159.95);
    final Produto produto3 = new Produto("Street Fighter", 120.50);
    final Produto produto4 = new Produto("Mario World", 80.20);
    final Produto produto5 = new Produto("FIFA", 230.00);
    final Produto produto6 = new Produto("Bomba Patch", 95.70);
    final Produto produto7 = new Produto("Doom", 54.30);
    final Produto produto8 = new Produto("Tíbia", 28.80);
    final Produto produto9 = new Produto("GTA V", 310.00);
    final Produto produto10 = new Produto("Need For Speed Pro Street", 79.90);

    vendedorRepository.addProduto(vendedor1.getDocumento(), produto1);
    vendedorRepository.addProduto(vendedor1.getDocumento(), produto2);
    vendedorRepository.addProduto(vendedor1.getDocumento(), produto3);
    vendedorRepository.addProduto(vendedor1.getDocumento(), produto4);
    vendedorRepository.addProduto(vendedor1.getDocumento(), produto5);
    vendedorRepository.addProduto(vendedor2.getDocumento(), produto6);
    vendedorRepository.addProduto(vendedor2.getDocumento(), produto7);
    vendedorRepository.addProduto(vendedor2.getDocumento(), produto8);
    vendedorRepository.addProduto(vendedor2.getDocumento(), produto9);
    vendedorRepository.addProduto(vendedor2.getDocumento(), produto10);
  }

  public static CompradorRepository getCompradorRepository() {
    return compradorRepository;
  }

  public static VendedorRepository getVendedorRepository() {
    return vendedorRepository;
  }

  public static TransacaoRepository getTransacaoRepository() {
    return transacaoRepository;
  }
}
