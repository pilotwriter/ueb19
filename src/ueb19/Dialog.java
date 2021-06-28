package ueb19;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Dialog {
	public void printOptions() {
		System.out.println("-1 - EXIT ");

		System.out.println("1 - Get size ");
		System.out.println("2- isEmpty ");
		System.out.println("3 - contains ");
		System.out.println("4 - toArray ");
		System.out.println("5 - add ");
		System.out.println("6 - remove ");
		System.out.println("7 - addAll ");
		System.out.println("8 - clear ");
		System.out.println("9 - get ");
		System.out.println("10 - set ");
		System.out.println("11 - add ");
		System.out.println("12 - remove ");
		System.out.println("13 - indexOf ");
	}

	public DoppeltVerketteteListe<String> getList() {
		return new DoppeltVerketteteListe<String>();
	}

	public Scanner getScanner() {
		return new Scanner(System.in);
	}

	public boolean isInputValid(int choice) {
		if (choice <= -2 || choice >= 14) {
			return false;
		}
		return true;
	}

	public void callCorrespondingFunction(int choice, DoppeltVerketteteListe<String> doubleLinkedList) {
		if (choice == 1) {
			printSizeOfLinkedList(doubleLinkedList);
		} else if (choice == 2) {
			printIsEmpty(doubleLinkedList);

		} else if (choice == 3) {
			System.out.println("Please write the String that you are searching for");
			String searchWord = getScanner().nextLine().toString();
			printContainsObject(doubleLinkedList, searchWord);

		} else if (choice == 4) {
			transformToArray(doubleLinkedList);
		} else if (choice == 5) {
			addElementToList(doubleLinkedList);
			doubleLinkedList.printList();

		} else if (choice == 6) {
			removeFromList(doubleLinkedList);
			doubleLinkedList.printList();

		} else if (choice == 7) {
			addAllToList(doubleLinkedList);
			doubleLinkedList.printList();

		} else if (choice == 8) {
			doubleLinkedList.clear();
			doubleLinkedList.printList();
		} else if (choice == 9) {
			System.out.println("Please give an index");
			int index = getScanner().nextInt();
			System.out.println("The item is " + doubleLinkedList.get(index));
		} else if (choice == 10) {
			System.out.println("Please give an index");
			int index = getScanner().nextInt();
			System.out.println("Please give an string");
			String replacementWord = getScanner().nextLine();
			doubleLinkedList.set(index, replacementWord);
			doubleLinkedList.printList();
		} else if (choice == 11) {
			addItemToIndex(doubleLinkedList);
			doubleLinkedList.printList();
		} else if (choice == 12) {
			removeFromIndex(doubleLinkedList);
			doubleLinkedList.printList();
		} else if (choice == 13) {
			getIndexOf(doubleLinkedList);
		}

	}

	public void transformToArray(List<String> doubleLinkedList){
		String[] words = new String[5];
		words[0] = "j";
		words[1] = "k";
		words[2] = "l";
		words[3] = "m";
		words[4] = "n";
		String [] myarr = doubleLinkedList.toArray(words);
		System.out.println(Arrays.toString(myarr));
		
	}

	public void addAllToList(List<String> doubleLinkedList) {
		System.out.println("Please write the inputs with whitespace between");
		String word = getScanner().nextLine();
		String[] words = getTokenizedWords(word);
		doubleLinkedList.addAll(Arrays.asList(words));
	}

	public String[] getTokenizedWords(String word) {
		return word.split(" ");
	}

	public void getIndexOf(List<String> doubleLinkedList) {
		System.out.println("Please give a string");
		String searchWord = getScanner().nextLine();
		System.out.println("The index of searched string is :" + doubleLinkedList.indexOf(searchWord));
	}

	public void removeFromIndex(List<String> doubleLinkedList) {
		System.out.println("Please give an index");
		int index = getScanner().nextInt();
		doubleLinkedList.remove(index);
	}

	public void addItemToIndex(List<String> doubleLinkedList) {
		System.out.println("Please give an index");
		int index = getScanner().nextInt();
		System.out.println("Please give a string");
		String additionalWord = getScanner().nextLine();
		doubleLinkedList.add(index, additionalWord);

	}

	public void removeFromList(List<String> doubleLinkedList) {
		System.out.println("Please give an string to remove");
		String wordToRemove = getScanner().nextLine();
		doubleLinkedList.remove(wordToRemove);
	}

	public void addElementToList(List<String> doubleLinkedList) {
		System.out.println("Please give an string to add");
		String givenWord = getScanner().nextLine();
		doubleLinkedList.add(givenWord);
	}

	public void printIsEmpty(List<String> doubleLinkedList) {
		System.out.println("Is this list is empty ? " + doubleLinkedList.isEmpty());
	}

	public void printContainsObject(List<String> doubleLinkedList, String searchWord) {
		System.out.println("The answer to this search querry is : " + doubleLinkedList.contains(searchWord));
	}

	public void printSizeOfLinkedList(List<String> doubleLinkedList) {
		System.out.println("The size of the list is : " + doubleLinkedList.size());
	}

	public void startDialog() {
		DoppeltVerketteteListe<String> doubleLinkedList = getList();
		Scanner integerScanner = getScanner();
		System.out.println("Double Linked list is created");
		int choice = 0;
		while (choice != -1) {
			System.out.println("Please choose and option");
			printOptions();
			choice = integerScanner.nextInt();
			if (isInputValid(choice)) {
				callCorrespondingFunction(choice, doubleLinkedList);
			}
		}
	}

}
