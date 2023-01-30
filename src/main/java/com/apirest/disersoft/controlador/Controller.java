package com.apirest.disersoft.controlador;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.apirest.disersoft.Entity.EntityExcel;
import com.apirest.disersoft.Repository.GenerarExcelRepository;
import com.apirest.disersoft.Utils.ExportExcel;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:4200/")
public class Controller {
    @PostMapping("/fileData")
    public void imagen(@RequestParam("file") MultipartFile file, HttpServletResponse response) throws IOException {
        GenerarExcelRepository repository = new GenerarExcelRepository();
        repository.GenerateExcel(file, response);
    }
}


