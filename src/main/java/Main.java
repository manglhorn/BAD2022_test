import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        /*
        Приветсвую, задачу удалось выполнить, хотя и получилось сыровато, хотелось бы добавить JUnit тесты,
        дабы продемонстрировать мои знания :) и также заюзать фичи из Java 8,
        НО так как времени было катастрофически мало, пришлось здесь по обычному.
        */

        /*
        1. Создаем баффер ридер и считываем данные с файла, закрываем ресурсы, перекидываем все данные в динамичный массив.
        */
        List<String> listOfStrings = new ArrayList<String>();
        BufferedReader bf = new BufferedReader(
                new FileReader("C:\\Users\\vlads\\Sysa_test_bac2022\\src\\numbers.txt"));
        String line = bf.readLine();
        while (line != null) {
            listOfStrings.add(line);
            line = bf.readLine();
        }
        bf.close();

        String[] originalArray
                = listOfStrings.toArray(new String[0]);
        String[] array
                = listOfStrings.toArray(new String[0]);

        /*
        2. Здесь все красивенько выводим с отдельных методов каждое арифметическое значение.
        */
        System.out.println("Max number: " + getMaxNumber(array));
        System.out.println("Min number: " + getMinNumber(array));
        System.out.println("Median: " + getMedian(array));
        System.out.println("Arithmetic mean: " + getArithmeticMean(array));
        System.out.println("*Max sequence: " + getSeqMax(originalArray));
        System.out.println("*Min sequence: " + getSeqMin(originalArray));
    }

    /*
     3. Легко находим среднеее арифтетическое.
    */
    public static int getArithmeticMean(String[] array) {
        int sum = 0;
        int countOfElements = 0;
        for (String str : array) {
            countOfElements++;
            sum += Integer.parseInt(str);
        }
        int newSum = sum / countOfElements;
        return newSum;
    }

    /*
        4. Получаем медиану: если количество цифр из файла четное - то одна логика:
        1) находим длинну массива с полученными данными из файла
        2) делим на 2, затем опять берем массив и опять делим на два, только отнимаем минус 1 со второго значения,
         суммируем их и делим на два --> вуаля.
         3) в случае нечетного числа просто делим на два длинну массива и получаем число.
    */
    public static int getMedian(String[] array) {
        int totalElements = array.length;
        int median = 0;
        if (totalElements % 2 == 0) {
            int sumOfMiddleElements = Integer.parseInt(array[totalElements / 2] +
                    Integer.parseInt(array[totalElements / 2 - 1]));
            median = sumOfMiddleElements / 2;
        } else {
            median = Integer.parseInt(array[Integer.parseInt(String.valueOf(array.length / 2))]);
        }
        return median;
    }

    /*
    5. Легко находим минимальное число :)
    */
    public static int getMinNumber(String[] array) {
        int min = 0;
        for (String str : array) {
            if (min > Integer.parseInt(str)) {
                min = Integer.parseInt(str);
            }
        }
        return min;
    }

    /*
    6. Легко находим максимальное число :)
    */
    public static int getMaxNumber(String[] array) {
        int max = 0;
        for (String str : array) {
            if (max < Integer.parseInt(str)) {
                max = Integer.parseInt(str);
            }
        }
        return max;
    }

    /*
    7. Самая сложная часть: создаем два динимических массива-коллекции, объявляем акутальную переменную и следующую за ней,
    если следующая за ней меньше, то вуаля добавляем число в нашу коллекцию. Если же нет, то добавляем проверку на размеры массивов,
    в случае если проверочный массив находит необхоимые числа то присваем их к нашему главному массиву.
    */
    public static List<Integer> getSeqMin(String[] originalArray) {
        List<Integer> longestMin = new ArrayList<Integer>();
        List<Integer> currentMin = new ArrayList<Integer>();
        int currentElement = 0;
        int nextElement = 0;
        currentMin.add(Integer.parseInt(originalArray[0]));
        for (int i = 0; i < originalArray.length - 1; i++) {
            currentElement = Integer.parseInt(originalArray[i]);
            nextElement = Integer.parseInt(originalArray[i + 1]);
            if (nextElement < currentElement) {
                currentMin.add(nextElement);
            } else {
                if (currentMin.size() > longestMin.size()) {
                    longestMin.clear();
                    longestMin.addAll(currentMin);
                }
                currentMin.clear();
                currentMin.add(Integer.parseInt(originalArray[i + 1]));
            }
        }
        return longestMin;
    }

    /*
    8. Та же логика, что и в прошлом, только следующий элемент в массиве должен быть больше предыдущего.
    */
    public static List<Integer> getSeqMax(String[] originalArray) {
        List<Integer> longestMax = new ArrayList<Integer>();
        List<Integer> currentMax = new ArrayList<Integer>();
        int currentElement = 0;
        int nextElement = 0;
        currentMax.add(Integer.parseInt(originalArray[0]));
        for (int i = 0; i < originalArray.length - 1; i++) {
            currentElement = Integer.parseInt(originalArray[i]);
            nextElement = Integer.parseInt(originalArray[i + 1]);
            if (nextElement > currentElement) {
                currentMax.add(nextElement);
            } else {
                if (currentMax.size() > longestMax.size()) {
                    longestMax.clear();
                    longestMax.addAll(currentMax);
                }
                currentMax.clear();
                currentMax.add(Integer.parseInt(originalArray[i + 1]));
            }
        }
        return longestMax;
    }
}
