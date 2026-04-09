# 11. Range-Based Matrix Builder (range, loops)
# Problem:
# Given n, build an n x n matrix where:
# •	Diagonal elements = 1 
# •	Above diagonal = increasing numbers 
# •	Below diagonal = decreasing numbers 

def matrix_builder(n):
    matrix=[]
    for i in range(n):
        row=[]
        for j in range(n):
            if i==j:
                row.append(1)
            elif j>i:
                row.append(j-i+1)
            else:
                row.append(i-j+1)
        matrix.append(row)
    return matrix

n=5
result=matrix_builder(n)
for i in result:
    print(i)