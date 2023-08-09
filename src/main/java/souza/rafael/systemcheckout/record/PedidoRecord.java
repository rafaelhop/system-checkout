package souza.rafael.systemcheckout.record;

import java.util.List;

public record PedidoRecord(Long id, List<String> produtos, Boolean finalizado, Double valorTotal) {
}
