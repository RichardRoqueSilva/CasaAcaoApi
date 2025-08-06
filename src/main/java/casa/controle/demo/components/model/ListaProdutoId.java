package casa.controle.demo.components.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable // Indica que esta classe será embutida em outra entidade
public class ListaProdutoId implements Serializable {

    @Column(name = "LISTA_ID")
    private Integer listaId;

    @Column(name = "PRODUTO_ID")
    private Integer produtoId;
}
