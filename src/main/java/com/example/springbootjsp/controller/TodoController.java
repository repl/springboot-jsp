package com.example.springbootjsp.controller;

import com.example.springbootjsp.model.Todo;
import com.example.springbootjsp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@SessionAttributes("name")
public class TodoController {

    @Autowired
    TodoService service;

    @RequestMapping(value="/todos", method = RequestMethod.GET)
    public String showTodos(Principal principal, Authentication authentication, ModelMap model){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println("User name: " + authentication.getName());
        System.out.println("User has authorities: " + userDetails.getAuthorities());
        //Principal principal = request.getUserPrincipal();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        model.put("name", name);
        model.put("roles", auth.getAuthorities());
        model.put("todos", service.retrieveTodos(name));
        return "list-todos";
    }

    @RequestMapping(value = "/todos/{id}", method = RequestMethod.GET)
    public String showTodo(Authentication authentication, @PathVariable("id") Integer id, ModelMap model) {
        List<Todo> todos = service.retrieveTodos(authentication.getName());
        if (todos != null) {
            Todo todo = todos.stream().filter(t -> t.getId() == id).findAny().orElse(null);
            model.put("todo", todo);
        }
        return "view-todo";
    }

}
