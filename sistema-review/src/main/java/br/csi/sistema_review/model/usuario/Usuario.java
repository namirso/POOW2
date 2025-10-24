@Entity
@Table(name= "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Usuario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @UudiGenerator
    private UUID uuid;

    @NotBlank
    private String nome;

    @Email(message = "Email inválido")
    private String email;

    @NonNull
    private String senha;
}