package polman.astra.ac.id.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import polman.astra.ac.id.model.Absen;
import polman.astra.ac.id.services.AbsenService;

@Controller
public class AbsenPageController {

    @Autowired
    AbsenService absenService;

    @RequestMapping("/addabsen")
    public String addAbsen(Model model){
        model.addAttribute("AbsenObject",new Absen());
        return "addabsenth";
    }

    @PostMapping("/saveAbsenPage")
    public String addAbsen(Absen absen){
        absenService.save(absen);
        return "redirect:/addabsen";
    }
}
