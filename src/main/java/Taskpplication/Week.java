package Taskpplication;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.time.DayOfWeek;

public class Week {
    private final LocalDate start;
    private final LocalDate end;

    public Week(LocalDate date) {
        this.start = date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        this.end = date.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public Day[] getAllDays() {
        Day[] days = new Day[7];
        for (int i = 0; i < 7; i++) {
            days[i] = new Day(start.plusDays(i));
        }
        return days;
    }
}

