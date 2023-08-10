package souza.rafael.systemcheckout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import souza.rafael.systemcheckout.model.FormaPagamento;
import souza.rafael.systemcheckout.record.PedidoRecord;
import souza.rafael.systemcheckout.record.RequestRelatorioRecord;
import souza.rafael.systemcheckout.service.RelatorioService;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("relatorios")
public class RelatorioController {

    @Autowired
    private RelatorioService service;

    @GetMapping
    public List<PedidoRecord> allPedidos(@RequestParam String dataInicio, @RequestParam String dataFim) {
        return service.buscarTodosPedidos(dataInicio, dataFim);
    }
    @GetMapping("/finalizados")
    public List<PedidoRecord> finalizados(@RequestParam String dataInicio, @RequestParam String dataFim,
                                          @RequestParam FormaPagamento formaPagamento) {
        return service.buscarPedidosFinalizados(dataInicio, dataFim, formaPagamento);
    }

    @GetMapping("/cancelados")
    public List<PedidoRecord> cancelados(@RequestParam String dataInicio, @RequestParam String dataFim) {
        return service.buscarPedidosCancelados(dataInicio, dataFim);
    }

}
