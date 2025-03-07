package com.example.library.service;

import com.example.library.model.Genre;
import com.example.library.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {
    @Autowired
    private GenreRepository genreRepository;

    // Получить все жанры
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    // Получить жанр по ID
    public Genre findById(Long id) {
        return genreRepository.findById(id).orElse(null);
    }

    // Сохранить жанр (добавление или обновление)
    public Genre save(Genre genre) {
        return genreRepository.save(genre);
    }

    // Удалить жанр по ID
    public void deleteById(Long id) {
        genreRepository.deleteById(id);
    }
}