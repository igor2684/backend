package com.backend.service;

import com.backend.entity.Document;
import com.backend.repository.DocumentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService {
    private final DocumentRepository documentRepository;

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public List<Document> findAll() {
        return documentRepository.findAll();
    }

    public Document save(Document newDocument) {
        return documentRepository.save(newDocument);
    }

    public Document find(Long id) {
        return documentRepository.findById(id).orElseThrow();
    }

    public Document update(Long id, Document newDocument) {
        return documentRepository.findById(id).map(document -> {
            newDocument.setId(document.getId());
            return documentRepository.save(newDocument);
        }).orElseThrow();
    }

    public void delete(Long id) {
        documentRepository.deleteById(id);
    }
}
