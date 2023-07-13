package polman.astra.ac.id.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import polman.astra.ac.id.model.Pelanggaran;
import polman.astra.ac.id.model.Pengguna;

import java.util.List;

public interface PenggunaRepository extends CrudRepository<Pengguna, Integer> {
    @Query(value = "select * from p5m.pengguna where status = 1",nativeQuery = true)
    public List<Pengguna> getAllPengguna();

    @Query("SELECT m FROM Pengguna m")
    public List<Pengguna> getPenggunas();

    @Query(value = "SELECT * FROM p5m.pengguna WHERE nama_pengguna=?1",nativeQuery = true)
    public Pengguna getPenggunaByNama(String nama);

    @Query(value = "SELECT * FROM p5m.pengguna WHERE id_pengguna=?1",nativeQuery = true)
    public Pengguna getPenggunaById(int id);
}
