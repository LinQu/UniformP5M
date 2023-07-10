package polman.astra.ac.id.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import polman.astra.ac.id.model.Pengguna;
import polman.astra.ac.id.model.response.PenggunaResponse;
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
    public ResponseEntity<PenggunaResponse> getPenggunaByNama(@RequestParam(value = "nama") String nama){
        Pengguna pengguna = penggunaService.getPenggunaByNama(nama);
        PenggunaResponse penggunaResponse = new PenggunaResponse();
        try {
            penggunaResponse.setmPengguna(pengguna);
            penggunaResponse.setMessage("Ambil Data Berhasil");
            penggunaResponse.setStatus(200);
        }catch (Exception e){
            penggunaResponse.setmPengguna(null);
            penggunaResponse.setMessage("Akun tidak ditemukan");
            penggunaResponse.setStatus(404);
        }
        return ResponseEntity.ok(penggunaResponse);
    }
}
