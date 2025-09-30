package quizapplication;

import java.util.Scanner;

public class QuestionServices {
    Question[] questions=new Question[5];
    String[] userResponse=new String[5];
    Scanner Sc=new Scanner(System.in);
    public  QuestionServices(){
        questions[0] = new Question(1, "size of int", "2", "6", "4", "8", "4");
        questions[1] = new Question(2, "size of double", "2", "6", "4", "8", "8");
        questions[2] = new Question(3, "size of char", "2", "6", "4", "8", "2");
        questions[3] = new Question(4, "size of long", "2", "6", "4", "8", "8");
        questions[4] = new Question(5, "size of boolean", "1", "2", "4", "8", "1");
    }
    public void playQuiz(){
        int i=0;
        for(Question q:questions){
            System.out.println(q.toString());
            System.out.println("Enter Your Answer \t");
            String res=Sc.next();
            userResponse[i++]=res;
        }
    }

    public void showScore(){
        int score=0;
        for(int i=0;i<questions.length;i++){
            if(questions[i].getAnswer().equalsIgnoreCase(userResponse[i])){
                score+=1;
            }
        }
        System.out.println("Total Score Is \t"+score);
    }
}
