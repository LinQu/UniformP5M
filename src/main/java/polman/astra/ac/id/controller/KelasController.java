package polman.astra.ac.id.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import polman.astra.ac.id.model.Kelas;
import polman.astra.ac.id.model.response.ListKelasResponse;
import polman.astra.ac.id.services.KelasService;

import java.time.Year;
import java.util.List;

@RestController
public class KelasController {
    @Autowired
    KelasService kelasService;

    @GetMapping("/getKelas")
    public ResponseEntity<ListKelasResponse> getKelas() {
        Year tahunSekarang = Year.now();
        Year tahunKemarin = tahunSekarang.minusYears(1);
        String AjaranSekarang = tahunKemarin.toString() + "/" + tahunSekarang.toString();
        List<Kelas> kelas = kelasService.getListKelasByTahunAjaran(AjaranSekarang);
        ListKelasResponse listKelasResponse = new ListKelasResponse();
        try{
            listKelasResponse.setmKelas(kelas);
            listKelasResponse.setMessage("Ambil Data Berhasil");
            listKelasResponse.setStatus(200);
        }catch (Exception e){
            listKelasResponse.setmKelas(null);
            listKelasResponse.setMessage("Data Kosong");
            listKelasResponse.setStatus(404);
        }
        return ResponseEntity.ok(listKelasResponse);
    }
}
