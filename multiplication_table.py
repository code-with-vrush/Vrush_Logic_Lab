#Print multiplication table of a number from 1 to 10	Input: 2 → Output: 2x1=2 ... 2x10=20

num_of_table = int(input("\nEnter your table like for e.g(if want table of 2,4,6 etc..):"))
print(f"\nhere we print table of {num_of_table}\n")
for i in range(1,10+1):
    print(num_of_table,"x",i,"=",num_of_table*i)

