package filemanager;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class FileController {
    @Autowired
    private FileRepository fileRepo;

    @GetMapping("/")
    public String redirect() {
        return "redirect:/files";
    }
    
    @GetMapping("/files")
    public String files(Model model) {
        model.addAttribute("files", fileRepo.findAll());
        
        return "files";
    }
    
    @PostMapping("files")
    public String create(@RequestParam("file") MultipartFile fileInput) throws IOException {
        File file = new File();
        file.setContent(fileInput.getBytes());
        file.setName(fileInput.getOriginalFilename());
        file.setContentLength(fileInput.getSize());
        file.setContentType(fileInput.getContentType());
        fileRepo.save(file);
        
        return "redirect:/files";
    }
    
    @GetMapping("files/{id}")
    public ResponseEntity<byte[]> viewFile(@PathVariable Long id) {
        File file = fileRepo.getOne(id);
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(file.getContentType()));
        headers.setContentLength(file.getContentLength());
        headers.add("Content-Disposition", "attachment; filename=" + file.getName());
        
        return new ResponseEntity<>(file.getContent(), headers, HttpStatus.CREATED);
    }
    
    @DeleteMapping("files/{id}")
    public String delete(@PathVariable Long id) {
        fileRepo.deleteById(id);
        return "redirect:/";
    }
}
