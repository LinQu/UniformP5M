package polman.astra.ac.id.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import polman.astra.ac.id.model.Kelas;
import polman.astra.ac.id.model.Mahasiswa;
import polman.astra.ac.id.model.response.AccessTokenResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class KelasService {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public KelasService() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    public List<Kelas> getListKelasByTahunAjaran(String tahunAjaran) {
        // Mendapatkan akses token
        String accessToken = getAccessToken();

        // Membuat header dengan akses token
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);
        String url = "https://api.polytechnic.astra.ac.id:2906/api_dev/efcc359990d14328fda74beb65088ef9660ca17e/SIA/getListKelas?id_konsentrasi=3";
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
            JsonNode kelasArray = root.isArray() ? root : null;

            if (kelasArray != null) {
                List<Kelas> filteredKelasList = new ArrayList<>();

                for (JsonNode kelasNode : kelasArray) {
                    String kelTahunAjaran = kelasNode.get("kel_tahun_ajaran").asText();

                    if (kelTahunAjaran.equals(tahunAjaran)) {
                        Kelas kelas = objectMapper.treeToValue(kelasNode, Kelas.class);
                        filteredKelasList.add(kelas);
                    }
                }

                return filteredKelasList;
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
