package br.csi.sistema_review.controller;

import br.csi.sistema_review.model.tipo.Tipo;
import br.csi.sistema_review.service.TipoService;
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
@RequestMapping("/tipo")
@Tag(name = "Tipos", description = "Path relacionado a operações de tipos de obra (Filme, Série, etc.)")
public class TipoController {

    private final TipoService service;

    public TipoController(TipoService service) {
        this.service = service;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar todos os tipos", description = "Retorna uma lista de todos os tipos cadastrados")
    public List<Tipo> listar() {
        return this.service.listar();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar tipo por ID", description = "Retorna um tipo correspondente ao ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Tipo.class))),
            @ApiResponse(responseCode = "404", description = "Tipo não encontrado", content = @Content)
    })
    public Tipo tipo(@Parameter(description = "ID do tipo a ser buscado") @PathVariable Long id) {
        return this.service.getTipo(id);
    }

    @GetMapping("/nome/{nome}")
    @Operation(summary = "Buscar tipo por Nome", description = "Retorna um tipo correspondente ao Nome fornecido")
    public Tipo tipoPorNome(@PathVariable String nome) {
        return this.service.getTipoPeloNome(nome);
    }

    @PostMapping
    @Operation(summary = "Criar um novo tipo", description = "Cria um novo tipo e o adiciona ao banco")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tipo criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos", content = @Content)
    })
    public void salvar(@RequestBody @Valid Tipo tipo) {
        this.service.salvar(tipo);
    }

    @PutMapping
    @Operation(summary = "Atualizar um tipo", description = "Atualiza um tipo existente com base no ID fornecido no corpo")
    public void atualizar(@RequestBody @Valid Tipo tipo) {
        this.service.atualizar(tipo);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar um tipo", description = "Deleta um tipo com base no ID fornecido")
    public void deletar(@Parameter(description = "ID do tipo a ser deletado") @PathVariable Long id) {
        this.service.excluir(id);
    }
}