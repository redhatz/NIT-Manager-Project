package com.projectmanagement.repository;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.projectmanagement.entity.ProjectDetails;



/**
 * @author prasanjeet
 * 
 * This Class is a repository class which Allows you to do CRUD operations
 *
 */
@Repository
public interface ProjectManagementRepository extends MongoRepository<ProjectDetails, String>{
	
	ProjectDetails findByProjectId(String projectId);

	
	 Page<ProjectDetails> findAll(Pageable pageable);


	void deleteByProjectId(String projectId);


	ProjectDetails findByProjectName(@Valid String projectName);
	
	List<ProjectDetails> findByStatus(String status);
	
	Page<ProjectDetails> findByStatus(String status,Pageable pageable);
	
	 
	 

}
