package br.csi.sistema_review.service;

import br.csi.sistema_review.model.review.Review;
import br.csi.sistema_review.model.review.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository repository;

    public ReviewService(ReviewRepository repository) {
        this.repository = repository;
    }

    public void salvar(Review review) {
        this.repository.save(review);
    }

    public List<Review> listar() {
        return this.repository.findAll();
    }

    public Review getReview(Long id) {
        return this.repository.findById(id).get();
    }

    public void excluir(Long id) {
        this.repository.deleteById(id);
    }

    public void atualizar(Review review) {
        Review r = this.repository.getReferenceById(review.getId());
        r.setTitulo(review.getTitulo());
        r.setDescricao(review.getDescricao());
        r.setNota(review.getNota());
        r.setObra(review.getObra()); // Atualiza a relação
        r.setUsuario(review.getUsuario()); // Atualiza a relação
        this.repository.save(r);
    }

    // Métodos expostos do repositório
    public List<Review> getReviewsPorObra(Long idobra) {
        return this.repository.findByObraId(idobra);
    }

    public List<Review> getReviewsPorUsuario(Long idusuario) {
        return this.repository.findByUsuarioId(idusuario);
    }

    public Review getReviewPorObraEUsuario(Long idobra, Long idusuario) {
        return this.repository.findByObraIdAndUsuarioId(idobra, idusuario);
    }
}