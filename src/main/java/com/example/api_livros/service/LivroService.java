package com.example.api_livros.service;

import com.example.api_livros.dto.LivroDTO;
import com.example.api_livros.exception.DuplicateResourceException;
import com.example.api_livros.exception.ResourceNotFoundException;
import com.example.api_livros.model.Autor;
import com.example.api_livros.model.Editora;
import com.example.api_livros.model.Livro;
import com.example.api_livros.repository.AutorRepository;
import com.example.api_livros.repository.EditoraRepository;
import com.example.api_livros.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Serviço responsável pela lógica de negócio relacionada a livros.
 * <p>
 * Esta classe implementa as operações CRUD (Create, Read, Update, Delete) para livros,
 * realizando validações de negócio, gerenciamento de relacionamentos com autor e editora,
 * conversões entre entidades e DTOs, e gerenciamento de transações.
 * </p>
 * 
 * @author @andreriffen
 * @version 1.0
 * @since 2025-11-30
 */
@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;
    private final EditoraRepository editoraRepository;

    /**
     * Cria um novo livro no sistema.
     * <p>
     * Valida se já existe um livro com o ISBN fornecido e se o autor e editora existem
     * antes de criar o livro. O preço de venda é calculado automaticamente.
     * </p>
     * 
     * @param livroDTO objeto contendo os dados do livro a ser criado
     * @return o livro criado com o ID gerado e preço de venda calculado
     * @throws DuplicateResourceException se já existir um livro com o ISBN fornecido
     * @throws ResourceNotFoundException se o autor ou editora não forem encontrados
     */
    @Transactional
    public LivroDTO criar(LivroDTO livroDTO) {
        if (livroRepository.existsByIsbn(livroDTO.getIsbn())) {
            throw new DuplicateResourceException("Livro", "isbn", livroDTO.getIsbn());
        }
        
        Autor autor = autorRepository.findById(livroDTO.getAutorId())
                .orElseThrow(() -> new ResourceNotFoundException("Autor", "id", livroDTO.getAutorId()));
        
        Editora editora = editoraRepository.findById(livroDTO.getEditoraId())
                .orElseThrow(() -> new ResourceNotFoundException("Editora", "id", livroDTO.getEditoraId()));
        
        Livro livro = new Livro();
        livro.setTitulo(livroDTO.getTitulo());
        livro.setIsbn(livroDTO.getIsbn());
        livro.setPrecoDeCusto(livroDTO.getPrecoDeCusto());
        livro.setMargemDeLucro(livroDTO.getMargemDeLucro());
        livro.setStatus(livroDTO.getStatus());
        livro.setAutor(autor);
        livro.setEditora(editora);
        
        livro = livroRepository.save(livro);
        return convertToDTO(livro);
    }

    /**
     * Busca um livro pelo seu identificador.
     * 
     * @param id o identificador do livro
     * @return o livro encontrado com todas as informações incluindo autor e editora
     * @throws ResourceNotFoundException se o livro não for encontrado
     */
    @Transactional(readOnly = true)
    public LivroDTO buscarPorId(Integer id) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Livro", "id", id));
        return convertToDTO(livro);
    }

    /**
     * Lista todos os livros cadastrados no sistema.
     * 
     * @return lista contendo todos os livros com suas informações completas
     */
    @Transactional(readOnly = true)
    public List<LivroDTO> listarTodos() {
        return livroRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Atualiza os dados de um livro existente.
     * <p>
     * Valida se o novo ISBN já está sendo usado por outro livro e se o autor e editora
     * existem antes de atualizar. O preço de venda é recalculado automaticamente.
     * </p>
     * 
     * @param id o identificador do livro a ser atualizado
     * @param livroDTO objeto contendo os novos dados do livro
     * @return o livro atualizado com preço de venda recalculado
     * @throws ResourceNotFoundException se o livro, autor ou editora não forem encontrados
     * @throws DuplicateResourceException se o novo ISBN já estiver sendo usado por outro livro
     */
    @Transactional
    public LivroDTO atualizar(Integer id, LivroDTO livroDTO) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Livro", "id", id));
        
        if (!livro.getIsbn().equals(livroDTO.getIsbn()) && 
            livroRepository.existsByIsbn(livroDTO.getIsbn())) {
            throw new DuplicateResourceException("Livro", "isbn", livroDTO.getIsbn());
        }
        
        Autor autor = autorRepository.findById(livroDTO.getAutorId())
                .orElseThrow(() -> new ResourceNotFoundException("Autor", "id", livroDTO.getAutorId()));
        
        Editora editora = editoraRepository.findById(livroDTO.getEditoraId())
                .orElseThrow(() -> new ResourceNotFoundException("Editora", "id", livroDTO.getEditoraId()));
        
        livro.setTitulo(livroDTO.getTitulo());
        livro.setIsbn(livroDTO.getIsbn());
        livro.setPrecoDeCusto(livroDTO.getPrecoDeCusto());
        livro.setMargemDeLucro(livroDTO.getMargemDeLucro());
        livro.setStatus(livroDTO.getStatus());
        livro.setAutor(autor);
        livro.setEditora(editora);
        
        livro = livroRepository.save(livro);
        return convertToDTO(livro);
    }

    /**
     * Remove um livro do sistema.
     * 
     * @param id o identificador do livro a ser removido
     * @throws ResourceNotFoundException se o livro não for encontrado
     */
    @Transactional
    public void deletar(Integer id) {
        if (!livroRepository.existsById(id)) {
            throw new ResourceNotFoundException("Livro", "id", id);
        }
        livroRepository.deleteById(id);
    }

    /**
     * Converte uma entidade Livro para seu DTO correspondente.
     * <p>
     * Este método mapeia todos os campos da entidade para o DTO, incluindo
     * informações do autor e editora relacionados.
     * </p>
     * 
     * @param livro a entidade Livro a ser convertida
     * @return o DTO correspondente com todos os dados mapeados
     */
    private LivroDTO convertToDTO(Livro livro) {
        LivroDTO dto = new LivroDTO();
        dto.setId(livro.getId());
        dto.setTitulo(livro.getTitulo());
        dto.setIsbn(livro.getIsbn());
        dto.setPrecoDeCusto(livro.getPrecoDeCusto());
        dto.setPrecoDeVenda(livro.getPrecoDeVenda());
        dto.setMargemDeLucro(livro.getMargemDeLucro());
        dto.setDataDeCadastro(livro.getDataDeCadastro());
        dto.setStatus(livro.getStatus());
        dto.setAutorId(livro.getAutor().getId());
        dto.setAutorNome(livro.getAutor().getNome());
        dto.setEditoraId(livro.getEditora().getId());
        dto.setEditoraNome(livro.getEditora().getNome());
        return dto;
    }
}
