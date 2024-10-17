package texteditor;

import java.util.Scanner;
import java.util.Stack;

public class TextEditor {
    private StringBuilder currentText;
    private Stack<String> undoStack;
    private Stack<String> redoStack;

    public TextEditor() {
        currentText = new StringBuilder();
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }

    public void write(String text) {
        undoStack.push(currentText.toString());
        currentText.append(text);
        redoStack.clear();
    }

    public void show() {
        System.out.println("Text saat ini: " + currentText.toString());
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            redoStack.push(currentText.toString());
            currentText = new StringBuilder(undoStack.pop());
        } else {
            System.out.println("Tidak ada yang bisa di undo.");
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            undoStack.push(currentText.toString());
            currentText = new StringBuilder(redoStack.pop());
        } else {
            System.out.println("Tidak ada yang bisa di redo.");
        }
    }

    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        Scanner scanner = new Scanner(System.in);
        String command;
        
        System.out.println("=== Text Editor ===");
        System.out.println("Perintah: write, show, undo, redo, exit");
      
        while (true) {
            System.out.print("\nMasukkan perintah: ");
            command = scanner.nextLine().trim().toLowerCase();
            
            switch (command) {
                case "write":
                    System.out.print("Massukkan text: ");
                    String text = scanner.nextLine();
                    editor.write(text);
                    break;
                    
                case "show":
                    editor.show();
                    break;
                    
                case "undo":
                    editor.undo();
                    break;
                    
                case "redo":
                    editor.redo();
                    break;
                    
                case "exit":
                    System.out.println("Keluar dari text editor.");
                    scanner.close();
                    return;
                    
                default:
                    System.out.println("Perintah salah. Mohon gunakan: write, show, undo, redo, exit.");
            }
        }
    }
}
