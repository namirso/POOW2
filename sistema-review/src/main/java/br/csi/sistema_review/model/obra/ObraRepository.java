package br.csi.sistema_review.model.obra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ObraRepository extends JpaRepository<Obra, Long> {

    // Método para buscar obras pelo nome (ignorando maiúsculas/minúsculas)
    public List<Obra> findByNomeContainingIgnoreCase(String nome);

    // Método para buscar todas as obras de um mesmo tipo (pelo ID do tipo)
    public List<Obra> findByTipoId(Long idtipo);

    // Método para buscar todas as obras de um(a) diretor(a)
    public List<Obra> findByDirecaoContainingIgnoreCase(String direcao);
}