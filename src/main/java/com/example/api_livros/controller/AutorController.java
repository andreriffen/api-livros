package com.example.api_livros.controller;

import com.example.api_livros.dto.AutorDTO;
import com.example.api_livros.service.AutorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gerenciamento de autores.
 * <p>
 * Esta classe expõe endpoints RESTful para operações CRUD (Create, Read, Update, Delete)
 * relacionadas a autores. Todos os endpoints retornam respostas no formato JSON.
 * </p>
 * 
 * @author @andreriffen
 * @version 1.0
 * @since 2025-11-30
 */
@RestController
@RequestMapping("/autores")
@RequiredArgsConstructor
@Tag(name = "Autores", description = "Endpoints para gerenciamento de autores")
public class AutorController {

    private final AutorService autorService;

    /**
     * Cria um novo autor no sistema.
     * 
     * @param autorDTO objeto contendo os dados do autor a ser criado
     * @return ResponseEntity contendo o autor criado e status HTTP 201 (CREATED)
     */
    @PostMapping
    @Operation(summary = "Criar novo autor", description = "Cria um novo autor no sistema")
    public ResponseEntity<AutorDTO> criar(@Valid @RequestBody AutorDTO autorDTO) {
        AutorDTO novoAutor = autorService.criar(autorDTO);
        return new ResponseEntity<>(novoAutor, HttpStatus.CREATED);
    }

    /**
     * Busca um autor específico pelo identificador.
     * 
     * @param id o identificador do autor
     * @return ResponseEntity contendo o autor encontrado e status HTTP 200 (OK)
     */
    @GetMapping("/{id}")
    @Operation(summary = "Buscar autor por ID", description = "Retorna um autor específico pelo ID")
    public ResponseEntity<AutorDTO> buscarPorId(@PathVariable Integer id) {
        AutorDTO autor = autorService.buscarPorId(id);
        return ResponseEntity.ok(autor);
    }

    /**
     * Lista todos os autores cadastrados no sistema.
     * 
     * @return ResponseEntity contendo a lista de autores e status HTTP 200 (OK)
     */
    @GetMapping
    @Operation(summary = "Listar todos os autores", description = "Retorna uma lista com todos os autores cadastrados")
    public ResponseEntity<List<AutorDTO>> listarTodos() {
        List<AutorDTO> autores = autorService.listarTodos();
        return ResponseEntity.ok(autores);
    }

    /**
     * Atualiza os dados de um autor existente.
     * 
     * @param id o identificador do autor a ser atualizado
     * @param autorDTO objeto contendo os novos dados do autor
     * @return ResponseEntity contendo o autor atualizado e status HTTP 200 (OK)
     */
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar autor", description = "Atualiza os dados de um autor existente")
    public ResponseEntity<AutorDTO> atualizar(
            @PathVariable Integer id,
            @Valid @RequestBody AutorDTO autorDTO) {
        AutorDTO autorAtualizado = autorService.atualizar(id, autorDTO);
        return ResponseEntity.ok(autorAtualizado);
    }

    /**
     * Remove um autor do sistema.
     * 
     * @param id o identificador do autor a ser removido
     * @return ResponseEntity vazio com status HTTP 204 (NO_CONTENT)
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar autor", description = "Remove um autor do sistema")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        autorService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
