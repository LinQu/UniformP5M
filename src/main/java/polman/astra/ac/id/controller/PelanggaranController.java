package polman.astra.ac.id.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import polman.astra.ac.id.Result;
import polman.astra.ac.id.model.Pelanggaran;
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
    public Result save(HttpServletResponse response, @RequestBody Pelanggaran PelanggaranParam) {
        Pelanggaran Pelanggaran = new Pelanggaran(PelanggaranParam.getId(), PelanggaranParam.getNama(), PelanggaranParam.getJam_minus());
        boolean isSuccess = pelanggaranService.save(Pelanggaran);
        if (isSuccess) {
            return new Result(500, "Success");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new Result(200, "Fail");
        }
    }

    @GetMapping("/getPelanggaran")
    public Pelanggaran getPelanggaran(HttpServletResponse response, @RequestParam("id_pelanggaran") Integer id) {
        Pelanggaran Pelanggaran = pelanggaranService.getPelanggaran(id);
        return Pelanggaran;
    }

    @GetMapping("/getPelanggarans")
    public List<Pelanggaran> getPelanggarans(HttpServletResponse response) {
        List<Pelanggaran> Pelanggaran = pelanggaranService.getPelanggarans();
        return Pelanggaran;
    }

    @DeleteMapping("/deletePelanggaran")
    public void deletePelanggaran(HttpServletResponse response, @RequestParam("id_pelanggaran") Integer id) {
        pelanggaranService.deletePelanggaran(id);

    }
    @GetMapping("/editPelanggaran")
    public ResponseEntity<Void> editPelanggaran(HttpServletResponse response, @RequestParam("id_pelanggaran") Integer id) {
        // Mengatur Location header untuk mengarahkan ke form updatehelmjson.html
        response.setHeader("Location", "http://localhost:8080/updatePelanggaranjson.html?id=" + id);
        return ResponseEntity.status(HttpStatus.SEE_OTHER).build();
    }


    @PutMapping("/updatePelanggaran")
    public void updatePelanggaran(HttpServletResponse response, @RequestParam("id_pelanggaran") Integer id, @RequestParam("nama_pelanggaran") String nama, @RequestParam("jam_minus") Integer jamMinus) {
        pelanggaranService.updatePelanggaran(id, nama, jamMinus);

    }
    @GetMapping("/Pelanggaran/report/{format}")    public String generateReport (@PathVariable String format) throws JRException, FileNotFoundException {
        return pelanggaranService.exportReport(format);
    }
}
