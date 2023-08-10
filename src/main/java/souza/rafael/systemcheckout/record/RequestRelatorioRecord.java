package souza.rafael.systemcheckout.record;

import souza.rafael.systemcheckout.model.FormaPagamento;

public record RequestRelatorioRecord(String dataInicio, String dataFim, FormaPagamento formaPagamento) {
}
