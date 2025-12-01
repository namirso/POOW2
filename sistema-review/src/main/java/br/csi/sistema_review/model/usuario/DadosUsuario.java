package br.csi.sistema_review.model.usuario;

public record DadosUsuario(Long id, String login, Integer permissao) {
    public DadosUsuario(Usuario usuario){
        this(usuario.getId(),usuario.getEmail(),usuario.getPermissao());
    }
}
