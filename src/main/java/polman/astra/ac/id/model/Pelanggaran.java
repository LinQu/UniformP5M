package polman.astra.ac.id.model;


import javax.persistence.*;

@Entity
@Table(name = "p5m.pelanggaran")
public class Pelanggaran {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_pelanggaran")
    private Integer id;

    @Column(name = "nama_pelanggaran")
    private String nama;

    @Column(name = "jam_minus")
    private Integer jam_minus;

    @Column(name = "status")
    private Integer status;

    public Pelanggaran() {
    }

    public Pelanggaran(Integer id, String nama, Integer jam_minus, Integer status) {
        this.id = id;
        this.nama = nama;
        this.jam_minus = jam_minus;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Integer getJam_minus() {
        return jam_minus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setJam_minus(Integer jam_minus) {
        this.jam_minus = jam_minus;
    }
}