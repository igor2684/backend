package com.backend.service;

import com.backend.entity.Document;
import com.backend.entity.Project;
import com.backend.repository.DocumentRepository;
import com.backend.repository.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ProjectServiceTest {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private ProjectService projectService;

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
        projectRepository.findById(project.getId()).orElseThrow();
        List<Project> projects = projectService.findAll();
        project = projects.getFirst();
        Set<Document> documents = project.getDocuments();
        assertEquals(1, projects.size());
        assertEquals(1, documents.size());
    }

    @Test
    void testSave() {
        Project expected = new Project();
        expected.setName("Name");
        expected.setStatus("status1");
        projectService.save(expected);
        Project actual = projectRepository.findById(expected.getId()).orElseThrow();
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getStatus(), actual.getStatus());
        assertEquals(expected.getDocuments(), actual.getDocuments());
    }

    @Test
    void testFind() {
        Project expected = new Project();
        expected.setName("Name");
        expected.setStatus("status1");
        projectRepository.save(expected);
        Document document = new Document();
        document.setProject(expected);
        document.setStatus("status1");
        document.setFilePath("/file");
        documentRepository.save(document);
        expected = projectRepository.findById(expected.getId()).orElseThrow();
        Project actual = projectService.find(expected.getId());
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getStatus(), actual.getStatus());
        assertEquals(expected.getDocuments(), actual.getDocuments());
    }

    @Test
    void testUpdate() {
        Project expected = new Project();
        expected.setName("Name");
        expected.setStatus("status1");
        projectRepository.save(expected);
        Document document = new Document();
        document.setProject(expected);
        document.setStatus("status1");
        document.setFilePath("/file");
        documentRepository.save(document);
        expected = projectRepository.findById(expected.getId()).orElseThrow();
        expected.setStatus("status2");
        Project actual = projectService.update(expected.getId(), expected);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getStatus(), actual.getStatus());
        assertEquals(expected.getDocuments(), actual.getDocuments());
    }

    @Test
    void testDelete() {
        Project expected = new Project();
        expected.setName("Name");
        expected.setStatus("status1");
        projectRepository.save(expected);
        Document document = new Document();
        document.setProject(expected);
        document.setStatus("status1");
        document.setFilePath("/file");
        documentRepository.save(document);
        expected = projectRepository.findById(expected.getId()).orElseThrow();
        documentRepository.deleteAll();
        long before = projectRepository.findById(expected.getId()).stream().count();
        projectService.delete(expected.getId());
        long after = projectRepository.findById(expected.getId()).stream().count();
        assertTrue(before > after);
    }
}
