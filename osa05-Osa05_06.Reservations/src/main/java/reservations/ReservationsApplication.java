package reservations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ReservationsApplication implements CommandLineRunner {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        // add test account
        SpringApplication.run(ReservationsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // add test account
        addTestAccount();
    }

    private void addTestAccount() {
        // Create a test account
        if (false) {
            Account testAccount = new Account();
            testAccount.setUsername("test1");
            testAccount.setPassword(passwordEncoder.encode("test1"));

            accountRepository.save(testAccount);

            System.out.println("Test account added successfully!");
        }

    }
}
