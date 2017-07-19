import tkinter as tk
import cv2
import numpy as np

from tkinter import Tk, Label, Button, PhotoImage
from tkinter import ttk
from tkinter.filedialog import askopenfilename

from PIL import Image, ImageTk

from logreader import LogReader
from mapreader import MapReader

class Gui(tk.Frame):

    def __init__(self, master=None):
        super().__init__(master)
        self.pack()
        self.create_widgets()
        self.gps_collection = list()

    def play(self):
        print("Play!")
        self.graph_button.configure(state="active")
        img = self.map.getMapImage()
        height, width = img.shape[:2]
        prop = float(height) / float(width)
        img_resized = np.zeros((height * (3.0 / 4.0), width * (3.0 / 4.0), 3), np.uint8)
        cntcnt=0
        print(img.shape)
        '''img[100:120, 100:120] = np.zeros(img[100:120, 100:120].shape)
        img[100:120, 100:120, 0] = 255*np.ones(img[100:120, 100:120, 0].shape)'''
        #for j in range (100, 120):
        #    for k in range (100, 120
        #    img[100, 100] = 255, 255, 0)
        for i in range (0, len(self.gps_collection)):
            coor = self.map.convert([self.gps_collection[i][0][0], self.gps_collection[i][0][1]])
            #print(self.gps_collection[i][0][0])
            #coor = self.map.convert([658295, 5024849])
            #print(coor)
            #cv2.circle(img, int(coor), 5, (0, 0, 255), 1)
            #print(img[coor])

            blue = img[int(coor[1])][int(coor[0])][0]
            green = img[int(coor[1])][int(coor[0])][1]
            red = img[int(coor[1])][int(coor[0])][2]
            #img[int(coor[1])][int(coor[0])] = [0, 0, 0]
            print(blue, green, red)
            '''if cntcnt == 0:
                blue = 255
                green = 202
                red = 156
                cntcnt += 1'''
            #if np.all(img[int(coor[0]), int(coor[1])] == (255, 202, 156)):
            if (blue > 245 and 192 < green < 212 and 146 < red < 166) or (red == 255 and (green + blue) == 0):
            #if blue != 229 and green != 237 and red != 240:
                cv2.circle(img, (int(coor[0]), int(coor[1])), 3, (0, 0, 255), 1)
            else:
                #print ("blue: " + str(blue) + "   green: " + str(green) + "   red: " + str(red))
                cv2.circle(img, (int(coor[0]), int(coor[1])), 3, (0, 255, 0), 1)
                print(coor[0], coor[1])
            img_resized = cv2.resize(img, (int(height * (3.0 / 4.0)), int(int(height * (3.0 / 4.0)) * prop)), fx=2, fy=2, interpolation=cv2.INTER_CUBIC)
            cv2.imshow("map", img_resized)
            #cv2.imshow("map", img)
            cv2.waitKey(1)

        #self.gps_collection

    def stop(self):
        print("Stop!")


    def next(self):
        print("Next!")   

    def map(self):
        print("Map!")
        self.stop_button.configure(state="active")
        self.next_button.configure(state="active")
        self.play_button.configure(state="active")
        self.map_button.configure(state="disabled")

        name = askopenfilename(initialdir="C:/Users/Batman/Documents/Programming/tkinter/", filetypes=(("image", "*.png"), ("All Files", "*.*")), title="Choose a file.")
        print(name)
        try:
            with open(name, 'r') as UseFile:
                print(UseFile.read())
        except:
            print("No file exists")

        map_reader = MapReader(name)
        map_reader.read()
        self.map = map_reader.getMap()
		
	
    def log(self):
        print("Log!")
        self.log_button.configure(state="disabled")
        self.map_button.configure(state="active")
		
        name = askopenfilename(initialdir="C:/Users/Batman/Documents/Programming/tkinter/", filetypes =(("Text File", "*.txt"),("All Files","*.*")), title = "Choose a file.")
        print (name)
        #Using try in case user types in unknown file or closes without choosing a file.
        try:
            with open(name,'r') as UseFile:
                print("Log file opened!")
        except:
            print("No file exists")
			
        log_reader = LogReader(name)
        log_reader.read()
        self.gps_collection = log_reader.gps_collector
	
    def graph(self):
        print("Graph!")

    def about(self):
        print("About!")
        print("tk")
        aboutwindow = AboutWindow()

    def create_widgets(self):
        
        play_image = Image.open('icon/play.png')
        play_icon = ImageTk.PhotoImage(play_image)
        self.play_button = Button(self.master, text="Play", command=self.play, image=play_icon)
        self.play_button.image = play_icon  # keep a reference!
        self.play_button.configure(state="disabled")
        self.play_button.pack(side="left")

		  
        stop_image = Image.open('icon/stop.png')
        stop_icon = ImageTk.PhotoImage(stop_image)
        self.stop_button = Button(self.master, text="Stop", command=self.stop, image=stop_icon)
        self.stop_button.image = stop_icon  # keep a reference!
        self.stop_button.configure(state="disabled")
        self.stop_button.pack(side="left")



        next_image = Image.open('icon/next.png')
        next_icon = ImageTk.PhotoImage(next_image)
        self.next_button = Button(self.master, text="next", command=self.next, image=next_icon)
        self.next_button.image = next_icon  # keep a reference!
        self.next_button.configure(state="disabled")
        self.next_button.pack(side="left")
		
        map_image = Image.open('icon/map.png')
        map_icon = ImageTk.PhotoImage(map_image)
        self.map_button = Button(self.master, text="map", command=self.map, image=map_icon)
        self.map_button.image = map_icon  # keep a reference!
        self.map_button.configure(state="disabled")
        self.map_button.pack(side="left")
		
        log_image = Image.open('icon/log.png')
        log_icon = ImageTk.PhotoImage(log_image)
        self.log_button = Button(self.master, text="log", command=self.log, image=log_icon)
        self.log_button.image = log_icon  # keep a reference!
        self.log_button.pack(side="left")
		
        graph_image = Image.open('icon/graph.png')
        graph_icon = ImageTk.PhotoImage(graph_image)
        self.graph_button = Button(self.master, text="graph", command=self.graph, image=graph_icon)
        self.graph_button.image = graph_icon  # keep a reference!
        self.graph_button.configure(state="disabled")
        self.graph_button.pack(side="left")
		
        about_image = Image.open('icon/about.png')
        about_icon = ImageTk.PhotoImage(about_image)
        self.about_button = Button(self.master, text="about", command=self.about, image=about_icon)
        self.about_button.image = about_icon  # keep a reference!
        self.about_button.pack(side="left")
		
		
class AboutWindow(tk.Frame):
		
		
    def __init__(self, master=None):
        print("init")
        super().__init__(master)

        print("prima di create window")
        self.create_window()
        self.pack()

    def create_window(self):
        t = tk.Toplevel(self)

        l = tk.Label(t, text="Rossi Alice e Ricci Francesco e Steccanella Lorenzo. \n ® 2017")
        l.pack(side="top", fill="both", expand=True, padx=100, pady=30)




      
