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

    public Pengguna getPenggunaById(int id){
        Pengguna Pengguna = penggunaRepository.getPenggunaById(id);
        return Pengguna;
    }

    public boolean save(Pengguna Pengguna){
        Pengguna result = penggunaRepository.save(Pengguna);
        boolean isSuccess = true;
        if(result == null) {
            isSuccess = false;
        }
        return isSuccess;
    }

    public boolean update(Pengguna penggunaa){
        Pengguna pengguna = penggunaRepository.getPenggunaById(penggunaa.getId());
        pengguna.setNama_pengguna(penggunaa.getNama_pengguna());
        pengguna.setRole(penggunaa.getRole());
        pengguna.setKelas(penggunaa.getKelas());
        pengguna.setStatus(penggunaa.getStatus());
        Pengguna result = penggunaRepository.save(pengguna);
        boolean isSuccess = true;
        if(result == null) {
            isSuccess = false;
        }
        return isSuccess;
    }

    public boolean delete(int id){
        Pengguna pengguna = penggunaRepository.getPenggunaById(id);
        pengguna.setStatus(0);
        Pengguna result = penggunaRepository.save(pengguna);
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
