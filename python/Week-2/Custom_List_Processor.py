# 4. Custom List Processor (list methods)
# Problem:
# Given a list of integers:
# 1.	Remove all negative numbers 
# 2.	Insert 0 after every even number 
# 3.	Sort the list in descending order 
# Return the final list.


llist=[4,5,6,-7,8,9,-3]
res1=[]
res_list=[]
for i in llist:
    if i>0:
        res1.append(i)

for i in res1:
    if i%2==0:
        res_list.append(i)
        res_list.append(0)
    else:
        res_list.append(i)

res_list.sort(reverse=True)

print(res_list)
