package com.example.todo.service;

import java.util.List;

import com.example.todo.dto.TodoDto;
import com.example.todo.entity.Todo;

public interface TodoService {

    List<TodoDto> getList();

    TodoDto create(TodoDto dto);

    TodoDto getTodo(Long id);

    List<TodoDto> getCompletedList();

    Long todoUpdate(Long id);

    void delete(Long id);

    public default TodoDto entityToDto(Todo entity) {
        return TodoDto.builder().id(entity.getId()).completed(entity.getCompleted()).important(entity.getImportant())
                .title(entity.getTitle()).createdDate(entity.getCreatedDate())
                .lastModified(entity.getLastModifiedDate())
                .build();
    }

    public default Todo dtoToEntity(TodoDto dto) {
        return Todo.builder().id(dto.getId()).completed(dto.getCompleted()).important(dto.getImportant())
                .title(dto.getTitle())
                .build();
    }

}