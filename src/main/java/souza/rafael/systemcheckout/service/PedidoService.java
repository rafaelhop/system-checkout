package souza.rafael.systemcheckout.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import souza.rafael.systemcheckout.model.FormaPagamento;
import souza.rafael.systemcheckout.model.Pedido;
import souza.rafael.systemcheckout.model.Produto;
import souza.rafael.systemcheckout.record.PedidoRecord;
import souza.rafael.systemcheckout.repository.PedidoRepository;
import souza.rafael.systemcheckout.repository.ProdutoRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    ProdutoRepository produtoRepository;

    public Double registrarPedido(String cpfCliente, List<String> produtos) {
        double valorTotal;
        var produtoList = produtoRepository.findAllByCodBarra(produtos);

        valorTotal = produtoList.stream().mapToDouble(Produto::getPreco).sum();

        var pedido = new  Pedido();
        pedido.setCpfCliente(cpfCliente);
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setProdutos(produtos);
        pedido.setValor(valorTotal);
        pedido.setFinalizado(false);
        pedido.setCancelado(false);

        repository.save(pedido);

        return  valorTotal;
    }

    public String finalizar(Long id, FormaPagamento pagamento) {
        var pedido  = repository.findById(id);
        if (pedido.isPresent()) {
            pedido.get().setFormaPagamento(pagamento);
            pedido.get().setFinalizado(true);

            repository.save(pedido.get());

            return "Pedido Finalizado com Sucesso. Agradecemos pela preferencia";
        }
        return "Pedido não encontrado";
    }

    public String cancelar(Long id, FormaPagamento pagamento) {
        var pedido  = repository.findById(id);
        if (pedido.isPresent()) {
            pedido.get().setFormaPagamento(pagamento);
            pedido.get().setFinalizado(false);
            pedido.get().setCancelado(true);

            repository.save(pedido.get());

            return "Pedido cancelado!";
        }

        return "Pedido não encontrado";
    }

    public List<PedidoRecord> buscarPedidos(String cpfCliente) {
        var pedidoList = new ArrayList<PedidoRecord>();
        var pedidos = repository.findAllByCpf(cpfCliente);

        pedidos.forEach( p -> {
            pedidoList.add(new PedidoRecord(p.getId(), p.getProdutos(),p.getFinalizado(), p.getValor()));
        });

        return  pedidoList;
    }
}
