package com.projectmanagement.repository;

import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.projectmanagement.entity.ProjectTeamDetails;

/**
 * @author prasanjeet
 * 
 * This Class is a repository class which allows you to do CRUD operations
 *
 */
@Repository
public interface ProjectTeamRepository extends MongoRepository<ProjectTeamDetails, String> {

	void deleteByTeamId(String teamId);

	ProjectTeamDetails findByTeamId(String teamId);

	ProjectTeamDetails findByProjectAssigned(@NotNull String projectAssigned);

}
