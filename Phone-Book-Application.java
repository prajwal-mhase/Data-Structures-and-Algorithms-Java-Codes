import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class TrieNode implements Serializable {
    TrieNode[] children = new TrieNode[128];
    String phoneNumber;
    boolean isEndOfWord;
    String originalName;

    public TrieNode() {
        for (int i = 0; i < 128; i++) {
            children[i] = null;
        }
        phoneNumber = null;
        isEndOfWord = false;
        originalName = null;
    }
}

class PhoneBookDB implements Serializable {
    private TrieNode root;

    public PhoneBookDB() {
        root = new TrieNode();
    }

    public void Insert(String name, String num) {
        while (!isValidPhoneNumber(num)) {
            System.out.print("Invalid phone number. Please enter a number between 6 to 10 digits: ");
            Scanner sr = new Scanner(System.in);
            num = sr.nextLine().trim();
        }

        TrieNode node = root;
        for (char ch : name.toCharArray()) {
            if (node.children[ch] == null) {
                node.children[ch] = new TrieNode();
            }
            node = node.children[ch];
        }
        node.isEndOfWord = true;
        node.phoneNumber = num;
        node.originalName = name;
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber.length() >= 6 && phoneNumber.length() <= 10) {
            return phoneNumber.matches("\\d+");
        }
        return false;
    }

    public void Display() {
        List<String> contacts = new ArrayList<>();
        collectContacts(root, new StringBuilder(), contacts);
        if (contacts.isEmpty()) {
            System.out.println("Contacts not available.");
            return;
        }
        Collections.sort(contacts, String.CASE_INSENSITIVE_ORDER);

        System.out.println("===============================================");
        System.out.printf("%-30s %-15s\n", "Name", "Phone Number");
        System.out.println("===============================================");

        for (String contact : contacts) {
            String[] parts = contact.split(": ");
            String name = parts[0];
            String phoneNumber = parts[1];
            System.out.printf("%-30s %-15s\n", name, phoneNumber);
        }

        System.out.println("===============================================");
    }

    private void collectContacts(TrieNode node, StringBuilder prefix, List<String> contacts) {
        if (node.isEndOfWord) {
            contacts.add(node.originalName + ": " + node.phoneNumber);
        }
        for (int i = 0; i < 128; i++) {
            if (node.children[i] != null) {
                prefix.append((char) i);
                collectContacts(node.children[i], prefix, contacts);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        }
    }

    public void Search(String key) {
        List<String> contacts = new ArrayList<>();
        collectContacts(root, new StringBuilder(), contacts);
        List<String> searchResults = new ArrayList<>();
        String lowerKey = key.toLowerCase();
        for (String contact : contacts) {
            if (contact.toLowerCase().contains(lowerKey)) {
                searchResults.add(contact);
            }
        }
        if (!searchResults.isEmpty()) {
            System.out.println("Search results:");
            for (String result : searchResults) {
                System.out.println(result);
            }
        } else {
            System.out.println("Contact not found.");
        }
        System.out.println();
    }

    public void Edit(String key) {
        List<String> contacts = new ArrayList<>();
        collectContacts(root, new StringBuilder(), contacts);
        List<String> matchingContacts = new ArrayList<>();
        String lowerKey = key.toLowerCase();
        for (String contact : contacts) {
            if (contact.toLowerCase().contains(lowerKey)) {
                matchingContacts.add(contact);
            }
        }
        if (matchingContacts.isEmpty()) {
            System.out.println("Contact not found.");
            return;
        }
        System.out.println("Matching contacts for editing:");
        for (String match : matchingContacts) {
            System.out.println(match);
        }
        System.out.print("Enter the exact contact name to edit: ");
        Scanner sr = new Scanner(System.in);
        String selectedName = sr.nextLine().trim();
        TrieNode node = findNodeByName(selectedName);
        if (node != null && node.isEndOfWord) {
            System.out.println("What do you want to change?");
            System.out.println("1. Name");
            System.out.println("2. Number");
            System.out.println("3. Both");
            int choice;
            try {
                choice = Integer.parseInt(sr.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please enter a number.");
                return;
            }
            switch (choice) {
                case 1:
                    System.out.print("Enter new name: ");
                    String newName = sr.nextLine().trim();
                    deleteHelper(root, selectedName, 0);
                    Insert(newName, node.phoneNumber);
                    System.out.println("Contact name updated.");
                    break;
                case 2:
                    System.out.print("Enter new phone number: ");
                    String newNum = sr.nextLine().trim();
                    node.phoneNumber = newNum;
                    System.out.println("Contact number updated.");
                    break;
                case 3:
                    System.out.print("Enter new name: ");
                    newName = sr.nextLine().trim();
                    System.out.print("Enter new phone number: ");
                    newNum = sr.nextLine().trim();
                    deleteHelper(root, selectedName, 0);
                    Insert(newName, newNum);
                    System.out.println("Contact updated.");
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        } else {
            System.out.println("Contact not found.");
        }
    }

    public void Delete(String key) {
        List<String> contacts = new ArrayList<>();
        collectContacts(root, new StringBuilder(), contacts);
        List<String> matchingContacts = new ArrayList<>();
        String lowerKey = key.toLowerCase();
        for (String contact : contacts) {
            if (contact.toLowerCase().contains(lowerKey)) {
                matchingContacts.add(contact);
            }
        }
        if (matchingContacts.isEmpty()) {
            System.out.println("No matching contacts found for deletion.");
            return;
        }
        System.out.println("Matching contacts for deletion:");
        for (String match : matchingContacts) {
            System.out.println(match);
        }
        System.out.print("Enter the exact contact name to delete: ");
        Scanner sr = new Scanner(System.in);
        String selectedName = sr.nextLine().trim();
        boolean found = false;
        for (String contact : matchingContacts) {
            String[] parts = contact.split(": ");
            if (parts[0].equals(selectedName)) {
                deleteHelper(root, parts[0], 0);
                found = true;
                System.out.println("Contact deleted.");
                break;
            }
        }
        if (!found) {
            System.out.println("Contact not found.");
        }
    }

    private TrieNode findNodeByName(String name) {
        TrieNode node = root;
        for (char ch : name.toCharArray()) {
            if (node.children[ch] == null) {
                return null;
            }
            node = node.children[ch];
        }
        return node;
    }

    private boolean deleteHelper(TrieNode node, String key, int depth) {
        if (node == null) {
            return false;
        }
        if (depth == key.length()) {
            if (!node.isEndOfWord) {
                return false;
            }
            node.isEndOfWord = false;
            return nodeHasNoChildren(node);
        }
        char ch = key.charAt(depth);
        boolean shouldDeleteCurrentNode = deleteHelper(node.children[ch], key, depth + 1);
        if (shouldDeleteCurrentNode) {
            node.children[ch] = null;
            return !node.isEndOfWord && nodeHasNoChildren(node);
        }
        return false;
    }

    private boolean nodeHasNoChildren(TrieNode node) {
        for (int i = 0; i < 128; i++) {
            if (node.children[i] != null) {
                return false;
            }
        }
        return true;
    }

    public void saveToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(this);
            System.out.println("Phone book saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving phone book: " + e.getMessage());
        }
    }

    public static PhoneBookDB loadFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (PhoneBookDB) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading phone book: " + e.getMessage());
            return new PhoneBookDB();
        }
    }
}

