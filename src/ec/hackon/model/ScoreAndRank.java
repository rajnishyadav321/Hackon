package ec.hackon.model;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ScoreAndRank {
		
	private int score;
	private int rank;
	public List<Integer> getDoneQuestions() {
		return doneQuestions;
	}
	public void setDoneQuestions(List<Integer> doneQuestions) {
		this.doneQuestions = doneQuestions;
	}
	private List<Integer> doneQuestions;
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
}
