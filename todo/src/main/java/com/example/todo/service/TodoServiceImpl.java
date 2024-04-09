package com.example.todo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    @Override
    public List<TodoDto> getList() {
        // 미완료 목록
        List<Todo> list = todoRepository.findByCompleted(false);
        // todo entity를 dto로 넘겨주기
        // List<TodoDto> todoList = new ArrayList<>();

        // list.forEach(entity -> {
        // todoList.add(entityToDto(entity));
        // });

        List<TodoDto> todoList = list.stream().map(todo -> entityToDto(todo)).collect(Collectors.toList());

        return todoList;

    }

    @Override
    public TodoDto create(TodoDto dto) {
        // TodoDto ==> Todo entity 형태
        Todo entity = todoRepository.save(dtoToEntity(dto));

        return entityToDto(entity);
    }

    @Override
    public TodoDto getTodo(Long id) {
        Todo todo = todoRepository.findById(id).get();
        return entityToDto(todo);
    }

    @Override
    public List<TodoDto> getCompletedList() {
        List<Todo> entities = todoRepository.findByCompleted(true);
        // List<TodoDto> list = new ArrayList<>();

        // entities.forEach(entity -> {
        // list.add(entityToDto(entity));
        // });

        List<TodoDto> compList = entities.stream().map(todo -> entityToDto(todo)).collect(Collectors.toList());

        return compList;
    }

    @Override
    public Long todoUpdate(Long id) {
        // 업데이트 완료 후 id만 return
        Todo entity = todoRepository.findById(id).get();
        entity.setCompleted(true);
        Todo todo = todoRepository.save(entity);

        return todo.getId();

    }

    @Override
    public void delete(Long id) {
        todoRepository.deleteById(id);
    }

}
