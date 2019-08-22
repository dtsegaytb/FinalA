package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    TodoRepository todoRepository;

    @RequestMapping("/")
    public String listTodos(Model model){
        model.addAttribute("todos", todoRepository.findAll());
            return "list";
        }
        @GetMapping("/add")
        public String todoForm(Model model){
         model.addAttribute("todo", new Todo());
         return "todoForm";
        }
        @PostMapping("/process")
    public String processForm(@Valid Todo todo, BindingResult result){
        if (result.hasErrors()){
            return "todoForm";
        }
        todoRepository.save(todo);
        return "redirect:/";
        }
        @RequestMapping("/detail/{id}")
    public String showTodo(@PathVariable("id") long id, Model model){
        model.addAttribute("todo", todoRepository.findById(id).get());
        return "show";
        }
        @RequestMapping("/update/{id}")
    public String updateTodo(@PathVariable("id") long id,
                             Model model){
        model.addAttribute("todo", todoRepository.findById(id).get());
        return "todoForm";
        }
        @RequestMapping("/delete/{id}")
    public String delTodo(@PathVariable("id") long id){
        todoRepository.deleteById(id);
        return "redirect:/";
    }
    }