class PhoneBook {
    public static void main(String[] args) {
        System.out.println("\n");
        System.out.println("=======================================");
        System.out.println("||            PHONEBOOK              ||");
        System.out.println("=======================================");
        Scanner sr = new Scanner(System.in);
        PhoneBookDB pb = PhoneBookDB.loadFromFile("phonebook.dat");

        while (true) {
            System.out.print("Menu: \n1. Add contact \n2. Display all contacts \n3. Search contact \n4. Edit contact \n5. Delete contact \n0. Exit \nEnter choice: ");
            try {
                int choice = sr.nextInt();
                sr.nextLine();
                switch (choice) {
                    case 0:
                        System.out.print("Do you want to save changes before exiting? (y/n): ");
                        if (sr.nextLine().trim().equalsIgnoreCase("y")) {
                            pb.saveToFile("phonebook.dat");
                        }
                        System.out.println("\nPhoneBook closed.");
                        sr.close();
                        System.exit(0);
                    case 1:
                        System.out.print("\nEnter contact name: ");
                        String name = sr.nextLine().trim();
                        System.out.print("Enter contact number: ");
                        String num = sr.nextLine().trim();
                        pb.Insert(name, num);
                        pb.saveToFile("phonebook.dat");
                        System.out.println();
                        break;
                    case 2:
                        System.out.println();
                        pb.Display();
                        System.out.println();
                        break;
                    case 3:
                        System.out.print("\nEnter contact name or number: ");
                        pb.Search(sr.nextLine().trim());
                        System.out.println();
                        break;
                    case 4:
                        System.out.print("\nEnter the contact name or number: ");
                        pb.Edit(sr.nextLine().trim());
                        pb.saveToFile("phonebook.dat");
                        System.out.println();
                        break;
                    case 5:
                        System.out.print("\nEnter the contact name or number: ");
                        pb.Delete(sr.nextLine().trim());
                        pb.saveToFile("phonebook.dat");
                        System.out.println();
                        break;
                    default:
                        System.out.println("\nInvalid choice!\n");
                        break;
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                sr.nextLine();
            }
        }
    }
}
