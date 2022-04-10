package program;

import entities.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Application {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("in.txt");
        List<Employee> list = new LinkedList<>();
        double salaryFilterEmail = 2000;

        try (BufferedReader br = new BufferedReader(new FileReader(path.toFile()))) {
            String line = br.readLine();
            while (line != null) {
                String[] fields = line.split(",");
                list.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
                line = br.readLine();
            }
        }

        Stream<String> stringStream = list.stream()
                .filter(p -> p.getSalary() > salaryFilterEmail)
                .sorted(Comparator.comparing(Employee::getEmail))
                .map(Employee::getEmail);
        System.out.println("Email of people whose salary is more than " + salaryFilterEmail + ":");
        stringStream.forEach(System.out::println);
        double sumSalaryM = list.stream()
                .filter(p -> p.getName().charAt(0) == 'M')
                .mapToDouble(Employee::getSalary)
                .sum();
        Stream<String> stringStream1 = list.stream()
                .filter(p -> p.getName().charAt(0) == 'M')
                .map(Employee::getName);
        System.out.println("Sum of salary of people whose name starts with 'M':" + sumSalaryM);
        stringStream1.forEach(System.out::println);
    }
}
