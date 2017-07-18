class LogReader:
        
    def __init__(self, nomefile):
        self.nomefile = nomefile
        
    def read(self):
        with open(self.nomefile, "r") as f:
            for line in f:
                print(line)