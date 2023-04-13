package com.in28minutes.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TodoService {
    private static List<Todo> todos=new ArrayList<>();
    static {
        todos.add(new Todo(1,"userA","UserA Kaam kro", LocalDate.now(),false));
        todos.add(new Todo(2,"userB","UserB Kaam kro", LocalDate.now(),false));
        todos.add(new Todo(3,"userC","UserC Kaam kro", LocalDate.now().plusYears(2),false));

    }
    public List<Todo> findByUsername(String username){
        return todos;
    }
}
