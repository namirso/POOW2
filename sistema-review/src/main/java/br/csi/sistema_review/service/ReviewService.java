package br.csi.sistema_review.service;

import br.csi.sistema_review.model.review.Review;
import br.csi.sistema_review.model.review.ReviewRepository;
import br.csi.sistema_review.model.usuario.Usuario;
import br.csi.sistema_review.model.usuario.UsuarioRepository; // Import necessário
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository repository;
    private final UsuarioRepository usuarioRepository;

    public ReviewService(ReviewRepository repository, UsuarioRepository usuarioRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public void salvar(Review review) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = "";
        if (authentication.getPrincipal() instanceof UserDetails) {
            username = ((UserDetails) authentication.getPrincipal()).getUsername();
        } else {
            username = authentication.getPrincipal().toString();
        }

        Usuario usuarioLogado = this.usuarioRepository.findUsuarioByEmail(username);

        review.setUsuario(usuarioLogado);
        this.repository.save(review);
    }

    public List<Review> listar() {
        return this.repository.findAll();
    }

    public Review getReview(Long id) {
        return this.repository.findById(id).orElse(null);
    }

    public void excluir(Long id) {
        this.repository.deleteById(id);
    }

    @Transactional
    public void atualizar(Review review) {
        Review r = this.repository.getReferenceById(review.getId());
        r.setTitulo(review.getTitulo());
        r.setDescricao(review.getDescricao());
        r.setNota(review.getNota());
        r.setObra(review.getObra());

        this.repository.save(r);
    }

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