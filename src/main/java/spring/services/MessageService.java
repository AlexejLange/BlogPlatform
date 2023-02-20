package spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.domains.Message;
import spring.repositories.MessageRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public Optional<Message> getById(Long id) {
        return messageRepository.findById(id);
    }

    public List<Message> getAll() {
        return messageRepository.findAll();
    }

    public Message save(Message message) {
        if (message.getId() == null) {
            message.setCreatedAt(LocalDateTime.now());
        }
        message.setUpdatedAt(LocalDateTime.now());
        return messageRepository.save(message);
    }

    public void delete(Message message) {
        messageRepository.delete(message);
    }

}
