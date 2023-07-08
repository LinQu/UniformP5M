package polman.astra.ac.id.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import polman.astra.ac.id.model.Pengguna;
import polman.astra.ac.id.repository.PenggunaRepository;

import java.util.List;

@Service
public class PenggunaService {
    @Autowired
    PenggunaRepository penggunaRepository;

    public boolean save(Pengguna Pengguna){
        Pengguna result = penggunaRepository.save(Pengguna);
        boolean isSuccess = true;
        if(result == null) {
            isSuccess = false;
        }
        return isSuccess;
    }

    public Pengguna getPenggunaByNama(String nama){
        Pengguna Pengguna = penggunaRepository.getPenggunaByNama(nama);
        return Pengguna;
    }

    public List<Pengguna> getAllPengguna(){
        List<Pengguna> Pengguna = penggunaRepository.getAllPengguna();
        return Pengguna;
    }




}
