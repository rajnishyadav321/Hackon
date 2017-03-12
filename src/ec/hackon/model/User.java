package ec.hackon.model;

import java.security.Timestamp;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlRootElement
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class User {

	private String userId;
	
	private PersonalDetails personalDetails;
	
	private ScoreAndRank scoreAndRank;
	
	private SubmissionDetails submissionDetails;
	

	
	

	public SubmissionDetails getSubmissionDetails() {
		return submissionDetails;
	}

	public void setSubmissionDetails(SubmissionDetails submissionDetails) {
		this.submissionDetails = submissionDetails;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public PersonalDetails getPersonalDetails() {
		return personalDetails;
	}

	public void setPersonalDetails(PersonalDetails personalDetails) {
		this.personalDetails = personalDetails;
	}

	public ScoreAndRank getScoreAndRank() {
		return scoreAndRank;
	}

	public void setScoreAndRank(ScoreAndRank scoreAndRank) {
		this.scoreAndRank = scoreAndRank;
	}

	

	
}
