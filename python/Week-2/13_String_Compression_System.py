# 13. String Compression System (strings, loops)
# Problem:
# Implement basic compression:
# •	"aaabbc" → "a3b2c1" 
# •	Return original string if compressed version is longer 

def compress(s):
    res=''
    count=1
    for i in range(len(s)):
        if i+1<len(s) and s[i]==s[i+1]:
            count+=1
        else:
            res+=s[i]+str(count)
            count=1
            
    return res if len(res)<len(s) else s

s='aaabbbbccccddddddd'
print(compress(s))