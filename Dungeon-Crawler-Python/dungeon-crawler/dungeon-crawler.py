user_input = raw_input("You wake up in a dungeon. What do?")

if user_input == "go to the next room":
    print "You go to the next room."

while user_input != "quit":
    user_input = raw_input(">> ")
