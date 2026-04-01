userInput = list(map(int,input().split()))
res=sum(userInput)
if res%2==0:
    print("even")
else:
    print("odd")