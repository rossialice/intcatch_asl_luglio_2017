import tkinter as tk

from tkinter import Tk, Label, Button, PhotoImage

from tkinter import ttk
from tkinter.filedialog import askopenfilename

from PIL import Image, ImageTk

class GUI(tk.Frame):

    def __init__(self, master=None):
        super().__init__(master)
        self.pack()
        self.create_widgets()

    def play(self):
        print("Play!")
        self.graph_button.configure(state="active")

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
	
    def log(self):
        print("Log!")
        self.log_button.configure(state="disabled")
        self.map_button.configure(state="active")
	
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

        print("ultimo")
        #t.wm_title("Window #%s" % self.counter)
        l = tk.Label(t, text="Rossi Alice e Ricci Francesco. \n ® 2017")
        l.pack(side="top", fill="both", expand=True, padx=100, pady=30)




      
