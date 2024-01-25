package examsandquestions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class QuestionController {
    @Autowired
    private QuestionsRepository questionsRepo; 

    @GetMapping("/questions")
    public String list(Model model) {
        model.addAttribute("questions", this.questionsRepo.findAll());
        return "questions";
    }

    @PostMapping("/questions")
    public String addQuestion(@RequestParam String title, @RequestParam String content) {
        Question question = new Question();
        question.setTitle(title);
        question.setContent(content);
        questionsRepo.save(question);
        
        return "redirect:/questions";
    }

}
