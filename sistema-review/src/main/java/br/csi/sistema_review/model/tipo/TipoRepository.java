package br.csi.sistema_review.model.tipo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoRepository extends JpaRepository<Tipo, Long> {

    // Método para buscar um tipo pelo nome
    public Tipo findByNome(String nome);
}