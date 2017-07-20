import scipy.interpolate as sp
import numpy as np
import pylab
import matplotlib.pyplot as plt
#COST = 293.7
COST = 328.3

dat= list()
plt.axis([0, 800, 150, 600])
dat= np.zeros([2, 70], float)
'''
for x in range (1, 71):
    t = float(x / 10)
    plt.plot([t], [(COST / t) + 150], 'go')
    #plt.plot([x], [(cost / x) + 150])
    #plt.show()
'''
for x in range (1, 71):
    t = float(x / 10)
    dat[0][x-1]=t*108
    dat[1][x-1]=((COST / t) + 150)
plt.plot(dat[0], dat[1], 'green')

plt.plot([0.5*108, 1*108, 1.5*108, 2*108, 2.5*108, 3*108, 4*108, 5*108, 5.5*108, 6.5*108, 7*108], [568.5, 444.7, 375, 339.5, 270, 259.3, 225.5, 207.5, 220, 210.2, 192], 'ro')
plt.xlabel("distance to obstacle (cm)")
plt.ylabel("pixels from top of image")
plt.show()
