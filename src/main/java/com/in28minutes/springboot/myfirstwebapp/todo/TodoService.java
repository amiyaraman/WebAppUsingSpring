package com.in28minutes.springboot.myfirstwebapp.todo;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class TodoService {
    private static List<Todo> todos=new ArrayList<>();
    private static int count = todos.size();
    static {
        todos.add(new Todo(1,"userA","UserA Kaam kro", LocalDate.now(),false));
        todos.add(new Todo(2,"userB","UserB Kaam kro", LocalDate.now(),false));
        todos.add(new Todo(3,"userC","UserC Kaam kro", LocalDate.now().plusYears(2),false));

    }
    public List<Todo> findByUsername(String username){
        Predicate<? super Todo> predicate =
                todo -> todo.getUsername().equalsIgnoreCase(username);
        return todos
                .stream()
                .filter(predicate)
                .toList();
    }
    public void addTodo(String username, String description, LocalDate targetDate,boolean done){
        int count = todos.size();

        Todo todo = new Todo(++count,username,description,targetDate,done);
         todos.add(todo);

    }
    public void deleteById(int id){
        Predicate<? super Todo> predicate=todo->todo.getId()==id;
        todos.removeIf(predicate);
    }
    public Todo findTodoById(int id){
        Predicate<? super Todo> predicate=todo->todo.getId()==id;
        Todo todo = todos
                .stream()
                .anyMatch(predicate)
                ?todos.stream()
                .filter(predicate)
                .findFirst()
                .get():null;
        return todo;
    }

    public void updateTodo(@Valid Todo todo) {
        deleteById(todo.getId());
        todos.add(todo);
    }
}
