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

    @Query(value = "SELECT\n" +
            "    nim,\n" +
            "    CONVERT(DATE, waktu) AS tanggal,\n" +
            "    (\n" +
            "        SELECT MIN(CONVERT(TIME, waktu))\n" +
            "        FROM p5m.absen\n" +
            "        WHERE nim = a.nim AND CONVERT(TIME, waktu) BETWEEN '04:30:00' AND '07:30:00' AND CONVERT(DATE, waktu) = CONVERT(DATE, a.waktu)\n" +
            "    ) AS jam_masuk,\n" +
            "    (\n" +
            "        SELECT MAX(CONVERT(TIME, waktu))\n" +
            "        FROM p5m.absen\n" +
            "        WHERE nim = a.nim AND CONVERT(TIME, waktu) BETWEEN '16:30:00' AND '21:00:00' AND CONVERT(DATE, waktu) = CONVERT(DATE, a.waktu)\n" +
            "    ) AS jam_keluar,\n" +
            "    CASE\n" +
            "        WHEN \n" +
            "            (SELECT MIN(CONVERT(TIME, waktu)) \n" +
            "             FROM p5m.absen\n" +
            "             WHERE nim = a.nim AND CONVERT(TIME, waktu) BETWEEN '04:30:00' AND '07:30:00' AND CONVERT(DATE, waktu) = CONVERT(DATE, a.waktu)\n" +
            "            ) IS NOT NULL AND\n" +
            "            (SELECT MAX(CONVERT(TIME, waktu)) \n" +
            "             FROM p5m.absen\n" +
            "             WHERE nim = a.nim AND CONVERT(TIME, waktu) BETWEEN '16:30:00' AND '21:00:00' AND CONVERT(DATE, waktu) = CONVERT(DATE, a.waktu)\n" +
            "            ) IS NOT NULL\n" +
            "        THEN 'Hadir'\n" +
            "        WHEN \n" +
            "            (SELECT MIN(CONVERT(TIME, waktu)) \n" +
            "             FROM p5m.absen\n" +
            "             WHERE nim = a.nim AND CONVERT(TIME, waktu) BETWEEN '04:30:00' AND '07:30:00' AND CONVERT(DATE, waktu) = CONVERT(DATE, a.waktu)\n" +
            "            ) IS NULL AND\n" +
            "            (SELECT MAX(CONVERT(TIME, waktu)) \n" +
            "             FROM p5m.absen\n" +
            "             WHERE nim = a.nim AND CONVERT(TIME, waktu) BETWEEN '16:30:00' AND '21:00:00' AND CONVERT(DATE, waktu) = CONVERT(DATE, a.waktu)\n" +
            "            ) IS NULL\n" +
            "        THEN 'Tidak Hadir'\n" +
            "        WHEN \n" +
            "            (SELECT MIN(CONVERT(TIME, waktu)) \n" +
            "             FROM p5m.absen\n" +
            "             WHERE nim = a.nim AND CONVERT(TIME, waktu) BETWEEN '04:30:00' AND '07:30:00' AND CONVERT(DATE, waktu) = CONVERT(DATE, a.waktu)\n" +
            "            ) IS NULL\n" +
            "        THEN 'Tidak Absen Masuk'\n" +
            "        ELSE 'Tidak Absen Keluar'\n" +
            "    END AS status_kehadiran\n" +
            "FROM p5m.absen a\n" +
            "WHERE nim = ?1\n" +
            "GROUP BY nim, CONVERT(DATE, waktu);\n",nativeQuery = true)
    public List<Object[]> getHistoryAbsenByNim(String nim);

    @Query(value = "SELECT\n" +
            "    nim,\n" +
            "    CONVERT(DATE, waktu) AS tanggal,\n" +
            "    (\n" +
            "        SELECT MIN(CONVERT(TIME, waktu))\n" +
            "        FROM p5m.absen\n" +
            "        WHERE nim = a.nim AND CONVERT(TIME, waktu) BETWEEN '04:30:00' AND '07:30:00' AND CONVERT(DATE, waktu) = CONVERT(DATE, a.waktu)\n" +
            "    ) AS jam_masuk,\n" +
            "    (\n" +
            "        SELECT MAX(CONVERT(TIME, waktu))\n" +
            "        FROM p5m.absen\n" +
            "        WHERE nim = a.nim AND CONVERT(TIME, waktu) BETWEEN '16:30:00' AND '21:00:00' AND CONVERT(DATE, waktu) = CONVERT(DATE, a.waktu)\n" +
            "    ) AS jam_keluar,\n" +
            "    CASE\n" +
            "        WHEN \n" +
            "            (SELECT MIN(CONVERT(TIME, waktu)) \n" +
            "             FROM p5m.absen\n" +
            "             WHERE nim = a.nim AND CONVERT(TIME, waktu) BETWEEN '04:30:00' AND '07:30:00' AND CONVERT(DATE, waktu) = CONVERT(DATE, a.waktu)\n" +
            "            ) IS NOT NULL AND\n" +
            "            (SELECT MAX(CONVERT(TIME, waktu)) \n" +
            "             FROM p5m.absen\n" +
            "             WHERE nim = a.nim AND CONVERT(TIME, waktu) BETWEEN '16:30:00' AND '21:00:00' AND CONVERT(DATE, waktu) = CONVERT(DATE, a.waktu)\n" +
            "            ) IS NOT NULL\n" +
            "        THEN 'Hadir'\n" +
            "        WHEN \n" +
            "            (SELECT MIN(CONVERT(TIME, waktu)) \n" +
            "             FROM p5m.absen\n" +
            "             WHERE nim = a.nim AND CONVERT(TIME, waktu) BETWEEN '04:30:00' AND '07:30:00' AND CONVERT(DATE, waktu) = CONVERT(DATE, a.waktu)\n" +
            "            ) IS NULL AND\n" +
            "            (SELECT MAX(CONVERT(TIME, waktu)) \n" +
            "             FROM p5m.absen\n" +
            "             WHERE nim = a.nim AND CONVERT(TIME, waktu) BETWEEN '16:30:00' AND '21:00:00' AND CONVERT(DATE, waktu) = CONVERT(DATE, a.waktu)\n" +
            "            ) IS NULL\n" +
            "        THEN 'Tidak Hadir'\n" +
            "        WHEN \n" +
            "            (SELECT MIN(CONVERT(TIME, waktu)) \n" +
            "             FROM p5m.absen\n" +
            "             WHERE nim = a.nim AND CONVERT(TIME, waktu) BETWEEN '04:30:00' AND '07:30:00' AND CONVERT(DATE, waktu) = CONVERT(DATE, a.waktu)\n" +
            "            ) IS NULL\n" +
            "        THEN 'Tidak Absen Masuk'\n" +
            "        ELSE 'Tidak Absen Keluar'\n" +
            "    END AS status_kehadiran\n" +
            "FROM p5m.absen a\n" +
            "WHERE nim = ?1 AND YEAR(waktu) = ?2 AND MONTH(waktu) = ?3\n" +
            "GROUP BY nim, CONVERT(DATE, waktu);\n",nativeQuery = true)
    public List<Object[]> getHistoryAbsenByNimAndMonth(String nim, int year, int month);

    @Query( value = "SELECT DISTINCT DATEPART(YEAR, waktu) AS tahun, DATEPART(MONTH, waktu) AS bulan FROM [p5m].[absen] WHERE nim = ?1", nativeQuery = true)
    public List<Object[]> getHistoryListMonthByNim(String nim);
}
