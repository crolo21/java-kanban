//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Converter converter = new Converter();
        StepTracker tracker = new StepTracker(scanner, converter);


        while (true) {
            printMenu();
            int actionNumber = scanner.nextInt();

            if (actionNumber == 1) {
                tracker.addNewNumberStepsPerDay();
            } else if (actionNumber == 2) {
                tracker.changeStepGoal();
            } else if (actionNumber == 3) {
                tracker.printStatistic();
            } else if (actionNumber == 4) {
                System.out.println("Выход.");
                break;
            } else {
                System.out.println("Извините, такой команды нет.");
            }
        }
    }

    public static void printMenu() {

        String[] dots = new String[50];

        for (int i = 0; i < dots.length; i++) {
            dots[i] = "=";
            System.out.print(dots[i]);
        }
        System.out.println();
        System.out.println("Выберите одну из команд:");
        System.out.println("1. Ввести количество шагов за определённый день.");
        System.out.println("2. Изменить цель по количеству шагов в день.");
        System.out.println("3. Напечатать статистику за определённый месяц.");
        System.out.println("4. Выйти из приложения.");

        for (int i = 0; i < dots.length; i++) {
            dots[i] = "=";
            System.out.print(dots[i]);
        }
        System.out.println();
    }
}
