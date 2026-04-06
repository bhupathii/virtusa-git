num=int(input())
res=[]
for i in range(1,num//+1):
    if num%i==0:
        res.append(i)
res.append(num)
print(*res)