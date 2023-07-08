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
import polman.astra.ac.id.model.Karyawan;


@Service
public class KaryawanService {
    private static final String CLIENT_ID = "efcc359990d14328fda74beb65088ef9660ca17e";
    private static final String CLIENT_SECRET = "c2b2b4b4c2b2b4b4c2b2b4b4c2b2b4b4";
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public KaryawanService() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    public Karyawan[] getKaryawanById(String id) {
        // Mendapatkan akses token
        String accessToken = getAccessToken();

        // Membuat header dengan akses token
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);
        String url = "https://api.polytechnic.astra.ac.id:2906/api_dev/efcc359990d14328fda74beb65088ef9660ca17e/SIA/getKaryawan?npk=" + id;
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        // Mengirim permintaan GET dengan header
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                requestEntity,
                String.class
        );
        // Mendapatkan respons JSON
        String jsonResponse = responseEntity.getBody();

        try {
            JsonNode root = objectMapper.readTree(jsonResponse);


            if (root.isArray()) {
                Karyawan[] karyawanArray = objectMapper.treeToValue(root, Karyawan[].class);

                return karyawanArray;
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String loginKaryawan(String Username){
        String url = "https://api.polytechnic.astra.ac.id:2906/api_dev/efcc359990d14328fda74beb65088ef9660ca17e/SIA/loginKaryawan?username=" + Username;
        String jsonResponse = restTemplate.getForObject(url, String.class);
        try {
            JsonNode root = objectMapper.readTree(jsonResponse);
            JsonNode karyawanArray = root.isArray() ? root : null;
            if (karyawanArray != null) {
                Karyawan[] karyawan = objectMapper.treeToValue(karyawanArray, Karyawan[].class);
                return "Berhasil Login";
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
