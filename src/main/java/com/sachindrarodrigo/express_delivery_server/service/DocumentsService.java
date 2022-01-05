package com.sachindrarodrigo.express_delivery_server.service;

import com.sachindrarodrigo.express_delivery_server.domain.Documents;
import com.sachindrarodrigo.express_delivery_server.dto.DocumentsDto;
import com.sachindrarodrigo.express_delivery_server.repository.DocumentsRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class DocumentsService {

    private DocumentsRepository documentsRepository;

    public List<DocumentsDto> getDocuments(){
        return documentsRepository.findAll().stream().map(this::mapDocuments).collect(Collectors.toList());
    }

    private DocumentsDto mapDocuments(Documents documents){
        return new DocumentsDto(documents.getDocumentId(), documents.getDescription(), documents.getFileName(), documents.getFileSize(), documents.getUser());
    }
}
