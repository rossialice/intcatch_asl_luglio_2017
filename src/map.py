class Map:
    def __init__(self, img, ul, br, zone):
        self.img = img
        self.ul = ul
        self.br = br
        self.zone = zone


    def getMapImage(self):
        return self.img

    def convert(self, p):
        res = list()
        res.append(float(self.ul[0]) - float(p[1]) )
        res.append(float(self.br[1]) - float(p[0]) )
        height, width=self.img.shape[:2]
        h_umt = float(self.ul[0]) - float(self.br[0])
        w_umt = float(self.br[1]) - float(self.ul[1])
        beta_i=(float(res[0])*float(height))/h_umt
        alpha_i = (float(res[1]) * float(width)) / w_umt
        res[0]=width - alpha_i
        res[1]=beta_i
        #print(res)
        return res