package com.interviewprep.seed;

import com.interviewprep.entity.Difficulty;
import com.interviewprep.entity.Question;
import com.interviewprep.entity.QuestionCategory;
import com.interviewprep.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Seeds a starter question bank on first boot so the app is usable immediately.
 * Add more questions here, or build an admin import endpoint later.
 */
@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final QuestionRepository questionRepository;

    @Override
    public void run(String... args) {
        if (questionRepository.count() > 0) return; // already seeded

        questionRepository.saveAll(List.of(
                // ---------------- JAVA - EASY ----------------
                Question.builder().category(QuestionCategory.JAVA).difficulty(Difficulty.EASY).topic("Core Java Basics")
                        .questionText("Which of these is NOT a primitive data type in Java?")
                        .optionA("int").optionB("boolean").optionC("String").optionD("char")
                        .correctOption("C")
                        .explanation("String is a class (reference type) in Java, not a primitive type.")
                        .build(),
                Question.builder().category(QuestionCategory.JAVA).difficulty(Difficulty.EASY).topic("OOP")
                        .questionText("Which OOP concept allows a class to inherit properties from another class?")
                        .optionA("Encapsulation").optionB("Inheritance").optionC("Polymorphism").optionD("Abstraction")
                        .correctOption("B")
                        .explanation("Inheritance lets a subclass acquire fields and methods of a parent class.")
                        .build(),
                Question.builder().category(QuestionCategory.JAVA).difficulty(Difficulty.EASY).topic("Core Java Basics")
                        .questionText("What is the default value of a boolean instance variable in Java?")
                        .optionA("true").optionB("false").optionC("null").optionD("0")
                        .correctOption("B")
                        .explanation("Uninitialized boolean instance variables default to false.")
                        .build(),

                // ---------------- JAVA - MEDIUM ----------------
                Question.builder().category(QuestionCategory.JAVA).difficulty(Difficulty.MEDIUM).topic("Collections")
                        .questionText("Which Collection implementation maintains insertion order AND allows fast lookups?")
                        .optionA("HashSet").optionB("TreeSet").optionC("LinkedHashSet").optionD("PriorityQueue")
                        .correctOption("C")
                        .explanation("LinkedHashSet combines a hash table with a linked list to preserve insertion order.")
                        .build(),
                Question.builder().category(QuestionCategory.JAVA).difficulty(Difficulty.MEDIUM).topic("Exception Handling")
                        .questionText("What happens if an exception is thrown in a try block and there's no matching catch, but a finally block exists?")
                        .optionA("finally is skipped").optionB("finally executes, then exception propagates")
                        .optionC("Program terminates silently").optionD("Compile error")
                        .correctOption("B")
                        .explanation("finally always executes before the exception propagates up the call stack.")
                        .build(),
                Question.builder().category(QuestionCategory.JAVA).difficulty(Difficulty.MEDIUM).topic("Multithreading")
                        .questionText("Which keyword ensures only one thread can execute a method/block at a time?")
                        .optionA("volatile").optionB("synchronized").optionC("transient").optionD("static")
                        .correctOption("B")
                        .explanation("synchronized enforces mutual exclusion on the method or block for thread safety.")
                        .build(),

                // ---------------- JAVA - HARD ----------------
                Question.builder().category(QuestionCategory.JAVA).difficulty(Difficulty.HARD).topic("JVM Internals")
                        .questionText("During which JVM memory area are class-level static variables stored (Java 8+)?")
                        .optionA("Heap").optionB("Stack").optionC("Metaspace").optionD("Program Counter Register")
                        .correctOption("C")
                        .explanation("Since Java 8, class metadata including static variables live in Metaspace (native memory), replacing PermGen.")
                        .build(),
                Question.builder().category(QuestionCategory.JAVA).difficulty(Difficulty.HARD).topic("Streams & Lambdas")
                        .questionText("What's the key difference between map() and flatMap() in Java Streams?")
                        .optionA("No difference").optionB("flatMap flattens nested streams into a single stream")
                        .optionC("map only works on primitives").optionD("flatMap is synchronous, map is not")
                        .correctOption("B")
                        .explanation("flatMap transforms each element into a stream and flattens all resulting streams into one.")
                        .build(),

                // ---------------- DSA - EASY ----------------
                Question.builder().category(QuestionCategory.DSA).difficulty(Difficulty.EASY).topic("Arrays")
                        .questionText("What is the time complexity of accessing an element by index in an array?")
                        .optionA("O(n)").optionB("O(log n)").optionC("O(1)").optionD("O(n^2)")
                        .correctOption("C")
                        .explanation("Arrays allow constant-time O(1) random access via index arithmetic.")
                        .build(),
                Question.builder().category(QuestionCategory.DSA).difficulty(Difficulty.EASY).topic("Sorting & Searching")
                        .questionText("Which sorting algorithm has the best average-case time complexity?")
                        .optionA("Bubble Sort O(n^2)").optionB("Selection Sort O(n^2)")
                        .optionC("Merge Sort O(n log n)").optionD("Insertion Sort O(n^2)")
                        .correctOption("C")
                        .explanation("Merge Sort consistently runs in O(n log n) regardless of input distribution.")
                        .build(),

                // ---------------- DSA - MEDIUM ----------------
                Question.builder().category(QuestionCategory.DSA).difficulty(Difficulty.MEDIUM).topic("Trees")
                        .questionText("In a Binary Search Tree, an in-order traversal visits nodes in which order?")
                        .optionA("Random order").optionB("Ascending sorted order")
                        .optionC("Descending sorted order").optionD("Level order")
                        .correctOption("B")
                        .explanation("In-order traversal (Left, Root, Right) of a BST yields elements in ascending sorted order.")
                        .build(),
                Question.builder().category(QuestionCategory.DSA).difficulty(Difficulty.MEDIUM).topic("Linked List")
                        .questionText("Which technique detects a cycle in a linked list in O(1) space?")
                        .optionA("Hash Set of visited nodes").optionB("Floyd's Cycle Detection (slow/fast pointers)")
                        .optionC("Recursion with memoization").optionD("Reversing the list")
                        .correctOption("B")
                        .explanation("Floyd's Tortoise and Hare algorithm uses two pointers at different speeds, needing only O(1) extra space.")
                        .build(),

                // ---------------- DSA - HARD ----------------
                Question.builder().category(QuestionCategory.DSA).difficulty(Difficulty.HARD).topic("Dynamic Programming")
                        .questionText("The 0/1 Knapsack problem has an optimal substructure best solved using which paradigm?")
                        .optionA("Greedy").optionB("Divide and Conquer").optionC("Dynamic Programming").optionD("Brute force only")
                        .correctOption("C")
                        .explanation("0/1 Knapsack has overlapping subproblems and optimal substructure, the hallmark of DP.")
                        .build(),
                Question.builder().category(QuestionCategory.DSA).difficulty(Difficulty.HARD).topic("Graphs")
                        .questionText("Which algorithm finds the shortest path in a graph with possible negative edge weights (no negative cycles)?")
                        .optionA("Dijkstra's Algorithm").optionB("Bellman-Ford Algorithm")
                        .optionC("Breadth First Search").optionD("Prim's Algorithm")
                        .correctOption("B")
                        .explanation("Bellman-Ford handles negative weights correctly, unlike Dijkstra which assumes non-negative weights.")
                        .build(),

                // ---------------- APTITUDE - Quantitative ----------------
                Question.builder().category(QuestionCategory.APTITUDE).difficulty(Difficulty.EASY).topic("Quantitative")
                        .questionText("A train travels 60 km in 1.5 hours. What is its speed in km/h?")
                        .optionA("30 km/h").optionB("40 km/h").optionC("45 km/h").optionD("50 km/h")
                        .correctOption("B")
                        .explanation("Speed = Distance / Time = 60 / 1.5 = 40 km/h.")
                        .build(),
                Question.builder().category(QuestionCategory.APTITUDE).difficulty(Difficulty.MEDIUM).topic("Quantitative")
                        .questionText("If the price of an item increases by 20% and then decreases by 20%, what is the net change?")
                        .optionA("No change").optionB("4% decrease").optionC("4% increase").optionD("2% decrease")
                        .correctOption("B")
                        .explanation("1.2 * 0.8 = 0.96, a net 4% decrease from the original price.")
                        .build(),

                // ---------------- APTITUDE - Logical Reasoning ----------------
                Question.builder().category(QuestionCategory.APTITUDE).difficulty(Difficulty.EASY).topic("Logical Reasoning")
                        .questionText("Find the odd one out: Dog, Cat, Lion, Snake, Tiger")
                        .optionA("Dog").optionB("Snake").optionC("Cat").optionD("Tiger")
                        .correctOption("B")
                        .explanation("All others are mammals; a snake is a reptile.")
                        .build(),
                Question.builder().category(QuestionCategory.APTITUDE).difficulty(Difficulty.MEDIUM).topic("Logical Reasoning")
                        .questionText("Complete the series: 2, 6, 12, 20, 30, ?")
                        .optionA("40").optionB("42").optionC("38").optionD("36")
                        .correctOption("B")
                        .explanation("Differences are 4,6,8,10,12 -> next term = 30 + 12 = 42 (pattern n*(n+1)).")
                        .build(),

                // ---------------- APTITUDE - Verbal ----------------
                Question.builder().category(QuestionCategory.APTITUDE).difficulty(Difficulty.EASY).topic("Verbal Ability")
                        .questionText("Choose the synonym of 'Meticulous'")
                        .optionA("Careless").optionB("Thorough").optionC("Quick").optionD("Lazy")
                        .correctOption("B")
                        .explanation("Meticulous means showing great attention to detail; thorough is the closest synonym.")
                        .build(),

                // ---------------- APTITUDE - Data Interpretation ----------------
                Question.builder().category(QuestionCategory.APTITUDE).difficulty(Difficulty.HARD).topic("Data Interpretation")
                        .questionText("If a company's revenue grew from ₹50L to ₹65L in a year, what is the % growth?")
                        .optionA("20%").optionB("25%").optionC("30%").optionD("15%")
                        .correctOption("C")
                        .explanation("Growth % = (65-50)/50 * 100 = 30%.")
                        .build()
        ));
    }
}
