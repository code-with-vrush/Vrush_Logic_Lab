#Swap two numbers without using a third variable	Input: a=5, b=10 → Output: a=10, b=5


a = int(input("\nEnter the first number: "))
b = int(input("Enter the second number: ")) 
print("\n-----------------")
print("|Before swapping|")
print("-----------------")
print("1st number is A=",a)
print("2nd number is B=",b)

a = a + b
b = a - b
a = a - b

print("\n-----------------")
print("|After swapping|")
print("-----------------")
print("your swapped number is A=",a)
print("your swapped number is B=",b,"\n")

