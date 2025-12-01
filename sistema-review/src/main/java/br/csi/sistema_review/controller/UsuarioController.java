package br.csi.sistema_review.controller;

import br.csi.sistema_review.model.usuario.Usuario;
import br.csi.sistema_review.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuario")
@Tag(name = "Usuários", description = "Path relacionado a operações de usuários")
public class UsuarioController {

    private UsuarioService service;
    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    /* http://localhost:8080/sistema-reviews/usuario/listar */
    @GetMapping()
    public List<Usuario> listar(){
        return this.service.listar();
    }

    @GetMapping("/uuid/{uuid}")
    public Usuario usuario(@PathVariable String uuid){
        return this.service.getUsuarioUUID(uuid);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar usuário por ID", description = "Retorna um usuário correspondente ao ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content)
    })
    public Usuario usuario( @Parameter(description = "ID do usuário a ser buscado") @PathVariable Long id){
        return this.service.getUsuario(id);
    }

    @PostMapping("/print-json")
    public void printJson(@RequestBody String json){
        System.out.println(json);
    }

    @PostMapping()
    @Operation(summary = "Criar um novo usuário", description = "Cria um novo usuário e o adiciona à lista")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos", content = @Content)
    })
    public void salvar(@RequestBody @Valid Usuario usuario){
        this.service.salvar(usuario);
    }

    @PutMapping("/uuid")
    public void atualizarUUID(@RequestBody Usuario usuario){
        this.service.atualizarUUID(usuario);
    }

    @PutMapping
    public void atualizar(@RequestBody Usuario usuario){
        this.service.atualizar(usuario);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        this.service.excluir(id);
    }


}