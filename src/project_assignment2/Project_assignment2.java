/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package project_assignment2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

/**
 *
 * @author virtu
 */
public class Project_assignment2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        StringBuilder textContent = new StringBuilder();

        try {
            System.out.println("Choose input method:");
            System.out.println("1 - Enter text manually");
            System.out.println("2 - Fetch text from URL");
            System.out.print("Enter choice (1 or 2): ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {

                System.out.println("Enter your text (type 'STOP' on a new line to finish):");

                while (true) {
                    String line = scanner.nextLine();
                    if (line.equalsIgnoreCase("STOP")) {
                        break;
                    }
                    textContent.append(line).append("\n");
                }

            } else if (choice == 2) {

                System.out.print("Enter URL: ");
                String urlString = scanner.nextLine();

                URL url = new URL(urlString);
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(url.openStream()));

                String line;
                while ((line = reader.readLine()) != null) {
                    textContent.append(line).append("\n");
                }
                reader.close();

            } else {
                System.out.println("Invalid choice.");
                return;
            }

            System.out.print("Enter file name (example: output.txt): ");
            String fileName = scanner.nextLine();

            System.out.print("Enter folder path to save (or press Enter for project folder): ");
            String folderPath = scanner.nextLine();

            File file;

            if (folderPath.isEmpty()) {
                file = new File(fileName);
            } else {
                file = new File(folderPath + File.separator + fileName);
            }

            // --- [ MODULE D: IO OPERATIONS (WRITE) ] ---
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(textContent.toString());
            writer.close();

            System.out.println("File saved successfully at: " + file.getAbsolutePath());

        } catch (FileNotFoundException e) {
            System.out.println("Error: File path not found.");
        } catch (IOException e) {
            System.out.println("Error: Problem reading/writing file.");
        } catch (Exception e) {
            System.out.println("Unexpected error occurred.");
        }

        scanner.close();
    }
}
