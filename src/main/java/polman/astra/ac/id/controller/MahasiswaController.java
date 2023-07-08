package polman.astra.ac.id.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import polman.astra.ac.id.model.response.LoginMahasiswaResponse;
import polman.astra.ac.id.model.Mahasiswa;
import polman.astra.ac.id.services.MahasiswaService;

@RestController
public class MahasiswaController {
    @Autowired
    MahasiswaService mahasiswaService;

    @GetMapping("/getMahasiswa")
    public Mahasiswa[] getMahasiswa(@RequestParam("nim") String nim){
        return mahasiswaService.getMahasiswaByNim(nim);
    }

    @PostMapping("/LoginMahasiswa")
    public ResponseEntity<LoginMahasiswaResponse> doLogin(@RequestParam("nim") String nim){
        Mahasiswa[] mahasiswa = mahasiswaService.getMahasiswaByNim(nim);
        LoginMahasiswaResponse loginMahasiswaResponse = new LoginMahasiswaResponse();
        try {
            loginMahasiswaResponse.setmMahasiswa(mahasiswa[0]);
            loginMahasiswaResponse.setMessage("Login Berhasil");
            loginMahasiswaResponse.setStatus(200);
        }catch (Exception e){
            loginMahasiswaResponse.setmMahasiswa(null);
            loginMahasiswaResponse.setMessage("Akun tidak ditemukan");
            loginMahasiswaResponse.setStatus(404);
        }
        return ResponseEntity.ok(loginMahasiswaResponse);
    }


}
