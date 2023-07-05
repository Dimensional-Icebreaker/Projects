import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class Question{
    private String question;
    private String[] answers;
    private String rightAnswer; 
    private static int nextQuestionNumber=1;
    private final int questionNumber;
    
    public  Question(String question,String[] answers,String rightAnswer ){
        this.question=question;
        this.answers=answers;

        List<String> list = Arrays.asList(answers);
        Collections.shuffle(list);
        list.toArray(answers);

        this.rightAnswer=rightAnswer;
        questionNumber=nextQuestionNumber++;
    }
    public boolean checkAnswer(int answer){
        if(answers[answer-1].equals(rightAnswer)){
            return true;
        }
        else{
            return false;
        }
        
        
    }
    public String toString() {
        return questionNumber+". "+question+"\n\t1) "+answers[0]+"\n\t2) "+answers[1]+"\n\t3) "+answers[2]+"\n\t4) "+answers[3];
    }
}