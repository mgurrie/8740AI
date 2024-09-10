import unittest
from calculator import add, subtract, multiply, divide

class TestCalculator(unittest.TestCase):
    def test_add(self):
        self.assertEqual(add(2, 3), 5)
        self.assertEqual(add(1, 2, 3, 4), 10)
        self.assertEqual(add(0.5, 0.7), 1.2)

    def test_subtract(self):
        self.assertEqual(subtract(5, 3), 2)
        self.assertEqual(subtract(10, 2, 3), 5)
        self.assertEqual(subtract(1.5, 0.7), 0.8)

    def test_multiply(self):
        self.assertEqual(multiply(2, 3), 6)
        self.assertEqual(multiply(1, 2, 3, 4), 24)
        self.assertEqual(multiply(0.5, 0.7), 0.35)

    def test_divide(self):
        self.assertEqual(divide(6, 3), 2)
        self.assertEqual(divide(24, 2, 3, 4), 1)

if __name__ == "__main__":
    unittest.main()
