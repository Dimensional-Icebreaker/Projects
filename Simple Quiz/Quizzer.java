public class Quizzer {
    public static void main(String[] args) {
        try {
            Quiz quiz=new Quiz();
            System.out.println("Score(0 to 100): "+quiz.takeQuiz()*100+"%");

          }
          catch(IllegalArgumentException e) {
            System.err.println("Invalid parameters found!");
          }
    }
    
}