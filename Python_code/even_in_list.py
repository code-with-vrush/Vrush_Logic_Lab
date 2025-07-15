#Print even numbers from a list	Input: [1, 2, 3, 4] → Output: 2 4

list_of_num= [1, 2, 3, 4]
even_list_of_num = []


for i in list_of_num:
    if i%2 == 0:
       even_list_of_num.append(i)

print(f"\nHere is Even number from this orignal numbre list:{list_of_num}\nhere is your Even number:{even_list_of_num}\n")
      