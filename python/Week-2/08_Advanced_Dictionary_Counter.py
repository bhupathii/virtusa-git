# 8. Advanced Dictionary Counter (dictionaries)
# Problem:
# Given a paragraph:
# •	Count frequency of each word 
# •	Ignore case and punctuation 
# •	Return top k frequent words 
from collections import Counter
import string
para="You can add new items to a group one by one or add many items at once. Any repeated items will be ignored, keeping everything unique."
para = para.translate(str.maketrans('', '', string.punctuation))
para=para.lower()
words=para.split()
frequency=Counter(words)

k=int(input("Enter the value for k: "))
print(frequency.most_common(k))