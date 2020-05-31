public class Main {
    public static void main(String[] args) {
        final int SIZE = 10000000;
        final int HALF = SIZE / 2;
        float[] arr = new float[SIZE];

        for (int i = 0; i < SIZE; i++) {
            arr[i]=1.0f;

        }

        long a = System.currentTimeMillis();

        for (int i = 0; i < SIZE; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));

        }

        System.currentTimeMillis();
        System.out.println("Время выполнения программы в одном потоке: " + (System.currentTimeMillis() - a));


        long b = System.currentTimeMillis();

        float[] a1 = new float[HALF];
        System.arraycopy(arr, 0, a1, 0, HALF);

        float[] a2 = new float[HALF];
        System.arraycopy(arr, HALF, a2, 0, HALF);

        Thread t1 = new Thread(()->{
            for (int i = 0; i < HALF; i++) {
                a1[i] = arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });

        Thread t2 = new Thread(()->{
            for (int i = 0; i < HALF; i++) {
                a2[i] = arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));

            }
        });

        t1.start();
        t2.start();

        System.arraycopy(a1, 0, arr, 0, HALF);
        System.arraycopy(a2, 0, arr, HALF, HALF);

        System.currentTimeMillis();
        System.out.println("Время выполнения программы в двух потоках: " + (System.currentTimeMillis() - b));
    }
}
