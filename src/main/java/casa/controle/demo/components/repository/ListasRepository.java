package casa.controle.demo.components.repository;

import casa.controle.demo.components.model.Listas;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ListasRepository extends JpaRepository<Listas, Integer> {

    // Otimização: Evita o problema N+1 ao buscar uma lista com seus itens e produtos
    @EntityGraph(attributePaths = {"itens.produto.categoria"})
    Optional<Listas> findById(Integer id);
}
