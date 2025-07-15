#Sum all elements in a list	Input: [1, 2, 3] → Output: 6

a = int(input("\nHow many numbers you want to enter: "))
list_of_num = []
sum = 0

for i in range(0,a):
    num = int(input("\nEnter the number: "))
    list_of_num.append(num)

for i in list_of_num:
    sum += i
print("\nyour entered list has",sum,"sum\n")
