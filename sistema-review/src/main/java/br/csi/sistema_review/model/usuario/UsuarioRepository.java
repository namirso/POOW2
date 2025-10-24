package br.csi.sistema_review.model.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    public Usuario findUsuarioByUuid(UUID uuid);
    public void deleteUsuarioByUuid(UUID uuid);
}