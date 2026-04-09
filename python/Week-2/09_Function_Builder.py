#  9. Function Builder (functions, parameters, return)
# Problem:
# Write a function that:
# •	Takes a list and a threshold value 
# •	Returns a new list of elements greater than threshold 
# •	Also returns their count 

def Func(list,value):
    res=[]
    for i in list:
        if i>value:
            res.append(i)
    return res       
elements=[1,2,5,3,7,6,8,9,12]
ans=Func(elements,3)
print(ans)
print(len(ans))


        