package polman.astra.ac.id.services;


import io.swagger.models.auth.In;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import polman.astra.ac.id.model.Pelanggaran;
import polman.astra.ac.id.repository.PelanggaranRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PelanggaranService {
    @Autowired
    PelanggaranRepository pelanggaranRepository;

    public  boolean save(Pelanggaran Pelanggaran){
        Pelanggaran result = pelanggaranRepository.save(Pelanggaran);
        boolean isSuccess = true;
        if(result == null) {
            isSuccess = false;
        }
        return isSuccess;
    }

    public Pelanggaran getPelanggaran(Integer id){
        Pelanggaran Pelanggaran = pelanggaranRepository.getPelanggaranById(id);
        return Pelanggaran;
    }

    public boolean deletePelanggaran(Integer id) {
        Pelanggaran pelanggaran =  pelanggaranRepository.getPelanggaranById(id);
        pelanggaran.setStatus(0);
        Pelanggaran result = pelanggaranRepository.save(pelanggaran);
        boolean isSuccess = true;
        if(result == null) {
            isSuccess = false;
        }
        return isSuccess;
    }


    public List<Pelanggaran> getPelanggarans(){
        List<Pelanggaran> Pelanggaran = pelanggaranRepository.getPelanggarans();
        return Pelanggaran;
    }

    @Transactional
    public boolean updatePelanggaran(Pelanggaran pelanggaranParam) {
        Pelanggaran pelanggaran = pelanggaranRepository.getPelanggaranById(pelanggaranParam.getId());
        pelanggaran.setNama(pelanggaranParam.getNama());
        pelanggaran.setJam_minus(pelanggaranParam.getJam_minus());
        pelanggaran.setStatus(pelanggaranParam.getStatus());
        Pelanggaran result = pelanggaranRepository.save(pelanggaran);
        boolean isucces = true;
        if(result == null){
            isucces = false;
        }return isucces;

    }

    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
        String path = "C:\\Users\\student\\Downloads";
        List<Pelanggaran> Pelanggaran = (List<Pelanggaran>) pelanggaranRepository.findAll();
        File file = ResourceUtils.getFile("classpath:task.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(Pelanggaran);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Vivki");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (reportFormat.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\Pelanggaran.html");
        } else if (reportFormat.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\Pelanggaran.pdf");

        }
        return "Export Report to" + reportFormat;
    }
}
