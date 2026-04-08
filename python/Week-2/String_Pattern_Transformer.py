# 5. String Pattern Transformer (strings as lists)
# Problem:
# Given a string:
# •	Reverse only vowels 
# •	Convert consonants to uppercase 
# •	Remove all digits 
# Return the transformed string.

s='hello21world'
vowels='aeiou'
final=[]

s=[ch for ch in s if not ch.isdigit()]
vowels_list=[]
for i in s:
    if i in vowels:
        vowels_list.append(i)
        
vowels_list[::-1]
index=0
for i in s:
    if i in vowels:
        final.append(vowels_list[index])
        index+=1
    else:
        final.append(i.upper())
        
final_s="".join(final)
print(final_s)