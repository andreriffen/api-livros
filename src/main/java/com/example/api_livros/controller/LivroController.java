package com.example.api_livros.controller;

import com.example.api_livros.dto.LivroDTO;
import com.example.api_livros.service.LivroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gerenciamento de livros.
 * <p>
 * Esta classe expõe endpoints RESTful para operações CRUD (Create, Read, Update, Delete)
 * relacionadas a livros. Todos os endpoints retornam respostas no formato JSON.
 * </p>
 * 
 * @author @andreriffen
 * @version 1.0
 * @since 2025-11-30
 */
@RestController
@RequestMapping("/livros")
@RequiredArgsConstructor
@Tag(name = "Livros", description = "Endpoints para gerenciamento de livros")
public class LivroController {

    private final LivroService livroService;

    /**
     * Cria um novo livro no sistema.
     * 
     * @param livroDTO objeto contendo os dados do livro a ser criado
     * @return ResponseEntity contendo o livro criado e status HTTP 201 (CREATED)
     */
    @PostMapping
    @Operation(summary = "Criar novo livro", description = "Cria um novo livro no sistema")
    public ResponseEntity<LivroDTO> criar(@Valid @RequestBody LivroDTO livroDTO) {
        LivroDTO novoLivro = livroService.criar(livroDTO);
        return new ResponseEntity<>(novoLivro, HttpStatus.CREATED);
    }

    /**
     * Busca um livro específico pelo identificador.
     * 
     * @param id o identificador do livro
     * @return ResponseEntity contendo o livro encontrado e status HTTP 200 (OK)
     */
    @GetMapping("/{id}")
    @Operation(summary = "Buscar livro por ID", description = "Retorna um livro específico pelo ID")
    public ResponseEntity<LivroDTO> buscarPorId(@PathVariable Integer id) {
        LivroDTO livro = livroService.buscarPorId(id);
        return ResponseEntity.ok(livro);
    }

    /**
     * Lista todos os livros cadastrados no sistema.
     * 
     * @return ResponseEntity contendo a lista de livros e status HTTP 200 (OK)
     */
    @GetMapping
    @Operation(summary = "Listar todos os livros", description = "Retorna uma lista com todos os livros cadastrados")
    public ResponseEntity<List<LivroDTO>> listarTodos() {
        List<LivroDTO> livros = livroService.listarTodos();
        return ResponseEntity.ok(livros);
    }

    /**
     * Atualiza os dados de um livro existente.
     * 
     * @param id o identificador do livro a ser atualizado
     * @param livroDTO objeto contendo os novos dados do livro
     * @return ResponseEntity contendo o livro atualizado e status HTTP 200 (OK)
     */
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar livro", description = "Atualiza os dados de um livro existente")
    public ResponseEntity<LivroDTO> atualizar(
            @PathVariable Integer id,
            @Valid @RequestBody LivroDTO livroDTO) {
        LivroDTO livroAtualizado = livroService.atualizar(id, livroDTO);
        return ResponseEntity.ok(livroAtualizado);
    }

    /**
     * Remove um livro do sistema.
     * 
     * @param id o identificador do livro a ser removido
     * @return ResponseEntity vazio com status HTTP 204 (NO_CONTENT)
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar livro", description = "Remove um livro do sistema")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        livroService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
