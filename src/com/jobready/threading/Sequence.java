package com.jobready.threading;

public class Sequence {
	private int value = 0;
	///public int getNext(){
		//value++;
		/*
		 * Some of illustration for thread,
		 * jadi misalnya thread 1 sudah membaca sampai koding line 6 (anggap aja nilai increment valuenya adl 4) lalu processor suruh hold for a few seconds, 
		 * lalu thread 2 membaca line 6 namun processor membiarkan dia membaca sampai line return value dan current value nya adl 4
		 * lalu di print 4 oleh thread 2 lalu processor suruh thread 2 pause for a few seconds, lalu processor meminta lg thread 1 untuk
		 * melanjutkan proses yg td terhenti di line 6 dan ke return value, maka nilai current value nya jg 4,
		 * maka thread 1 dan thread 2 mem-print value yg sama. This is not a threads safe
		 * */
		//return value;
		
		// we can define a synchronized block
		//synchronized (this) { // 'this' adl objek dari instance Sequence
			//value++;
			//return value;
			// maka tidak akan ada multiple / duplicate value di setiap thread nya,
			// karena jika masuk sini, maka akan memblock thread hingga ini selesai.
			// nilai dr antara thread 1 dan 2 ada kemungkinan terbalik, misal thread 1 print value 4 dan thread 2 print value 3.
		//}
	///}
	
	
	// another way, using synchronized signature to method
	public synchronized int getNext(){
		value++;
		return value;
	}
}
