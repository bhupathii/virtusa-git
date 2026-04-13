# 14. Hybrid Data Structure Processor (lists + tuples + sets + dicts)
# Problem:
# Given mixed data:
# •	Extract unique elements 
# •	Count occurrences 
# •	Store results as {element: count} 
# •	Return sorted list of (element, count) tuples 

data=[2,3,4,3,1,2,3,4,6,4,1,2,1,1,2,2]

unique_elements=set(data)
occurences={}
for i in unique_elements:
    c=data.count(i)
    occurences[i]=c
    
sorted_list=sorted(occurences.items())

print("Unique Elements: ",unique_elements)
print("Occurences: ",occurences)
print("Sorted list of (elements, count)",sorted_list)