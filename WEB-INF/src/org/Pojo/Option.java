package org.Pojo;

public class Option {
	private int id;
	private String options;
	private Question question_id;

	public int getId() {
		return id;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Question getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(Question question_id) {
		this.question_id = question_id;
	}

}
