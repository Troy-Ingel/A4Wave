package mainGame;

public class UnorderedList {
	// Author: Michael Woo
	/*
	 * Instance Variables
	 */
	private int numItems;
	private int[] levels;

	/*
	 * Constructors
	 */
	public UnorderedList(int size) {
		levels = new int[size];
		numItems = 0;

	}

	/*
	 * Instance methods
	 */
	// Checks if the list is empty
	public boolean isEmpty() {
		return numItems == 0;
	}

	// Returns the number of items in the list
	public int size() {
		return numItems;
	}

	// Adds an element to the list
	public void addItem(int newItem) {
		levels[numItems] = newItem;
		numItems += 1;
	}

	// Removes an element from the list
	public void removeItem(int index) {
		for (int i = index; i < numItems; i++) {
<<<<<<< HEAD
			levels[i] = levels[i + 1];
=======
			levels[i] = levels[i + index];
>>>>>>> master

		}
		numItems--;
	}

	// Removes all elements from the list
	public void removeAll() {
		numItems = 0;
	}

	// Gives the position of an element according to the index.
	public int getItem(int indexnum) {
		return levels[indexnum];
	}

	// Prints out the elements in the list
	public void display() {
		for (int i = 0; i < numItems; i++)
			System.out.print(levels[i] + " ");
		System.out.println(" ");

		if (numItems == 0) {
			System.out.println("List is empty");
		}
	}
}
