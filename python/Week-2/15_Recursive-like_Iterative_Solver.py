# 15. Recursive-Like Iterative Solver (while + functions)
# Problem:
# Simulate recursion using a loop:
# •	Given n, compute factorial using only while 
# •	Track intermediate steps in a list 
# •	Return both result and steps 

n=int(input())
steps=[]
i=1
res=1
while i<=n:
    res*=i
    steps.append(res)
    i+=1
    
        
print("Steps: ")
for i in steps:
    print(i)
    
print("Result: ",res)