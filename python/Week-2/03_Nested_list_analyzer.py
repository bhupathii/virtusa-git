# 3. Nested List Analyzer (lists, for loop, list operations)
# Problem:
# Given a nested list of integers, return:
# •	Sum of each sublist 
# •	Maximum value among all sublists 
# •	Flattened list without duplicates 

arr=[[1,2,3],[4,5,6],[7,8,9]]
wihtout_dupli=[]
maxi=[]
sum_arr=[]
def summ(list):
    x=0
    for i in list:
        x+=i
    return x

for i in arr:
    sum_arr.append(summ(i))
    maxi.append(max(i))
for i in arr:
    for j in i:
        if j not in wihtout_dupli:
            wihtout_dupli.append(j)
            
print("Sum of each sublist= ",sum_arr)
print("Maximum value of each sublist= ",maxi)
print("Flattened list wihtout duplicates=" ,wihtout_dupli)


