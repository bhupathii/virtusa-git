# 6. Tuple-Based Record System (tuples)
# Problem:
# You are given a list of tuples (name, score).
# Return:
# •	The name of the student with highest score 
# •	The average score 
# •	A tuple of all students scoring above average 

records=[("kiran",85),("Sayeed",97),("Bhupathi",80),("Vijay",90),("Ravi",27)]

maxi=0
top_student=""
total=0
for name,mark in records:
    if mark>maxi:
        total+=mark
        maxi=mark
        top_student=name
avg=total/len(records)

above_avg=[]
for name,mark in records:
    if mark>=avg:
        above_avg.append((name,mark))
        
above_avg=tuple(above_avg)

print("Name of the student with Highest Score: ",top_student)
print("The average score: ",avg)
print("Above Average Students: ",above_avg)





