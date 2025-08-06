package casa.controle.demo.components.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "LISTA_PRODUTOS")
public class ListaProdutos {

    @EmbeddedId // Marca o campo que contém a chave primária composta
    private ListaProdutoId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("listaId") // Mapeia o atributo 'listaId' da classe 'ListaProdutoId'
    @JoinColumn(name = "LISTA_ID")
    private Listas lista;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("produtoId") // Mapeia o atributo 'produtoId' da classe 'ListaProdutoId'
    @JoinColumn(name = "PRODUTO_ID")
    private Produtos produto;

    @Column(name = "QUANTIDADE", nullable = false)
    private Integer quantidade;

    @Column(name = "PRECO_UNITARIO")
    private BigDecimal precoUnitario;

    @Column(name = "COMPRADO", nullable = false)
    private boolean comprado = false;
}