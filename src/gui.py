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

    def create_widgets(self):
        
        play_image = Image.open('icon/play.png')
        play_icon = ImageTk.PhotoImage(play_image)
        self.play_button = Button(self.master, text="Play", command=self.play, image=play_icon)
        self.play_button.image = play_icon  # keep a reference!
        self.play_button.pack(side="left")
		
class GUI(tk.Frame):

    def __init__(self, master=None):
        super().__init__(master)
        self.pack()
        self.create_widgets()
		
    def stop(self):
        print("Stop!")    

    def create_widgets(self):
        
        stop_image = Image.open('icon/stop.png')
        stop_icon = ImageTk.PhotoImage(stop_image)
        self.stop_button = Button(self.master, text="Stop", command=self.stop, image=stop_icon)
        self.stop_button.image = stop_icon  # keep a reference!
        self.stop_button.pack(side="left")
