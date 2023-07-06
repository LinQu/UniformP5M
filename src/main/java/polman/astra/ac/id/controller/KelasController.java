package polman.astra.ac.id.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import polman.astra.ac.id.model.Kelas;
import polman.astra.ac.id.services.KelasService;

import java.time.Year;

@RestController
public class KelasController {
    @Autowired
    KelasService kelasService;

    @GetMapping("/getKelas")
    public Kelas[] getKelas() {
        Year tahunSekarang = Year.now();
        Year tahunKemarin = tahunSekarang.minusYears(1);
        String AjaranSekarang = tahunKemarin.toString() + "/" + tahunSekarang.toString();
        return kelasService.getListKelasByTahunAjaran(AjaranSekarang);
    }
}
