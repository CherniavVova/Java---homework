package com.example.library.controller;

import com.example.library.model.Author;
import com.example.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    // Просмотр всех авторов
    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("authors", authorService.findAll());
        return "authors/list";
    }

    // Просмотр одного автора по ID
    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        model.addAttribute("author", authorService.findById(id));
        return "authors/view";
    }

    // Форма для добавления нового автора
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("author", new Author());
        return "authors/edit";
    }

    // Сохранение автора (добавление или обновление)
    @PostMapping
    public String save(@ModelAttribute Author author) {
        authorService.save(author);
        return "redirect:/authors";
    }

    // Форма для редактирования автора
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("author", authorService.findById(id));
        return "authors/edit";
    }

    // Удаление автора по ID
    @PostMapping("/{id}/delete")
    public String deleteById(@PathVariable Long id) {
        authorService.deleteById(id);
        return "redirect:/authors";
    }
}