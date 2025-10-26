package br.csi.sistema_review.model.tipo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tipos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Schema(description = "Entidade que representa o tipo de uma obra (ex: Filme, Série)")
public class Tipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID do tipo", example = "1")
    private Long id;

    @NonNull
    @Schema(description = "Nome do tipo", example = "Filme")
    private String nome;
}