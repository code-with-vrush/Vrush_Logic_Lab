#Write a function to find factorial of a number	Input: 5 → Output: 120

f_num = int(input("\nEnter the number: "))
factorial = 1

if f_num<0:
    print("we cant't find factorial this ",f_num,"Nagtive number")
elif f_num==0:
    print("For zero(0) factorial  is 0 or 1")
else:
    for i in range(1,f_num+1):
        factorial*=i
    
print("\nYour factorial of this",f_num, "is" ,factorial)





