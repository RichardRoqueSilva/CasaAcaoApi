package casa.controle.demo.components.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.HashSet;
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
}