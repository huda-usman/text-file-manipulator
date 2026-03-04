<div align="center">

<!-- BANNER -->
<img src="https://capsule-render.vercel.app/api?type=waving&color=0:0d1120,50:00e5ff,100:0d1120&height=200&section=header&text=Text%20File%20Manipulator&fontSize=42&fontColor=ffffff&fontAlignY=38&desc=DSA-Powered%20File%20Operations%20%7C%20Java%20%2B%20Web%20UI&descAlignY=58&descSize=16&animation=fadeIn" width="100%"/>

<br/>

<!-- BADGES -->
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![HTML5](https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white)
![CSS3](https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white)
![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black)
![DSA](https://img.shields.io/badge/DSA-Stack%20%7C%20LinkedList%20%7C%20Array-00e5ff?style=for-the-badge)
![License](https://img.shields.io/badge/License-MIT-green?style=for-the-badge)

<br/>

> **A menu-driven Java console application & sleek browser UI demonstrating core DSA concepts**
> **through real-world file manipulation — powered by custom Stack, Singly LinkedList & Arrays.**

<br/>

[🚀 Quick Start](#-quick-start) • [✨ Features](#-features) • [🧠 DSA Deep Dive](#-dsa-deep-dive) • [🌐 Web UI](#-web-ui) • [🧪 Testing Guide](#-testing-guide) • [📁 Project Structure](#-project-structure)

</div>

---

## 📌 Overview

**Text File Manipulator** is a dual-interface project — a **Java console app** and a **modern web UI** — that brings Data Structures & Algorithms to life through practical file operations. Instead of abstract textbook examples, every DSA concept here solves a real problem: deduplication, clipboard management, sorting, content insertion, and file merging.

The project was built as part of a **Data Structures & Algorithms course** to demonstrate that foundational CS concepts — stacks, linked lists, arrays — aren't just theory. They're the engines behind the tools we use every day.

No `java.util.Stack`. No `java.util.LinkedList`. Everything is built from scratch.

---

## ✨ Features

<table>
<thead>
<tr>
<th align="center">#</th>
<th align="left">Operation</th>
<th align="left">What It Does</th>
<th align="left">DSA Used</th>
</tr>
</thead>
<tbody>
<tr>
<td align="center"><b>1</b></td>
<td>🔁 <b>Eliminate Repeated Lines</b></td>
<td>Scans every line and removes all duplicates, keeping first occurrence</td>
<td><code>Singly LinkedList</code></td>
</tr>
<tr>
<td align="center"><b>2</b></td>
<td>↕️ <b>Reverse Content</b></td>
<td>Flips the entire file — last line becomes first</td>
<td><code>Stack (LIFO)</code></td>
</tr>
<tr>
<td align="center"><b>3</b></td>
<td>➕ <b>Insert New Line</b></td>
<td>Inserts a new line before every occurrence of a target keyword</td>
<td><code>Two Stacks</code></td>
</tr>
<tr>
<td align="center"><b>4</b></td>
<td>📋 <b>Copy Text</b></td>
<td>Copies any line by number into a Stack-based clipboard</td>
<td><code>Stack (peek)</code></td>
</tr>
<tr>
<td align="center"><b>5</b></td>
<td>📌 <b>Paste Text</b></td>
<td>Pastes clipboard content at any specified line position</td>
<td><code>Singly LinkedList</code></td>
</tr>
<tr>
<td align="center"><b>6</b></td>
<td>✂️ <b>Cut Line</b></td>
<td>Removes a line from the file and stores it in the clipboard</td>
<td><code>Stack + LinkedList</code></td>
</tr>
<tr>
<td align="center"><b>7</b></td>
<td>🔤 <b>Sort Content</b></td>
<td>Sorts all lines alphabetically using in-place Bubble Sort</td>
<td><code>Array + Bubble Sort</code></td>
</tr>
<tr>
<td align="center"><b>8</b></td>
<td>💾 <b>Write / Merge File</b></td>
<td>Appends another file's content into the current file</td>
<td><code>Singly LinkedList</code></td>
</tr>
</tbody>
</table>

---

## 🧠 DSA Deep Dive

### 📦 Custom Stack

A fully hand-written array-backed stack — the backbone of clipboard and reversal operations.

```java
class Stack {
    String[] a;
    int top;

    Stack(int size)          // initialize with file size
    void push(String x)      // add to top
    String pop()             // remove from top
    String peek()            // view top without removing
    String peekIndex(int i)  // view any element by index
    void display()           // print all elements
    boolean isEmpty()        // check if empty
    boolean clearStack()     // wipe entire stack
    int stackSize()          // count elements
}
```

**Used in:** Reverse (ops 2), Insert (op 3), Copy (op 4), Cut (op 6)

---

### 🔗 Custom Singly LinkedList

A hand-written singly linked list with an inner `Node` class — used to hold file content in memory during rewriting.

```java
class LinkedList {
    class Node {
        String data;
        Node next;
    }

    void addFirst(String data)           // prepend node
    void addLast(String data)            // append node
    String deletefirstString()           // pop from head
    boolean contains(String data)        // linear search
    void display()                       // print all nodes
    boolean isEmpty()                    // null head check
    int size()                           // count nodes
    void reverseAndWriteToFile(String p) // stack-based reversal + file write
}
```

**Used in:** Eliminate duplicates (op 1), Paste (op 5), Cut (op 6), Merge (op 8)

---

### 📐 Array + Bubble Sort

For sorting, all file lines are loaded into a plain `String[]` array and sorted using a classic **Bubble Sort** with `String.compareTo()` for lexicographic comparison.

```java
String[] Name = new String[SizeOfFile];
// ... read lines into array ...

for (int i = 0; i < count; i++) {
    for (int j = i + 1; j < count; j++) {
        if (Name[i].compareTo(Name[j]) > 0) {
            // swap
            String temp = Name[i];
            Name[i] = Name[j];
            Name[j] = temp;
        }
    }
}
```

**Time Complexity:** O(n²) — intentionally kept as Bubble Sort for educational demonstration.

---

### 🔄 How Insert New Line Works (Two-Stack Technique)

```
Original Stack (lines pushed in order):
TOP → Zara | Kamil | Nadia | Omar | ...

Pop each line:
  → If line contains "Omar": push new line FIRST, then push "Omar"
  → Else: push line as-is

Reverse the result stack → correct order restored
Write to file
```

This preserves order while inserting — a classic two-stack pattern.

---

## 🚀 Quick Start

### ☕ Java Console App

**Prerequisites:** Java JDK 8 or higher

```bash
# Clone the repo
git clone https://github.com/hudausman010/text-file-manipulator.git
cd text-file-manipulator

# Compile
cd src
javac TextFileManipulator.java

# Copy sample file to the same directory
cp ../samples/file1.txt .

# Run
java TextFileManipulator
```

> ⚠️ When prompted, enter the filename **without** the `.txt` extension — just type `file1`

**Expected console output:**
```
-------------------------------------------------------------------------
           T E X T   F I L E   M A N I P U L A T O R
-------------------------------------------------------------------------
Enter File Name in which you want changes :
> file1

Huda
Omar
Bilal
...
Length of File : 193

------------------------------------------------------------------
 PRESS :
(1). Eliminate repeated Lines from the file
(2). Reverse the content of file
(3). Insert new Line
(4). Copy text
(5). Paste text Line
(6). Cut the Line
(7). Sort the content of the file
(8). Write one file content to another file
(9). Exit
```

---

### 🌐 Web UI

No installation. No server. Just open in a browser:

```bash
# From the project root
open web/index.html
# or double-click it in your file explorer
```

---

## 🌐 Web UI

The web interface is a **single self-contained HTML file** — no frameworks, no build tools, no dependencies. Everything runs in the browser.

### Interface Highlights

| Feature | Description |
|---------|-------------|
| 🌙 / ☀️ **Theme Toggle** | Smooth dark ↔ light mode switch with animated pill toggle |
| 📂 **Drag & Drop Upload** | Drop any `.txt` file directly onto the upload zone |
| 🖥️ **Split Pane View** | Input and output side-by-side with line numbers |
| 📋 **Live Clipboard Badge** | Shows what's currently copied/cut in real time |
| 💾 **Download Output** | Save the modified content as a new `.txt` file |
| 🔢 **Line Numbers** | Both panes show line numbers for easy reference |
| 🔔 **Toast Notifications** | Every action gives instant feedback with color-coded toasts |

### Screenshots

> 🌑 Dark Mode — terminal-style with cyan neon accents and grid background

> ☀️ Light Mode — clean paper-white with deep navy text and teal accents

---

## 🧪 Testing Guide

Use `samples/file1.txt` which contains **28 names with 7 intentional duplicates** — crafted to make every operation visually interesting.

```
Huda, Omar, Bilal, Imran, Sana, Mariam, Umer, Asad, Farhan, Zara,
Saad, Hina, Omar✦, Bilal✦, Nadia, Huda✦, Rashid, Tariq, Sana✦,
Yusuf, Layla, Farhan✦, Mehreen, Junaid, Nadia✦, Khansa, Kamil, Zara✦

✦ = duplicate
```

### Operation-by-Operation Test Cases

```
Op 1 — Eliminate Repeated Lines
  Expected: 21 unique lines remain, 7 removed (Omar, Bilal, Sana, Farhan, Nadia, Huda, Zara)

Op 2 — Reverse Content
  Expected: Zara is now line 1, Huda is now line 28

Op 3 — Insert New Line
  Keyword:  Omar
  New Line: ── SECTION BREAK ──
  Expected: "── SECTION BREAK ──" appears before both occurrences of Omar

Op 4 — Copy Text
  Line: 3  (Bilal)
  Expected: Clipboard badge shows "Bilal"

Op 5 — Paste Text
  After copying line 3, paste at line 15
  Expected: "Bilal" inserted before line 15, all lines shift down

Op 6 — Cut Line
  Line: 5  (Sana)
  Expected: Sana removed from file, clipboard badge shows "Sana"

Op 7 — Sort Content
  Expected: Asad appears first, Zara appears last

Op 8 — Merge File
  Paste extra names in the merge textarea
  Expected: New names appended after the last line
```

---

## 📁 Project Structure

```
text-file-manipulator/
│
├── 📂 src/
│   └── TextFileManipulator.java    # Java console app
│                                   # Contains: TextFileManipulator, LinkedList, Stack
│
├── 📂 web/
│   └── index.html                  # Complete web UI — HTML + CSS + JS in one file
│                                   # Dark/light theme, split pane, clipboard badge
│
├── 📂 samples/
│   └── file1.txt                   # 28-name demo file with 7 intentional duplicates
│
├── .gitignore                      # Ignores *.class, IDE files, OS files
└── README.md                       # You are here
```

---

## ⚙️ How File I/O Works

The Java app uses three different file I/O strategies depending on the operation:

| Strategy | Used For |
|----------|----------|
| `RandomAccessFile` | Initial file read at startup (byte-by-byte) |
| `BufferedReader` + `BufferedWriter` | Most operations (line-by-line read → modify → rewrite) |
| `FileWriter` (empty write) | Clearing file contents before rewriting |

The file is **modified in-place** — the original file is overwritten with the processed result after each operation.

---

## 🛠️ Built With

<div align="center">

| Technology | Role |
|------------|------|
| ☕ **Java SE 8+** | Core logic, DSA implementations, file I/O |
| 🌐 **HTML5** | Web interface structure |
| 🎨 **CSS3** | Glassmorphism UI, animations, dark/light theming |
| ⚡ **Vanilla JavaScript** | All 8 operations in the browser, no frameworks |
| 🔤 **Google Fonts** | Orbitron · JetBrains Mono · Syne |

</div>

---

## 📄 License

This project is licensed under the **MIT License** — feel free to use, modify, and distribute.

---

<div align="center">

<img src="https://capsule-render.vercel.app/api?type=waving&color=0:0d1120,50:00e5ff,100:0d1120&height=100&section=footer" width="100%"/>

### 🙋‍♀️ Connect with Me

Made with ❤️ and lots of ☕ by **Huda Usman**

[![LinkedIn](https://img.shields.io/badge/LinkedIn-Huda%20Usman-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/hudausman010)

*"Data structures aren't just theory — they're the engines behind every tool we build."*

</div>
