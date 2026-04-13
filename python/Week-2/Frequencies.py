# 16. Problem:
# You are given raw data (list of strings):
# 1.	Clean data (remove digits, special chars) 
# 2.	Convert to lowercase 
# 3.	Store word frequencies using dictionary 
# 4.	Use set to remove duplicates 
# 5.	Return top 3 frequent words as tuples (word, count) 

import re
from collections import Counter

def process_text(data):
    cleaned_words=[]

    for line in data:
        line = re.sub(r'[^a-zA-Z\s]','', line)
        line = line.lower()
        cleaned_words.extend(line.split())

    freq = Counter(cleaned_words)
    
    unique_words = set(cleaned_words)

    top3 = sorted(freq.items(), key=lambda x: x[1], reverse=True)[:3]

    return unique_words, dict(freq), top3

# Example
data = ["Hello123 world!!", "Hello AI!!", "AI is amazing 2024"]

unique, freq, top3 = process_text(data)

print("Unique Words:", unique)
print("Frequencies:", freq)
print("Top 3:", top3)