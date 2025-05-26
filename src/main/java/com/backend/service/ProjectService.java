package com.backend.service;

import com.backend.entity.Project;
import com.backend.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public Project save(Project newProject) {
        return projectRepository.save(newProject);
    }

    public Project find(Long id) {
        return projectRepository.findById(id).orElseThrow();
    }

    public Project update(Long id, Project newProject) {
        return projectRepository.findById(id).map(project -> {
            newProject.setId(project.getId());
            return projectRepository.save(newProject);
        }).orElseThrow();
    }

    public void delete(Long id) {
        projectRepository.deleteById(id);
    }
}
