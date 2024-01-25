package banktransfer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("accounts", this.accountRepository.findAll());
        return "index";
    }

    @Transactional
    @PostMapping("/")
    public String transfer(@RequestParam String from, @RequestParam String to, @RequestParam Integer amount) {
        Account accountFrom = this.accountRepository.findByIban(from);
        Account accountTo = this.accountRepository.findByIban(to);
        
        accountFrom.setBalance(accountFrom.getBalance() - amount);
        accountTo.setBalance(accountTo.getBalance() + amount);    
        if (accountFrom.getBalance() < 0 || accountTo.getBalance() < 0) {
            throw new NullPointerException();
        }
        return "redirect:/";
    }
    
    @PostMapping("/account") 
    public String create(@RequestParam String iban, @RequestParam int balance) {
        this.accountRepository.save(new Account(iban, balance));
        
        return "redirect:/";
    }
}
