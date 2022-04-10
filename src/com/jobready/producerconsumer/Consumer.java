package com.jobready.producerconsumer;

import java.util.List;
// this thread/task/class is gonna answer / remove the questions
public class Consumer implements Runnable {
	
	List<Integer> questionList = null;
	
	public Consumer(List<Integer> questionList){
		this.questionList = questionList;
	}
	
	public void answerQuestion() throws InterruptedException {
		// wait until questionList is available
		synchronized (questionList) {
			while (questionList.isEmpty()) {
				System.out.println("No Question to Answer... Waiting for producer to get questions");
				questionList.wait(); 
			}
		}
		
		// answer and remove questions
		synchronized (questionList) {
			Thread.sleep(5000);
			System.out.println("ANSWERED Question: " + questionList.remove(0));
			questionList.notify(); 
		}
	}
	
	@Override
	public void run() {
		
		while (true) {
			try {
				answerQuestion();
			} catch (InterruptedException e) {
			}
		}
		
	}

}
