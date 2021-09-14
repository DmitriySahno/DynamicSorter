import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        measure();
    }

    private static int[] sort(int[] arr) {
        if (arr.length <= 1000) { //для случаев с малым n используем пузырьковую сортировку.
            bubbleSort(arr);    //Сортировку выбором не используем, т.к. асимптотика у них одинаковая
        } else if (arr.length <= 100000) {
            quickSort(arr, 0, arr.length);
        } else { //для очень большого значения n также используем сортировку слиянием,
            return mergeSort(arr);      // т.к. в случае наихудшего случая для большого значения n длительность работы алгоритма будет ощутимо выше
        }
        return arr;
    }



    //Функция для измерения
    private static void measure() {
        int y = 10; //количество подмассивов, x - количество элементов в подмассиве
        for (int x = 10; x <= 1000000; x *= 10) {
            System.out.println("____"+x+":____");
            int[][] arrs = new int[y][x];
            for (int i = 0; i < y; i++) {
                for (int j = x - 1; j >= 0; j--) {
                    arrs[i][j] = j;
                }
            }
            for (int i = 0; i < y; i++) {
                long startTime = System.currentTimeMillis();
                sort(arrs[i]);
//                bubbleSort(arrs[i]);
//                insertionSort(arrs[i]);
//                choosingSort(arrs[i]);
//                quickSort(arrs[i], 0, x);
//                mergeSort(arrs[i]);
//                System.out.println(Arrays.toString(sort(arrs[i])));
//                System.out.println(Arrays.toString(arrs[i]));
                long endTime = System.currentTimeMillis();
                long delta = endTime - startTime;
                System.out.println(delta);
            }
        }
    }

    //Быстрая сортировка
    private static void quickSort(int[] arr, int low, int high) {

        if (arr.length == 0) {
            return;
        }

        if (low >= high - 1) {
            return;
        }
        int l = low;
        int r = high - 1;

        int pivot = arr[l + (r - low) / 2];

        while (l <= r) {
            while (arr[l] < pivot) {
                l++;
            }

            while (arr[r] > pivot) {
                r--;
            }

            if (l < r) {
                int temp = arr[l];
                arr[l] = arr[r];
                arr[r] = temp;
                l++;
                r--;
            } else break;
        }
        if (low < l)
            quickSort(arr, low, l);
        if ((high) > r)
            quickSort(arr, r, high - 1);
    }

    // Сортировка слиянием
    private static int[] mergeSort(int[] arr) {
        if (arr.length < 2)
            return arr;
        int pivot = arr.length / 2;
        int[] left = new int[pivot];
        int[] right = new int[arr.length - pivot];
        int i = 0, j = 0;

        for (int k = 0; k < arr.length; k++) {
            if (k < pivot) {
                left[i] = arr[k];
                i++;
            } else {
                right[j] = arr[k];
                j++;
            }
        }

        return merge(sort(left), sort(right));
    }

    // Сортировка слиянием (доп функция)
    private static int[] merge(int[] a, int[] b) {
        int i = 0, j = 0, r = 0;
        int[] result = new int[a.length + b.length];

        while (i < a.length || j < b.length) {
            if (j == b.length || i != a.length && a[i] < b[j]) {
                result[r] = a[i];
                i++;
            } else {
                result[r] = b[j];
                j++;
            }
            r++;
        }

        return result;
    }

    // Сортировка пузырьком
    private static void bubbleSort(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    //Сортировка вставкой
    private static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
            }
        }
    }

    /*
    Выводы: Хоть у некоторых неквадратичных алгоритмов сортировки асимптотика для худшего случая равна O(n^2),
    время выполнения для худшего случая неквадратичных алгоритмов гораздо меньше квадратичных.
    По моим замерам с массивами длинной менее 10 тысяч представленные алгоритмы сортировки справляются одинаково хорошо,
    однако с массивами свыше 10 тысяч заметно экспоненциальное увеличение длительности обработки квадратичные алгоритмы.
    Тем не менее для массивов с малой длинной я решил использовать квадратичные функции основываясь на утверждении, что
    "сортировки, работающие за асимптотическое время , часто скрывают в асимптотике константу, из-за которой при малых
    они могут проигрывать по времени квадратичным сортировкам"
    */


}
