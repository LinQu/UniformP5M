package polman.astra.ac.id.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "p5m.absen")
public class Absen {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nim")
    private String nim;

    @Column(name = "waktu")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime waktu;

    public Absen() {

    }

    public Absen(Integer id, String nim, LocalDateTime waktu) {
        this.id = id;
        this.nim = nim;
        this.waktu = waktu;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public LocalDateTime getWaktu() {
        return waktu;
    }

    public void setWaktu(LocalDateTime waktu) {
        this.waktu = waktu;
    }

    @Override
    public String toString() {
        return "Absen{" +
                "id=" + id +
                ", nim='" + nim + '\'' +
                ", waktu=" + waktu +
                '}';
    }
}
