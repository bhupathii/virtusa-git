num=int(input())
for i in range(2,int((num**0.5))+1):
    if num%i==0:
        print("yes")
        break
else:
    print("no")