package polman.astra.ac.id.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import polman.astra.ac.id.model.Absen;
import polman.astra.ac.id.model.Pengguna;

public class AbsenResponse {
    @JsonProperty("data")
    private Absen mAbsen;

    @JsonProperty("message")
    private String message;

    @JsonProperty("status")
    private int status;

    public Absen getmAbsen() {
        return mAbsen;
    }

    public void setmAbsen(Absen mAbsen) {
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
        return "LoginResponse{" +
                "mAbsen=" + mAbsen +
                ", message='" + message + '\'' +
                ", status=" + status +
                '}';
    }
}
