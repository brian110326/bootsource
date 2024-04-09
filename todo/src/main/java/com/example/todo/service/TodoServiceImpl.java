package com.example.todo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todo.dto.TodoDto;
import com.example.todo.entity.Todo;
import com.example.todo.repository.TodoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor // ==> @Autowired private final TodoRepository todoRepository
@Service
@Log4j2
public class TodoServiceImpl {

    private final TodoRepository todoRepository;

    public List<TodoDto> getList() {
        // 미완료 목록
        List<Todo> list = todoRepository.findByCompleted(false);
        // todo entity를 dto로 넘겨주기
        List<TodoDto> todoList = new ArrayList<>();

        list.forEach(entity -> {
            todoList.add(entityToDto(entity));
        });

        return todoList;

    }

    public TodoDto create(TodoDto dto) {
        // TodoDto ==> Todo entity 형태
        Todo entity = todoRepository.save(dtoToEntity(dto));

        return entityToDto(entity);
    }

    public TodoDto getTodo(Long id) {
        Todo todo = todoRepository.findById(id).get();
        return entityToDto(todo);
    }

    public List<TodoDto> getCompletedList() {
        List<Todo> entities = todoRepository.findByCompleted(true);
        List<TodoDto> list = new ArrayList<>();

        entities.forEach(entity -> {
            list.add(entityToDto(entity));
        });

        return list;
    }

    public Long todoUpdate(Long id) {
        // 업데이트 완료 후 id만 return
        Todo entity = todoRepository.findById(id).get();
        entity.setCompleted(true);
        Todo todo = todoRepository.save(entity);

        return todo.getId();

    }

    public void delete(Long id) {
        todoRepository.deleteById(id);
    }

    private TodoDto entityToDto(Todo entity) {
        return TodoDto.builder().id(entity.getId()).completed(entity.getCompleted()).important(entity.getImportant())
                .title(entity.getTitle()).createdDate(entity.getCreatedDate())
                .lastModified(entity.getLastModifiedDate())
                .build();
    }

    private Todo dtoToEntity(TodoDto dto) {
        return Todo.builder().id(dto.getId()).completed(dto.getCompleted()).important(dto.getImportant())
                .title(dto.getTitle())
                .build();
    }
}
