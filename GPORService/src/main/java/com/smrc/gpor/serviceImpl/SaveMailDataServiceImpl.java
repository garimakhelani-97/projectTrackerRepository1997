package com.smrc.gpor.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smrc.gpor.dto.ProjectSubmissionDTO;
import com.smrc.gpor.repository.ProjectRepository;
import com.smrc.gpor.service.SaveMailDataService;

@Service
public class SaveMailDataServiceImpl implements  SaveMailDataService {
	@Autowired
	private ProjectRepository projectRepository;
	@Override
	public StringBuilder getMailBody(ProjectSubmissionDTO projectSubmissionDTO) {
		String projectName=projectRepository.findByprojectId(projectSubmissionDTO.getProjectId());
		   
		StringBuilder mailBody=new StringBuilder();
		mailBody.append( "<html>"
				+ "<body>"
				+ "<font size=2 face='Arial'>Dear Sir/Madam,</font>"
				+ "<br><br>"
				+ "<font size=2 face='Arial'>You have received a message from SMRC Application</font>"
				+ "<br><br>"
				+ "  <table border='0'>"
				+ "<tr>"
				+ "<td valign='top'>"
				+ "<font size=2 face='Arial'><b>Year</b></font>"
				+ "</td>"
				+ "<td valign='top'><font size=2 face='Arial'>:</font></td>"
				+ "<td valign='top'><font size=2 face='Arial'><pre style='font-family: inherit; font-size: inherit; color: inherit; background-color: inherit; border-style: inherit; padding: inherit;'>"+projectSubmissionDTO.getYear()+"</pre> " //year
				+ "</font> "
				+ "</td>"
				+ "</tr> "
				+ "<tr> "
				+ "<td valign='top'><font size=2 face='Arial'><b>Month</b></font></td> "
				+ "<td valign='top'><font size=2 face='Arial'>:</font></td><td valign='top'><font size=2 face='Arial'>"
				+ "<pre style='font-family: inherit; font-size: inherit; color: inherit; background-color: inherit; border-style: inherit; padding: inherit;'>"+projectSubmissionDTO.getMonth()+"</pre>" //month
				+ " </font>"
				+ "</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td valign='top'><font size=2 face='Arial'><b>Comment Type</b></font></td>"
				+ "<td valign='top'><font size=2 face='Arial'>:</font></td>"
				+ "<td valign='top'><font size=2 face='Arial'> "
				+ "<pre style='font-family: inherit; font-size: inherit; color: inherit; background-color: inherit; border-style: inherit; padding: inherit;'>"+projectSubmissionDTO.getActionKey()+"</pre> </font> "
				+ "</td> "
				+ "</tr>"
				+ "<tr> "
				+ "<td valign='top'><font size=2 face='Arial'><b>Comment</b></font></td>"
				+ "<td valign='top'><font size=2 face='Arial'>:</font></td>"
				+ "<td valign='top'><font size=2 face='Arial'> "
				+ "<pre  style='font-family: inherit; font-size: inherit; color: blue; background-color: inherit; border-style: inherit; padding: inherit;'>"+ projectSubmissionDTO.getComments()+"</pre></font> </td>"
				+ "</tr>"
				+ "<tr> <td valign='top' colspan='3'>&nbsp;</td> </tr>"
				+ "</table><br><!-- For the following Units: <BR> --><div>Project Submission Status: <BR>"
				+ "<table id=submissionStatusSingleMail border=1>"
				+ "<tr style = 'background-color : #B0B0B0'>"
				+ "<th>SNo.</th>"
				+ "<th>Project Id</th>"
				+ "<th>Project Name</th>"
				+ "<th>Status </th>"
				+ "<tr><td>1</td>"
				+ "<td>"+projectSubmissionDTO.getProjectId()+"</td>" //projectId
				+ "<td>"+projectName+"</td>"
				+ "<td>Test_zvi.albert@sunpharma.com</td>"
				+ "</tr></table>"
				+ "</div> <!-- <br><font size=2 face='Arial'><b>Message Sent By</b></font><font size=2 face='Arial'> : </font><font size=2 face='Arial'>SMRC Admin</font><br> --><br><font size=2 face='Arial'>"
				+ "<b>Message Sent By</b></font><font size=2 face='Arial'> : </font>"
				+ "<font size=2 face='Arial'>SMRC Admin <span> on</span> "+projectSubmissionDTO.getUpdatedDate()+"</font><br><br><br>"
				+ "<font size=2 face='Arial'>Please click on the link to access <a href= 'http://172.29.57.87:5050/test-sun-spms';;  >SMRC</a>.</font><br><br>"
				+ "<font size=2 face='Arial'>This is an auto-generated email, please do not reply.</font><br><br>"
				+ "<font size=2 face='Arial'>Best Regards,</font><br><font size=2 face='Arial'>SMRC Administrator</font>"
				+ "</body>"
				+ "</html>");
			return mailBody	;
			}

}
