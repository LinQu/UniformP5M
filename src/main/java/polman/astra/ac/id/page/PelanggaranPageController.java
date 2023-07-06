package polman.astra.ac.id.page;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import polman.astra.ac.id.model.Pelanggaran;
import polman.astra.ac.id.repository.PelanggaranRepository;
import polman.astra.ac.id.services.PelanggaranService;

import java.util.List;

@Controller
public class PelanggaranPageController {
    @Autowired
    PelanggaranService pelanggaranService;

    @Autowired
    PelanggaranRepository pelanggaranRepository;

    @RequestMapping("/listpelanggaranth")
    public String getPelanggaran(Model model){
        List<Pelanggaran> pelanggarans=pelanggaranService.getPelanggarans();
        model.addAttribute("listPelanggaran",pelanggarans);
        return "listPelanggaranth";
    }

    @RequestMapping("/viewaddpelanggaranth")
    public String viewaddPelanggaranth(Model model){

        model.addAttribute("PelanggaranObject",new Pelanggaran());
        return "addpelanggaranth";
    }

    @PostMapping("/savepelanggaranth")
    public String addPelanggaran(Pelanggaran Pelanggaran){
        pelanggaranService.save(Pelanggaran);
        return "redirect:/listpelanggaranth";
    }

    @GetMapping("/edit/{id_pelanggaran}")
    public String showupdateform(@PathVariable("id_pelanggaran") int id, Model model){
        Pelanggaran Pelanggaran = pelanggaranRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Pelanggaran Id:" + id));
        model.addAttribute("pelanggarans", Pelanggaran);
        return "updatepelanggaranth";
    }

    @PostMapping("/update/{id_pelanggaran}")
    public String updatePelanggaran(@PathVariable("id_pelanggaran") int id, Pelanggaran Pelanggaran, BindingResult bindingResult, Model model) {
        Pelanggaran.setId(id);
        if (bindingResult.hasErrors()){
            return "updatepelanggaranth";
        }
        pelanggaranRepository.save(Pelanggaran);
        return "redirect:/listpelanggaranth";

    }

    @GetMapping("/delete/{id_pelanggaran}")
    public String deletePelanggaran(@PathVariable("id_pelanggaran") int id, Model model){
        Pelanggaran Pelanggaran = pelanggaranRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ID Pelanggaran:" + id));
        pelanggaranRepository.delete(Pelanggaran);
        return "redirect:/listpelanggaranth";
    }
}
