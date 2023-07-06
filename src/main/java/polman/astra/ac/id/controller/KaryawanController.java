package polman.astra.ac.id.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import polman.astra.ac.id.model.Karyawan;
import polman.astra.ac.id.model.LoginResponse;
import polman.astra.ac.id.services.KaryawanService;


@RestController
public class KaryawanController {
    @Autowired
    KaryawanService karyawanService;

    @GetMapping("/getKaryawan")
    public Karyawan[] getKaryawan(@RequestParam(value = "username") String username){
        return karyawanService.getKaryawanById(username);
    }

    @PostMapping("/LoginKaryawan")
    public ResponseEntity<LoginResponse> LoginKaryawan(@RequestParam(value = "username") String username){
        Karyawan[] karyawan = karyawanService.getKaryawanById(username);
        LoginResponse loginResponse = new LoginResponse();
        try {
            loginResponse.setmKaryawan(karyawan[0]);
            loginResponse.setMessage("Login Berhasil");
            loginResponse.setStatus(200);
        }catch (Exception e){
            loginResponse.setmKaryawan(null);
            loginResponse.setMessage("Akun tidak ditemukan");
            loginResponse.setStatus(404);
        }
        return ResponseEntity.ok(loginResponse);
    }
}
