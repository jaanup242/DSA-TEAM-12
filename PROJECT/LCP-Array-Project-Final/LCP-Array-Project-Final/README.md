# LCP Array

**Title:** LCP Array  
**Description:** Compute the Longest Common Prefix array from a suffix array and visualize the result.

## 1. VS Code terminal / dry run
Open the project in VS Code, then:

```powershell
cd java
javac LCPArray.java
java LCPArray
```

Enter a string such as `banana` to see suffixes, suffix array, LCP array, repeated patterns, and Naive vs Kasai timing in the terminal.

## 2. Website through Live Server
Open `frontend/index.html` and choose **Open with Live Server**. The website independently computes and visualizes the suffix array and LCP array; it does not need a Java server or `localhost:8080`.

## Structure

```text
LCP-Array/
├── java/
│   └── LCPArray.java
├── frontend/
│   ├── index.html
│   ├── style.css
│   └── script.js
└── README.md
```
