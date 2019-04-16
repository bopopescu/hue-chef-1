from PIL import Image
import os
import requests
from requests.auth import HTTPBasicAuth
import numpy as np
import matplotlib.pyplot as plt
from skimage import io
import glob
import json
from argparse import *


#Load Image

def main():

	input_parser = ArgumentParser()
	input_parser.add_argument('-i', '-dir', required=True, nargs=1, metavar=('f1'))
	parser=input_parser.parse_args()
	inputdir=parser.i

	out ={}
	images = glob.glob('data/reduced_images/'+inputdir[0]+'/*.jpg')
	for image in images:
		with open(image,'rb') as file:
			file_name=image.split('/')[3].split('.')[0]

			img=Image.open(file)
			#img = img.resize((255,255),Image.ANTIALIAS)
		
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


	with open('data/palette_data/'+inputdir[0]+'.json','w') as f:
		json.dump(out,f)




if __name__ == '__main__':
    main()	

