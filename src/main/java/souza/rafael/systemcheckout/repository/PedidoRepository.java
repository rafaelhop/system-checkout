package souza.rafael.systemcheckout.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import souza.rafael.systemcheckout.model.Pedido;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findAllByCpf(String cpf);

    @Query( value = "SELECT * FROM pedidos " +
            "WHERE data_pedido BETWEEN :dataInicio AND :dataFim ", nativeQuery = true)
    List<Pedido> buscarTodos(String dataInicio, String dataFim);

    @Query( value = "SELECT * FROM pedidos " +
            "WHERE data_pedido BETWEEN :dataInicio AND :dataFim " +
            "AND forma_pagamento = :pagamento " +
            "AND finalizado = 1 ", nativeQuery = true)
    List<Pedido> buscarFinalizados(String dataInicio, String dataFim, String pagamento);

    @Query( value = "SELECT * FROM pedidos " +
            "WHERE data_pedido BETWEEN :dataInicio AND :dataFim " +
            "AND cancelado = 1 ", nativeQuery = true)
    List<Pedido> buscarCancelados(String dataInicio, String dataFim);
}
