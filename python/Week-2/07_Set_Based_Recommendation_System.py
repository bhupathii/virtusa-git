# 7. Set-Based Recommendation System (sets)
# Problem:
# Given two users’ liked items:
# •	Return common items 
# •	Return unique items liked by only one user 
# •	Recommend items from user B not seen by user A 

userA={"Apple","Mango","Banana","Grapes"}
userB={"Orange","Apple","Kiwi","Water"}

common=set()
unique=set()
recommend=set()

for i in userA:
    if i in userB:
        common.add(i)
    if i not in userB:
        unique.add(i)

for i in userB:
    if i not in userA:
        recommend.add(i)
        unique.add(i)
        
print("Common items: ",common)
print("Unique items: ",unique)
print("Items for user A not seen by User B: ",recommend)
