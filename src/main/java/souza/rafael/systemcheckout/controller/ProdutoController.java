package souza.rafael.systemcheckout.controller;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import souza.rafael.systemcheckout.model.Produto;
import souza.rafael.systemcheckout.record.ProdutoRecord;
import souza.rafael.systemcheckout.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping("/{codBarra}")
    public ProdutoRecord buscarProduto(@PathVariable("codBarra") String codBarra) {
        return service.buscarProduto(codBarra);
    }
    @PostMapping
    public void addProduto(@RequestBody ProdutoRecord produto) {
        service.addProduto(produto);
    }

    @PutMapping("/{codBarra}")
    public ProdutoRecord atualizaProduto(@PathVariable("codBarra") String codBarra,
                                         @RequestBody ProdutoRecord produto) {

        return service.update(codBarra, produto);
    }

    @DeleteMapping("/{codBarra}")
    public String delete(@PathVariable("codBarra") String codBarra) {
        return service.delete(codBarra);
    }
}
