package quizapplication;

public class Question {
    private  int questionNumber;
    private String question;
    private String options1;
    private String options2;
    private String options3;
    private String options4;
    private String answer;
    public Question(int id, String question, String opt1, String opt2, String opt3, String opt4, String answer) {
        this.questionNumber = id;
        this.question = question;
        this.options1 = opt1;
        this.options2 = opt2;
        this.options3 = opt3;
        this.options4 = opt4;
        this.answer = answer;
    }
    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setOptions1(String options1) {
        this.options1 = options1;
    }

    public void setOptions2(String options2) {
        this.options2 = options2;
    }

    public void setOptions3(String options3) {
        this.options3 = options3;
    }

    public void setOptions4(String options4) {
        this.options4 = options4;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString(){
        return this.questionNumber+"\n"+this.question+"\n"+options1+" "+options2+" "+options3+" "+options4;
    }

    public String getAnswer(){
        return this.answer;
    }

}
