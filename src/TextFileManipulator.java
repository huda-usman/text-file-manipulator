import java.util.*;
import java.io.*;

class TextFileManipulator {
    static Scanner sc = new Scanner(System.in);
    static int SizeOfFile;
    static File files;
    static LinkedList ContantOfList;
    static String Lines[];
    static int indexno = 0;
    static Stack ClipBoard;
    static boolean Answer = false;

    public static void main(String[] args) throws Exception {
        System.out.println(
                "-----------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(
                "                                           T E X T   F I L E   M A N I P U L A T O R                                                    ");
        System.out.println(
                "-----------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Enter File Name in which you want changes : ");
        String Name = sc.next();
        String Path = Name + ".txt";
        System.out.println(
                "-----------------------------------------------------------------------------------------------------------------------------------------");

        files = new File(Path);
        checkname(Name); //checks if file is exists or not
        if (Answer) {
            System.exit(0);
        }
        RandomAccessFile Randomfile = new RandomAccessFile(files, "rw");
        int Index = Randomfile.read();
        while (Index != -1) {
            System.out.print((char) Index);
            Index = Randomfile.read();
        }
        System.out.println();
        System.out.println("Length of File : " + Randomfile.length());

        SizeOfFile = (int) Randomfile.length();

        Randomfile.close();

        Lines = new String[SizeOfFile]; 
        ContantOfList = new LinkedList();
        ClipBoard = new Stack(SizeOfFile);
        int x;
        do {
            try {

                System.out.println(
                        "----------------------------------------------------------------------------------------------------------------------------------");
                System.out.println(" PRESS :\r \n" +
                        "(1). Eliminate repeated Lines from the file \r\n" +
                        "(2). Reverse the content of file \r\n" +
                        "(3). Insert new Line \r\n" +
                        "(4). Copy text \r\n" +
                        "(5). Paste text Line\r\n" +
                        "(6). Cut the Line \r\n" +
                        "(7). Sort the content of the file\r\n" +
                        "(8). Write one file content to another file \r\n" +
                        "(9). Exit");

                x = sc.nextInt();
                System.out.println("Selected option: " + x);
                switch (x) {
                    case 1: {
                        eliminateRepeatedLine(Path);
                    }
                        break;

                    case 2: {
                        reverseContent(Path);
                    }
                        break;

                    case 3: {
                        addNewLine(Path);
                    }
                        break;

                    case 4: {
                        copytext(Path);
                    }
                        break;

                    case 5: {
                        pastetext(Path);
                    }
                        break;

                    case 6: {
                        cuttext(Path);
                    }
                        break;

                    case 7: {
                        sortcontant(Path);
                    }
                        break;

                    case 8: {
                        mergeContent(Path);
                    }
                        break;

                    case 9: {
                        System.out.println(
                                "---------------------------------------------------------------------------------------------------");
                        System.out.println();
                        System.out.println("Thank You !!..");
                    }
                        break;

                    default: {
                        System.out.println("Invalid input");
                    }
                        break;
                }
            } catch (NoSuchElementException e) {
                System.out.println("Exiting due to Ctrl+C");
                break;
            }

        } while (x != 9);

    }

    private static void checkname(String Name) // Checks is file exists or not
    {
        String Path = Name + ".txt";
        File file = new File(Path);
        if (!file.exists()) {
            System.out.println("File does not exist : " + Path);
            System.out.println("do you want to re-enter name : yes/no");
            String Choice = sc.next();
            if (Choice.equalsIgnoreCase("NO")) {
                Answer = true;
                return;
            } else if (Choice.equalsIgnoreCase("YES")) {
                System.out.println("Enter name :");
                String name = sc.next();
                checkname(name);
            } else {
                System.out.println("Invalid Input :");
                Answer = true;
            }
        }
    }

    // eliminates repeated Lines

    private static void eliminateRepeatedLine(String Path) throws Exception {

        File OriginalFile = new File(Path);
        if (!OriginalFile.exists()) {
            System.out.println("File does not exist : " + Path);
            return;
        }
        BufferedReader Reader = new BufferedReader(new FileReader(Path));
        String Line;
        while ((Line = Reader.readLine()) != null) {
            if (!ContantOfList.contains(Line)) {
                ContantOfList.addLast(Line);
            }
        }

        Reader.close();

        BufferedWriter Writer = new BufferedWriter(new FileWriter(Path));
        Writer.close();

        File Size = new File(Path);
        System.out.println("length of file :" + Size.length());

        BufferedWriter ContantWriter = new BufferedWriter(new FileWriter(Path));

        while (!ContantOfList.isEmpty()) {
            ContantWriter.write(ContantOfList.deletefirstString());
            ContantWriter.newLine();
        }

        ContantWriter.flush();
        ContantWriter.close();
        System.out.println("File Modified Successfully.");

    }

