package com.quizhub.quiz.services;

import com.quizhub.quiz.event.EventRequest;
import com.quizhub.quiz.exceptions.BadRequestException;
import com.quizhub.quiz.exceptions.ConflictException;
import com.quizhub.quiz.model.Category;
import com.quizhub.quiz.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.quizhub.quiz.services.QuizService.registerEvent;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        registerEvent(EventRequest.actionType.GET, "/api/categories/all", "200");
        return categoryRepository.findAll();
    }

    public Category add(Category category) {
        if (categoryRepository.existsByName(category.getName())) {
            registerEvent(EventRequest.actionType.GET, "/api/categories", "409");
            throw new ConflictException("Name already in use");
        }
        registerEvent(EventRequest.actionType.GET, "/api/categories", "200");
        return categoryRepository.save(category);
    }

    public Category getCategory(UUID id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            registerEvent(EventRequest.actionType.GET, "/api/categories", "200");
            return optionalCategory.get();
        } else {
            registerEvent(EventRequest.actionType.GET, "/api/categories", "400");
            throw new BadRequestException("Wrong category id");
        }
    }
}
