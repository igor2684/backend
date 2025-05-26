package com.backend.controller;

import com.backend.service.DocumentService;
import com.backend.entity.Document;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DocumentController {
    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping(value = "/documents")
    public List<Document> findAllDocuments() {
        return documentService.findAll();
    }

    @PostMapping(value = "/documents")
    public Document saveDocument(@RequestBody Document newDocument) {
        return documentService.save(newDocument);
    }

    @GetMapping(value = "/documents/{id}")
    public Document findDocument(@PathVariable Long id) {
        return documentService.find(id);
    }

    @PutMapping(value = "/documents/{id}")
    public Document updateDocument(@PathVariable Long id, @RequestBody Document newDocument) {
        return documentService.update(id, newDocument);
    }

    @DeleteMapping(value = "/documents/{id}")
    public ResponseEntity<Object> deleteDocument(@PathVariable Long id) {
        documentService.delete(id);
        return ResponseEntity.ok().build();
    }
}
