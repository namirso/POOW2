package br.csi.sistema_review.model.obra;

import br.csi.sistema_review.model.tipo.Tipo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "obras")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Schema(description = "Entidade que representa uma obra (filme, série, etc.)")
public class Obra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID da obra", example = "1")
    private Long id;

    @NonNull
    @Schema(description = "Nome da obra", example = "Clube da Luta")
    private String nome;

    @NonNull
    @Schema(description = "Nome do(a) diretor(a)", example = "David Fincher")
    private String direcao;

    @Schema(description = "URL da imagem de capa", example = "https://url.com/imagem.png")
    private String url_imagem;

    // Relacionamento: Muitas obras podem pertencer a UM tipo.
    // A coluna no banco será 'idtipo'
    @ManyToOne
    @JoinColumn(name = "idtipo")
    private Tipo tipo;
}