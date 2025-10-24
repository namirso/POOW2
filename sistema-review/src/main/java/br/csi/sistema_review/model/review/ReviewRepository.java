package br.csi.sistema_review.model.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // Método para buscar todas as reviews de uma obra específica
    public List<Review> findByObraId(Long idobra);

    // Método para buscar todas as reviews de um usuário específico
    public List<Review> findByUsuarioId(Long idusuario);

    // Método para buscar uma review específica de um usuário para uma obra
    public Review findByObraIdAndUsuarioId(Long idobra, Long idusuario);
}