package spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import spring.domains.Account;
import spring.domains.Message;
import spring.services.AccountService;
import spring.services.MessageService;

import java.security.Principal;
import java.util.Optional;

@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private AccountService accountService;

    @GetMapping("/messages/{id}")
    public String getMessage(@PathVariable Long id, Model model) {

        // find post by id
        Optional<Message> optionalMessage = this.messageService.getById(id);

        // if post exists put it in model
        if (optionalMessage.isPresent()) {
            Message message = optionalMessage.get();
            model.addAttribute("message", message);
            return "message";
        } else {
            return "404";
        }
    }

    @PostMapping("/messages/{id}")
    @PreAuthorize("isAuthenticated()")
    public String updateMessage(@PathVariable Long id, Message message, BindingResult result, Model model) {

        Optional<Message> optionalMessage = messageService.getById(id);
        if (optionalMessage.isPresent()) {
            Message existingMessage = optionalMessage.get();

            existingMessage.setTitle(message.getTitle());
            existingMessage.setBody(message.getBody());

            messageService.save(existingMessage);
        }

        return "redirect:/messages/" + message.getId();
    }

    @GetMapping("/messages/new")
    @PreAuthorize("isAuthenticated()")
    public String createNewMessage(Model model, Principal principal) {

        String authUsername = "anonymousUser";
        if (principal != null) {
            authUsername = principal.getName();
        }

        Optional<Account> optionalAccount = accountService.findOneByEmail(authUsername);
        if (optionalAccount.isPresent()) {
            Message message = new Message();
            message.setAccount(optionalAccount.get());
            model.addAttribute("message", message);
            return "message_new";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/messages/new")
    @PreAuthorize("isAuthenticated()")
    public String createNewMessage(@ModelAttribute Message message, Principal principal) {
        String authUsername = "anonymousUser";
        if (principal != null) {
            authUsername = principal.getName();
        }
        if (message.getAccount().getEmail().compareToIgnoreCase(authUsername) < 0) {
            // TODO: some kind of error?
            // our account email on the Post not equal to current logged in account!
        }
        messageService.save(message);
        return "redirect:/messages/" + message.getId();
    }

    @GetMapping("/messages/{id}/edit")
    @PreAuthorize("isAuthenticated()")
    public String getMessageForEdit(@PathVariable Long id, Model model) {

        // find post by id
        Optional<Message> optionalMessage = messageService.getById(id);
        // if post exist put it in model
        if (optionalMessage.isPresent()) {
            Message message = optionalMessage.get();
            model.addAttribute("message", message);
            return "message_edit";
        } else {
            return "404";
        }
    }

    @GetMapping("/messages/{id}/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteMessage(@PathVariable Long id) {

        // find post by id
        Optional<Message> optionalMessage = messageService.getById(id);
        if (optionalMessage.isPresent()) {
            Message message = optionalMessage.get();

            messageService.delete(message);
            return "redirect:/";
        } else {
            return "404";
        }
    }

}
