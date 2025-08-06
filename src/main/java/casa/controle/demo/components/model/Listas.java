package casa.controle.demo.components.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table(name = "LISTAS")
public class Listas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LISTA_ID")
    private Integer id;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "DATA_CRIACAO")
    private Instant dataCriacao = Instant.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USUARIO_ID", nullable = false)
    private Usuarios usuario;

    @OneToMany(mappedBy = "lista", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<ListaProdutos> itens = new HashSet<>();


    public void addItem(Produtos produto, Integer quantidade) {
        ListaProdutos item = new ListaProdutos(this, produto, quantidade);
        this.itens.add(item);
    }


    public void removeItem(Produtos produto) {
        this.itens.removeIf(item -> item.getProduto().equals(produto) && item.getLista().equals(this));
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == o || getClass() != o.getClass()) return false;
        Listas lista = (Listas) o;
        return Objects.equals(id, lista.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // --- MÉTODO toString manual ---
    // Importante: NÃO inclua a coleção 'itens' para evitar recursão
    @Override
    public String toString() {
        return "Lista{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", dataCriacao=" + dataCriacao +
                '}';
    }

}