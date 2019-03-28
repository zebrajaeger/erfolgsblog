package de.zebrajaeger.erfolgsblog.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Lars Brandt, Silpion IT Solutions GmbH
 */
@Controller
public class PendingEntryController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/api/pending/finalize")
    public long finalizePending() {
        Account account = getAccount();
        Entry pending = getPending(account);
        return pending.getId();
    }

    @GetMapping("/api/pending")
    public long getPendingEntryId() {
        Account account = getAccount();
        Entry pending = getPending(account);
        return pending.getId();
    }


    @PostMapping("/api/pending")
    public void addItemToPending(@RequestParam Item.Type type, @RequestParam String text, @RequestParam MultipartFile file) throws IOException {
        Account account = getAccount();
        Entry pending = getPending(account);

        Item i = new Item();
        pending.getItems().add(i);

        i.setType(type);
        i.setText(text);
        if(file!=null) {
            i.setFile(file.getBytes());
        }

        accountRepository.save(account);
    }

    private Entry getPending(Account account) {
        Entry pending = account.getPending();
        if (pending == null) {
            pending = new Entry();
        }
        return pending;
    }

    private Account getAccount() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account account;
        if (authentication.getClass().equals(Account.class)) {
            account = (Account) authentication;
        } else {
            account = accountRepository.findAccountByLogin(authentication.getName()).orElseThrow(() -> new AccountStatusException("Not logged in") {
            });
        }
        return account;
    }

}
