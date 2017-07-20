import numpy as np
import cv2
import math
import sys

cap = cv2.VideoCapture('C:/Users/pc/Videos/Movavi Library/Ultimissimotentativo.avi')

#fourcc = cap.get(6)
fourcc = cv2.VideoWriter_fourcc(*'XVID')
#fo2=int(fourcc)
print("fourcc: "+str(fourcc))
fps= cap.get(5)
print("fps: "+str(fps))
width= cap.get(3)
w2=int(width)
print("width: "+str(width))
height= cap.get(4)
h2=int(height)
print("height: "+str(height))
#out = cv2.VideoWriter('output.avi',fourcc, fps, (width,height))
out = cv2.VideoWriter('output.avi',fourcc, fps, (w2,h2))

if out.isOpened():print("Canale output aperto")
else:
    print("Errore output")
    sys.exit(-1)

period=10000 #ms

scrivi=False

cnt=1

while(cap.isOpened()):
    ret, frame = cap.read()
    curr_pos = cap.get(0)
    if curr_pos > (( cnt * period )):
        scrivi=not scrivi
        cnt += 1
        print("scrivi")
        for i in range(0,30):
            ret, frame = cap.read()

    if scrivi:


        cv2.imshow('frame', frame)
        out.write(frame)
        tasto = cv2.waitKey(0)
        if tasto & 0xFF == ord('q'):
            break

'''
        print(curr_pos)

        #if scrivi==True:
         #   frame = cv2.flip(frame,0)

        # write the flipped frame
          #  out.write(frame)

            cv2.imshow('frame',frame)
            tasto = cv2.waitKey(30)
            if tasto & 0xFF == ord('q'):
                break'''
    #else:
      #  break

cap.release()
out.release()
cv2.destroyAllWindows()