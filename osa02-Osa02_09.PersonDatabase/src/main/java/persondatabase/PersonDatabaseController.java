package persondatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PersonDatabaseController {
    @Autowired
    private PersonRepository persons;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("persons", this.persons.findAll());
        return "index";
    }
    
    @PostMapping("/")
    public String homePost(@RequestParam String name) {
        this.persons.save(new Person(name));
        return "redirect:/";
    }

}
