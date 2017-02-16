import sys

user_input = raw_input("You wake up in a dungeon. What do?")

dungeon_map = 10
current = 0;

while user_input != "quit":
    if user_input == "go to the next room":
        if (current < 10):
            print "You go to the next room."
            current += 1
        elif (current == 10):
            print "You have escaped the dungeon."
            raise SystemExit(0)
        else:
            print "You cannot go that way..."

    user_input = raw_input(">> ")
