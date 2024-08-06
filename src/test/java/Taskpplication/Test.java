package Taskpplication;

/**
 * Temporary main class to test as we go.
 */
public class Test {
	
	public static void main(String[] args) {
		/* test leap year */
		System.out.println("-----Testing a leap year-----");
		Month month = new Month(3, 2024);
		Day[][] days = month.getMonth();
		for ( int i = 0; i < 6; i++ ) {
			for ( int j = 0; j < 7; j++ ) {
				System.out.println("	Week " + (i+1) + ", " + "Day " + (j+1) + ": " + days[i][j].return_date());
			}
		}
		
		System.out.println();

		/* test previous year */
		System.out.println("-----Testing month showing a previous year-----");
		month = new Month(1, 2024);
		days = month.getMonth();
		for ( int i = 0; i < 6; i++ ) {
			for ( int j = 0; j < 7; j++ ) {
				System.out.println("	Week " + (i+1) + ", " + "Day " + (j+1) + ": " + days[i][j].return_date());
			}
		}
	}
}