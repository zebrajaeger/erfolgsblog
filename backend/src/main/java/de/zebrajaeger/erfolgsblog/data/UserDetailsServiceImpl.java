package de.zebrajaeger.erfolgsblog.data;

import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Lars Brandt, Silpion IT Solutions GmbH
 */
@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {
    private AccountRepository accountRepository;

    public UserDetailsServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Account account = accountRepository
                .findAccountByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username: " + " not found"));

        List<SimpleGrantedAuthority> grantedAuthorities = account.getRoles().stream()
                .map(role -> "ROLE_" + role.toString())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new User(account.getLogin(), account.getPassword(), grantedAuthorities);
    }
}
