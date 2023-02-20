package spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import spring.domains.Account;
import spring.domains.Authority;
import spring.domains.Message;
import spring.repositories.AuthorityRepository;
import spring.services.AccountService;
import spring.services.MessageService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class SeedData implements CommandLineRunner {

    @Autowired
    private MessageService messageService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Message> messages = messageService.getAll();

        if (messages.size() == 0) {

            Authority user = new Authority();
            user.setName("ROLE_USER");
            authorityRepository.save(user);

            Authority admin = new Authority();
            admin.setName("ROLE_ADMIN");
            authorityRepository.save(admin);

            Account account1 = new Account();
            Account account2 = new Account();

            account1.setFirstName("user_first");
            account1.setLastName("user_last");
            account1.setEmail("user.user@domain.com");
            account1.setPassword("password");
            Set<Authority> authorities1 = new HashSet<>();
            authorityRepository.findById("ROLE_USER").ifPresent(authorities1::add);
            account1.setAuthorities(authorities1);


            account2.setFirstName("admin_first");
            account2.setLastName("admin_last");
            account2.setEmail("admin.admin@domain.com");
            account2.setPassword("password");

            Set<Authority> authorities2 = new HashSet<>();
            authorityRepository.findById("ROLE_ADMIN").ifPresent(authorities2::add);
            authorityRepository.findById("ROLE_USER").ifPresent(authorities2::add);
            account2.setAuthorities(authorities2);

            accountService.save(account1);
            accountService.save(account2);

            Message message1 = new Message();
            message1.setTitle("What is Lorem Ipsum?");
            message1.setBody("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
            message1.setAccount(account1);

            Message message2 = new Message();
            message2.setTitle("Something else Ipsum");
            message2.setBody("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Magna eget est lorem ipsum dolor sit amet consectetur adipiscing. Tempus quam pellentesque nec nam aliquam sem et tortor. Pellentesque sit amet porttitor eget. Sed augue lacus viverra vitae congue eu consequat. Ultrices vitae auctor eu augue. Mattis rhoncus urna neque viverra. Consectetur lorem donec massa sapien faucibus et molestie ac feugiat. Sociis natoque penatibus et magnis dis parturient montes nascetur. Cursus turpis massa tincidunt dui ut ornare lectus. Odio pellentesque diam volutpat commodo sed egestas egestas fringilla. Id cursus metus aliquam eleifend mi. Nibh nisl condimentum id venenatis a condimentum.");
            message2.setAccount(account2);

            messageService.save(message1);
            messageService.save(message2);
        }
    }

}
