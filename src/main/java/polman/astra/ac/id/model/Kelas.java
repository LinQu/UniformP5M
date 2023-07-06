package polman.astra.ac.id.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Kelas {
    @JsonProperty("kel_id")
    private String kel_id;
    @JsonProperty("kel_tahun_ajaran")
    private String kel_tahun_ajaran;
    @JsonProperty("kel_tingkat")
    private String kel_tingkat;

    @Override
    public String toString() {
        return "Kelas{" +
                "kel_id=" + kel_id +
                ", kel_nama='" + kel_tahun_ajaran + '\'' +
                ", kel_tingkat=" + kel_tingkat +
                '}';
    }

    public String getKel_id() {
        return kel_id;
    }

    public void setKel_id(String kel_id) {
        this.kel_id = kel_id;
    }

    public String getKel_tahun_ajaran() {
        return kel_tahun_ajaran;
    }

    public void setKel_tahun_ajaran(String kel_tahun_ajaran) {
        this.kel_tahun_ajaran = kel_tahun_ajaran;
    }

    public String getKel_tingkat() {
        return kel_tingkat;
    }

    public void setKel_tingkat(String kel_tingkat) {
        this.kel_tingkat = kel_tingkat;
    }
}
