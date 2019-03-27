package de.zebrajaeger.erfolgsblog;

import de.zebrajaeger.erfolgsblog.data.Account;
import de.zebrajaeger.erfolgsblog.data.AccountRepository;
import de.zebrajaeger.erfolgsblog.data.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author Lars Brandt, Silpion IT Solutions GmbH
 */
@Component
public class Initializer {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private AccountRepository accountRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void initializeDB() {
        if(!accountRepository.findAccountByLogin("Admin").isPresent()){
            Account account = new Account();
            account.setLogin("Admin");
            account.setPassword(passwordEncoder.encode("admin"));
            account.getRoles().add(Role.ADMIN);
            account.getRoles().add(Role.USER);
            accountRepository.save(account);
            System.out.println("Created new User: 'Admin'/'admin'");
        }
    }
}
