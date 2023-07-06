package polman.astra.ac.id.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Karyawan {
    @JsonProperty("id")
    private String id;

    @JsonProperty("username")
    private String username;

    @JsonProperty("nama")
    private String nama;

    @JsonProperty("jabatan")
    private String jabatan;

    @JsonProperty("struktur")
    private String struktur;

    @JsonProperty("parent")
    private String parent;


    @Override
    public String toString() {
        return "Karyawan{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", nama='" + nama + '\'' +
                ", jabatan='" + jabatan + '\'' +
                ", struktur='" + struktur + '\'' +
                ", parent='" + parent + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getStruktur() {
        return struktur;
    }

    public void setStruktur(String struktur) {
        this.struktur = struktur;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }
}
