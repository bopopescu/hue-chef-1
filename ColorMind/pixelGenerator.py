from PIL import Image
import os
import requests
from requests.auth import HTTPBasicAuth
import numpy as np
import matplotlib.pyplot as plt
from skimage import io
import glob


#f_name='P1'

out ={}

images = glob.glob('utils/*.jpg')
#Load Image
for image in images:
	with open(image,'rb') as file:
		file_name=image.split('/')[1].split('.')[0]
		img=Image.open(file)
		img=img.resize((255,255),Image.ANTIALIAS)
		pix_val = list(img.getdata())
		pix_val_2 = [list(elem) for elem in pix_val]
		out[file_name]=len(pix_val_2)
		

# image = Image.open(open('utils/'+f_name+'.jpg','rb'))
# #print(image.size
# image = image.resize((255,255),Image.ANTIALIAS)
# #print(image.size)
# #image.save("P2_scaled.jpg",quality=95)


# #Generate pixel
# pix_val = list(image.getdata()) 	#list of tuples
# pix_val_2 = [list(elem) for elem in pix_val]

# out = {}
# out[f_name]=len(pix_val_2)

print out