    // reverse the contant

    private static void reverseContent(String Path) {

        ContantOfList.reverseAndWriteToFile(Path);
    }

    // add new Line in file

    private static void addNewLine(String Path) throws Exception {

        File OriginalFile = new File(Path);
        if (!OriginalFile.exists()) {
            System.out.println("File does not exist: " + Path);
            return;
        }
        long Size = OriginalFile.length();
        Stack OriginalLines = new Stack((int) Size);
        Stack ModifiedLines = new Stack((int) Size);

        System.out.println("Enter the target word where you want to add Lines:");
        sc.nextLine();
        String TargetWord = sc.nextLine();

        System.out.println("Enter the new Line to replace with:");
        String NewLineToReplace = sc.nextLine();

        BufferedReader Reader = new BufferedReader(new FileReader(OriginalFile));
        String Line;

        while ((Line = Reader.readLine()) != null) {
            OriginalLines.push(Line);
        }

        Reader.close();

        while (!OriginalLines.isEmpty()) {
            String CurrentLine = OriginalLines.pop();
            if (CurrentLine.contains(TargetWord)) {
                ModifiedLines.push(NewLineToReplace);
            }
            ModifiedLines.push(CurrentLine);
        }

        BufferedWriter ContantWriter = new BufferedWriter(new FileWriter(OriginalFile));

        while (!ModifiedLines.isEmpty()) {
            ContantWriter.write(ModifiedLines.pop());
            ContantWriter.newLine();
        }

        ContantWriter.flush();
        ContantWriter.close();

        System.out.println("File modified successfully.");
    }

    // copy text

    private static void copytext(String Path) throws Exception {

        File OriginalFile = new File(Path);
        if (!OriginalFile.exists()) {
            System.out.println("File does not exist: " + Path);
            return;
        }
        long Size = OriginalFile.length();
        Stack OriginalLines = new Stack((int) Size);

        ClipBoard.clearstack();
        ClipBoard.display();

        String SourceFile = Path;

        // Open the source file for reading
        BufferedReader SourceReader = new BufferedReader(new FileReader(SourceFile));

        String Line;

        // Read Lines from the source file and store them in a linked List
        while ((Line = SourceReader.readLine()) != null) {
            OriginalLines.push(Line);
        }

        // Close the source file
        SourceReader.close();

        System.out.println();
        System.out.println("enter Line number you want to copy :");
        int CopyLineNumber = sc.nextInt();
        ClipBoard.push(OriginalLines.peekindex(OriginalLines.stacksize() - (CopyLineNumber - 1))); // -1 to adjust for
                                                                                                   // 0-based index

        System.out.println("Stack :");
        ClipBoard.display();

        System.out.println();
        System.out.println("Copied Successfully!!");
    }

    // paste text

    private static void pastetext(String Path) throws Exception {

        File OriginalFile = new File(Path);
        if (!OriginalFile.exists()) {
            System.out.println("File does not exist: " + Path);
            return;
        }

        System.out.println("Enter Line number where do you want to paste Text :");
        int PasteLineNo = sc.nextInt();
        if (PasteLineNo <= 0) {
            System.out.println("Invalid Number");
            return;
        }

        LinkedList CopyFile = new LinkedList();
        BufferedReader SourceReader = new BufferedReader(new FileReader(Path));

        String Line;
        int Count = 1;
        // Read Lines from the source file and store them in a linked List
        while ((Line = SourceReader.readLine()) != null) {
            if (Count == PasteLineNo) {
                String NewLine = ClipBoard.peek();
                CopyFile.addLast(NewLine);
            }
            CopyFile.addLast(Line);
            Count++;
        }

        SourceReader.close();

        BufferedWriter DemoWriter = new BufferedWriter(new FileWriter(Path));
        DemoWriter.close();

        System.out.println("linked List Before:");
        CopyFile.display();

        BufferedWriter ContantWriter = new BufferedWriter(new FileWriter(Path));
        String AddLines = CopyFile.deletefirstString();
        while (AddLines != null) {
            ContantWriter.write(AddLines);
            ContantWriter.newLine();
            AddLines = CopyFile.deletefirstString();
        }
        ContantWriter.close();
        System.out.println();
        System.out.println("linked List after:");
        CopyFile.display();
        System.out.println("Pasted Successfully!!");
    }

