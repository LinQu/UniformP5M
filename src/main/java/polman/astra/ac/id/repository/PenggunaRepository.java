package polman.astra.ac.id.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import polman.astra.ac.id.model.Pengguna;

import java.util.List;

public interface PenggunaRepository extends CrudRepository<Pengguna, Integer> {
    @Query(value = "SELECT * FROM Pengguna",nativeQuery = true)
    public List<Pengguna> getAllPengguna();
}
