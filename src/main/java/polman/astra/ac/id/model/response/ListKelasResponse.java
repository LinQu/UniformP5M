package polman.astra.ac.id.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import polman.astra.ac.id.model.Kelas;
import polman.astra.ac.id.model.Mahasiswa;

import java.util.List;

public class ListKelasResponse {
    @JsonProperty("data")
    private List<Kelas> mKelas;

    @JsonProperty("message")
    private String message;

    @JsonProperty("status")
    private int status;

    public List<Kelas> getmKelas() {
        return mKelas;
    }

    public void setmKelas(List<Kelas> mKelas) {
        this.mKelas = mKelas;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ListMahasiswaResponse{" +
                "mKelas=" + mKelas +
                ", message='" + message + '\'' +
                ", status=" + status +
                '}';
    }
}
