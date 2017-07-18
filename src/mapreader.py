import numpy as np
import cv2
from map import Map


class MapReader:
    def __init__(self, nomefile):
        self.nomefile = nomefile

    def read(self):

        self.map_img = cv2.imread(self.nomefile, 1)
        height, width=self.map_img.shape[:2]
        prop=height/width
        '''self.minimap=np.zeros((height*(3/4), width*(3/4), 3), np.uint8)
        self.minimap=cv2.resize(self.map, (int(height*(3/4)), int( int(height*(3/4)) * prop )), fx=2, fy=2, interpolation=cv2.INTER_CUBIC)
        cv2.imshow(self.nomefile, self.minimap)'''
        cfg_file=self.nomefile[0:len(self.nomefile)-3] + "cfg"
        print(cfg_file)

        with open(cfg_file, "r") as f:

            ul_cnt = -1
            br_cnt = -1
            zone_cnt = -1


            for line in f:
                op=0
                #print(line)
                if "[ul]" in line:
                    ul_cnt = 2
                    op += 1
                if ul_cnt == 2  and op == 0:
                    ul_x = line
                    ul_cnt -= 1
                    op += 1
                if ul_cnt == 1  and op == 0:
                    ul_y = line
                    ul_cnt -= 1
                    op += 1
                if "[br]" in line:
                    br_cnt = 2
                    op += 1
                if br_cnt == 2 and op == 0:
                    br_x = line
                    br_cnt -= 1
                    op += 1
                if br_cnt == 1 and op == 0:
                    br_y = line
                    br_cnt -= 1
                    op += 1
                if "[zone]" in line:
                    zone_cnt = 1
                    op += 1
                if zone_cnt == 1 and op == 0:
                    zone = line
                    zone_cnt -= 1
                    op += 1
            print("ul:" + ul_x + " " + ul_y)
            ul = [ul_x, ul_y]
            print("br:" + br_x + " " + br_y)
            br = [br_x, br_y]
            print("zone:" + zone)
            self.map = Map(self.map_img, ul, br, zone)




    def getMap(self):
        return self.map

if __name__ == "__main__":
    map_reader = MapReader("../data/map.png")
    map_reader.read()

'''
cv2.waitKey(0)
cv2.destroyAllWindows()
 '''