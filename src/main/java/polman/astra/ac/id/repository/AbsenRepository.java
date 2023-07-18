package polman.astra.ac.id.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import polman.astra.ac.id.model.Absen;

import java.util.List;

public interface AbsenRepository extends CrudRepository<Absen,Integer> {
    @Query(value = "SELECT * FROM p5m.absen",nativeQuery = true)
    public List<Absen> getAllAbsen();

    @Query(value = "SELECT * FROM p5m.absen WHERE nim = ?1",nativeQuery = true)
    public List<Absen> getAbsenByNim(String nim);

    @Query(value = "SELECT nim,CONVERT(date, waktu) AS tanggal,CONVERT(time, MIN(waktu)) AS jam_masuk,CONVERT(time, MAX(waktu)) AS jam_keluar FROM p5m.absen WHERE nim = ?1 GROUP BY nim,CONVERT(date, waktu)",nativeQuery = true)
    public List<Object[]> getHistoryAbsenByNim(String nim);
}
