import math
import sys
from tkinter import *
from PIL import Image, ImageEnhance
from PIL import ImageTk
from tkinter import filedialog
import cv2
from skimage.segmentation import felzenszwalb, slic, quickshift
from skimage.segmentation import mark_boundaries
from skimage.segmentation import find_boundaries
from scipy.misc import toimage
import numpy as np
import glob
import matplotlib.pyplot as plt
import os


cap = cv2.VideoCapture('C:/workspace/intcatch_asl_luglio_2017/web/misdistanza2.avi')

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

photo_cnt = 0

if out.isOpened():print("Canale output aperto")
else:
    print("Errore output")
    sys.exit(-1)

period=366 #ms
while photo_cnt < 30:
    ret, frame = cap.read()
    curr_pos = cap.get(0)
    print("curr_pos: ", curr_pos, "period: ", period, "photo_cnt: ", photo_cnt)
    if ((period + (period*photo_cnt) - 20) < int(curr_pos) < (period + (period*photo_cnt) + 20) ):
        print("Sono nell'if")
        cv2.imshow('frame', frame)
        #out.write(frame)
        tasto = cv2.waitKey(30)
        photo_cnt += 1
        Fname = ('frame' + str(photo_cnt) + '.png')
        print(Fname)
        ret_val = cv2.imwrite(Fname, frame)
        #print(ret_val)
        if ret_val:
            print('Stampa riuscita')
        else:
            print('Errore di stampa')
        #f = filedialog.asksaveasfile(mode='wb', initialfile=Fname, defaultextension=".jpg", filetypes=(("PNG file", "*.png"), ("Jpg file", "*.jpg"),("All Files", "*.*")))
        '''if f:
            abs_path = os.path.abspath(f.name)
            visible_path = f.name
            maskOUT = toimage(frame)
            maskOUT.save(abs_path)
            visible_path = ('frame' + str(photo_cnt))
            # maskVisible = toimage(mask2saveVisible)
            # maskVisible.save(visible_path)
            # cv2.imshow("prova", mask)
            # cv2.waitKey(0)
            cv2.imwrite(visible_path, frame)'''
        if tasto & 0xFF == ord('q'):
            break

cap.release()
out.release()
cv2.destroyAllWindows()