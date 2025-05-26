package com.backend.controller;

import com.backend.entity.Project;
import com.backend.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping(value = "/projects")
    public List<Project> findAllProjects() {
        return projectService.findAll();
    }

    @PostMapping(value = "/projects")
    public Project saveProject(@RequestBody Project newProject) {
        return projectService.save(newProject);
    }

    @GetMapping(value = "/projects/{id}")
    public Project findProject(@PathVariable Long id) {
        return projectService.find(id);
    }

    @PutMapping(value = "/projects/{id}")
    public Project updateProject(@PathVariable Long id, @RequestBody Project newProject) {
        return projectService.update(id, newProject);
    }

    @DeleteMapping(value = "/projects/{id}")
    public ResponseEntity<Object> deleteProject(@PathVariable Long id) {
        projectService.delete(id);
        return ResponseEntity.ok().build();
    }
}
