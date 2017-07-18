class LogReader:
        
    def __init__(self, nomefile):
        self.nomefile = nomefile
        self.gps_collector = list()

        
    def read(self):
        with open(self.nomefile, "r") as f:
            for line in f:
                self.parse(line)
        for i in range(len(self.gps_collector)):
            print(self.gps_collector[i])

    def parse(self, line):
        if "pose" in line:
            start_index = line.find('[')
            finish_index = line.find(']')
            start_index += 1
            if start_index > 0 and finish_index > 0:
                p = line[start_index : finish_index]
                p_list = list()
                for i in range (3):
                    p_list.append(float(p.split(',')[i]))
            self.gps_collector.append([p_list, self.parse2(line.split(']')[1]), self.parse3(line.split(']')[2])])

    def parse2(self, line):
        start_index = line.find('[')
        start_index += 1
        if start_index > 0 :
            q = line[start_index :]
            q_list = list()
            for i in range (4):
                q_list.append(float(q.split(',')[i]))
            return q_list

    def parse3(self, line):
        start_index = line.find(':')
        start_index += 2
        finish_index = line.find('}')
        finish_index -= 1
        if start_index > 0 and finish_index > 0:
            zone = line[start_index : finish_index]
            return zone



if __name__ == "__main__":
    log_reader = LogReader("../data/platypus_20170623_045902.txt")
    log_reader.read()






        #pose=Pose(p, q, zone)


