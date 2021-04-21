package br.com.gamestorebr.repository;

import br.com.gamestorebr.model.pessoa.Comprador;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompradorRepository {

  private final Map<String, Comprador> registros = new HashMap<>();

  public void add(final Comprador comprador) {

    if (registros.get(comprador.getDocumento()) != null) {

      System.out.println("\nERRO: Comprador já cadastrado para CPF informado...");

      return;
    }

    registros.putIfAbsent(comprador.getDocumento(), comprador);

    System.out.println("\nComprador cadastrado com sucesso!");
  }

  public Comprador get(final String cpf) {
    return registros.get(cpf);
  }

  public void update(final Comprador comprador) {
    registros.put(comprador.getDocumento(), comprador);
  }

  public Comprador getByName(final String nome) {
    return registros.values().stream()
        .filter(comprador -> comprador.getNome().equals(nome))
        .findFirst()
        .orElse(null);
  }

  public void delete(final String cpf) {
    registros.remove(cpf);
  }

  public List<Comprador> listAll() {
    return new ArrayList<>(registros.values());
  }
}
