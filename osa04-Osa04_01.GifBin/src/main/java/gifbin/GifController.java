package gifbin;

import java.io.IOException;
import java.io.OutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class GifController {

    @Autowired
    private GifRepository gifRepo;

    @GetMapping("/gifs")
    public String redirect() {
        return "redirect:/gifs/1";
    }

    @GetMapping("/gifs/{id}")
    public String home(Model model, @PathVariable(name = "id") Long id) {
        model.addAttribute("count", gifRepo.count());
        Gif gif = gifRepo.getOne(id);
        
        if (id > 1 && id <= gifRepo.count()) {
            model.addAttribute("previous", id - 1);
        }
        
        if (id > 0 && id <= gifRepo.count()) {
            model.addAttribute("current", id);
        }

        if (id > 0 && id < gifRepo.count()) {
            model.addAttribute("next", id + 1);
        }

        return "gifs";
    }

    @GetMapping(path = "/gifs/{id}/content", produces = "image/gif")
    @ResponseBody
    public byte[] get(OutputStream out, @PathVariable Long id) throws IOException {
        return gifRepo.getOne(id).getContent();
    }

    @PostMapping("/gifs")
    public String create(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.getContentType().startsWith("image/gif")) {
            Gif gif = new Gif();
            gif.setContent(file.getBytes());
            gifRepo.save(gif);
        }

        return "redirect:/gifs";
    }

}
