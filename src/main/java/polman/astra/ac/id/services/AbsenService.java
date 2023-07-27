package polman.astra.ac.id.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import polman.astra.ac.id.model.Absen;
import polman.astra.ac.id.model.KehadiranPerBulanDTO;
import polman.astra.ac.id.repository.AbsenRepository;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class AbsenService {
    @Autowired
    AbsenRepository absenRepository;

    public boolean save(Absen absen){
        Absen result = absenRepository.save(absen);
        boolean isSuccess = true;
        if(result == null) {
            isSuccess = false;
        }
        return isSuccess;
    }

    public List<Absen> getAllAbsen(){
        return absenRepository.getAllAbsen();
    }

    public List<Absen> getAbsenByNim(String nim){
        return absenRepository.getAbsenByNim(nim);
    }

//    public List<Object[]> getHistoryAbsenByNim(String nim){
//        return absenRepository.getHistoryAbsenByNim(nim);
//    }

    public List<Object[]> getHistoryAbsenByNim(String nim) {
        List<Object[]> rawAbsensi = absenRepository.getHistoryAbsenByNim(nim);
        List<Object[]> absensiData = new ArrayList<>();

        for (Object[] data : rawAbsensi) {
            String nimValue = (String) data[0];
            LocalTime jamMasuk = ((java.sql.Time) data[2]).toLocalTime();
            LocalTime jamKeluar = data[3] != null ? ((java.sql.Time) data[3]).toLocalTime() : null;

            String status = getStatusKehadiran(jamMasuk, jamKeluar);

            // Menambahkan data kehadiran dan status kehadiran dalam bentuk Object[] ke list
            Object[] absenInfo = { nimValue, data[1], jamMasuk, jamKeluar, status };
            absensiData.add(absenInfo);
        }

        return absensiData;
    }

    public List<Object[]> getHistoryAbsenByNimAndMonth(String nim,int month,int year) {
        List<Object[]> rawAbsensi = absenRepository.getHistoryAbsenByNimAndMonth(nim,year,month);
        List<Object[]> absensiData = new ArrayList<>();

        for (Object[] data : rawAbsensi) {
            String nimValue = (String) data[0];
            LocalTime jamMasuk = ((java.sql.Time) data[2]).toLocalTime();
            LocalTime jamKeluar = data[3] != null ? ((java.sql.Time) data[3]).toLocalTime() : null;

            String status = getStatusKehadiran(jamMasuk, jamKeluar);

            // Menambahkan data kehadiran dan status kehadiran dalam bentuk Object[] ke list
            Object[] absenInfo = { nimValue, data[1], jamMasuk, jamKeluar, status };
            absensiData.add(absenInfo);
        }

        return absensiData;
    }



    private String getStatusKehadiran(LocalTime jamMasuk, LocalTime jamKeluar) {
        boolean hasMasuk = jamMasuk.isBefore(LocalTime.of(7, 31));
        boolean hasKeluar = jamKeluar != null && jamKeluar.isAfter(LocalTime.of(16, 29));

        if (hasMasuk && hasKeluar) {
            return "Hadir";
        } else if (hasMasuk) {
            return "Tidak Absen Keluar";
        } else if (hasKeluar) {
            return "Tidak Absen Masuk";
        } else {
            return "Tidak Absen";
        }
    }

    public List<KehadiranPerBulanDTO> getKehadiranPersentasePerBulan(String nim) {
        List<KehadiranPerBulanDTO> result = new ArrayList<>();
        List<Object[]> historyAbsen = absenRepository.getHistoryAbsenByNim(nim);

        Map<String, Integer> hadirPerBulan = new HashMap<>();
        Map<String, Integer> totalHariPerBulan = new HashMap<>();

        for (Object[] absen : historyAbsen) {
            String tanggal =  absen[1].toString(); // Mengambil data tanggal dari hasil query
            LocalDate localDate = LocalDate.parse(tanggal, DateTimeFormatter.ISO_DATE);
            int month = localDate.getMonthValue();
            int year = localDate.getYear();
            String bulan = getMonthFromDateString(tanggal);
            String statusKehadiran = (String) absen[4]; // Mengambil data status kehadiran dari hasil query

            hadirPerBulan.putIfAbsent(bulan, 0);
            totalHariPerBulan.putIfAbsent(bulan, 0);

            totalHariPerBulan.put(bulan, getTotalDaysInMonth(year,month));

            if ("Hadir".equalsIgnoreCase(statusKehadiran)) {
                hadirPerBulan.put(bulan, hadirPerBulan.get(bulan) + 1);
            }else if ("Tidak Absen Keluar".equalsIgnoreCase(statusKehadiran)) {
                hadirPerBulan.put(bulan, hadirPerBulan.get(bulan) + 1);
            }else if ("Tidak Absen Masuk".equalsIgnoreCase(statusKehadiran)) {
                hadirPerBulan.put(bulan, hadirPerBulan.get(bulan) + 1);
            }
        }

        for (Map.Entry<String, Integer> entry : hadirPerBulan.entrySet()) {
            String bulan = entry.getKey();
            int totalHadir = entry.getValue();
            int totalHari = totalHariPerBulan.get(bulan);

            double persentaseKehadiran = (totalHadir / (double) totalHari) * 100;

            KehadiranPerBulanDTO dto = new KehadiranPerBulanDTO();
            dto.setBulan(bulan);
            dto.setPersentaseKehadiran(persentaseKehadiran);

            result.add(dto);
        }

        Collections.reverse(result);

        return result;
    }

    private String getMonthFromDateString(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH) + 1;

        return getMonthNameIndonesia(month);
    }

    public KehadiranPerBulanDTO getKehadiranPersentasePerBulan(String nim, int year, int month) {
        KehadiranPerBulanDTO result = new KehadiranPerBulanDTO();
        List<Object[]> historyAbsen = absenRepository.getHistoryAbsenByNimAndMonth(nim, year, month);

        int totalHadir = 0;
        int totalHari = getTotalDaysInMonth(year, month);

        for (Object[] absen : historyAbsen) {
            String tanggal = absen[1].toString(); // Mengambil data tanggal dari hasil query
            String statusKehadiran = (String) absen[4];

            if ("Hadir".equalsIgnoreCase(statusKehadiran) ||
                    "Tidak Absen Keluar".equalsIgnoreCase(statusKehadiran) ||
                    "Tidak Absen Masuk".equalsIgnoreCase(statusKehadiran)) {
                totalHadir++;
            }
        }

        double persentaseKehadiran = (totalHadir / (double) totalHari) * 100;

        result.setBulan(getMonthNameIndonesia(month)); // Fungsi ini mengambil nama bulan dalam bahasa Indonesia berdasarkan angka bulan (1-12)
        result.setPersentaseKehadiran(persentaseKehadiran);

        return result;
    }

    public List<Object[]> getHistoryListMonthByNim(String NIM){
        return absenRepository.getHistoryListMonthByNim(NIM);
    }
    private String getMonthNameIndonesia(int month) {
        String[] monthNames = {
                "Januari", "Februari", "Maret", "April", "Mei", "Juni",
                "Juli", "Agustus", "September", "Oktober", "November", "Desember"
        };
        return monthNames[month - 1];
    }

    // Fungsi untuk mendapatkan jumlah hari dalam bulan tertentu pada tahun tertentu
    private int getTotalDaysInMonth(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        return yearMonth.lengthOfMonth();
    }

}
