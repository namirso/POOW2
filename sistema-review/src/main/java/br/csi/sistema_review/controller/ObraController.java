package br.csi.sistema_review.controller;

import br.csi.sistema_review.model.obra.Obra;
import br.csi.sistema_review.service.ObraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/obra")
@Tag(name = "Obras", description = "Path relacionado a operações das obras")
public class ObraController {

    private final ObraService service;

    public ObraController(ObraService service) {
        this.service = service;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar todas as obras")
    public List<Obra> listar() {
        return this.service.listar();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar obra por ID", description = "Retorna uma obra correspondente ao ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Obra encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Obra.class))),
            @ApiResponse(responseCode = "404", description = "Obra não encontrada", content = @Content)
    })
    public Obra obra(@Parameter(description = "ID da obra a ser buscada") @PathVariable Long id) {
        return this.service.getObra(id);
    }

    @GetMapping("/nome/{nome}")
    @Operation(summary = "Buscar obras por Nome", description = "Retorna uma lista de obras que contenham o nome fornecido")
    public List<Obra> obrasPorNome(@PathVariable String nome) {
        return this.service.getObrasPorNome(nome);
    }

    @GetMapping("/tipo/{idtipo}")
    @Operation(summary = "Buscar obras por ID do Tipo", description = "Retorna uma lista de obras de um tipo específico")
    public List<Obra> obrasPorTipo(@PathVariable Long idtipo) {
        return this.service.getObrasPorTipo(idtipo);
    }

    @GetMapping("/direcao/{direcao}")
    @Operation(summary = "Buscar obras por Direção", description = "Retorna uma lista de obras que contenham o nome do(a) diretor(a)")
    public List<Obra> obrasPorDirecao(@PathVariable String direcao) {
        return this.service.getObrasPorDirecao(direcao);
    }

    @PostMapping
    @Operation(summary = "Criar uma nova obra", description = "Cria uma nova obra e a adiciona ao banco")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Obra criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos", content = @Content)
    })
    public void salvar(@RequestBody @Valid Obra obra) {
        this.service.salvar(obra);
    }

    @PutMapping
    @Operation(summary = "Atualizar uma obra", description = "Atualiza uma obra existente com base no ID fornecido no corpo")
    public void atualizar(@RequestBody @Valid Obra obra) {
        this.service.atualizar(obra);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar uma obra", description = "Deleta uma obra com base no ID fornecido")
    public void deletar(@Parameter(description = "ID da obra a ser deletada") @PathVariable Long id) {
        this.service.excluir(id);
    }
}