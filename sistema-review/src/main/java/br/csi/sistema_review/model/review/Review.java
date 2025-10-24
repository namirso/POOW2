package br.csi.sistema_review.model.review;

import br.csi.sistema_review.model.obra.Obra;
import br.csi.sistema_review.model.usuario.Usuario;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Schema(description = "Entidade que representa a review de um usuário para uma obra")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID da review", example = "1")
    private Long id;

    @NonNull
    @Schema(description = "Título da review", example = "Obra-prima")
    private String titulo;

    @NonNull
    @Schema(description = "Descrição detalhada da review", example = "Um filme impactante...")
    private String descricao;

    @NonNull
    @Schema(description = "Nota da review (ex: 1 a 5)", example = "5")
    private Integer nota;

    // Relacionamento: Muitas reviews podem ser de UMA obra.
    // A coluna no banco será 'idobra'
    @ManyToOne
    @JoinColumn(name = "idobra")
    private Obra obra;

    // Relacionamento: Muitas reviews podem ser de UM usuário.
    // A coluna no banco será 'idusuario'
    @ManyToOne
    @JoinColumn(name = "idusuario")
    private Usuario usuario;
}