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

    def stop(self):
        print("Stop!")

    def next(self):
        print("Next!")

    def map(self):
        print("Map!")

    def open_file(self):
        name = askopenfilename(initialdir="C:/Users/Batman/Documents/Programming/tkinter/",
                           filetypes =(("Text File", "*.txt"),("All Files","*.*")),
                           title = "Choose a file."
                           )
        print (name)
        #Using try in case user types in unknown file or closes without choosing a file.
        try:
            with open(name,'r') as UseFile:
                print(UseFile.read())
        except:
            print("No file exists")

    def log(self):
        print("Log!")
        open_file(self)

    def graph(self):
        print("Graph!")

    def about(self):
        print("About!")

    def create_widgets(self):
        #self.hi_there = tk.Button(self)
        #self.hi_there["text"] = "Hello World\n(click me)"
        #self.hi_there["command"] = self.say_hi
        #self.hi_there.pack(side="top")

        #self.quit = tk.Button(self, text="QUIT", fg="red",
        #                      command=root.destroy)
        #self.quit.pack(side="bottom")

        #self.label = Label(self.master, text="This is our first GUI!")
        #self.label.pack(side="left")

        play_image = Image.open('images/play.png')
        play_icon = ImageTk.PhotoImage(play_image)
        self.play_button = Button(self.master, text="Play", command=self.play, image=play_icon)
        self.play_button.image = play_icon  # keep a reference!
        self.play_button.pack(side="left")

        stop_image = Image.open('images/stop.png')
        stop_icon = ImageTk.PhotoImage(stop_image)
        self.stop_button = Button(self.master, text="Stop", command=self.stop, image=stop_icon)
        self.stop_button.image = stop_icon  # keep a reference!
        self.stop_button.pack(side="left")

        next_image = Image.open('images/next.png')
        next_icon = ImageTk.PhotoImage(next_image)
        self.next_button = Button(self.master, text="Next", command=self.next, image=next_icon)
        self.next_button.image = next_icon  # keep a reference!
        self.next_button.pack(side="left")

        map_image = Image.open('images/map.png')
        map_icon = ImageTk.PhotoImage(map_image)
        self.map_button = Button(self.master, text="Map", command=self.map, image=map_icon)
        self.map_button.image = map_icon  # keep a reference!
        self.map_button.pack(side="left")

        log_image = Image.open('images/log.png')
        log_icon = ImageTk.PhotoImage(log_image)
        self.log_button = Button(self.master, text="Log", command=self.log, image=log_icon)
        self.log_button.image = log_icon  # keep a reference!
        self.log_button.pack(side="left")

        graph_image = Image.open('images/graph.png')
        graph_icon = ImageTk.PhotoImage(graph_image)
        self.graph_button = Button(self.master, text="Graph", command=self.graph, image=graph_icon)
        self.graph_button.image = graph_icon  # keep a reference!
        self.graph_button.pack(side="left")

        about_image = Image.open('images/about.png')
        about_icon = ImageTk.PhotoImage(about_image)
        self.about_button = Button(self.master, text="About", command=self.about, image=about_icon)
        self.about_button.image = about_icon  # keep a reference!
        self.about_button.pack(side="left")


        self.close_button = Button(self.master, text="Close", command=self.master.quit)
        self.close_button.pack(side="left")

    def say_hi(self):
        print("hi there, everyone!")

