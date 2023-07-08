package polman.astra.ac.id.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import polman.astra.ac.id.model.Karyawan;

public class LoginResponse {
    @JsonProperty("data")
    private Karyawan mKaryawan;

    @JsonProperty("message")
    private String message;

    @JsonProperty("status")
    private int status;

    public Karyawan getmKaryawan() {
        return mKaryawan;
    }

    public void setmKaryawan(Karyawan mKaryawan) {
        this.mKaryawan = mKaryawan;
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
                "mKaryawwan=" + mKaryawan +
                ", message='" + message + '\'' +
                ", status=" + status +
                '}';
    }
}
