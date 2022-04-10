package com.jobready.threading;

public class ApplicationLecture36 {
	/**
	 * Multithread programs that we can run multi programs in the same time
	 * @param args
	 */
	public static void main(String args[]){
		
		System.out.println("Starting Thread 1");
		Task taskRunner = new Task("Thread-A");
		// other way to naming the thread
		//taskRunner.setName("Thread-A");
		//taskRunner.start();
		// other approach
		TaskImplementsRunnable taskRunnerOther = new TaskImplementsRunnable("Thread-Other-A"); // class TaskImplementsRunnable implements Runnable interface, not extends Thread
		Thread t1 = new Thread(taskRunnerOther); // buat objek Thread, lalu parameter yg dapat di include-kan adl objek yg mengimplemen Runnable
		t1.start();
		
		// taskRunner dan code di bawah berjalan bersamaan, namun karena taskRunner membutuhkan waktu utk di jalankan.
		// maka baris di bawah akan langsung di execute tanpa harus menunggu object taskRunner selesai
		//System.out.println("hello there..");
		
		// taskRunner.start(); // we can't use this run() more than once
		// instead we can do like this
		System.out.println("Starting Thread 2");
		Task taskRunner2 = new Task("Thread-B");
		//taskRunner2.setName("Thread-B");
		//taskRunner2.start();
		// result dr taskRunner2 ini akan bertabrakkan dgn taskRunner karena mereka di eksekusi bersamaan
		// other approach
		TaskImplementsRunnable taskRunnerOther2 = new TaskImplementsRunnable("Thread-Other-B"); // class TaskImplementsRunnable implements Runnable interface, not extends Thread
		Thread t2 = new Thread(taskRunnerOther2); // buat objek Thread, lalu parameter yg dapat di include-kan adl objek yg mengimplemen Runnable
		t2.start();
		
		// another approach, using anonymous class define with Runnable Interface
		Thread t3 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				for(int i=0; i < 1000; i++){
					System.out.println("number: " + i + " - " + Thread.currentThread().getName());
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}
		});
		t3.start();
		
		Thread t4 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				for(int i=0; i < 1000; i++){
					System.out.println("number (t4): " + i + " - " + Thread.currentThread().getName());
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}
		});
		t4.start();
		
		
		// own
		for (int i = 0; i<10; i++){
			//System.out.println("Hello there..." + i);
		}
		
		
	}
}

class Task extends Thread {
		
	String name;
	
	public Task(String name) {
		this.name = name;
	}

	public void run(){
		
		Thread.currentThread().setName(this.name);
		
		for(int i=0; i < 1000; i++){
			System.out.println("number: " + i + " - " + Thread.currentThread().getName());
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}

class TaskImplementsRunnable implements Runnable {
	
	String name;
	
	public TaskImplementsRunnable(String name) {
		this.name = name;
	}

	public void run(){
		
		Thread.currentThread().setName(this.name);
		
		for(int i=0; i < 1000; i++){
			System.out.println("number: " + i + " - " + Thread.currentThread().getName());
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
