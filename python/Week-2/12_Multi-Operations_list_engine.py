# 12. Multi-Operation List Engine (list operations + methods)
# Problem:
# Given a list:
# •	Rotate it by k positions 
# •	Remove duplicates while preserving order 
# •	Split into even and odd lists 
# Return both lists.

num=[1,2,3,4,5,6,7,8,8,9]

k=int(input("Enter the value for k: "))
rotated=num[:k]+num[k:][::-1]
remove_dupli=[]
for i in num:
    if i not in num:
        remove_dupli.append(i)
even=[]
odd=[]
for i in num:
    if i%2==0:
        even.append(i)
    else:
        odd.append(i)
        
print("Rotation by k elements: ",rotated)
print("Remove duplicates while preserving order: ",remove_dupli)
print("Even numbers: ",even)
print("Odd Numbers: ",odd)

