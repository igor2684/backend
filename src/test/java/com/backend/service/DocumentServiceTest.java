package com.backend.service;

import com.backend.entity.Document;
import com.backend.entity.Project;
import com.backend.repository.ProjectRepository;
import com.backend.repository.DocumentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class DocumentServiceTest {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private DocumentService documentService;

    @Test
    void testFindAll() {
        Project project = new Project();
        project.setName("Name");
        project.setStatus("status1");
        projectRepository.save(project);
        Document document = new Document();
        document.setProject(project);
        document.setStatus("status1");
        document.setFilePath("/file");
        documentRepository.save(document);
        List<Document> documents = documentService.findAll();
        document = documents.getFirst();
        project = document.getProject();
        assertEquals(1, documents.size());
        assertNotNull(project);
    }

    @Test
    void testSave() {
        Project project = new Project();
        project.setName("Name");
        project.setStatus("status1");
        projectRepository.save(project);
        Document expected = new Document();
        expected.setProject(project);
        expected.setStatus("status1");
        expected.setFilePath("/file");
        documentService.save(expected);
        Document actual = documentRepository.findById(expected.getId()).orElseThrow();
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getProject(), actual.getProject());
        assertEquals(expected.getStatus(), actual.getStatus());
        assertEquals(expected.getFilePath(), actual.getFilePath());
    }

    @Test
    void testFind() {
        Project project = new Project();
        project.setName("Name");
        project.setStatus("status1");
        projectRepository.save(project);
        Document expected = new Document();
        expected.setProject(project);
        expected.setStatus("status1");
        expected.setFilePath("/file");
        expected = documentRepository.save(expected);
        Document actual = documentService.find(expected.getId());
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getProject(), actual.getProject());
        assertEquals(expected.getStatus(), actual.getStatus());
        assertEquals(expected.getFilePath(), actual.getFilePath());
    }

    @Test
    void testUpdate() {
        Project project = new Project();
        project.setName("Name");
        project.setStatus("status1");
        projectRepository.save(project);
        Document expected = new Document();
        expected.setProject(project);
        expected.setStatus("status1");
        expected.setFilePath("/file");
        expected = documentRepository.save(expected);
        expected.setStatus("status2");
        Document actual = documentService.update(expected.getId(), expected);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getProject(), actual.getProject());
        assertEquals(expected.getStatus(), actual.getStatus());
        assertEquals(expected.getFilePath(), actual.getFilePath());
    }

    @Test
    void testDelete() {
        Project project = new Project();
        project.setName("Name");
        project.setStatus("status1");
        projectRepository.save(project);
        Document expected = new Document();
        expected.setProject(project);
        expected.setStatus("status1");
        expected.setFilePath("/file");
        expected = documentRepository.save(expected);
        long before = documentRepository.findById(expected.getId()).stream().count();
        documentService.delete(expected.getId());
        long after = documentRepository.findById(expected.getId()).stream().count();
        assertTrue(before > after);
    }
}
