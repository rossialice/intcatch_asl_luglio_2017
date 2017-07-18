import numpy as np
import cv2


class MapReader:
    def __init__(self, nomefile):
        self.nomefile = nomefile

    def read(self):

        self.map = cv2.imread(self.nomefile, 1)
        height, width=self.map.shape[:2]
        prop=height/width
        self.minimap=np.zeros((height*(3/4), width*(3/4), 3), np.uint8)
        self.minimap=cv2.resize(self.map, (int(height*(3/4)), int( int(height*(3/4)) * prop )), fx=2, fy=2, interpolation=cv2.INTER_CUBIC)
        cv2.imshow(self.nomefile, self.minimap)
        cv2.waitKey(0)
        cv2.destroyAllWindows()