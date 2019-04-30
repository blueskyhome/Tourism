package com.example.tourism.tools;

import cn.bmob.v3.BmobObject;

public class Question extends BmobObject{
    private String thing_name;
    private String type;
    private String question;
    private String answer;
    private Boolean isAnswer;

    public void setThing_name(String thing_name) {
        this.thing_name = thing_name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setIsAnswer(Boolean answer) {
        isAnswer = answer;
    }

    @Override
    public String getObjectId() {
        return super.getObjectId();
    }

    public String getThing_name() {
        return thing_name;
    }

    public String getType() {
        return type;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public Boolean getIsAnswer() {
        return isAnswer;
    }
}
