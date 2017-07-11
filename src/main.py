import numpy as np
import cv2
import sys

import tkinter

from tkinter import *
from tkinter import ttk

from gui import GUI


#test opencv

'''
print('test opencv')

# Load a color image
img_c = cv2.imread('../image/intcatchlogo.png', 1)

# Load a color image in grayscale
img_g = cv2.imread('../image/intcatchlogo.png', 0)

if img_c is not None and img_g is not None:
    print("immagini caricate correttamente")
    print("test [OK]")
else:
    print("impossibile leggere le immagini")
    print("test FAILED")
    sys.exit()
	
cv2.namedWindow('color image', 1)
cv2.imshow('color image', img_c)

cv2.namedWindow('gray scale image', 1)
cv2.imshow('gray scale image', img_g)

cv2.waitKey(0)
cv2.destroyAllWindows()


'''

root = Tk()
my_gui = GUI(master=root)
root.mainloop()
