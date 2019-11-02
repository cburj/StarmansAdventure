
public class Question
{
	//FIELDS FOR QUESTION OBJECTS/INSTANCES
	private String question;
	private String answer;
	private int points;
	
	//CONSTRUCTOR
	public Question(String question, String answer, int points)
	{
		this.question = question;
		this.answer = answer;
		this.points = points;
	}

	//GETTERs and SETTERs for FIELDS
	
	public String getQuestion() {
		return question;
	}


	public void setQuestion(String question) {
		this.question = question;
	}


	public String getAnswer() {
		return answer;
	}


	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
	public void printQAP()
	{
		System.out.println("Q: " +question);
		System.out.println("A: " +answer);
		System.out.println("P: " +points);
		System.out.println("");
	}
	
}