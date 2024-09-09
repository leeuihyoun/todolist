package com.todo.todo.controller;

import com.todo.todo.entity.Todo;
import com.todo.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    // TodoService 주입
    @Autowired
    private TodoService todoService;

    // 모든 Todo 항목을 가져오는 GET 요청 처리
    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodos() {
        // 모든 Todo 항목을 가져와 OK 상태와 함께 반환
        return ResponseEntity.ok(todoService.getAllTodos());
    }

    // 특정 ID의 Todo 항목을 가져오는 GET 요청 처리
    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable("id") Long id) {
        try {
            // 주어진 ID의 Todo 항목을 가져옴
            Todo todo = todoService.getTodoById(id);
            // 찾은 Todo 항목을 OK 상태와 함께 반환
            return ResponseEntity.ok(todo);
        } catch (RuntimeException e) {
            // Todo 항목을 찾지 못한 경우 404 Not Found 반환
            return ResponseEntity.notFound().build();
        }
    }

    // 새로운 Todo 항목을 생성하는 POST 요청 처리
    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
        // 새 Todo 항목을 생성하고 201 Created 상태와 함께 반환
        return ResponseEntity.status(HttpStatus.CREATED).body(todoService.createTodo(todo));
    }

    // 기존 Todo 항목을 수정하는 PUT 요청 처리
    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable("id") Long id, @RequestBody Todo todoDetails) {
        // 제목이 비어있는지 확인
        if (todoDetails.getTitle() == null || todoDetails.getTitle().trim().isEmpty()) {
            // 제목이 비어있으면 400 Bad Request 반환
            return ResponseEntity.badRequest().build();
        }
        try {
            // Todo 항목 업데이트
            Todo updatedTodo = todoService.updateTodo(id, todoDetails);
            // 업데이트된 Todo 항목을 OK 상태와 함께 반환
            return ResponseEntity.ok(updatedTodo);
        } catch (RuntimeException e) {
            // Todo 항목을 찾지 못한 경우 404 Not Found 반환
            return ResponseEntity.notFound().build();
        }
    }

    // Todo 항목을 삭제하는 DELETE 요청 처리
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable("id") Long id) {
        try {
            // Todo 항목 삭제
            todoService.deleteTodo(id);
            // 삭제 성공 시 204 No Content 반환
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            // Todo 항목을 찾지 못한 경우 404 Not Found 반환
            return ResponseEntity.notFound().build();
        }
    }

    // 처리되지 않은 RuntimeException 처리
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException e) {
        // 예상치 못한 오류 발생 시 500 Internal Server Error 반환
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}