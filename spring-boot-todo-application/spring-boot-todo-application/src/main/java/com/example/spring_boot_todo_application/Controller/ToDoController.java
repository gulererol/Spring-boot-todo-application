package com.example.spring_boot_todo_application.Controller;

import com.example.spring_boot_todo_application.Entity.ToDo;
import com.example.spring_boot_todo_application.Service.ToDoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class ToDoController {
    private final ToDoService toDoService;

    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @GetMapping
    public List<ToDo> getAllToDos() {
        return toDoService.getAllToDos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToDo> getToDoById(@PathVariable Long id) {
        ToDo toDo = toDoService.getToDoById(id);
        return ResponseEntity.ok(toDo);
    }

    @PostMapping
    public ResponseEntity<ToDo> createToDo(@RequestBody ToDo toDo) {
        ToDo createdToDo = toDoService.createToDo(toDo);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdToDo);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ToDo> updateToDo(@PathVariable Long id, @RequestBody ToDo toDoDetails) {
        ToDo updatedToDo = toDoService.updateToDo(id, toDoDetails);
        return ResponseEntity.ok(updatedToDo);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteToDo(@PathVariable Long id) {
        toDoService.deleteToDo(id);
        return ResponseEntity.noContent().build();
    }
}

