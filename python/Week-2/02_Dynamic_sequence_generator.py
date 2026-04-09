# 2. Dynamic Sequence Generator (while loop, range, lists)
# Problem:
# Given an integer n, generate a sequence:
# •	Start with [1] 
# •	If current number is even → divide by 2 
# •	If odd → multiply by 3 and add 1 
# •	Stop when sequence reaches 1 or length exceeds n 
# Return the generated list.


n=int(input())

res=[]
while n>=1 and len(res)<=n:
    if n%2==0:
        res.append(n//2)
        n/=2
    else:
        res.append((n//3)+1)
        n/=(3)+1
print(res)