package com.sachindrarodrigo.express_delivery_server.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.sachindrarodrigo.express_delivery_server.domain.Documents;
import com.sachindrarodrigo.express_delivery_server.domain.User;
import com.sachindrarodrigo.express_delivery_server.dto.DocumentsDto;
import com.sachindrarodrigo.express_delivery_server.exception.ExpressDeliveryException;
import com.sachindrarodrigo.express_delivery_server.repository.DocumentsRepository;
import com.sachindrarodrigo.express_delivery_server.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class StorageService {

    private final DocumentsRepository documentsRepository;
    private final UserRepository userRepository;

    @Value("${application.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3Client;

    public StorageService(DocumentsRepository documentsRepository, UserRepository userRepository) {
        this.documentsRepository = documentsRepository;
        this.userRepository = userRepository;
    }

    public String uploadFile(MultipartFile file) {
        File fileObj = convertMultiPartFileToFile(file);
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
        fileObj.delete();
        return "File uploaded : " + fileName;
    }

    public void uploadNicFile(MultipartFile file, String email, DocumentsDto dto) throws ExpressDeliveryException {
        File fileObj = convertMultiPartFileToFile(file);
        String fileName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
        Long fileSize = file.getSize();
        s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
        fileObj.delete();

        saveDocument(fileName, fileSize, email, "NIC");
    }

    public void uploadLicenceFile(MultipartFile file, String email, DocumentsDto dto) throws ExpressDeliveryException {
        File fileObj = convertMultiPartFileToFile(file);
        String fileName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
        Long fileSize = file.getSize();
        s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
        fileObj.delete();

        saveDocument(fileName, fileSize, email, "License");
    }

    public void uploadInsuaranceFile(MultipartFile file, String email, DocumentsDto dto) throws ExpressDeliveryException {
        File fileObj = convertMultiPartFileToFile(file);
        String fileName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
        Long fileSize = file.getSize();
        s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
        fileObj.delete();

        saveDocument(fileName, fileSize, email, "Insurance");
    }

    public void saveDocument(String fileName, Long fileSize, String email, String description) throws ExpressDeliveryException {

        Optional<User> userOptional = userRepository.findById(email);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));

        documentsRepository.save(Documents.builder().user(user)
                .fileSize(fileSize)
                .fileName(fileName)
                .description(user.getFirstName() + " " + user.getLastName() + " " + description)
                .build());
    }


    public byte[] downloadFile(String fileName) {
        S3Object s3Object = s3Client.getObject(bucketName, fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            return IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public String deleteFile(String fileName) {
        s3Client.deleteObject(bucketName, fileName);
        return fileName + " removed ...";
    }


    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            log.error("Error converting multipartFile to file", e);
        }
        return convertedFile;
    }
}
