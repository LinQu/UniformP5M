package polman.astra.ac.id.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import polman.astra.ac.id.model.Pelanggaran;

import java.util.List;

public class ListPelanggaranResponse {
    @JsonProperty("data")
    private List<Pelanggaran> mPelanggaran;

    @JsonProperty("message")
    private String message;

    @JsonProperty("status")
    private int status;

    public List<Pelanggaran> getmPelanggaran() {
        return mPelanggaran;
    }

    public void setmPelanggaran(List<Pelanggaran> mPelanggaran) {
        this.mPelanggaran = mPelanggaran;
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
                "mPelanggaran=" + mPelanggaran +
                ", message='" + message + '\'' +
                ", status=" + status +
                '}';
    }
}
