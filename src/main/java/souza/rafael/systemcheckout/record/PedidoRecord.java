package souza.rafael.systemcheckout.record;

import souza.rafael.systemcheckout.model.FormaPagamento;

import java.util.List;

public record PedidoRecord(Long id, List<String> produtos, Boolean finalizado, Double valorTotal, String pagamento) {
}
