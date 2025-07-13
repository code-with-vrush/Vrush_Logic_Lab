#Count vowels in a given string	Input: "hello" → Output: 2 vowels

str = str(input("\nEnter the string: "))
vowels = 0
vars = ['a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U']
for str in str:

     if str == "a" or str == "e" or str == "i" or str == "o" or str == "u" or str == "A" or str == "E" or str == "I" or str == "O" or str == "U":
        vowels += 1

print("\nyour entered string has",vowels,"vowels\n")
        