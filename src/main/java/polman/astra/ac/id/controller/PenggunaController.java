package polman.astra.ac.id.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import polman.astra.ac.id.model.Pengguna;
import polman.astra.ac.id.services.PenggunaService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class PenggunaController {
    @Autowired
    PenggunaService penggunaService;
    @GetMapping("/getPengguna")
    public List<Pengguna> getPengguna(HttpServletResponse response){
        List<Pengguna> pengguna = penggunaService.getAllPengguna();
        return pengguna;
    }

    @GetMapping("/getPenggunaByNama")
    public Pengguna getPenggunaByNama(@RequestParam(value = "nama") String nama){
        return penggunaService.getPenggunaByNama(nama);
    }
}
