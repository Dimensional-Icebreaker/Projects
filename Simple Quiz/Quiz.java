import java.util.ArrayList;
import java.util.Scanner;
import java.lang.*;
public class Quiz{
    private ArrayList<Question> questions=new ArrayList<Question>();
    public Quiz(){
        loadQuiz();
    }
    private void loadQuiz(){
        Question questionOne =new Question("What is blueprint for a software object in Java? ",new String[] {"class","interface","API","encapsulation"},"class");
        questions.add(questionOne);
        Question questionTwo =new Question("A software object's state is stored in ? ",new String[]{"methods","superclass","subclass","fields"},"fields" );
        questions.add(questionTwo);
        Question questionThree =new Question("Which data type is used to create a variable that should store text? ",new String[]{"string","STRING","String","txt"},"String" );
        questions.add(questionThree);
        Question questionFour =new Question("Which method can be used to find the length of a array? ? ",new String[]{"array.sizeof()","array.strlen()","array.subclass","array.length"},"array.length" );
        questions.add(questionFour);
        Question questionFive =new Question("Which operator can be used to compare two values? ",new String[]{"<<","==","=","||"},"==");
        questions.add(questionFive);
        
    }
    public double takeQuiz(){
        double correctAnswer=0;
        for(Question question: questions){
            System.out.println(question);
            Scanner scanner = new Scanner(System.in);
            System.out.print("What's your answer ? \n");
            String input = scanner.nextLine();
            if (question.checkAnswer(Integer.parseInt(input))){
                correctAnswer++;
            }
        }
        return correctAnswer/questions.size();
    }
}