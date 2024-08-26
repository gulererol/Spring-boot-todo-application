package com.example.spring_boot_todo_application.Repository;

import com.example.spring_boot_todo_application.Entity.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {
}

