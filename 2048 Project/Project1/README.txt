Luke Oluoch
Project: 1
MW 2:00-3:15 Lab

A few notes for how I made my program. It is a GUI that used JPanels for the frame. It used WASD for directional movements, Q to quit, and R to reset. Upon entering Q or R,
you will be prompted by the console to confirm. This uses a scanner, so you have to choose Y/N to do so. For your ease, I made the escape key a quic reset, in case you
needed to check some things.

Known bugs I couldn't figure out how to fix:
+Every movement moves to the grid right before the last grid, so to move a number to the borders initially takes two key presses, but this stops being a problem down the line
+The numbers only sum when they are directly adjacent
+Once the grid is filled, every move starts being called an invalid move

Those were the ones I couldn't conquer. 

Upon winning or losing, the program automatically closes, and the console prints your victory/loss statement, plus the counter count. Every valid move also prints the counter.
I left that in so you know it works well.