#  1. Smart Number Classifier (Flow Control, if-elif, indentation)
# Problem:
# Given an integer n, classify it based on the following rules:
# •	If divisible by both 3 and 5 → "FizzBuzz" 
# •	If divisible by 3 → "Fizz" 
# •	If divisible by 5 → "Buzz" 
# •	If prime → "Prime" 
# •	Otherwise → "Composite" 
# Return the classification.

def is_prime(n):
    if n<2:
        return False
    for i in range(2,int(n**0.5)+1):
        if n%i==0:
            return False
    return True

n=int(input())
if n%3==0 and n%5==0:
    print("FizzBuzz")
elif n%3==0:
    print("Fizz")
elif n%5==0:
    print("Buzz")
elif is_prime(n):
    print("Prime")
else:
    print("Composite")