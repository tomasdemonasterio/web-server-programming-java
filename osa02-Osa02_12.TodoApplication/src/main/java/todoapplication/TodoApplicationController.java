package todoapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TodoApplicationController {
    @Autowired
    private TodoRepository todos;
    
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("items", todos.findAll());
        return "index";
    }
    
    @PostMapping("/")
    public String create(@RequestParam String name) {
        todos.save(new Todo(name));
        return "redirect:/";
    }
    
    @GetMapping("/{id}")
    public String getOne(Model model, @PathVariable Long id) {
        Todo todo = this.todos.getOne(id);
        todo.incrementChecked();
        this.todos.save(todo);
        model.addAttribute("item", this.todos.getOne(id));
        return "todo";
    }
}
