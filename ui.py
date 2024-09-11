import tkinter as tk
from calculator import add, subtract, multiply, divide

class CalculatorUI(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("Calculator")
        self.configure(bg="black")

        self.display = tk.Entry(self, font=("Arial", 24), fg="white", bg="black", justify="right")
        self.display.grid(row=0, column=0, columnspan=5, padx=10, pady=10, sticky="ew")

        buttons = [
            "C", "7", "8", "9", "/",
            "3", "4", "5", "6", "*",
            ".", "0", "1", "2","-",
            "=", "+"
        ]

        row = 1
        col = 0
        for button_text in buttons:
            button_fg = "black" if button_text in ["C", "="] else "red"
            button_bg = "orange" if button_text in ["C", "="] else "gray20"
            button = tk.Button(self, text=button_text, font=("Arial", 18), fg=button_fg, bg=button_bg,
                               command=lambda x=button_text: self.button_click(x))
            button.grid(row=row, column=col, padx=5, pady=5, sticky="nsew")
            col += 1
            if col > 4:
                col = 0
                row += 1

    def button_click(self, button_text):
        if button_text == "C":
            self.display.delete(0, tk.END)
        elif button_text == "=":
            try:
                expression = self.display.get()
                result = self.perform_operation(expression)
                self.display.delete(0, tk.END)
                self.display.insert(0, str(result))
            except ValueError:
                self.display.delete(0, tk.END)
                self.display.insert(0, "Error")
        else:
            self.display.insert(tk.END, button_text)

    def perform_operation(self, expression):
        try:
            numbers = []
            operation = ""
            current_number = ""

            for char in expression:
                if char.isdigit() or char == ".":
                    current_number += char
                else:
                    if current_number:
                        numbers.append(float(current_number))
                        current_number = ""
                    operation = char

            if current_number:
                numbers.append(float(current_