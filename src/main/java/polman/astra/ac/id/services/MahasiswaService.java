package polman.astra.ac.id.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import polman.astra.ac.id.model.response.AccessTokenResponse;
import polman.astra.ac.id.model.Mahasiswa;

import java.util.Arrays;
import java.util.List;

@Service
public class MahasiswaService {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public MahasiswaService() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    public Mahasiswa[] getMahasiswaByNim(String nim) {
        String url = "https://api.polytechnic.astra.ac.id:2906/api_dev/efcc359990d14328fda74beb65088ef9660ca17e/SIA/getMahasiswa?nim=" + nim;

        String jsonResponse = restTemplate.getForObject(url, String.class);

        try {
            JsonNode root = objectMapper.readTree(jsonResponse);


            if (root.isArray()) {
                Mahasiswa[] mahasiswaArray = objectMapper.treeToValue(root, Mahasiswa[].class);
                // Mengubah kelas menjadi 2 huruf terakhir
                for (Mahasiswa mahasiswa : mahasiswaArray) {
                    String kelas = mahasiswa.getKelas();
                    String kelasModified = kelas.substring(kelas.length() - 2);
                    mahasiswa.setKelas(kelasModified);
                }
                return mahasiswaArray;
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Mahasiswa> getListMahasiswaBykelas(String kelas) {
        // Mendapatkan akses token
        String accessToken = getAccessToken();

        // Membuat header dengan akses token
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);
        // Membuat permintaan HTTP GET dengan header
        String url = "https://api.polytechnic.astra.ac.id:2906/api_dev/efcc359990d14328fda74beb65088ef9660ca17e/SIA/getListMahasiswaByKelas?kelas=" + kelas;
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        // Mengirim permintaan GET dengan header
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                requestEntity,
                String.class
        );
        // Mengirim permintaan HTTP GET ke API
        String jsonResponse = responseEntity.getBody();
        try {
            JsonNode root = objectMapper.readTree(jsonResponse);
            if (root.isArray()) {
                Mahasiswa[] mahasiswaArray = objectMapper.treeToValue(root, Mahasiswa[].class);
                // Mengubah kelas menjadi 2 huruf terakhir
                for (Mahasiswa mahasiswa : mahasiswaArray) {
                    String kelasMahasiswa = mahasiswa.getKelas();
                    String kelasModified = kelasMahasiswa.substring(kelasMahasiswa.length() - 2);
                    mahasiswa.setKelas(kelasModified);
                }
                return Arrays.asList(mahasiswaArray);
            }
        } catch (JsonProcessingException e) {

            e.printStackTrace();
        }
        return null;
    }

    private String getAccessToken() {
        // Menyiapkan permintaan untuk mengambil akses token
        String url = "https://api.polytechnic.astra.ac.id:2906/api_dev/AccessToken/Get";
        // Membuat header untuk permintaan akses token
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //headers.setBasicAuth(CLIENT_ID, CLIENT_SECRET); // Mengatur client_id dan client_secret sesuai kebutuhan

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "password");
        body.add("username", "asd");
        body.add("password", "asd");

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);

        // Mengirim permintaan POST untuk mendapatkan akses token
//        ResponseEntity<AccessTokenResponse> responseEntity = restTemplate.exchange(
//                url,
//                HttpMethod.POST,
//                requestEntity,
//                AccessTokenResponse.class
//        );
        AccessTokenResponse response = restTemplate.postForObject(url, requestEntity, AccessTokenResponse.class);
        if (response != null) {
            return response.getAccess_token();
        }
        return null;
    }
}
