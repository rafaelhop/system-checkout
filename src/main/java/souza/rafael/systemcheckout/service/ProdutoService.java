package souza.rafael.systemcheckout.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import souza.rafael.systemcheckout.model.Produto;
import souza.rafael.systemcheckout.record.ProdutoRecord;
import souza.rafael.systemcheckout.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;
    public ProdutoRecord buscarProduto(String codBarra) {
        var produto = repository.findByCodBarraAndAtivoTrue(codBarra);
        return  new ProdutoRecord(produto.getId(), produto.getNome(),
                    produto.getCodBarra(), produto.getPreco(), produto.getAtivo());
    }

    public void addProduto(ProdutoRecord produto) {
        repository.save(converterRecordToEntity(produto));
    }
    private Produto converterRecordToEntity(ProdutoRecord produtoRecord) {
        var produto = new Produto();
        produto.setNome(produtoRecord.nome());
        produto.setPreco(produtoRecord.preco());
        produto.setCodBarra(produtoRecord.codBarra());
        produto.setAtivo(produtoRecord.ativo());
        return  produto;
    }

    public ProdutoRecord update(String codBarra, ProdutoRecord produtoRecord) {
        var produto = repository.findByCodBarraAndAtivoTrue(codBarra);
        produto.setNome(produtoRecord.nome());
        produto.setCodBarra(produtoRecord.codBarra());
        produto.setPreco(produtoRecord.preco());
        produto.setAtivo(produtoRecord.ativo());

        repository.save(produto);
        return  produtoRecord;
    }

    public String delete(String codBarra) {
        var produto = repository.findByCodBarraAndAtivoTrue(codBarra);
        if (produto == null) {
            return "O produto inexistente, verifique o codigo de barras";
        }
        produto.setAtivo(false);
        repository.save(produto);

        return "Produto deletado com sucesso! ";
    }
}
