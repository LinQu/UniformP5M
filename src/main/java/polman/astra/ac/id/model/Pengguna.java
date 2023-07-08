package polman.astra.ac.id.model;

import javax.persistence.*;

@Entity
@Table(name = "p5m.pengguna")
public class Pengguna {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pengguna")
    private Integer id;

    @Column(name = "nama_pengguna")
    private String nama_pengguna;

    @Column(name = "role")
    private String role;

    @Column(name = "kelas")
    private String kelas;

    @Column(name = "status")
    private int status;

    public Pengguna() {
    }

    public Pengguna(Integer id, String nama_pengguna, String role, String kelas, int status) {
        this.id = id;
        this.nama_pengguna = nama_pengguna;
        this.role = role;
        this.kelas = kelas;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama_pengguna() {
        return nama_pengguna;
    }

    public void setNama_pengguna(String nama_pengguna) {
        this.nama_pengguna = nama_pengguna;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
