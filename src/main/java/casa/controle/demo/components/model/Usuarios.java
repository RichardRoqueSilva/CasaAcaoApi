package casa.controle.demo.components.model;

import jakarta.persistence.*;
import lombok.Data;


@Data // Anotação do Lombok que gera Getters, Setters, toString, equals, hashCode
@Entity
@Table(name = "USUARIOS")
public class Usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USUARIO_ID")
    private Integer id;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;
}