    // cut text
    private static void cuttext(String Path) throws Exception {

        File OriginalFile = new File(Path);
        if (!OriginalFile.exists()) {
            System.out.println("File does not exist: " + Path);
            return;
        }
        System.out.println("Line no:");
        int CutLineNo = sc.nextInt();
        if (CutLineNo <= 0) {
            System.out.println("Invalid no");
            return;
        }
        ClipBoard.clearstack();
        ClipBoard.display();

        LinkedList List = new LinkedList();

        BufferedReader Reader = new BufferedReader(new FileReader(Path));
        String Line;
        int Count = 1;

        // Read Lines from the source file and store them in a linked List
        while ((Line = Reader.readLine()) != null) {
            if (Count != CutLineNo) {
                List.addLast(Line);
            } else {
                ClipBoard.push(Line);
            }
            Count++;
        }
        Reader.close();

        BufferedWriter DemoWriter = new BufferedWriter(new FileWriter(Path));
        DemoWriter.close();

        System.out.println("linked List Before:");
        List.display();

        BufferedWriter ContantWriter = new BufferedWriter(new FileWriter(Path));
        String AddLines = List.deletefirstString();
        while (AddLines != null) {
            ContantWriter.write(AddLines);
            ContantWriter.newLine();
            AddLines = List.deletefirstString();
        }
        ContantWriter.close();

        System.out.println();
        System.out.println("Linked List After:");
        List.display();

        System.out.println();
        System.out.println("Line is Cutted Successfully!!");

    }

    // Method to sort the content of a file alphabetically
private static void sortcontant(String Path) throws Exception {
    // Create a File object based on the provided file path
    File OriginalFile = new File(Path);
    
    // Check if the file exists
    if (!OriginalFile.exists()) {
        // If the file does not exist, print an error message and return from the method
        System.out.println("File does not exist: " + Path);
        return;
    }

    // Initialize a BufferedReader to read from the file
    BufferedReader SourceReader = new BufferedReader(new FileReader(Path));

    // Initialize variables
    String Line;
    String Name[] = new String[SizeOfFile]; 
    int Count = 0;

    // Read each line from the source file and store them in the Name array
    while ((Line = SourceReader.readLine()) != null) {
        Name[Count] = Line; // Store the line in the array
        Count++; // Increment the count of lines read
    }
    
    // Close the BufferedReader after reading
    SourceReader.close();

    // Print the original content of the file
    System.out.println("Original array:");
    for (int i = 0; i < Count; i++) {
        System.out.println(Name[i]); // Print each line from the array
    }

    // Sort the content alphabetically using a simple bubble sort
    String Temp;
    for (int i = 0; i < Count; i++) {
        for (int j = i + 1; j < Count; j++) {
            // Compare adjacent elements in the array and swap if necessary
            if (Name[i].compareTo(Name[j]) > 0) {
                Temp = Name[i];
                Name[i] = Name[j];
                Name[j] = Temp;
            }
        }
    }
    // Print the sorted content of the file
    System.out.println();
    System.out.println("Sorted array:");
    for (int i = 0; i < Count; i++) {
        System.out.println(Name[i]); // Print each line from the sorted array
    }

    // Overwrite the original file with the sorted content
    BufferedWriter Writer = new BufferedWriter(new FileWriter(Path)); // FileWriter to write to the file
    for (int i = 0; i < Count; i++) {
        Writer.write(Name[i]); // Write each line from the sorted array to the file
        Writer.newLine(); // Ensure each line is on a new line in the file
    }
    Writer.close(); 
  System.out.println("File modified successfully!!");
}


    // Method to merge content from one file into another
private static void mergeContent(String Path) throws Exception {
    // Read the user input for the source file name
    sc.nextLine();
    System.out.println("Enter source file name (which file data you want to add in file): ");
    String Path1 = sc.nextLine();
    String Path2 = Path1 + ".txt"; // Append ".txt" to the provided file name

    // Create a File object for the source file
    File OriginalFile = new File(Path2);
    
    // Check if the source file exists
    if (!OriginalFile.exists()) {
        System.out.println("File does not exist: " + Path2);
        return;
    }
    // Initialize a LinkedList to store the lines from both files
    LinkedList List = new LinkedList();

    // Read lines from the target file and store them in the LinkedList
    BufferedReader Reader = new BufferedReader(new FileReader(Path));
    String OriginalLine;
    while ((OriginalLine = Reader.readLine()) != null) {
        List.addLast(OriginalLine);
    }
    Reader.close(); // Close the reader after reading all lines

    // Read lines from the source file and store them in the LinkedList
    BufferedReader SourceReader = new BufferedReader(new FileReader(Path2));
    String Line;
    while ((Line = SourceReader.readLine()) != null) {
        List.addLast(Line);
    }
    SourceReader.close(); // Close the reader after reading all lines

    // Clear the contents of the target file
    BufferedWriter Demowriter = new BufferedWriter(new FileWriter(Path));
    Demowriter.close(); // Close the writer immediately to clear the file

    // Display the contents of the LinkedList before writing to the file
    System.out.println("Linked List Before:");
    List.display();

    // Write the contents of the LinkedList back to the target file
    BufferedWriter ContantWriter = new BufferedWriter(new FileWriter(Path));
    String AddLines = List.deletefirstString(); // Get the first element of the LinkedList
    while (AddLines != null) {
        ContantWriter.write(AddLines); // Write the line to the file
        ContantWriter.newLine(); // Move to the next line in the file
        AddLines = List.deletefirstString(); // Get the next element from the LinkedList
    }
    ContantWriter.close(); // Close the writer after writing all lines

    // Display the contents of the LinkedList after writing to the file
    System.out.println();
    System.out.println("Linked List After:");
    List.display();

    System.out.println(); 
}
}

