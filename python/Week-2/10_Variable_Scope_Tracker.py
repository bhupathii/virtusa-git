# 10. Variable Scope Tracker (global vs local)
# Problem:
# Create a system where:
# •	A global counter tracks total function calls 
# •	A local counter tracks calls inside a function 
# •	Return both counters after multiple calls 


global_c=0
def Tracker():
    global global_c
    local=0
    global_c+=1
    local+=1
    return global_c,local

print(Tracker())
print(Tracker())
print(Tracker())
print(Tracker())
print(Tracker())
    
    