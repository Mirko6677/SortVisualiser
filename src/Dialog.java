import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Dialog extends JFrame {

    private final JPanel panel;
    private final JComboBox<String> comboBox;
    private List<Integer> numbers;
    private final int sleepValue = 10;

    public Dialog() {
        setTitle("Sort Visualiser");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 400);
        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int totalWidth = getWidth();
                int numElements = numbers.size();
                int columnWidth = totalWidth / numElements;
                int heightFactor = getHeight() / Collections.max(numbers);
                int remainingSpace = totalWidth - numElements * columnWidth;
                int startX = remainingSpace / 2;
                for(int i = 0; i < numElements; i++) {
                    int columnHeight = numbers.get(i) * heightFactor;
                    g.setColor(Color.BLACK);
                    int x = startX + i * columnWidth;
                    int y = getHeight() - columnHeight;
                    g.fillRect(x, y, columnWidth, columnHeight);
                }
            }
        };

        panel.setBounds(10, 92, 414, 227);
        contentPane.add(panel);

        JButton sortButton = new JButton("Sort");
        sortButton.setBounds(333, 22, 91, 23);
        contentPane.add(sortButton);

        comboBox = new JComboBox<>();
        comboBox.setBounds(221, 23, 103, 22);
        comboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Bubble Sort", "Selection Sort", "Insertion Sort", "Merge Sort", "Quick Sort", "Heap Sort"}));
        contentPane.add(comboBox);



        sortButton.addActionListener(e -> {
            String selectedSort = (String) comboBox.getSelectedItem();
            switch (selectedSort) {
                case "Bubble Sort":
                    new Thread(() -> bubbleSort(numbers)).start();
                    break;
                case "Selection Sort":
                    new Thread(() -> selectionSort(numbers)).start();
                    break;
                case "Insertion Sort":
                    new Thread(() -> insertionSort(numbers)).start();
                    break;
                case "Merge Sort":
                    new Thread(() -> mergeSort(numbers, 0, numbers.size() - 1)).start();
                    break;
                case "Quick Sort":
                    new Thread(() -> quickSort(numbers, 0, numbers.size() - 1)).start();
                    break;
                case "Heap Sort":
                    new Thread(() -> heapSort(numbers)).start();
                    break;
            }
        });

        generateRandomNumbers();
    }

    private void generateRandomNumbers() {
        numbers = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            numbers.add((int) (Math.random() * 50) + 1);
        }
    }

    private void bubbleSort(List<Integer> arr) {
        int n = arr.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr.get(j) > arr.get(j + 1)) {
                    int temp = arr.get(j);
                    arr.set(j, arr.get(j + 1));
                    arr.set(j + 1, temp);

                    panel.repaint();

                    try {
                        Thread.sleep(sleepValue);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void selectionSort(List<Integer> arr) {
        int n = arr.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr.get(j) < arr.get(minIndex)) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                Collections.swap(arr, i, minIndex);

                panel.repaint();

                try {
                    Thread.sleep(sleepValue);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void insertionSort(List<Integer> arr) {
        int n = arr.size();
        for (int i = 1; i < n; i++) {
            int key = arr.get(i);
            int j = i - 1;
            while (j >= 0 && arr.get(j) > key) {
                arr.set(j + 1, arr.get(j));
                j = j - 1;
            }
            arr.set(j + 1, key);

            panel.repaint();

            try {
                Thread.sleep(sleepValue);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private void mergeSort(List<Integer> arr, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    private void merge(List<Integer> arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        List<Integer> L = new ArrayList<>(arr.subList(l, m + 1));
        List<Integer> R = new ArrayList<>(arr.subList(m + 1, r + 1));
        int i = 0, j = 0, k = l;
        while (i < n1 && j < n2) {
            if (L.get(i) <= R.get(j)) {
                arr.set(k, L.get(i));
                i++;
            } else {
                arr.set(k, R.get(j));
                j++;
            }
            k++;

            panel.repaint();

            try {
                Thread.sleep(sleepValue);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (i < n1) {
            arr.set(k, L.get(i));
            i++;
            k++;
        }
        while (j < n2) {
            arr.set(k, R.get(j));
            j++;
            k++;
        }
    }


    private void quickSort(List<Integer> arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private int partition(List<Integer> arr, int low, int high) {
        int pivot = arr.get(high);
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr.get(j) < pivot) {
                i++;
                Collections.swap(arr, i, j);

                panel.repaint();

                try {
                    Thread.sleep(sleepValue);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        Collections.swap(arr, i + 1, high);
        return i + 1;
    }


    private void heapSort(List<Integer> arr) {
        int n = arr.size();
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        for (int i = n - 1; i >= 0; i--) {
            Collections.swap(arr, 0, i);
            heapify(arr, i, 0);

            panel.repaint();

            try {
                Thread.sleep(sleepValue);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void heapify(List<Integer> arr, int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        if (l < n && arr.get(l) > arr.get(largest)) {
            largest = l;
        }
        if (r < n && arr.get(r) > arr.get(largest)) {
            largest = r;
        }
        if (largest != i) {
            Collections.swap(arr, i, largest);
            heapify(arr, n, largest);
        }
    }

}