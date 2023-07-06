package polman.astra.ac.id.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import polman.astra.ac.id.model.Pelanggaran;

import java.util.List;

public interface PelanggaranRepository extends CrudRepository<Pelanggaran, Integer> {
    @Query("SELECT M FROM Pelanggaran M WHERE M.id = :id")
    public Pelanggaran getPelanggaranById(@Param("id") Integer id);

    @Query("SELECT m FROM Pelanggaran m")
    public List<Pelanggaran> getPelanggarans();

    @Modifying
    @Query("DELETE from Pelanggaran m WHERE m.id = :id")
    public void deletePelanggaranById(@Param("id") Integer id);

    @Modifying
    @Query("UPDATE Pelanggaran m SET m.jam_minus = :jam_minus, m.nama = :nama WHERE m.id = :id")
    public void updatePelanggaranById(@Param("id") Integer id,  @Param("nama") String nama, @Param("jam_minus") Integer jam_minus);

}
