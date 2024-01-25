package greeting;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GreetingController {

    ///greet?greeting=Greetings&name=Earthling
    @GetMapping("/greet")
    @ResponseBody
    public String greet(@RequestParam String greeting,
                        @RequestParam String name) {
        return greeting + ", " + name;
    }
}
