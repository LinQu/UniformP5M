package polman.astra.ac.id.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import polman.astra.ac.id.model.Pengguna;

import java.util.List;

public class ListPenggunaResponse {
    @JsonProperty("data")
    private List<Pengguna> mPengguna;

    @JsonProperty("message")
    private String message;

    @JsonProperty("status")
    private int status;

    public List<Pengguna> getmPengguna() {
        return mPengguna;
    }

    public void setmPengguna(List<Pengguna> mPengguna) {
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
