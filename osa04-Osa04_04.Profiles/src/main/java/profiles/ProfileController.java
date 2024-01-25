package profiles;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProfileController {

    @Autowired
    private Environment environment;

    @ResponseBody
    @GetMapping("/profile")
    public String getProfile() {
        String[] profiles = this.environment.getActiveProfiles();
        String allProfiles = "";
        for (int i = 0; i < profiles.length; i++) {
            allProfiles += profiles[i];
        }
        return allProfiles;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("profiles", this.environment.getActiveProfiles());
        return "index";
    }

}
