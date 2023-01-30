package com.apirest.disersoft.Repository;

import com.apirest.disersoft.Entity.EntityExcel;
import com.apirest.disersoft.Utils.ExportExcel;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

public class GenerarExcelRepository {

    public void GenerateExcel(MultipartFile file, HttpServletResponse response) throws IOException {
        List<EntityExcel> excel = new ArrayList<>();
        String content = new String(file.getBytes(), StandardCharsets.UTF_8);
        String[] result = content.split("\r?\n|\r");
        for (String data: result) {
            if(data.indexOf("ADC_EVENT:") != -1){
                String[] exist = data.split("ADC_EVENT:");
                EntityExcel model = new EntityExcel();
                if(exist.length > 1){
                    System.out.println(exist[1]);
                    String[] objectOne = exist[1].split(",");
                    if(objectOne.length > 11){
                        model.V_master = (objectOne[0].split(":").length > 1)?objectOne[0].split(":")[1] : "";
                        model.V_battery = (objectOne[1].split(":").length > 1)?objectOne[1].split(":")[1] : "";
                        model.master = (objectOne[2].split(":").length > 1)?objectOne[2].split(":")[1] : "";
                        model.battery = (objectOne[3].split(":").length > 1)?objectOne[3].split(":")[1] : "";
                        model.charger = (objectOne[4].split(":").length > 1)?objectOne[4].split(":")[1] : "";
                        model.nvr = (objectOne[5].split(":").length > 1)?objectOne[5].split(":")[1] : "";
                        model.switchh = (objectOne[6].split(":").length > 1)?objectOne[6].split(":")[1] : "";
                        model.iot = (objectOne[7].split(":").length > 1)?objectOne[7].split(":")[1] : "";
                        model.fan = (objectOne[8].split(":").length > 1)?objectOne[8].split(":")[1] : "";
                        model.status = (objectOne[9].split(":").length > 1)?objectOne[9].split(":")[1] : "";
                        model.Pwm = (objectOne[10].split(":").length > 1)?objectOne[10].split(":")[1] : "";
                        model.temp = (objectOne[11].split(":").length > 1)?objectOne[11].split(":")[1] : "";
                        excel.add(model);
                    }

                }
            }
        }

        ExportExcel generar = new ExportExcel(excel);
        generar.generateExcelFile(response);

    }
}
