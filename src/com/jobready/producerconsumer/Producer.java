package com.jobready.producerconsumer;

import java.util.List;
//this thread/task/class is gonna produce and read the questions
public class Producer implements Runnable {
	
	List<Integer> questionList = null;
	final int LIMIT = 5;
	private int questionNo;
	
	public Producer(List<Integer> questionList){
		this.questionList = questionList;
	}
	
	public void readQuestion(int questionNo) throws InterruptedException {
		// wait until questionList is available to produce
		synchronized (questionList) {
			while (questionList.size() == LIMIT) {
				System.out.println("Questions have piled up.. wait for answers");
				questionList.wait(); // wait & notify can only be called in synchronized block. wait until consumer remove the list of question in 
				// questionList collection
				// wait() --> the current thread to wait until it is awakened, typically by being notified or interrupted
				// get away from this wait() as possible since it is a primitive
			}
		}
		
		// produce and read questions
		synchronized (questionList) {
			System.out.println("New Question: " + questionNo);
			questionList.add(questionNo);
			Thread.sleep(100);
			questionList.notify(); // wait & notify can only be called in synchronized block
		}
	}
	
	@Override
	public void run() {
		
		while (true) {
			try {
				readQuestion(questionNo++);
			} catch (InterruptedException e) {
			}
		}
		
	}

}
