import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class QuestionMaster
{
	//FIELDS
	private ArrayList<Question> questionList;
	private Random rand;
	
	public QuestionMaster()
	{
		questionList = new ArrayList<Question>();
		rand = new Random();
		
	}
	
	//Method for AUTOMATICALLY importing a file containing the questions.
	public void importFile() throws FileNotFoundException
	{
		String myFile = "src/resources/questions.txt";
		importQuestions(myFile);
	}
	
	//Method for MANUALLY selecting a file that contains the Questions
	public void selectFile() throws FileNotFoundException
    {
        Frame myFrame = null;
        FileDialog fileBox = new FileDialog(myFrame, "Open", FileDialog.LOAD);
        fileBox.setVisible(true);
        String myFile = "/resources/" + fileBox.getFile();
        importQuestions(myFile);
    }
	
	//Method to populate the "questionsList" ArrayList with questions from the input text file.
	public void importQuestions(String fileName) throws FileNotFoundException
    {
		String inputFile = fileName;
        if(inputFile != null)
        {
            File dataFile = new File(inputFile);
            Scanner scanner = null;
            try
            {
                scanner = new Scanner(dataFile);
            }
            catch(Exception ex)
            {
                System.out.println("> Error");
                System.out.println(ex.getMessage());
            }
            while(scanner.hasNext())
            {
                String lineOfText = scanner.nextLine().trim();
                if(!lineOfText.startsWith("//") && !lineOfText.equals(""))
                {
                	Scanner lineScanner = new Scanner(lineOfText);
                    lineScanner.useDelimiter(",");
                    String question = lineScanner.next().trim();
                    String answer = lineScanner.next().trim();
                    int points = lineScanner.nextInt();
                    
                    Question newQ = new Question(question, answer, points);
                    storeQuestion(newQ);
        	
                    lineScanner.close();
                }
            }
            scanner.close();
        }
        else
        {
            System.out.println("> You have not selected a file");
        }
    }
	
	//Method the takes an instance of a question, and then adds it to the ArrayList of all questions.
	public void storeQuestion(Question newQuestion)
	{
		questionList.add(newQuestion);
	}
	
	//Prints out a list of all Questions to the console.
	public void printAllQs()
	{
		for(Question question: questionList)
		{
			question.printQAP();
		}
	}

	//Method to print out a randomly selected question from the ArrayList to the console.
	//Doesn't currently pick a UNIQUE random Question - so repetition is likely.
	public void randomQuestion()
	{
		int pos = rand.nextInt(questionList.size());
		Question randomQ = questionList.get(pos);
		System.out.println(randomQ.getQuestion());
	}
	
	//Method to return the string value of a randomly selected question from the file.
	public String returnQuestion()
	{
		int pos = rand.nextInt(questionList.size());
		Question randomQ = questionList.get(pos);
		return randomQ.getQuestion() + " (" + randomQ.getPoints() + " Points)";
	}
}