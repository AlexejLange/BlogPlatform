package spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import spring.domains.Message;
import spring.services.MessageService;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private MessageService messageService;

    @GetMapping("/")
    public String home(Model model) {
        List<Message> messages = messageService.getAll();
        model.addAttribute("messages", messages);
        return "home";
    }
}
