package com.example.api_livros.service;

import com.example.api_livros.dto.EditoraDTO;
import com.example.api_livros.exception.DuplicateResourceException;
import com.example.api_livros.exception.ResourceNotFoundException;
import com.example.api_livros.model.Editora;
import com.example.api_livros.repository.EditoraRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Serviço responsável pela lógica de negócio relacionada a editoras.
 * <p>
 * Esta classe implementa as operações CRUD (Create, Read, Update, Delete) para editoras,
 * realizando validações de negócio, conversões entre entidades e DTOs, e gerenciamento de transações.
 * </p>
 * 
 * @author @andreriffen
 * @version 1.0
 * @since 2025-11-30
 */
@Service
@RequiredArgsConstructor
public class EditoraService {

    private final EditoraRepository editoraRepository;
    private final ModelMapper modelMapper;

    /**
     * Cria uma nova editora no sistema.
     * <p>
     * Valida se já existe uma editora com o email fornecido antes de criar.
     * </p>
     * 
     * @param editoraDTO objeto contendo os dados da editora a ser criada
     * @return a editora criada com o ID gerado
     * @throws DuplicateResourceException se já existir uma editora com o email fornecido
     */
    @Transactional
    public EditoraDTO criar(EditoraDTO editoraDTO) {
        if (editoraRepository.existsByEmail(editoraDTO.getEmail())) {
            throw new DuplicateResourceException("Editora", "email", editoraDTO.getEmail());
        }
        
        Editora editora = modelMapper.map(editoraDTO, Editora.class);
        editora = editoraRepository.save(editora);
        return modelMapper.map(editora, EditoraDTO.class);
    }

    /**
     * Busca uma editora pelo seu identificador.
     * 
     * @param id o identificador da editora
     * @return a editora encontrada
     * @throws ResourceNotFoundException se a editora não for encontrada
     */
    @Transactional(readOnly = true)
    public EditoraDTO buscarPorId(Integer id) {
        Editora editora = editoraRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Editora", "id", id));
        return modelMapper.map(editora, EditoraDTO.class);
    }

    /**
     * Lista todas as editoras cadastradas no sistema.
     * 
     * @return lista contendo todas as editoras
     */
    @Transactional(readOnly = true)
    public List<EditoraDTO> listarTodos() {
        return editoraRepository.findAll().stream()
                .map(editora -> modelMapper.map(editora, EditoraDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Atualiza os dados de uma editora existente.
     * <p>
     * Valida se o novo email já está sendo usado por outra editora antes de atualizar.
     * </p>
     * 
     * @param id o identificador da editora a ser atualizada
     * @param editoraDTO objeto contendo os novos dados da editora
     * @return a editora atualizada
     * @throws ResourceNotFoundException se a editora não for encontrada
     * @throws DuplicateResourceException se o novo email já estiver sendo usado por outra editora
     */
    @Transactional
    public EditoraDTO atualizar(Integer id, EditoraDTO editoraDTO) {
        Editora editora = editoraRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Editora", "id", id));
        
        if (!editora.getEmail().equals(editoraDTO.getEmail()) && 
            editoraRepository.existsByEmail(editoraDTO.getEmail())) {
            throw new DuplicateResourceException("Editora", "email", editoraDTO.getEmail());
        }
        
        editora.setNome(editoraDTO.getNome());
        editora.setEmail(editoraDTO.getEmail());
        editora.setCidade(editoraDTO.getCidade());
        editora.setEstado(editoraDTO.getEstado());
        
        editora = editoraRepository.save(editora);
        return modelMapper.map(editora, EditoraDTO.class);
    }

    /**
     * Remove uma editora do sistema.
     * 
     * @param id o identificador da editora a ser removida
     * @throws ResourceNotFoundException se a editora não for encontrada
     */
    @Transactional
    public void deletar(Integer id) {
        if (!editoraRepository.existsById(id)) {
            throw new ResourceNotFoundException("Editora", "id", id);
        }
        editoraRepository.deleteById(id);
    }
}
