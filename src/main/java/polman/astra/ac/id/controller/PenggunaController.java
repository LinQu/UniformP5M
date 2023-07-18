package polman.astra.ac.id.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import polman.astra.ac.id.model.Pengguna;
import polman.astra.ac.id.model.response.ListPenggunaResponse;
import polman.astra.ac.id.model.response.PenggunaResponse;
import polman.astra.ac.id.services.PenggunaService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class PenggunaController {
    @Autowired
    PenggunaService penggunaService;
    @GetMapping("/getPengguna")
    public ResponseEntity<ListPenggunaResponse> getPengguna(){
        List<Pengguna> pengguna = penggunaService.getAllPengguna();
        ListPenggunaResponse listPenggunaResponse = new ListPenggunaResponse();
        try{
            listPenggunaResponse.setmPengguna(pengguna);
            listPenggunaResponse.setMessage("Ambil Data Berhasil");
            listPenggunaResponse.setStatus(200);
        }catch (Exception e){
            listPenggunaResponse.setmPengguna(null);
            listPenggunaResponse.setMessage("Data Kosong");
            listPenggunaResponse.setStatus(404);
        }
        return ResponseEntity.ok(listPenggunaResponse);
    }

    @PostMapping("/savePengguna")
    public ResponseEntity<PenggunaResponse> savePengguna(@RequestBody Pengguna mPengguna){
        Pengguna pengguna = new Pengguna(mPengguna.getId(), mPengguna.getNama_pengguna(),mPengguna.getRole(),mPengguna.getKelas(),1);
        boolean result = penggunaService.save(pengguna);
        PenggunaResponse penggunaResponse = new PenggunaResponse();
        if (result) {

            try {
                penggunaResponse.setmPengguna(pengguna);
                penggunaResponse.setMessage("Simpan Data Berhasil");
                penggunaResponse.setStatus(200);
            } catch (Exception e) {
                penggunaResponse.setmPengguna(null);
                penggunaResponse.setMessage("Simpan Data Gagal");
                penggunaResponse.setStatus(404);
            }
        }else {
            penggunaResponse.setmPengguna(null);
            penggunaResponse.setMessage("Simpan Data Gagal");
            penggunaResponse.setStatus(404);
        }
        return ResponseEntity.ok(penggunaResponse);
    }

    @PutMapping("/updatePengguna")
    public ResponseEntity<PenggunaResponse> updatePengguna(@RequestBody Pengguna mPengguna){
        Pengguna pengguna = new Pengguna(mPengguna.getId(), mPengguna.getNama_pengguna(),mPengguna.getRole(),mPengguna.getKelas(),mPengguna.getStatus());
        boolean result = penggunaService.update(pengguna);
        PenggunaResponse penggunaResponse = new PenggunaResponse();
        if (result) {

            try {
                penggunaResponse.setmPengguna(pengguna);
                penggunaResponse.setMessage("Update Data Berhasil");
                penggunaResponse.setStatus(200);
            } catch (Exception e) {
                penggunaResponse.setmPengguna(null);
                penggunaResponse.setMessage("Update Data Gagal");
                penggunaResponse.setStatus(404);
            }
        }else {
            penggunaResponse.setmPengguna(null);
            penggunaResponse.setMessage("Update Data Gagal");
            penggunaResponse.setStatus(404);
        }
        return ResponseEntity.ok(penggunaResponse);
    }

    @DeleteMapping("/deletePengguna/{id}")
    public ResponseEntity<PenggunaResponse> deletePengguna(@PathVariable("id") int id){

        boolean result = penggunaService.delete(id);
        PenggunaResponse penggunaResponse = new PenggunaResponse();
        if (result) {
        Pengguna pengguna = penggunaService.getPenggunaById(id);
            try {
                penggunaResponse.setmPengguna(pengguna);
                penggunaResponse.setMessage("Delete Data Berhasil");
                penggunaResponse.setStatus(200);
            } catch (Exception e) {
                penggunaResponse.setmPengguna(null);
                penggunaResponse.setMessage("Delete Data Gagal");
                penggunaResponse.setStatus(404);
            }
        }else {
            penggunaResponse.setmPengguna(null);
            penggunaResponse.setMessage("Delete Data Gagal");
            penggunaResponse.setStatus(404);
        }
        return ResponseEntity.ok(penggunaResponse);
    }
    @PostMapping("/getPenggunaByNama")
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
