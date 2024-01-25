package reservations;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepository;
    
    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/reservations")
    public String reservations(Model model) {
        model.addAttribute("reservations", this.reservationRepository.findAll());
        
        return "reservations";
    }

    @PostMapping("/reservations")
    public String addReservation(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate reservationFrom,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate reservationTo) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        reservationRepository.save(new Reservation(this.accountRepository.findByUsername(username), reservationFrom, reservationTo));
        return "redirect:/reservations";
    }
    

}
