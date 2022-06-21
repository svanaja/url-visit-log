package com.org.programming.task;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.org.programming.task.model.AuditLog;

@SpringBootApplication
public class App {

	/**
	 * This is main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);

		Set<String> uniqueIpAddress = new HashSet<String>();
		List<AuditLog> auditLogList = parseLogFile();
		for (AuditLog auditLog : auditLogList) {
			uniqueIpAddress.add(auditLog.getIpAddress());
		}
		
		System.out.println("The number of Unique IP Address : " + uniqueIpAddress);
		
		
		System.out.println("The top 3 most Visited URL: ");
		List<String> top3MostVisitedUrl = auditLogList.stream()
		        .collect(Collectors.groupingBy(p -> p.getrequestUrl(), Collectors.counting()))
		        .entrySet().stream()
		        .sorted(Map.Entry.<String, Long> comparingByValue(Comparator.reverseOrder()).thenComparing(Map.Entry.comparingByKey()))
		        .limit(3)
		        .map(Map.Entry::getKey)
		        .collect(Collectors.toList());

		top3MostVisitedUrl.forEach(System.out::println);

		

		System.out.println("The top most active IP Address : ");
		List<String> top3MostActiveIpAddress = auditLogList.stream()
		        .collect(Collectors.groupingBy(p -> p.getIpAddress(), Collectors.counting()))
		        .entrySet().stream()
		        .sorted(Map.Entry.<String, Long> comparingByValue(Comparator.reverseOrder()).thenComparing(Map.Entry.comparingByKey()))
		        .limit(3)
		        .map(Map.Entry::getKey)
		        .collect(Collectors.toList());
		
		top3MostActiveIpAddress.forEach(System.out::println);

	}
	
	private static LinkedHashMap<String, Integer> sortMapByValue(Map<String, Integer> map) {

		Comparator<Entry<String, Integer>> valueComparator = new Comparator<Entry<String, Integer>>() {

			@Override
			public int compare(Entry<String, Integer> e1, Entry<String, Integer> e2) {
				Integer v1 = e1.getValue();
				Integer v2 = e2.getValue();
				return v2.compareTo(v1);
			}
		};

		// Sort method needs a List, so let's first convert Set to List in Java
		Set<Entry<String, Integer>> entries = map.entrySet();
		List<Entry<String, Integer>> listOfEntries = new ArrayList<Entry<String, Integer>>(entries);

		// sorting HashMap by values using comparator
		Collections.sort(listOfEntries, valueComparator);

		LinkedHashMap<String, Integer> sortedByValue = new LinkedHashMap<String, Integer>(listOfEntries.size());

		// copying entries from List to Map
		for (Entry<String, Integer> entry : listOfEntries) {
			sortedByValue.put(entry.getKey(), entry.getValue());
		}

		return sortedByValue;
	}
	
	
	private static List<AuditLog> parseLogFile() {
		File logFile = new File("src/main/resources/programming-task-example-data.txt");
		List<AuditLog> auditLogList = new ArrayList<AuditLog>();
		try (Scanner scanner = new Scanner(logFile)) {
			
			while (scanner.hasNext()) {
				String[] tokens = scanner.nextLine().split(" ");
				AuditLog log = new AuditLog(tokens[0], tokens[3].substring(1), tokens[5], tokens[6], tokens[8]);
				auditLogList.add(log);
				System.out.println(log.toString());

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return auditLogList;
	}
}
