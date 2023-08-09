package souza.rafael.systemcheckout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import souza.rafael.systemcheckout.model.FormaPagamento;
import souza.rafael.systemcheckout.record.PedidoRecord;
import souza.rafael.systemcheckout.service.PedidoService;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @GetMapping("{cpfCliente}")
    public List<PedidoRecord> buscarPedidos(@PathVariable("cpfCliente") String cpfCliente) {
        return  service.buscarPedidos(cpfCliente);
    }

    @PostMapping("/{cpfCliente")
    public Double registrarPedido(@PathVariable("cpfCliente") String cpfCliente, @RequestBody List<String> produtos) {
        return service.registrarPedido(cpfCliente ,produtos);
    }

    @PutMapping("finalizar/{id}")
    public String finalizar(@PathVariable("id") Long id, @RequestBody FormaPagamento pagamento) {
        return  service.finalizar(id, pagamento);
    }

    @PutMapping("cancelar/{id}")
    public String cancelar(@PathVariable("id") Long id, @RequestBody FormaPagamento pagamento) {
        return service.cancelar(id, pagamento);
    }

}
