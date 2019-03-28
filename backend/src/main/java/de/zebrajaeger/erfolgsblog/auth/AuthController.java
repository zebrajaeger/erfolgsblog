package de.zebrajaeger.erfolgsblog.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lars Brandt, Silpion IT Solutions GmbH
 */
@RestController
public class AuthController {
    @GetMapping("/api/auth/check")
    public void checkLogin(){
        // nothing to do
    }
}
