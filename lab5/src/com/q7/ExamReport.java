package com.q7;

import java.util.Scanner;

class Question
{
	int number;
	char answer;
	char StudentAnswer;
	String Result;
}

class QuestionPaper
{
	Question questions[]=new Question[8];
	int correct=0;
	int wrong=0;

	QuestionPaper()
	{
		for(int i=0;i<8;i++)
		{
			questions[i]=new Question();
		}
		for(int i=0;i<8;i++)
		{
			questions[i].number=i+1;
		}
		questions[0].answer='C';
		questions[1].answer='A';
		questions[2].answer='B';
		questions[3].answer='D';
		questions[4].answer='B';
		questions[5].answer='C';
		questions[6].answer='C';
		questions[7].answer='A';
	}

	void Result(char StudentAnswer[])
	{
		for(int i=0;i<8;i++)
		{
			questions[i].StudentAnswer=StudentAnswer[i];
			if(questions[i].answer==questions[i].StudentAnswer)
			{
				questions[i].Result="Correct";
				correct++;
			}
			else if(questions[i].StudentAnswer=='X')
			{
				questions[i].Result="Unmarked";
			}
			else
			{
				questions[i].Result="Wrong";
				wrong++;
			}
		}	
	}
}



public class ExamReport {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		QuestionPaper qp=new QuestionPaper();

		char StudentAnswer[]=new char[8];
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the Student Answers:");
		for(int i=0;i<8;i++)
		{
			StudentAnswer[i]=sc.next().charAt(0);
		}
		qp.Result(StudentAnswer);
		System.out.println("Question No.\tCorrect Answer\tStudent Answer\tResult");
		for(int i=0;i<8;i++)
		{
			System.out.println(qp.questions[i].number+"\t\t\t"+qp.questions[i].answer+"\t\t"+qp.questions[i].StudentAnswer+"\t\t"+qp.questions[i].Result);
		}
		System.out.println("Total Correct Answers:"+qp.correct);
		System.out.println("Total Wrong Answers:"+qp.wrong);
		System.out.println("Unmarked Questions:"+(8-(qp.correct+qp.wrong)));
	}

}
