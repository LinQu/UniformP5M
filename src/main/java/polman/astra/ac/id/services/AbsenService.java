package polman.astra.ac.id.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import polman.astra.ac.id.model.Absen;
import polman.astra.ac.id.repository.AbsenRepository;

import java.util.List;

@Service
public class AbsenService {
    @Autowired
    AbsenRepository absenRepository;

    public boolean save(Absen absen){
        Absen result = absenRepository.save(absen);
        boolean isSuccess = true;
        if(result == null) {
            isSuccess = false;
        }
        return isSuccess;
    }

    public List<Absen> getAllAbsen(){
        return absenRepository.getAllAbsen();
    }

    public List<Absen> getAbsenByNim(String nim){
        return absenRepository.getAbsenByNim(nim);
    }

    public List<Object[]> getHistoryAbsenByNim(String nim){
        return absenRepository.getHistoryAbsenByNim(nim);
    }
}
