package persistence;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import domain.QuizList;
import domain.impl.QuizListImpl;

public class QuizListIO {

	private static final String FILENAME = "quizlist.ser";

	public static QuizList loadQuizList() {
		File file = new File(FILENAME);
		if (!file.isFile()) {
			try {
				file.createNewFile();
				System.out.println(FILENAME + " created.");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		} else {

			ObjectInputStream d = null;
			try {
				d = new ObjectInputStream(new BufferedInputStream(
						new FileInputStream(FILENAME)));
			} catch (FileNotFoundException e) {
				System.out.println("");
				System.out.print("File " + FILENAME + " not found. ");
			} catch (EOFException e) {
				System.out.println("");
				System.out.print("File " + FILENAME + " empty or corrupt. ");
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (d == null) {
				System.out.println("Nothing loaded.");
				return null;
			} else {
				try {
					d.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				QuizList quizList = null;
				try {
					quizList = (QuizList) d.readObject();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				return quizList;
			} 
		}
	}
	
	public static void saveQuizList() {
		QuizList quizList = QuizListImpl.getInstance();
		ObjectOutputStream encode = null;
	    try {
	        encode = new ObjectOutputStream(
	                new BufferedOutputStream(
	                        new FileOutputStream(FILENAME)));
	    } catch (FileNotFoundException e) {
	        System.err.println("encoding... " + e);
	    } catch (IOException e1) {
	        e1.printStackTrace();
	    }
	    try {
	        encode.writeObject(quizList);
	        System.out.println("Saved Quiz List.");
	    } catch (IOException e2) {
	        e2.printStackTrace();
	    }
	    try {
	        encode.close();
	    } catch (IOException e3) {
	        e3.printStackTrace();
	    }
	}

}
