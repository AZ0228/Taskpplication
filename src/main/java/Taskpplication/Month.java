package Taskpplication;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import Taskpplication.Database.*;

/**
 * The Month class represents all the days in
 * a specific month and year.
 * @version 1.0
 */
public class Month {
	
	private Day[][] days;
	private int month_number;
	private int year;
	private int[][] numTasks;
	private TaskDAO dao;
	
	/**
	 * Default Month constructor. Fails to construct
	 * if inputs contain an invalid value.
	 * @param mi	this Month's month_number
	 * @param y		this Month's year
	 */
	public Month(int mi, int y) {
		if ( mi < 1 || mi > 12 || y < 0 ) {
			return;
		}

		dao = new TaskDAO();
		
		month_number = mi;
		year = y;
		days = new Day[6][7];
		numTasks = new int[6][7];
		/*
		 * k is day (1 to 31)
		 * m is month (1 = March, ..., 10 = December, 11 = Jan, 12 = Feb)
		 * 	Treat Jan & Feb as months of the preceding year
		 * C is century (1987 has C = 19)
		 * Y is year (1987 has Y = 87 except Y = 86 for Jan & Feb)
		 * W is week day (0 = Sunday, ..., 6 = Saturday)
		 * 
		 * Source: https://cs.uwaterloo.ca/~alopez-o/math-faq/node73.html
		 */
		int k = 1;
		int m = month_number > 2 ? month_number - 2 : month_number + 10;
		int C = month_number > 2 ? year / 100 : (year-1) / 100;
		int Y = month_number > 2 ? year % 100 : (year-1) % 100;
		int W = (k + (int)Math.floor(2.6*m - 0.2) -(2*C) + Y +(int)Math.floor(Y/4) + (int)Math.floor(C/4) + 7) % 7;
		
//		System.out.println(W);
//		System.out.println(m);
		int day = 1 - W;
		int prev_month = month_number == 1 ? 12 : month_number - 1;
		int prev_year = month_number == 1 ? year - 1 : year;
		int next_month = month_number == 12 ? 1 : month_number + 1;
		int next_year = month_number == 12 ? year + 1 : year;
		int prev_month_size = monthSize(prev_month, prev_year);
		int month_size = monthSize(month_number, year);
		
		for ( int i = 0; i < 6; i++ ) {
			days[i] = new Day[7];
			for ( int j = 0; j < 7; j++ ) {
				boolean outsideMonth = false;
				LocalDate ld;
				if ( day > 0 && day <= month_size ) {
					ld = LocalDate.of(year, month_number, day);
				}
				else if ( day > month_size ) {
					ld = LocalDate.of(next_year, next_month, day - month_size);
					outsideMonth = true;
				}
				else {
					ld = LocalDate.of(prev_year, prev_month, prev_month_size + day);
					outsideMonth = true;
				}
				days[i][j] = new Day(ld);
				days[i][j].setOutsideMonth(outsideMonth);
				day++;
			}
		}

		refreshTaskNum();
	}

	public void refreshTaskNum(){
		this.numTasks = new int[6][7];
		List<Task> tasksThisMonth = dao.getTasksForMonth(days[0][0].return_date(), days[5][6].return_date());
		for (Task task : tasksThisMonth) {
			LocalDate taskDate = task.getDateTime().toLocalDate();
			long daysDifference = ChronoUnit.DAYS.between(days[0][0].return_date(), taskDate);

			int x = (int) daysDifference / 7;  // Row index
			int y = (int) daysDifference % 7;  // Column index

			if (x >= 0 && x < days.length && y >= 0 && y < days[x].length) {
				Day day = days[x][y];
				numTasks[x][y]++;

			}
		}
	}
	
	public int getMonthNumber()
	{
		return month_number;
	}
	/**
	 * Returns the number of days in the month and year given.
	 * @param m		month number, January = 1, December = 12
	 * @param y		year
	 * @return		the number of days in the month and year given.
	 */
	public static int monthSize(int m, int y) {
		switch(m) {
			case 1:	return 31;
			case 2:	/* 
					 * check if leap year:
					 * 	divisible by four
					 * 	if end of century year
					 * 		must also be divisible by 400
					 */
					if ( (y % 4 != 0) || (y % 100 == 0 && y % 400 != 0) ) {
						return 28;
					}
					return 29;
			case 3:	return 31;
			case 4:	return 30;
			case 5:	return 31;
			case 6:	return 30;
			case 7:	return 31;
			case 8:	return 31;
			case 9:	return 30;
			case 10:	return 31;
			case 11:	return 30;
			case 12:	return 31;
			default:	return 0;
		}
	}
	
	/**
	 * Returns days.
	 * @return	days
	 */
	public Day[][] getMonth() {
		return days;
	}

	public int[][] getNumTasks() { return numTasks; }
	
	/**
	 * Returns the week that the date is in,
	 * if it is not in this month returns an
	 * empty Day array of size 7.
	 * @param date	the date to search for.
	 * @return		the week of date.
	 */
	public Day[] getWeek(LocalDate date) {
		for ( int i = 0; i < 6; i++ ) {
			for ( int j = 0; j < 7; j++ ) {
				if ( days[i][j].return_date().equals(date) ) {
					return days[i];
				}
			}
		}
		return new Day[7];
	}

	public Day[] getWeek(int index) {
		if( index < 0 || index > 7 ) {
			return null;
		}
		return days[index];
	}


}