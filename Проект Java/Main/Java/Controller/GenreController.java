package com.example.library.controller;

import com.example.library.model.Genre;
import com.example.library.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/genres")
public class GenreController {
    @Autowired
    private GenreService genreService;

    // Просмотр всех жанров
    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("genres", genreService.findAll());
        return "genres/list";
    }

    // Просмотр одного жанра по ID
    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        model.addAttribute("genre", genreService.findById(id));
        return "genres/view";
    }

    // Форма для добавления нового жанра
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("genre", new Genre());
        return "genres/edit";
    }

    // Сохранение жанра (добавление или обновление)
    @PostMapping
    public String save(@ModelAttribute Genre genre) {
        genreService.save(genre);
        return "redirect:/genres";
    }

    // Форма для редактирования жанра
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("genre", genreService.findById(id));
        return "genres/edit";
    }

    // Удаление жанра по ID
    @PostMapping("/{id}/delete")
    public String deleteById(@PathVariable Long id) {
        genreService.deleteById(id);
        return "redirect:/genres";
    }
}