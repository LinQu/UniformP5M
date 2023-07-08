package polman.astra.ac.id.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import polman.astra.ac.id.model.Mahasiswa;
import polman.astra.ac.id.model.Pengguna;

public class PenggunaResponse {
    @JsonProperty("data")
    private Pengguna mPengguna;

    @JsonProperty("message")
    private String message;

    @JsonProperty("status")
    private int status;

    public Pengguna getmPengguna() {
        return mPengguna;
    }

    public void setmPengguna(Pengguna mPengguna) {
        this.mPengguna = mPengguna;
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
        return "LoginResponse{" +
                "mPengguna=" + mPengguna +
                ", message='" + message + '\'' +
                ", status=" + status +
                '}';
    }
}
