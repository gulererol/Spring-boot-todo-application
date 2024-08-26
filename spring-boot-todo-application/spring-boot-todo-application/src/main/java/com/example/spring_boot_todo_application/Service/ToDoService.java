package com.example.spring_boot_todo_application.Service;

import com.example.spring_boot_todo_application.Entity.ToDo;
import com.example.spring_boot_todo_application.Repository.ToDoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ToDoService {
    private final ToDoRepository toDoRepository;

    public ToDoService(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    public List<ToDo> getAllToDos() {
        return toDoRepository.findAll();
    }

    public ToDo getToDoById(Long id) {
        return toDoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ToDo not found with id " + id));
    }

    public ToDo createToDo(ToDo toDo) {
        toDo.setCreatedDate(LocalDateTime.now());
        return toDoRepository.save(toDo);
    }

    public ToDo updateToDo(Long id, ToDo toDoDetails) {
        ToDo toDo = getToDoById(id);
        toDo.setDescription(toDoDetails.getDescription());
        toDo.setCompleted(toDoDetails.getCompleted());
        toDo.setDueDate(toDoDetails.getDueDate());
        return toDoRepository.save(toDo);
    }

    public void deleteToDo(Long id) {
        ToDo toDo = getToDoById(id);
        toDoRepository.delete(toDo);
    }
}
