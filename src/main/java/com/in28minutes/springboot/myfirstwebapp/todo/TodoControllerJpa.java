package com.in28minutes.springboot.myfirstwebapp.todo;

import com.in28minutes.springboot.myfirstwebapp.repositry.TodoRepository;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@SessionAttributes("name")
public class TodoControllerJpa {
    private TodoRepository todoRepository;

    public TodoControllerJpa(TodoRepository todoRepository) {

        this.todoRepository=todoRepository;
    }

    @RequestMapping("list-todos")
    public  String listAllTodos(ModelMap model){
        String userName = getLoggedUsername();
        List<Todo> todos=todoRepository.findByUsername(userName);
        model.put("todos",todos);


        return "listTodos";
    }
    @RequestMapping(value = "add-todo",method = RequestMethod.GET)
    public String showAddTodoPage(ModelMap model){
        Todo todo = new Todo(0,getLoggedUsername(),"",LocalDate.now().plusYears(1),false);
        model.put("todo",todo);
        return "todo";
    }
    @RequestMapping(value = "add-todo",method = RequestMethod.POST)
    public String addNewTodo( @Valid Todo todo, BindingResult result){
        if(result.hasErrors()){
            return "todo";
        }
        todo.setUsername(getLoggedUsername());
        todoRepository.save(todo);
       // todoService.addTodo(getLoggedUsername(),todo.getDescription(), todo.getTargetDate(),todo.isDone());

        return "redirect:list-todos";
    }
    @RequestMapping(value = "delete-todo")
    public String deleteTodo(@RequestParam int id){
        todoRepository.deleteById(id);
        //todoService.deleteById(id);
        return "redirect:list-todos";
    }

    @RequestMapping(value = "update-todo" ,method = RequestMethod.GET)
    public String showUpdateTodoPage(@RequestParam int id,ModelMap model){
        Todo todo = todoRepository.findById(id).get();
        model.put("todo",todo);
        return "todo";
    }
    @RequestMapping(value = "update-todo",method = RequestMethod.POST)
    public String UpdateTodoPage( @Valid Todo todo , BindingResult result){
        if(result.hasErrors()){
            return "todo";
        }
        String username = getLoggedUsername();
        todo.setUsername(username);
        todoRepository.save(todo);


        return "redirect:list-todos";
    }
    private  String getLoggedUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

}
