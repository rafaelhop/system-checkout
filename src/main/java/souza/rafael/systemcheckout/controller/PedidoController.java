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

    @GetMapping("{cpf}")
    public List<PedidoRecord> buscarPedidos(@PathVariable("cpfCliente") String cpf) {
        return  service.buscarPedidos(cpf);
    }

    @PostMapping("/{cpf}")
    public Double registrarPedido(@PathVariable("cpf") String cpf, @RequestBody List<String> produtos) {
        return service.registrarPedido(cpf ,produtos);
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
