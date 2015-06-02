package nju.iip.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * 量表题目对应的JavaBean
 * @author wangqiang
 *
 */
public class Questions {
	
	String question;
	
	List<String> answers = new ArrayList<String>();
	
	int correctAnswer;

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}


	public List<String> getAnswers() {
		return answers;
	}

	public void setAnswers(List<String> options) {
		this.answers.addAll(options);
	}

	public int getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(int correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

}
