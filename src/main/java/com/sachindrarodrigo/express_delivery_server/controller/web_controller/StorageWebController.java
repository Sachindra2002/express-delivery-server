package com.sachindrarodrigo.express_delivery_server.controller.web_controller;

import com.sachindrarodrigo.express_delivery_server.exception.ExpressDeliveryException;
import com.sachindrarodrigo.express_delivery_server.service.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class StorageWebController {

    @Autowired
    private StorageService service;

    @GetMapping("/download")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ByteArrayResource> viewDriver(@RequestParam String fileName)  {
        byte[] data = service.downloadFile(fileName);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }
}
