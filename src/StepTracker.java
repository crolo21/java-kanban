import java.util.Scanner;

public class StepTracker {
    Scanner scanner;
    Converter converter;
    MonthData[] monthToData = new MonthData[12];
    int goalByStepsPerDay = 10000;

    StepTracker(Scanner scan, Converter converter) {
        this.scanner = scan;
        this.converter = converter;

        for (int i = 0; i < monthToData.length; i++) {
            monthToData[i] = new MonthData();
        }
    }

    void addNewNumberStepsPerDay() {
        System.out.println("Введите номер месяца от 1 до 12 (включительно).");
        int month = scanner.nextInt();
        if (month < 1 || month > 12) {
            System.out.println("Вы ввели некорректный месяц, нужно ввести от 1 до 12 (включительно). Вы ввели: " + month);
            return;
        }

        System.out.println("Введите день от 1 до 30 (включительно)");
        int day = scanner.nextInt();
        if (day < 1 || day > 30) {
            System.out.println("Вы ввели некорректный день, нужно ввести от 1 до 30 (включительно). Вы ввели: " + day);
            return;
        }

        System.out.println("Введите количество шагов");
        int steps = scanner.nextInt();
        if (steps < 0) {
            System.out.println("Количество шагов должно быть положительным! Вы ввели: " + steps);
            return;
        }

        MonthData monthData = monthToData[month - 1];
        monthData.days[day - 1] = steps;
    }

    void changeStepGoal() {
        System.out.println("Сейчас ваша цель " + goalByStepsPerDay + ". Какую цель вы хотите задать? Введите значение: ");
        int newGoalByStepsPerDay = scanner.nextInt();
        if (newGoalByStepsPerDay < 0) {
            System.out.println("Вы ввели некоректную цель, цель должна иметь значение > 0. Вы ввели :" + newGoalByStepsPerDay);
            return;
        } else {
            goalByStepsPerDay = newGoalByStepsPerDay;
            System.out.println("Ваша новая цель: " + goalByStepsPerDay);
        }
    }

    void printStatistic() {
        System.out.println("Введите номер месяца от 1 до 12 (включительно).");
        int month = scanner.nextInt();
        if (month < 1 || month > 12) {
            System.out.println("Вы ввели некорректный месяц, нужно ввести от 1 до 12 (включительно). Вы ввели: " + month);
            return;
        }
        MonthData monthData = monthToData[month - 1];
        int sumSteps = monthData.sumStepsFromMonth();
        monthData.printDaysAndStepsFromMonth();
        System.out.println("В этом месяце вы сделали :" + sumSteps + " шагов.");
        System.out.println("Максимальное количество шагов за месяц: " + monthData.maxSteps());
        System.out.println("В среднем за 30 дней вы сделали " + (sumSteps / 30) + " шагов.");
        System.out.println("В километрах вы прошли: " + converter.convertToKm(sumSteps));
        System.out.println("в килокалориях вы сожгли: " + converter.convertStepsToKilocalories(sumSteps));
        System.out.println("Лучшая серия за этот месяц: " + monthData.bestSeries(goalByStepsPerDay));
    }
}
