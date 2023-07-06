package polman.astra.ac.id.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Mahasiswa {
    @JsonProperty("nim")
    private String nim;

    @JsonProperty("nama")
    private String nama;

    @JsonProperty("angkatan")
    private String angkatan;

    @JsonProperty("kelas")
    private String kelas;

    @JsonProperty("prodi")
    private String prodi;

    @JsonProperty("dosen_wali")
    private String dosen_wali;

    @JsonProperty("mhs_email")
    private String mhs_email;

    @JsonProperty("dul_pas_foto")
    private String dul_pas_foto;

    @Override
    public String toString() {
        return "Mahasiswa{" +
                "nim='" + nim + '\'' +
                ", nama='" + nama + '\'' +
                ", angkatan='" + angkatan + '\'' +
                ", kelas='" + kelas + '\'' +
                ", prodi='" + prodi + '\'' +
                ", dosen_wali='" + dosen_wali + '\'' +
                ", mhs_email='" + mhs_email + '\'' +
                ", dul_pas_foto='" + dul_pas_foto + '\'' +
                '}';
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAngkatan() {
        return angkatan;
    }

    public void setAngkatan(String angkatan) {
        this.angkatan = angkatan;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getProdi() {
        return prodi;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

    public String getDosen_wali() {
        return dosen_wali;
    }

    public void setDosen_wali(String dosen_wali) {
        this.dosen_wali = dosen_wali;
    }

    public String getMhs_email() {
        return mhs_email;
    }

    public void setMhs_email(String mhs_email) {
        this.mhs_email = mhs_email;
    }

    public String getDul_pas_foto() {
        return dul_pas_foto;
    }

    public void setDul_pas_foto(String dul_pas_foto) {
        this.dul_pas_foto = dul_pas_foto;
    }
}
