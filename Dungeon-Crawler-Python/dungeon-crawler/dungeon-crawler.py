import sys
import random

class Room(object):

    def __init__(self):

        self.items = []
        
        for i in range(0, random.randint(1, 10)):
            self.items.append("Item %d" %  i)

    def get_items(self):
        return self.items

rooms = []

for i in range(0, 10):
    rooms.append(Room())

current = 0;

user_input = raw_input("You wake up in a dungeon. What do?").lower()

while user_input != "quit":
    if user_input == "go to the next room":
        if (current < len(rooms)):
            print "You go to the next room."
            current += 1
        elif (current == len(rooms)):
            print "You have escaped the dungeon."
            raise SystemExit(0)
        else:
            print "You cannot go that way..."
    elif user_input == "look around":
        print rooms[current].get_items()
    else:
        print "Invalid action... Try again."

    user_input = raw_input(">> ").lower()
