package com.example.api_livros.service;

import com.example.api_livros.dto.AutorDTO;
import com.example.api_livros.exception.DuplicateResourceException;
import com.example.api_livros.exception.ResourceNotFoundException;
import com.example.api_livros.model.Autor;
import com.example.api_livros.repository.AutorRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Serviço responsável pela lógica de negócio relacionada a autores.
 * <p>
 * Esta classe implementa as operações CRUD (Create, Read, Update, Delete) para autores,
 * realizando validações de negócio, conversões entre entidades e DTOs, e gerenciamento de transações.
 * </p>
 * 
 * @author @andreriffen
 * @version 1.0
 * @since 2025-11-30
 */
@Service
@RequiredArgsConstructor
public class AutorService {

    private final AutorRepository autorRepository;
    private final ModelMapper modelMapper;

    /**
     * Cria um novo autor no sistema.
     * <p>
     * Valida se já existe um autor com o email fornecido antes de criar.
     * </p>
     * 
     * @param autorDTO objeto contendo os dados do autor a ser criado
     * @return o autor criado com o ID gerado
     * @throws DuplicateResourceException se já existir um autor com o email fornecido
     */
    @Transactional
    public AutorDTO criar(AutorDTO autorDTO) {
        if (autorRepository.existsByEmail(autorDTO.getEmail())) {
            throw new DuplicateResourceException("Autor", "email", autorDTO.getEmail());
        }
        
        Autor autor = modelMapper.map(autorDTO, Autor.class);
        autor = autorRepository.save(autor);
        return modelMapper.map(autor, AutorDTO.class);
    }

    /**
     * Busca um autor pelo seu identificador.
     * 
     * @param id o identificador do autor
     * @return o autor encontrado
     * @throws ResourceNotFoundException se o autor não for encontrado
     */
    @Transactional(readOnly = true)
    public AutorDTO buscarPorId(Integer id) {
        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Autor", "id", id));
        return modelMapper.map(autor, AutorDTO.class);
    }

    /**
     * Lista todos os autores cadastrados no sistema.
     * 
     * @return lista contendo todos os autores
     */
    @Transactional(readOnly = true)
    public List<AutorDTO> listarTodos() {
        return autorRepository.findAll().stream()
                .map(autor -> modelMapper.map(autor, AutorDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Atualiza os dados de um autor existente.
     * <p>
     * Valida se o novo email já está sendo usado por outro autor antes de atualizar.
     * </p>
     * 
     * @param id o identificador do autor a ser atualizado
     * @param autorDTO objeto contendo os novos dados do autor
     * @return o autor atualizado
     * @throws ResourceNotFoundException se o autor não for encontrado
     * @throws DuplicateResourceException se o novo email já estiver sendo usado por outro autor
     */
    @Transactional
    public AutorDTO atualizar(Integer id, AutorDTO autorDTO) {
        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Autor", "id", id));
        
        if (!autor.getEmail().equals(autorDTO.getEmail()) && 
            autorRepository.existsByEmail(autorDTO.getEmail())) {
            throw new DuplicateResourceException("Autor", "email", autorDTO.getEmail());
        }
        
        autor.setNome(autorDTO.getNome());
        autor.setEmail(autorDTO.getEmail());
        autor.setNacionalidade(autorDTO.getNacionalidade());
        
        autor = autorRepository.save(autor);
        return modelMapper.map(autor, AutorDTO.class);
    }

    /**
     * Remove um autor do sistema.
     * 
     * @param id o identificador do autor a ser removido
     * @throws ResourceNotFoundException se o autor não for encontrado
     */
    @Transactional
    public void deletar(Integer id) {
        if (!autorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Autor", "id", id);
        }
        autorRepository.deleteById(id);
    }
}