class LinkedList {
    static Scanner sc = new Scanner(System.in);

    class Node {
        String data;
        Node next;

        Node(String data) {
            this.data = data;
            this.next = null;
        }
    }

    Node Head = null;

    void addFirst(String data) {
        Node n = new Node(data);
        if (Head == null) {
            Head = n;
        } else {
            n.next = Head;
            Head = n;
        }
    }

    void addLast(String data) {
        Node n = new Node(data);
        if (Head == null) {
            Head = n;
        } else {
            Node Temp = Head;
            while (Temp.next != null) {
                Temp = Temp.next;
            }
            Temp.next = n;
        }
    }

    void delFirst() {
        if (Head == null) {
            System.out.println("List is empty");
        } else {
            Node del = Head;
            Head = Head.next;
            del.next = null;
        }
    }

    String deletefirstString() {
        if (Head == null) {
            return null;
        } else {
            Node del = Head;
            Head = Head.next;
            del.next = null;
            return del.data;
        }

    }

    void display() {
        if (Head == null) {
            System.out.println("List is empty");
        } else {
            Node Temp = Head;
            while (Temp != null) {
                System.out.println(Temp.data);
                Temp = Temp.next;
            }
        }
    }

    Boolean isEmpty() {
        if (Head == null)
            return true;
        else
            return false;
    }

    int Size() {
        if (Head == null) {
            return 0;
        } else {
            Node Temp = Head;
            int Count = 0;
            while (Temp != null) {
                Count++;
                Temp = Temp.next;
            }
            return Count;
        }
    }

    boolean contains(String data) {
        Node Temp = Head;
        while (Temp != null) {
            if (Temp.data.equals(data)) {
                return true;
            }
            Temp = Temp.next;
        }
        return false;
    }

    void reverseAndWriteToFile(String inputPath) {
        try {
            Stack stack = new Stack(TextFileManipulator.SizeOfFile); // Create an instance of the Stack class
            BufferedReader Reader = new BufferedReader(new FileReader(inputPath));

            String Line;
            while ((Line = Reader.readLine()) != null) {
                stack.push(Line); // Push each Line onto the stack
            }

            Reader.close();
            BufferedWriter Writer = new BufferedWriter(new FileWriter(inputPath));
            Writer.close();

            BufferedWriter ContantWriter = new BufferedWriter(new FileWriter(inputPath));
            while (!stack.isEmpty()) {
                String reversedLine = stack.pop(); // Pop Lines from the stack
                ContantWriter.write(reversedLine);
                ContantWriter.newLine();
            }

            ContantWriter.close();
            System.out.println("File modified successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


class Stack {
    String[] a;
    int top;

    Stack(int Size) {
        this.a = new String[Size];
        top = -1;
    }

    void push(String x) {
        if (top >= a.length - 1)
            System.out.println("overflow");
        else
            a[++top] = x;
    }

    String pop() {
        if (top == -1) {
            System.out.println("underflow");
            return null;
        } else

            return a[top--];
    }

    void display() {
        if (top == -1) {
            System.out.println("stack is empty!!");
        } else {
            for (int Index = top; Index >= 0; Index--) {
                System.out.println(a[Index] + " ");
            }
        }
    }

    int stacksize() {
        if (top == -1) {
            return 0;
        } else {
            int Count = 0;
            for (int Index = top; Index >= 0; Index--) {
                Count++;
            }
            return Count;
        }
    }

    boolean isEmpty() {
        if (top == -1)
            return true;
        else
            return false;
    }

    String peek() {
        if (top == -1)
            return null;
        else
            return (a[top]);
    }

    String peekindex(int Index) {
        if (top - Index + 1 <= -1) {
            return null;
        } else {
            return (a[top - Index + 1]);
        }
    }

    boolean clearstack() {
        if (top == -1) {
            return true;
        } else {
            while (top != -1) {
                pop();
            }
            return true;
        }
    }
}