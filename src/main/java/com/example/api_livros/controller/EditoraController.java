package com.example.api_livros.controller;

import com.example.api_livros.dto.EditoraDTO;
import com.example.api_livros.service.EditoraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gerenciamento de editoras.
 * <p>
 * Esta classe expõe endpoints RESTful para operações CRUD (Create, Read, Update, Delete)
 * relacionadas a editoras. Todos os endpoints retornam respostas no formato JSON.
 * </p>
 * 
 * @author @andreriffen
 * @version 1.0
 * @since 2025-11-30
 */
@RestController
@RequestMapping("/editoras")
@RequiredArgsConstructor
@Tag(name = "Editoras", description = "Endpoints para gerenciamento de editoras")
public class EditoraController {

    private final EditoraService editoraService;

    /**
     * Cria uma nova editora no sistema.
     * 
     * @param editoraDTO objeto contendo os dados da editora a ser criada
     * @return ResponseEntity contendo a editora criada e status HTTP 201 (CREATED)
     */
    @PostMapping
    @Operation(summary = "Criar nova editora", description = "Cria uma nova editora no sistema")
    public ResponseEntity<EditoraDTO> criar(@Valid @RequestBody EditoraDTO editoraDTO) {
        EditoraDTO novaEditora = editoraService.criar(editoraDTO);
        return new ResponseEntity<>(novaEditora, HttpStatus.CREATED);
    }

    /**
     * Busca uma editora específica pelo identificador.
     * 
     * @param id o identificador da editora
     * @return ResponseEntity contendo a editora encontrada e status HTTP 200 (OK)
     */
    @GetMapping("/{id}")
    @Operation(summary = "Buscar editora por ID", description = "Retorna uma editora específica pelo ID")
    public ResponseEntity<EditoraDTO> buscarPorId(@PathVariable Integer id) {
        EditoraDTO editora = editoraService.buscarPorId(id);
        return ResponseEntity.ok(editora);
    }

    /**
     * Lista todas as editoras cadastradas no sistema.
     * 
     * @return ResponseEntity contendo a lista de editoras e status HTTP 200 (OK)
     */
    @GetMapping
    @Operation(summary = "Listar todas as editoras", description = "Retorna uma lista com todas as editoras cadastradas")
    public ResponseEntity<List<EditoraDTO>> listarTodos() {
        List<EditoraDTO> editoras = editoraService.listarTodos();
        return ResponseEntity.ok(editoras);
    }

    /**
     * Atualiza os dados de uma editora existente.
     * 
     * @param id o identificador da editora a ser atualizada
     * @param editoraDTO objeto contendo os novos dados da editora
     * @return ResponseEntity contendo a editora atualizada e status HTTP 200 (OK)
     */
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar editora", description = "Atualiza os dados de uma editora existente")
    public ResponseEntity<EditoraDTO> atualizar(
            @PathVariable Integer id,
            @Valid @RequestBody EditoraDTO editoraDTO) {
        EditoraDTO editoraAtualizada = editoraService.atualizar(id, editoraDTO);
        return ResponseEntity.ok(editoraAtualizada);
    }

    /**
     * Remove uma editora do sistema.
     * 
     * @param id o identificador da editora a ser removida
     * @return ResponseEntity vazio com status HTTP 204 (NO_CONTENT)
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar editora", description = "Remove uma editora do sistema")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        editoraService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
