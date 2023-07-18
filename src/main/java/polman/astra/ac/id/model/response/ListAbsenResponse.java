package polman.astra.ac.id.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import polman.astra.ac.id.model.Absen;
import polman.astra.ac.id.model.Pelanggaran;

import java.util.List;

public class ListAbsenResponse {
    @JsonProperty("data")
    private List<Absen> mAbsen;

    @JsonProperty("message")
    private String message;

    @JsonProperty("status")
    private int status;

    public List<Absen> getmAbsen() {
        return mAbsen;
    }

    public void setmAbsen(List<Absen> mAbsen) {
        this.mAbsen = mAbsen;
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
        return "AbsenResponse{" +
                "mAbsen=" + mAbsen +
                ", message='" + message + '\'' +
                ", status=" + status +
                '}';
    }
}
