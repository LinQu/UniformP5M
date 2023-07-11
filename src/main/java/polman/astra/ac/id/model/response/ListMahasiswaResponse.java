package polman.astra.ac.id.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import polman.astra.ac.id.model.Mahasiswa;
import polman.astra.ac.id.model.Pengguna;
import polman.astra.ac.id.services.MahasiswaService;

import java.util.List;

public class ListMahasiswaResponse {
    @JsonProperty("data")
    private List<Mahasiswa> mMahasiswa;

    @JsonProperty("message")
    private String message;

    @JsonProperty("status")
    private int status;

    public List<Mahasiswa> getmMahasiswa() {
        return mMahasiswa;
    }

    public void setmMahasiswa(List<Mahasiswa> mMahasiswa) {
        this.mMahasiswa = mMahasiswa;
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
                "mMahasiswa=" + mMahasiswa +
                ", message='" + message + '\'' +
                ", status=" + status +
                '}';
    }
}
