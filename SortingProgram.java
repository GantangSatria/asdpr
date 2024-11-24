import java.util.ArrayList;
import java.util.Scanner;

public class SortingProgram {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Integer> numbers = new ArrayList<>();
        boolean running = true;

        System.out.println("=== Program Sorting Angka ===");

        try {
            while (running) {
                System.out.println("\nMenu Utama:");
                System.out.println("1. Tambah Angka");
                System.out.println("2. Quick Sort");
                System.out.println("3. Merge Sort");
                System.out.println("4. Lihat Daftar Angka");
                System.out.println("5. Keluar");
                System.out.print("Pilihan Anda: ");
                
                int choice = scan.nextInt();
                
                switch (choice) {
                    case 1:
                        System.out.print("Masukkan angka: ");
                        int newNumber = scan.nextInt();
                        numbers.add(newNumber);
                        System.out.println("Angka " + newNumber + " telah ditambahkan pada indeks " + (numbers.size() - 1));
                        break;
                        
                        case 2:
                        if (numbers.isEmpty()) {
                            System.out.println("Daftar angka masih kosong!");
                            break;
                        }
                        int[] quickSortArray = convertToArray(numbers);
                        System.out.println("\nArray sebelum Quick Sort:");
                        printArray(quickSortArray);
                        
                        long startTime = System.nanoTime();
                        quickSort(quickSortArray, 0, quickSortArray.length - 1);
                        long endTime = System.nanoTime();
                        
                        System.out.println("Array setelah Quick Sort:");
                        printArray(quickSortArray);
                        
                        updateListFromSortedArray(numbers, quickSortArray);
                        System.out.println("Daftar angka telah diperbarui dan tetap tersortir.");
                        
                        System.out.println("Waktu eksekusi Quick Sort: " + 
                            ((endTime - startTime) / 1000000.0) + " ms");
                        break;
                    
                    case 3:
                        if (numbers.isEmpty()) {
                            System.out.println("Daftar angka masih kosong!");
                            break;
                        }
                        int[] mergeSortArray = convertToArray(numbers);
                        System.out.println("\nArray sebelum Merge Sort:");
                        printArray(mergeSortArray);
                        
                        startTime = System.nanoTime();
                        mergeSort(mergeSortArray, 0, mergeSortArray.length - 1);
                        endTime = System.nanoTime();
                        
                        System.out.println("Array setelah Merge Sort:");
                        printArray(mergeSortArray);
                        
                        updateListFromSortedArray(numbers, mergeSortArray);
                        
                        System.out.println("Waktu eksekusi Merge Sort: " + 
                            ((endTime - startTime) / 1000000.0) + " ms");
                        break;                    
                        
                    case 4:
                        if (numbers.isEmpty()) {
                            System.out.println("Daftar angka masih kosong!");
                        } else {
                            System.out.println("\nDaftar angka saat ini:");
                            System.out.print("[ ");
                            for (int i = 0; i < numbers.size(); i++) {
                                System.out.print(numbers.get(i));
                                if (i < numbers.size() - 1) {
                                    System.out.print(", ");
                                }
                            }
                            System.out.println(" ]");
                            System.out.println("Jumlah elemen: " + numbers.size());
                        }
                        break;
                        
                    case 5:
                        System.out.println("Terima kasih! Program selesai.");
                        running = false;
                        break;
                        
                    default:
                        System.out.println("Pilihan tidak valid!");
                        break;
                }
            }
        } catch (Exception ex) {
            System.out.println("Terjadi kesalahan: " + ex.getMessage());
        } finally {
            scan.close();
        }
    }

    private static int[] convertToArray(ArrayList<Integer> list) {
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    private static void printArray(int[] arr) {
        System.out.print("[ ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println(" ]");
    }

    private static void updateListFromSortedArray(ArrayList<Integer> list, int[] sortedArray) {
        list.clear();
        for (int num : sortedArray) {
            list.add(num);
        }
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }
    
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        
        return i + 1;
    }

    private static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(arr, left, middle);
            mergeSort(arr, middle + 1, right);
            merge(arr, left, middle, right);
        }
    }
    
    private static void merge(int[] arr, int left, int middle, int right) {
        int n1 = middle - left + 1;
        int n2 = right - middle;
        
        int[] L = new int[n1];
        int[] R = new int[n2];
        
        for (int i = 0; i < n1; i++)
            L[i] = arr[left + i];
        for (int j = 0; j < n2; j++)
            R[j] = arr[middle + 1 + j];
        
        int i = 0, j = 0;
        int k = left;
        
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
        
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
}