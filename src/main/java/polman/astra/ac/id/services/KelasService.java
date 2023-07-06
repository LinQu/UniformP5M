package polman.astra.ac.id.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import polman.astra.ac.id.model.Kelas;

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

    public Kelas[] getListKelasByTahunAjaran(String tahunAjaran) {
        String url = "https://api.polytechnic.astra.ac.id:2906/api_dev/efcc359990d14328fda74beb65088ef9660ca17e/SIA/getListKelas?id_konsentrasi=3";

        String jsonResponse = restTemplate.getForObject(url, String.class);

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

                return filteredKelasList.toArray(new Kelas[0]);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
