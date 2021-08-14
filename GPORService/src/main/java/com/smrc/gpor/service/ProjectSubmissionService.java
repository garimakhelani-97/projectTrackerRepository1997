package com.smrc.gpor.service;

import javax.mail.MessagingException;

import com.smrc.gpor.dto.ProjectSubmissionDTO;

public interface ProjectSubmissionService {

	public String submitProject(ProjectSubmissionDTO projectSubmissionDTO,String userId) throws MessagingException;
}
