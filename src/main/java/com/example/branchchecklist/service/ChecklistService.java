package com.example.branchchecklist.service;

import com.example.branchchecklist.dto.ChecklistCategoryDto;
import com.example.branchchecklist.dto.ChecklistResponseDto;
import com.example.branchchecklist.model.ChecklistCategory;
import com.example.branchchecklist.model.ChecklistSubItem;
import com.example.branchchecklist.repository.ChecklistRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChecklistService {

    private final ChecklistRepository repository;

    public ChecklistService(ChecklistRepository repository) {
        this.repository = repository;
    }

    public ChecklistResponseDto getChecklistData(String sectionName) {

        ChecklistCategory section = repository.findBySection(sectionName)
                .orElseThrow(() -> new RuntimeException("Section Not Found: " + sectionName));

        ChecklistResponseDto response = new ChecklistResponseDto();
        response.setSection(section.getSection());

        List<ChecklistCategoryDto> categoryDtos =
                section.getCategories()
                        .stream()
                        .map(cat -> {
                            ChecklistCategoryDto dto = new ChecklistCategoryDto();
                            dto.setTitle(cat.getTitle());

                            List<String> subItemNames = cat.getSubItems()
                                    .stream()
                                    .map(ChecklistSubItem::getName)
                                    .collect(Collectors.toList());

                            dto.setSubItems(subItemNames);
                            return dto;
                        })
                        .collect(Collectors.toList());

        response.setCategories(categoryDtos);

        return response;
    }
}
