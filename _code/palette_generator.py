from PIL import Image
import os
import requests
from requests.auth import HTTPBasicAuth
import numpy as np
import matplotlib.pyplot as plt
from skimage import io
import glob
import json


#Load Image

out ={}
images = glob.glob('utils/P701-805/*.jpg')
for image in images:
	with open(image,'rb') as file:
		file_name=image.split('/')[2].split('.')[0]

		img=Image.open(file)
		img = img.resize((255,255),Image.ANTIALIAS)
		
		pix_val = list(img.getdata()) 	
		pix_val_2 = [list(elem) for elem in pix_val]
		
		#Prepare payload
		s = str(pix_val_2)
		payload = '{"input":' + s + ',"model":"default"}'
		try:
			#print "Generating palette for "+file_name
			response = requests.post('http://colormind.io/api/',payload.encode())
			responseJson = response.json()
			result = responseJson.get('result');
			out[file_name]=result
		except:
			continue


print json.dumps(out)

