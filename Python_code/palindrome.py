#Check if a string is a palindrome	Input: madam → Output: Palindrome

str = input("\nEnter you string for check your string is Palindrome or NOT: ")

if str == str[::-1]:
    print(f"\nYour Enterd string '{str}' is Palindrome\n")
else:
    print(f"\nYour Enterd string '{str}' is NOT Palindrome\n")