package souza.rafael.systemcheckout.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String produtos;

    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;

    private LocalDateTime dataPedido;

    private Boolean finalizado;
    private Boolean cancelado;

    private Double valor;


    private String cpf;


}
