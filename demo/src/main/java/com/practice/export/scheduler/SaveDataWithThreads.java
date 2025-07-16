package com.practice.export.scheduler;

import java.util.concurrent.*;

public class SaveDataWithThreads {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (int i = 1; i <= 10; i++) {
            int recordId = i;
            Callable<String> task = () -> {
                try {
                    saveData(recordId);
                    return "Saved record " + recordId;
                } catch (Exception e) {
                    return "Failed to save record " + recordId + ": " + e.getMessage();
                }
            };

            Future<String> future = executor.submit(task);

            // Optional: handle timeout
            try {
                String result = future.get(3, TimeUnit.SECONDS);
                System.out.println(result);
            } catch (TimeoutException e) {
                future.cancel(true);
                System.out.println("Timeout on record " + recordId);
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        }

        executor.shutdown();
    }

    public static void saveData(int id) throws Exception {
        if (id == 5 || id == 8) {
            throw new Exception("Database error for ID " + id); // Simulate error
        }
        Thread.sleep(1000); // Simulate save time
        System.out.println("Record " + id + " saved by thread " + Thread.currentThread().getName());
    }
}
