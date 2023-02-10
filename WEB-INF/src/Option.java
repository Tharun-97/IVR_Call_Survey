public class Option {
	private int option_id;
	private String option;
	private Question question_id;

	public Option(int option_id, String option) {
		this.option_id = option_id;
		this.option = option;
	}

	public Option() {
	
	}

	public int getOption_id() {
		return option_id;
	}

	public void setOption_id(int option_id) {
		this.option_id = option_id;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public Question getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(Question question_id) {
		this.question_id = question_id;
	}

}
