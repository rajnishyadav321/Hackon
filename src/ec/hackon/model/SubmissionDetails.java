package ec.hackon.model;




import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class SubmissionDetails {
	
	
	private String challengeId;
	private String challangeName;
	private String answer;
	private boolean status;
	

	public String getChallengeId() {
		return challengeId;
	}
	public void setChallengeId(String challengeId) {
		this.challengeId = challengeId;
	}
	public String getChallangeName() {
		return challangeName;
	}
	public void setChallangeName(String challangeName) {
		this.challangeName = challangeName;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}

	
}
