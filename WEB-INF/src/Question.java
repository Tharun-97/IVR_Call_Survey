import java.util.Set;

public class Question {
	private int question_id;
	private String question;
	private Set<Option> options;
	private Option answer;
    
	public Question(int question_id, String question, Set<Option> options) {
		this.question_id = question_id;
		this.question = question;
		this.options = options;
	}

	public Question() {
	
	}

	public int getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Set<Option> getOptions() {
		return options;
	}

	public void setOptions(Set<Option> options) {
		this.options = options;
	}

}
