package polman.astra.ac.id.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import polman.astra.ac.id.model.Absen;
import polman.astra.ac.id.model.response.AbsenResponse;
import polman.astra.ac.id.model.response.ListAbsenResponse;
import polman.astra.ac.id.services.AbsenService;

import java.sql.Date;
import java.util.List;

@RestController
public class AbsenController {
    @Autowired
    AbsenService absenService;

    @PostMapping("/saveAbsen")
    public ResponseEntity<AbsenResponse> saveAbsen(@RequestBody Absen mAbsen){
        Absen absen = new Absen(mAbsen.getId(),mAbsen.getNim(),mAbsen.getWaktu());
        boolean result = absenService.save(absen);
        AbsenResponse absenResponse = new AbsenResponse();
        if (result) {
            try {
                absenResponse.setmAbsen(absen);
                absenResponse.setMessage("Simpan Data Berhasil");
                absenResponse.setStatus(200);
            } catch (Exception e) {
                absenResponse.setmAbsen(null);
                absenResponse.setMessage("Simpan Data Gagal");
                absenResponse.setStatus(404);
            }
        }else {
            absenResponse.setmAbsen(null);
            absenResponse.setMessage("Simpan Data Gagal");
            absenResponse.setStatus(404);
        }
        return ResponseEntity.ok(absenResponse);
    }

    @GetMapping("/getAbsenByNim")
    public ResponseEntity<ListAbsenResponse> getAbsenByNim(@RequestParam(name = "nim") String nim){
        ListAbsenResponse listAbsenResponse = new ListAbsenResponse();
        try {
            listAbsenResponse.setmAbsen(absenService.getAbsenByNim(nim));
            listAbsenResponse.setMessage("Ambil Data Berhasil");
            listAbsenResponse.setStatus(200);
        } catch (Exception e) {
            listAbsenResponse.setmAbsen(null);
            listAbsenResponse.setMessage("Data Kosong");
            listAbsenResponse.setStatus(404);
        }
        return ResponseEntity.ok(listAbsenResponse);
    }

    @GetMapping("/getHistoryAbsenByNim")
    public ResponseEntity<List<Object[]>> getHistoryAbsenByNim(@RequestParam(name = "nim") String nim){
        List<Object[]> listAbsenResponse = absenService.getHistoryAbsenByNim(nim);
        return ResponseEntity.ok(listAbsenResponse);
    }
}
