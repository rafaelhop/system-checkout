package souza.rafael.systemcheckout.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import souza.rafael.systemcheckout.model.Produto;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Produto findByCodBarraAndAtivoTrue(String codBarra);

    @Query( value = "Select * from produtos " +
            "where cod_barra IN :produtos " +
            "AND ativo = 1 ", nativeQuery = true)
    List<Produto> findAllByCodBarra(List<String> produtos);
}
