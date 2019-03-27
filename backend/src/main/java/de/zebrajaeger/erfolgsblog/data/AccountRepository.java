package de.zebrajaeger.erfolgsblog.data;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author Lars Brandt, Silpion IT Solutions GmbH
 */
public interface AccountRepository extends CrudRepository<Account, Long> {

    Optional<Account> findAccountByLogin(String login);
}
