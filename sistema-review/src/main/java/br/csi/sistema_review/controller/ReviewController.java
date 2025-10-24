package br.csi.sistema_review.controller;

import br.csi.sistema_review.model.review.Review;
import br.csi.sistema_review.service.ReviewService;
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
@RequestMapping("/review")
@Tag(name = "Reviews", description = "Path relacionado a operações das reviews")
public class ReviewController {

    private final ReviewService service;

    public ReviewController(ReviewService service) {
        this.service = service;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar todas as reviews")
    public List<Review> listar() {
        return this.service.listar();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar review por ID", description = "Retorna uma review correspondente ao ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Review encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Review.class))),
            @ApiResponse(responseCode = "404", description = "Review não encontrada", content = @Content)
    })
    public Review review(@Parameter(description = "ID da review a ser buscada") @PathVariable Long id) {
        return this.service.getReview(id);
    }

    @GetMapping("/obra/{idobra}")
    @Operation(summary = "Buscar reviews por ID da Obra", description = "Retorna todas as reviews de uma obra específica")
    public List<Review> reviewsPorObra(@PathVariable Long idobra) {
        return this.service.getReviewsPorObra(idobra);
    }

    @GetMapping("/usuario/{idusuario}")
    @Operation(summary = "Buscar reviews por ID do Usuário", description = "Retorna todas as reviews de um usuário específico")
    public List<Review> reviewsPorUsuario(@PathVariable Long idusuario) {
        return this.service.getReviewsPorUsuario(idusuario);
    }

    @GetMapping("/obra/{idobra}/usuario/{idusuario}")
    @Operation(summary = "Buscar review por Obra e Usuário", description = "Retorna a review de um usuário para uma obra específica")
    public Review reviewPorObraEUsuario(@PathVariable Long idobra, @PathVariable Long idusuario) {
        return this.service.getReviewPorObraEUsuario(idobra, idusuario);
    }

    @PostMapping
    @Operation(summary = "Criar uma nova review", description = "Cria uma nova review e a adiciona ao banco")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Review criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos", content = @Content)
    })
    public void salvar(@RequestBody @Valid Review review) {
        this.service.salvar(review);
    }

    @PutMapping
    @Operation(summary = "Atualizar uma review", description = "Atualiza uma review existente com base no ID fornecido no corpo")
    public void atualizar(@RequestBody @Valid Review review) {
        this.service.atualizar(review);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar uma review", description = "Deleta uma review com base no ID fornecido")
    public void deletar(@Parameter(description = "ID da review a ser deletada") @PathVariable Long id) {
        this.service.excluir(id);
    }
}