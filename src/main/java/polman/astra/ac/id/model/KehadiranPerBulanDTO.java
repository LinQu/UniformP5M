package polman.astra.ac.id.model;

public class KehadiranPerBulanDTO {
    private String bulan;
    private double persentaseKehadiran;

    // Buat constructor dan getter setter


    public KehadiranPerBulanDTO() {
    }

    public KehadiranPerBulanDTO(String bulan, double persentaseKehadiran) {
        this.bulan = bulan;
        this.persentaseKehadiran = persentaseKehadiran;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public double getPersentaseKehadiran() {
        return persentaseKehadiran;
    }

    public void setPersentaseKehadiran(double persentaseKehadiran) {
        this.persentaseKehadiran = persentaseKehadiran;
    }

    @Override
    public String toString() {
        return "KehadiranPerBulanDTO{" +
                "bulan='" + bulan + '\'' +
                ", persentaseKehadiran=" + persentaseKehadiran +
                '}';
    }
}
