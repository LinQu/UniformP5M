package polman.astra.ac.id.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import polman.astra.ac.id.Result;
import polman.astra.ac.id.model.Pelanggaran;
import polman.astra.ac.id.model.response.ListPelanggaranResponse;
import polman.astra.ac.id.model.response.PelanggaranResponse;
import polman.astra.ac.id.services.PelanggaranService;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.util.List;

@RestController
@SecurityRequirement(name = "bearerAuth")
public class PelanggaranController {

    @Autowired
    PelanggaranService pelanggaranService;


        @PostMapping(value = "/savePelanggaran", produces = "application/json")
    public ResponseEntity<PelanggaranResponse> save(@RequestBody Pelanggaran PelanggaranParam) {
        Pelanggaran Pelanggaran = new Pelanggaran(PelanggaranParam.getId(), PelanggaranParam.getNama(), PelanggaranParam.getJam_minus(),1);
        boolean isSuccess = pelanggaranService.save(Pelanggaran);
        PelanggaranResponse pelanggaranResponse = new PelanggaranResponse();
        if (isSuccess) {
            try{
                pelanggaranResponse.setmPelanggaran(Pelanggaran);
                pelanggaranResponse.setMessage("Simpan Data Berhasil");
                pelanggaranResponse.setStatus(200);
            }catch (Exception e){
                pelanggaranResponse.setmPelanggaran(null);
                pelanggaranResponse.setMessage("Simpan Data Gagal");
                pelanggaranResponse.setStatus(404);
            }
        } else {
            pelanggaranResponse.setmPelanggaran(null);
            pelanggaranResponse.setMessage("Simpan Data Gagal");
            pelanggaranResponse.setStatus(404);
        }
        return ResponseEntity.ok(pelanggaranResponse);
    }

    @GetMapping("/getPelanggaran")
    public Pelanggaran getPelanggaran(HttpServletResponse response, @RequestParam("id_pelanggaran") Integer id) {
        Pelanggaran Pelanggaran = pelanggaranService.getPelanggaran(id);
        return Pelanggaran;
    }

    @GetMapping("/getPelanggarans")
    public ResponseEntity<ListPelanggaranResponse> getPelanggarans(HttpServletResponse response) {
        List<Pelanggaran> Pelanggaran = pelanggaranService.getPelanggarans();
        ListPelanggaranResponse listPelanggaranResponse = new ListPelanggaranResponse();
        try {
            listPelanggaranResponse.setmPelanggaran(Pelanggaran);
            listPelanggaranResponse.setMessage("Ambil Data Berhasil");
            listPelanggaranResponse.setStatus(200);
        } catch (Exception e) {
            listPelanggaranResponse.setmPelanggaran(null);
            listPelanggaranResponse.setMessage("Data Kosong");
            listPelanggaranResponse.setStatus(404);
        }
        return ResponseEntity.ok(listPelanggaranResponse);

    }

    @DeleteMapping("/deletePelanggaran/{id}")
    public ResponseEntity<PelanggaranResponse> deletePelanggaran(@PathVariable("id") Integer id) {
        boolean result = pelanggaranService.deletePelanggaran(id);
        PelanggaranResponse pelanggaranResponse = new PelanggaranResponse();
        if (result) {
            Pelanggaran pelanggaran = pelanggaranService.getPelanggaran(id);
            try {
                pelanggaranResponse.setmPelanggaran(pelanggaran);
                pelanggaranResponse.setMessage("Hapus Data Berhasil");
                pelanggaranResponse.setStatus(200);
            } catch (Exception e) {
                pelanggaranResponse.setmPelanggaran(null);
                pelanggaranResponse.setMessage("Hapus Data Gagal");
                pelanggaranResponse.setStatus(404);
            }
        } else {
            pelanggaranResponse.setmPelanggaran(null);
            pelanggaranResponse.setMessage("Hapus Data Gagal");
            pelanggaranResponse.setStatus(404);
        }
        return ResponseEntity.ok(pelanggaranResponse);
    }
    @GetMapping("/editPelanggaran")
    public ResponseEntity<Void> editPelanggaran(HttpServletResponse response, @RequestParam("id_pelanggaran") Integer id) {
        // Mengatur Location header untuk mengarahkan ke form updatehelmjson.html
        response.setHeader("Location", "http://localhost:8080/updatePelanggaranjson.html?id=" + id);
        return ResponseEntity.status(HttpStatus.SEE_OTHER).build();
    }


    @PutMapping("/updatePelanggaran")
    public ResponseEntity<PelanggaranResponse> updatePelanggaran(@RequestBody Pelanggaran pelanggaranParam) {
        Pelanggaran pelanggaran = new Pelanggaran(pelanggaranParam.getId(),pelanggaranParam.getNama(),pelanggaranParam.getJam_minus(),1);
        boolean isSucces = pelanggaranService.updatePelanggaran(pelanggaran);
        PelanggaranResponse pelanggaranResponse = new PelanggaranResponse();
        if(isSucces){
            try{
                pelanggaranResponse.setmPelanggaran(pelanggaran);
                pelanggaranResponse.setMessage("Update Data Berhasil");
                pelanggaranResponse.setStatus(200);
            }catch (Exception e){
                pelanggaranResponse.setmPelanggaran(null);
                pelanggaranResponse.setMessage("Update Data Berhasil");
                pelanggaranResponse.setStatus(404);
            }
        }else {
            pelanggaranResponse.setmPelanggaran(null);
            pelanggaranResponse.setMessage("Update Data Berhasil");
            pelanggaranResponse.setStatus(404);
        }
        return ResponseEntity.ok(pelanggaranResponse);

    }
    @GetMapping("/Pelanggaran/report/{format}")    public String generateReport (@PathVariable String format) throws JRException, FileNotFoundException {
        return pelanggaranService.exportReport(format);
    }
}
