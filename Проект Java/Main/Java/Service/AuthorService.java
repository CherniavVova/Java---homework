package com.example.library.service;

import com.example.library.model.Author;
import com.example.library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    // Получить всех авторов
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    // Получить автора по ID
    public Author findById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    // Сохранить автора (добавление или обновление)
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    // Удалить автора по ID
    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }
}