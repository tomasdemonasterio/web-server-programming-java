package examsandquestions;

import java.time.LocalDate;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ExamController {
    @Autowired
    private ExamsRepository examsRepo;
    
    @Autowired
    private QuestionsRepository questionsRepo;

    @GetMapping("/exams")
    public String listExams(Model model, @RequestParam(defaultValue = "0") Integer page) {
        Pageable pageable = PageRequest.of(page, 5, Sort.by("examDate").ascending());
        model.addAttribute("exams", examsRepo.findAll(pageable));
        return "exams";
    }

    @GetMapping("/exams/{id}")
    public String viewExam(Model model, @PathVariable Long id) {
        model.addAttribute("exam", this.examsRepo.getOne(id));
        model.addAttribute("questions", this.questionsRepo.findAll());

        return "exam";
    }
    @PostMapping("/exams")
    public String addExam(@RequestParam String subject,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate examDate) {
        Exam exam = new Exam();
        exam.setSubject(subject);
        exam.setExamDate(examDate);
        this.examsRepo.save(exam);

        return "redirect:/exams";
    }

    @PostMapping("/exams/{examId}/questions/{questionId}")
    @Transactional
    public String addQuestionToExam(@PathVariable Long examId, @PathVariable Long questionId) {
        Exam exam = examsRepo.getOne(examId);
        Question question = questionsRepo.getOne(questionId);
        if (!exam.getQuestions().contains(question)) {
            exam.getQuestions().add(question);
        } 
        return "redirect:/exams/" + examId;
    }
}
