package com.todo.todo.service;

import com.todo.todo.entity.Todo;
import java.util.List;

public interface TodoService {
    List<Todo> getAllTodos();
    Todo getTodoById(Long id);
    Todo createTodo(Todo todo);
    Todo updateTodo(Long id, Todo todoDetails);
    void deleteTodo(Long id);
}
