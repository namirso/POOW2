package br.csi.sistema_review.model.usuario;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import java.util.UUID;


@Entity
@Table(name= "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Schema(description = "Entidade que representa um usuário do sistema")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID do usuário", example = "1")
    private Long id;
    @UuidGenerator
    private UUID uuid;
    @NonNull
    @Schema(description = "Nome do usuário", example = "Felipe")
    private String nome;
    @NonNull
    private String email;
    @NonNull
    private String senha;
    @NonNull
    private Integer permissao;
}