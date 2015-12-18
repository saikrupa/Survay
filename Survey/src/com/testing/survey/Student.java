package com.testing.survey;

import java.io.Serializable;

import android.util.Log;

public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

      
    private String[] Question=new String[7];
    
    private static int QuestionNumber=0;
    
    private static int QuestionTelugu=1;
    
    private static int Answer1=2;
    
    private static int Answer2=3;
    
    private static int Answer3=4;
    
    private static int Answer4=5;
    
    private static int Answer5=6;
    
    private int radioButtonId=0;
    
    private boolean isSelected;
    
    private String final_answer;


	public Student() {

    }
    public Student(String[] questionArray) {
    	
    	Question=questionArray;
    	
    	Log.v("string", questionArray[0]);
    	Log.v("question", Question[0]);
    	
    }

    public String getQuestionNumber() {
        return Question[QuestionNumber];
    }

    public String getQuestion() {
        return Question[QuestionTelugu];
    }

    public String getAnswer1() {
        return Question[Answer1];
    }
    
    public String getAnswer2() {
        return Question[Answer2];
    }
    
    public String getAnswer3() {
        return Question[Answer3];
    }
    
    public String getAnswer4() {
        return Question[Answer4];
    }
    
    public String getAnswer5() {
        return Question[Answer5];
    }

    public void putQuestionNumber(String value) {
        
    	this.Question[QuestionNumber]=value;
    	
    }

    public void putQuestion(String value) {

    	this.Question[QuestionTelugu]=value;
    }

    public void putAnswer1(String value) {
        
    	this.Question[Answer1]=value;
    }
    
    public void putAnswer2(String value) {
        
    	this.Question[Answer2]=value;
    	
    }
    
    public void putAnswer3(String value) {
        
    	this.Question[Answer3]=value;
    }
    
    public void putAnswer4(String value) {
        
    	this.Question[Answer4]=value;
        
    }
    
    public void putAnswer5(String value) {
        
    	this.Question[Answer5]=value;
    }
    public boolean isSelected() {
    	  
    	return isSelected;
    	 
    }
    public void setSelected(boolean isSelected) {
    	  
    	this.isSelected = isSelected;
    	 
    }
    
    public void setSelectedRadioButtonId(int radioButtonId) {
		
    	this.radioButtonId=radioButtonId;
    	
	}
    public int getSelectedRadioButtonId() {
		
    	return radioButtonId;
    	
	}
    public String getFinalAnswer(){
      return final_answer;
    }

    public void setFinalAnswer(String final_answer){
      this.final_answer = final_answer;
    }
	
    
}
