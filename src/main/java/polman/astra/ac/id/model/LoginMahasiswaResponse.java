package polman.astra.ac.id.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginMahasiswaResponse {
    @JsonProperty("data")
    private Mahasiswa mMahasiswa;

    @JsonProperty("message")
    private String message;

    @JsonProperty("status")
    private int status;

    public Mahasiswa getmMahasiswa() {
        return mMahasiswa;
    }

    public void setmMahasiswa(Mahasiswa mMahasiswa) {
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
        return "LoginResponse{" +
                "mMahasiswa=" + mMahasiswa +
                ", message='" + message + '\'' +
                ", status=" + status +
                '}';
    }
}
