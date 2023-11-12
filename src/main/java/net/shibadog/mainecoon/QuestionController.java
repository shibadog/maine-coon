package net.shibadog.mainecoon;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class QuestionController {
    
    private final QuestionService service;

    public QuestionController(QuestionService service) {
        this.service = service;
    }

    @GetMapping(value="/")
    public String getQuestion(Model model) {
        final int questionCount = 4;
        final int digit = 1;
        Map<String, List<Question>> questions = Map.of(
                "plus", IntStream.range(0, questionCount)
                        .mapToObj(i -> service.additionCarry(digit))
                        .map(Question::of)
                        .toList(),
                "minus", IntStream.range(0, questionCount)
                        .mapToObj(i -> service.subtractionBorrow(digit))
                        .map(Question::of)
                        .toList(),
                "times", IntStream.range(0, questionCount)
                        .mapToObj(i -> service.multiplication())
                        .map(Question::of)
                        .toList(),
                "divide", IntStream.range(0, questionCount)
                        .mapToObj(i -> service.divisionRemainder())
                        .map(Question::of)
                        .toList()
        );
        model.addAttribute("questions", questions);
        return "index";
    }
    
    public static record Question (
        String siki,
        String answer
    ) {
        public static Question of(net.shibadog.mainecoon.QuestionService.Question q) {
            return new Question(q.siki().toString(), q.answer());
        }
    }
}
