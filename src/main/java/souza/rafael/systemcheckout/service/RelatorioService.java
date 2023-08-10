package souza.rafael.systemcheckout.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import souza.rafael.systemcheckout.model.FormaPagamento;
import souza.rafael.systemcheckout.record.PedidoRecord;
import souza.rafael.systemcheckout.record.RequestRelatorioRecord;
import souza.rafael.systemcheckout.repository.PedidoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RelatorioService {

    @Autowired
    private PedidoRepository pedidoRepository;
    public List<PedidoRecord> buscarTodosPedidos(String dataInicio, String dataFim) {
        var pedidos = new ArrayList<PedidoRecord>();
        pedidoRepository.buscarTodos(dataInicio, dataFim)
                .forEach(p -> {
                    pedidos.add(new PedidoRecord(p.getId(), List.of(p.getProdutos()),p.getFinalizado(), p.getValor(), p.getFormaPagamento().toString()));
                });
        return  pedidos;
    }

    public List<PedidoRecord> buscarPedidosFinalizados(String dataInicio, String dataFim, FormaPagamento formaPagamento) {
        var pedidos = new ArrayList<PedidoRecord>();
        pedidoRepository.buscarFinalizados(dataInicio, dataFim, formaPagamento.toString())
                .forEach(p -> {
                    pedidos.add(new PedidoRecord(p.getId(), List.of(p.getProdutos()),p.getFinalizado(),
                                                p.getValor(), p.getFormaPagamento().toString()));

                });
        return  pedidos;
    }

    public List<PedidoRecord> buscarPedidosCancelados(String dataInicio, String dataFim) {
        var pedidos = new ArrayList<PedidoRecord>();
        pedidoRepository.buscarCancelados(dataInicio, dataFim)
                .forEach(p -> {
                    pedidos.add(new PedidoRecord(p.getId(), List.of(p.getProdutos()),p.getFinalizado(),
                            p.getValor(), p.getFormaPagamento().toString()));

                });
        return  pedidos;
    }
}