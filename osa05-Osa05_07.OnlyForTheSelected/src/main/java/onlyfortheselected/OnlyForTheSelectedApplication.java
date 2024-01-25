package onlyfortheselected;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class OnlyForTheSelectedApplication implements CommandLineRunner {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(OnlyForTheSelectedApplication.class, args);
    }
    
    @Override
    public void run(String... args) throws Exception {
        // add test account
        addTestAccount();
    }

    private void addTestAccount() {
        // Create a test account
        if (true) {
            Account userAccount = new Account();
            userAccount.setUsername("user");
            userAccount.setPassword(passwordEncoder.encode("user"));
            List<String> authoritiesUser = new ArrayList<>();
            authoritiesUser.add("USER");
            userAccount.setAuthorities(authoritiesUser);
            accountRepository.save(userAccount);
            
            Account adminAccount = new Account();
            List<String> authoritiesAdmin = new ArrayList<>();
            adminAccount.setUsername("admin");
            adminAccount.setPassword(passwordEncoder.encode("admin"));
            authoritiesAdmin.add("ADMIN");
            adminAccount.setAuthorities(authoritiesAdmin);
            accountRepository.save(adminAccount);
            

            System.out.println("Test account added successfully!");
        }

    }
}
