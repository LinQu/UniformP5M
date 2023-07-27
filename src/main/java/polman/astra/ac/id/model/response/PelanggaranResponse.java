package polman.astra.ac.id.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import polman.astra.ac.id.model.Pelanggaran;
import polman.astra.ac.id.model.Pengguna;

public class PelanggaranResponse {
    @JsonProperty("data")
    private Pelanggaran mPelanggaran;

    @JsonProperty("message")
    private String message;

    @JsonProperty("status")
    private int status;

    @Override
    public String toString() {
        return "PelanggaranResponse{" +
                "mPelanggaran=" + mPelanggaran +
                ", message='" + message + '\'' +
                ", status=" + status +
                '}';
    }

    public Pelanggaran getmPelanggaran() {
        return mPelanggaran;
    }

    public void setmPelanggaran(Pelanggaran mPelanggaran) {
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


}
