package reloadheroes;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReloadController {
    @Autowired
    ReloadStatusService reloadStatusService;

    @RequestMapping("*")
    public String reload(Model model) {
        model.addAttribute("status", reloadStatusService.getReloadStatus());

        model.addAttribute("scores", reloadStatusService.getTopFive());
        return "index";
    }
}
