package casa.controle.demo.components.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "LISTA_PRODUTOS")
public class ListaProdutos {

    @EmbeddedId // Marca o campo que contém a chave primária composta
    private ListaProdutoId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("listaId")
    @JoinColumn(name = "LISTA_ID")
    private Listas lista;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("produtoId")
    @JoinColumn(name = "PRODUTO_ID")
    private Produtos produto;

    @Column(name = "QUANTIDADE", nullable = false)
    private Integer quantidade;

    @Column(name = "PRECO_UNITARIO")
    private BigDecimal precoUnitario;

    @Column(name = "COMPRADO", nullable = false)
    private boolean comprado = false;

    public ListaProdutos(Listas lista, Produtos produto, Integer quantidade) {
        // Define as entidades da relação
        this.lista = lista;
        this.produto = produto;

        // Define os atributos da relação
        this.quantidade = quantidade;
        this.comprado = false;

        // Cria e define a chave composta
        this.id = new ListaProdutoId(lista.getId(), produto.getId());
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListaProdutos that = (ListaProdutos) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // --- MÉTODO toString manual ---
    // Não inclua as entidades 'lista' e 'produto' para evitar o ciclo
    @Override
    public String toString() {
        return "ListaProduto{" +
                "id=" + id +
                ", quantidade=" + quantidade +
                ", comprado=" + comprado +
                '}';
    }
}