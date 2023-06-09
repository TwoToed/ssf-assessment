package vttp2023.batch3.ssf.frontcontroller.model;

public class Captcha {
    private String question;
    private int answer;
    
    public Captcha(String question, int answer) {
        this.question = question;
        this.answer = answer;
    }
    public String getquestion() {
        return question;
    }
    public void setquestion(String question) {
        this.question = question;
    }
    public int getAnswer() {
        return answer;
    }
    public void setAnswer(int answer) {
        this.answer = answer;
    }
    @Override
    public String toString() {
        return "Captcha [question=" + question + ", answer=" + answer + "]";
    }

    
}
